package com.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

public class FastestPingTimeReadPreference extends ReadPreference {
  private static final Logger LOGGER = LoggerFactory.getLogger(FastestPingTimeReadPreference.class);

  @Override
  public boolean isSlaveOk() {
    return true;
  }

  @Override
  public DBObject toDBObject() {
    return new BasicDBObject("mode", getName());
  }

  @Override
  public String getName() {
    return "nearestNode";
  }

  @Override
  public List<ServerDescription> choose(ClusterDescription clusterDescription) {
    final Set<ServerDescription> nodeSet = clusterDescription.getAll();

    if (nodeSet.isEmpty()) {
      return null;
    }

    final ServerDescription nearestNode = selectNearestQueryableNode(nodeSet);

    if (LOGGER.isTraceEnabled()) {
      StringBuilder buffer = new StringBuilder();
      for (ServerDescription node : nodeSet) {
        if (!node.equals(nearestNode)) {
          buffer.append("[");
          buffer.append(node.getAddress().getHost());
          buffer.append("/");
          buffer.append(node.getAveragePingTimeNanos());
          buffer.append("] ");
        }
      }

      String choosenNode = nearestNode.getAddress().getHost() + "/" + nearestNode.getAveragePingTimeNanos();
      LOGGER.trace("take {} as mongodb host. other {}", choosenNode, buffer.toString());
    } else {
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("take {} as mongodb host",
          (nearestNode == null) ? "--" : nearestNode.getAddress().getHost());
      }
    }
    return asList(nearestNode);
  }


  private static ServerDescription selectNearestQueryableNode(final Set<ServerDescription> nodeSet) {
    ServerDescription nearest = null;
    for (ServerDescription node : nodeSet) {
      if (isQueryable(node)) {
        nearest = calculateNearest(nearest, node);
      }
    }
    return nearest;
  }

  private static ServerDescription calculateNearest(final ServerDescription nodeA, final ServerDescription nodeB) {
    if (nodeA == null) {
      return nodeB;
    }
    if (Float.compare(nodeA.getAveragePingTimeNanos(), nodeB.getAveragePingTimeNanos()) > 0) {
      return nodeB;
    }
    return nodeA;
  }

  private static boolean isQueryable(final ServerDescription node) {
    return node.isOk() && (node.isSecondary() || node.isPrimary());
  }
}
