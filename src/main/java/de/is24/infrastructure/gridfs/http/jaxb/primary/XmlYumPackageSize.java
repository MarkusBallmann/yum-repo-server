package de.is24.infrastructure.gridfs.http.jaxb.primary;

import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@XmlType(propOrder = {"packaged","installed","archive"})
public class XmlYumPackageSize {

  private Integer packaged;
  private Integer installed;
  private Integer archive;

    @XmlAttribute(name="packaged")
  public Integer getPackaged() {
    return packaged;
  }

  public String getPackagedAsString() {
    return byteCountToDisplaySize(packaged);
  }

  public void setPackaged(final Integer packaged) {
    this.packaged = packaged;
  }
  @XmlAttribute(name="installed")
  public Integer getInstalled() {
    return installed;
  }

  public void setInstalled(final Integer installed) {
    this.installed = installed;
  }
  @XmlAttribute(name="archive")
  public Integer getArchive() {
    return archive;
  }

  public void setArchive(final Integer archive) {
    this.archive = archive;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if ((o == null) || (getClass() != o.getClass())) {
      return false;
    }

    final XmlYumPackageSize other = (XmlYumPackageSize) o;
    return new EqualsBuilder().append(packaged, other.packaged)
      .append(installed, other.installed)
      .append(archive, other.archive)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(packaged).append(installed).append(archive).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(packaged)
      .append(installed)
      .append(archive)
      .toString();
  }
}
