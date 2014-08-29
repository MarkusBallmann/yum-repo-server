package de.is24.infrastructure.gridfs.http.jaxb.primary;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class XmlYumPackageDir
{
    private String foldername;

    public XmlYumPackageDir(String foldername)
    {
        super();
        this.foldername = foldername;
    }

    final private String type="dir";

    @XmlValue
    public String getFoldername()
    {
        return foldername;
    }

    public void setFoldername(String foldername)
    {
        this.foldername = foldername;
    }

    @XmlAttribute(name = "type")
    public String getType()
    {
        return type;
    }



}
