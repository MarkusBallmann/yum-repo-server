package de.is24.infrastructure.gridfs.http.jaxb.primary;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder = {"epoch", "ver", "rel"})
public class XmlYumPackageVersion {

    private int epoch;
    private String ver;
    private String rel;

  @XmlAttribute(name="epoch")
  public int getEpoch() {
    return epoch;
  }

  public void setEpoch(final int epoch) {
    this.epoch = epoch;
  }
  @XmlAttribute(name="ver")
  public String getVer() {
    return ver;
  }

  public void setVer(final String ver) {
    this.ver = ver;
  }

  @XmlAttribute(name="rel")
  public String getRel() {
    return rel;
  }

  public void setRel(final String rel) {
    this.rel = rel;

  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if ((o == null) || (this.getClass() != o.getClass())) {
      return false;
    }

    XmlYumPackageVersion other = (XmlYumPackageVersion) o;
    return new EqualsBuilder().append(epoch, other.epoch).append(ver, other.ver).append(rel, other.rel).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(epoch).append(ver).append(rel).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(epoch).append(ver).append(rel).toString();
  }
}
