package de.is24.infrastructure.gridfs.http.jaxb.primary;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="metadata" )
public class XmlYumMetadata
{
    private final String xmllns = "http://linux.duke.edu/metadata/common";
    private final String xmlnsrpm = "http://linux.duke.edu/metadata/rpm";
    private int packages;

    public XmlYumMetadata()
    {
        super();
    }
    public XmlYumMetadata(int packages)
    {
        super();
        this.packages = packages;
    }

    @XmlAttribute(name = "xmllns")
    public String getXmllns()
    {
        return xmllns;
    }

    @XmlAttribute(name = "xmllns:rpm")
    public String getXmlnsrpm()
    {
        return xmlnsrpm;
    }

    @XmlAttribute(name = "packages")
    public int getPackages()
    {
        return packages;
    }

    public void setPackages(int packages)
    {
        this.packages = packages;
    }
}
