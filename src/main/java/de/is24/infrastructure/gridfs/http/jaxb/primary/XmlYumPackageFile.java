package de.is24.infrastructure.gridfs.http.jaxb.primary;

import javax.xml.bind.annotation.XmlValue;

public class XmlYumPackageFile
{
    private String filename;

    public XmlYumPackageFile(String filename)
    {
        super();
        this.filename = filename;
    }


    @XmlValue
    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }


}
