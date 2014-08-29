package de.is24.infrastructure.gridfs.http.jaxb.primary;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@XmlRootElement(name="package")
//@XmlType(propOrder = { "name", "arch", "version", "checksum", "summary", "description", "packager", "url", "time", "size", "location" })
@XmlType(propOrder = { "name", "arch", "version", "checksum", "summary", "description", "packager", "url", "time", "size", "location", "packageFormat" })

public class XmlYumPackage {
  private String name;
  private String arch;
  private String summary;
  private XmlYumPackageVersion version;
  private XmlYumPackageChecksum checksum;
  private String description;
  private String packager;
  private String url;
  private String type = "rpm";
  private XmlYumPackageTime time;
  private XmlYumPackageSize size;
  private XmlYumPackageLocation location;
  private XmlYumPackageFormat packageFormat;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getArch() {
    return arch;
  }

  public void setArch(final String arch) {
    this.arch = arch;
  }

  public XmlYumPackageChecksum getChecksum() {
    return checksum;
  }

  public void setChecksum(final XmlYumPackageChecksum checksum) {
    this.checksum = checksum;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(final String summary) {
    this.summary = summary;
  }

  public XmlYumPackageVersion getVersion() {
    return version;
  }

  public void setVersion(final XmlYumPackageVersion version) {
    this.version = version;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getPackager() {
    return packager;
  }

  public void setPackager(final String packager) {
    this.packager = packager;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public XmlYumPackageTime getTime() {
    return time;
  }

  public void setTime(final XmlYumPackageTime time) {
    this.time = time;
  }

  public XmlYumPackageSize getSize() {
    return size;
  }

  public void setSize(final XmlYumPackageSize size) {
    this.size = size;
  }

  public XmlYumPackageLocation getLocation() {
    return location;
  }

  public void setLocation(final XmlYumPackageLocation location) {
    this.location = location;
  }
  @XmlElement(name="format")
  public XmlYumPackageFormat getPackageFormat() {
    return packageFormat;
  }

  public void setPackageFormat(final XmlYumPackageFormat packageFormat) {
    this.packageFormat = packageFormat;
  }



  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if ((o == null) || (this.getClass() != o.getClass())) {
      return false;
    }

    XmlYumPackage other = (XmlYumPackage) o;
    return new EqualsBuilder().append(name, other.name)
      .append(arch, other.arch)
      .append(checksum, other.checksum)
      .append(summary, other.summary)
      .append(version, other.version)
      .append(description, other.description)
      .append(packager, other.packager)
      .append(url, other.url)
      .append(time, other.time)
      .append(size, other.size)
      .append(location, other.location)
      .append(packageFormat, other.packageFormat)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(name)
      .append(arch)
      .append(checksum)
      .append(summary)
      .append(version)
      .append(description)
      .append(packager)
      .append(url)
      .append(time)
      .append(size)
      .append(location)
      .append(packageFormat)
      .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(name)
      .append(arch)
      .append(checksum)
      .append(summary)
      .append(version)
      .append(description)
      .append(packager)
      .append(url)
      .append(time)
      .append(size)
      .append(location)
      .append(packageFormat)
      .toString();
  }
  @XmlAttribute(name="type")
public String getType() {
    return type;
}

public void setType(String type) {
    this.type = type;
}
}
