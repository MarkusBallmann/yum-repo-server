package de.is24.infrastructure.gridfs.http.jaxb.primary;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
@XmlType(propOrder = {"name","flags","epoch", "ver", "rel"})
public class XmlYumPackageFormatEntry
{
    private String name;
    private String flags;
    private int epoch;
    private String ver;
    private String rel;

    @XmlAttribute(name = "name")
    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    @XmlAttribute(name = "flags")
    public String getFlags()
    {
        return flags;
    }

    public void setFlags(final String flags)
    {
        this.flags = flags;
    }

    @XmlAttribute(name = "epoch")
    public int getEpoch()
    {
        return epoch;
    }

    public void setEpoch(int epoch)
    {
        this.epoch = epoch;
    }

    @XmlAttribute(name = "ver")
    public String getVer()
    {
        return ver;
    }

    public void setVer(String ver)
    {
        this.ver = ver;
    }

    @XmlAttribute(name = "rel")
    public String getRel()
    {
        return rel;
    }

    public void setRel(String rel)
    {
        this.rel = rel;
    }

    /*public XmlYumPackageVersion getVersion() {
      return version;
    }

    public void setVersion(final XmlYumPackageVersion version) {
      this.version = version;
    }*/

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass()))
        {
            return false;
        }

        final XmlYumPackageFormatEntry other = (XmlYumPackageFormatEntry) o;
        return new EqualsBuilder().append(name, other.name)
            .append(flags, other.flags)
            .append(epoch, other.epoch)
            .append(ver, other.ver)
            .append(rel, other.rel)
            .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37).append(name).append(flags).append(epoch).append(ver)
            .append(rel).toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(name)
            .append(flags)
            .append(epoch)
            .append(ver)
            .append(rel)
            .toString();
    }
}
