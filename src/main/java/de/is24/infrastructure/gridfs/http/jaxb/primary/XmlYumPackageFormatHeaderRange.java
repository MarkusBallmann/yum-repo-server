package de.is24.infrastructure.gridfs.http.jaxb.primary;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"headerStart", "headerEnd"})
public class XmlYumPackageFormatHeaderRange {

    private int headerStart;
    private int headerEnd;

    public XmlYumPackageFormatHeaderRange()
    {
        super();
    }

    public XmlYumPackageFormatHeaderRange(int headerStart, int headerEnd)
    {
        super();
        this.headerStart = headerStart;
        this.headerEnd = headerEnd;
    }

    @XmlAttribute(name="start")
    public int getHeaderStart()
    {
        return headerStart;
    }
    public void setHeaderStart(int headerStart)
    {
        this.headerStart = headerStart;
    }

    @XmlAttribute(name="end")
    public int getHeaderEnd()
    {
        return headerEnd;
    }
    public void setHeaderEnd(int headerEnd)
    {
        this.headerEnd = headerEnd;
    }

}
