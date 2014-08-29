package de.is24.infrastructure.gridfs.http.jaxb.primary;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlYumPackageRequirement extends XmlYumPackageFormatEntry {
  private String pre;

  @XmlAttribute(name="pre")
  public String getPre() {
    return pre;
  }

  public void setPre(String pre) {
    this.pre = pre;
  }

}
