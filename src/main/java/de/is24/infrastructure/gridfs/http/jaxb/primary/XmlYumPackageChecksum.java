package de.is24.infrastructure.gridfs.http.jaxb.primary;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@XmlType(propOrder = {"type","pkgId"})
public class XmlYumPackageChecksum {

  private String checksum;
  private String type;
  private String pkgId = "YES";

  public XmlYumPackageChecksum() {
  }

  public XmlYumPackageChecksum(String type, String checksum) {
    this.type = type;
    this.checksum = checksum;
  }
  @XmlValue
  public String getChecksum() {
    return checksum;
  }

  public void setChecksum(final String checksum) {
    this.checksum = checksum;
  }
  @XmlAttribute(name="type")
  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }
  @XmlAttribute(name="pkgId")
  public String getPkgId() {
    return pkgId;
  }

  public void setPkgId(final String pkgId) {
    this.pkgId = pkgId;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }

    final XmlYumPackageChecksum other = (XmlYumPackageChecksum) o;
    return new EqualsBuilder().append(checksum, other.checksum)
      .append(type, other.type)
      .append(pkgId, other.pkgId)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(checksum).append(type).append(pkgId).toHashCode();
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(checksum)
      .append(type)
      .append(pkgId)
      .toString();
  }
}
