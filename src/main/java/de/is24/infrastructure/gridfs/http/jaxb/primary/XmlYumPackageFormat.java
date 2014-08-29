package de.is24.infrastructure.gridfs.http.jaxb.primary;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@XmlType(propOrder = {"license", "vendor", "group", "buildHost", "sourceRpm", "headerRange", "provides", "requires",
    "obsoletes", "conflicts", "files","dirs"}, namespace="")

public class XmlYumPackageFormat
{
    private String license;
    private String vendor;
    private String group;
    private String buildHost;
    private String sourceRpm;
    private XmlYumPackageFormatHeaderRange headerRange;
    private List<XmlYumPackageFormatEntry> provides;
    private List<XmlYumPackageRequirement> requires;
    private List<XmlYumPackageFormatEntry> obsoletes;
    private List<XmlYumPackageFormatEntry> conflicts;
    private List<XmlYumPackageFile> files;
    private List<XmlYumPackageDir> dirs;

    @XmlElement(name="rpm:license")
    public String getLicense()
    {
        return license;
    }

    public void setLicense(final String license)
    {
        this.license = license;
    }
    @XmlElement(name="rpm:vendor")
    public String getVendor()
    {
        return vendor;
    }

    public void setVendor(final String vendor)
    {
        this.vendor = vendor;
    }
    @XmlElement(name="rpm:group")
    public String getGroup()
    {
        return group;
    }

    public void setGroup(final String group)
    {
        this.group = group;
    }

    @XmlElement(name = "rpm:buildhost")
    public String getBuildHost()
    {
        return buildHost;
    }

    public void setBuildHost(final String buildHost)
    {
        this.buildHost = buildHost;
    }

    @XmlElement(name = "rpm:sourcerpm")
    public String getSourceRpm()
    {
        return sourceRpm;
    }

    public void setSourceRpm(final String sourceRpm)
    {
        this.sourceRpm = sourceRpm;
    }

    @XmlElementWrapper(name = "rpm:provides")
    @XmlElement(name = "rpm:entry")
    public List<XmlYumPackageFormatEntry> getProvides()
    {
        return provides;
    }

    public void setProvides(final List<XmlYumPackageFormatEntry> provides)
    {
        this.provides = provides;
    }

    @XmlElementWrapper(name = "rpm:requires")
    @XmlElement(name = "rpm:entry")
    public List<XmlYumPackageRequirement> getRequires()
    {
        return requires;
    }

    public void setRequires(final List<XmlYumPackageRequirement> requires)
    {
        this.requires = requires;
    }

    @XmlElementWrapper(name = "rpm:obsoletes")
    @XmlElement(name = "rpm:entry")
    public List<XmlYumPackageFormatEntry> getObsoletes()
    {
        return obsoletes;
    }

    public void setObsoletes(final List<XmlYumPackageFormatEntry> obsoletes)
    {
        this.obsoletes = obsoletes;
    }

    @XmlElementWrapper(name = "rpm:conflicts")
    @XmlElement(name = "rpm:entry")
    public List<XmlYumPackageFormatEntry> getConflicts()
    {
        return conflicts;
    }

    public void setConflicts(final List<XmlYumPackageFormatEntry> conflicts)
    {
        this.conflicts = conflicts;
    }

    @XmlElement(name = "rpm:header-range")
    public XmlYumPackageFormatHeaderRange getHeaderRange()
    {
        return headerRange;
    }

    public void setHeaderRange(XmlYumPackageFormatHeaderRange headerRange)
    {
        this.headerRange = headerRange;
    }

    @XmlElement(name = "file")
    public List<XmlYumPackageFile> getFiles()
    {
        return files;
    }

    public void setFiles(List<XmlYumPackageFile> files)
    {
        this.files = files;
    }
    @XmlElement(name = "file")
    public List<XmlYumPackageDir> getDirs()
    {
        return dirs;
    }

    public void setDirs(List<XmlYumPackageDir> dirs)
    {
        this.dirs = dirs;
    }

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

        final XmlYumPackageFormat other = (XmlYumPackageFormat) o;
        return new EqualsBuilder().append(license, other.license)
            .append(vendor, other.vendor)
            .append(group, other.group)
            .append(buildHost, other.buildHost)
            .append(sourceRpm, other.sourceRpm)
            .append(requires, other.requires)
            .append(provides, other.provides)
            .append(obsoletes, other.obsoletes)
            .append(conflicts, other.conflicts)
            .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37).append(license)
            .append(vendor)
            .append(group)
            .append(buildHost)
            .append(sourceRpm)
            .append(requires)
            .append(provides)
            .append(obsoletes)
            .append(conflicts)
            .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(license)
            .append(vendor)
            .append(group)
            .append(buildHost)
            .append(sourceRpm)
            .append(requires)
            .append(provides)
            .append(obsoletes)
            .append(conflicts)
            .toString();
    }

}
