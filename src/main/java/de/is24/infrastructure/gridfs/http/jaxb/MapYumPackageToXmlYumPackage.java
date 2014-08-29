package de.is24.infrastructure.gridfs.http.jaxb;

import static de.is24.infrastructure.gridfs.http.domain.yum.YumPackageFileType.DIR;

import java.util.ArrayList;
import java.util.List;

import de.is24.infrastructure.gridfs.http.domain.yum.YumPackage;
import de.is24.infrastructure.gridfs.http.domain.yum.YumPackageChecksum;
import de.is24.infrastructure.gridfs.http.domain.yum.YumPackageDir;
import de.is24.infrastructure.gridfs.http.domain.yum.YumPackageFile;
import de.is24.infrastructure.gridfs.http.domain.yum.YumPackageFormat;
import de.is24.infrastructure.gridfs.http.domain.yum.YumPackageFormatEntry;
import de.is24.infrastructure.gridfs.http.domain.yum.YumPackageLocation;
import de.is24.infrastructure.gridfs.http.domain.yum.YumPackageRequirement;
import de.is24.infrastructure.gridfs.http.domain.yum.YumPackageSize;
import de.is24.infrastructure.gridfs.http.domain.yum.YumPackageTime;
import de.is24.infrastructure.gridfs.http.domain.yum.YumPackageVersion;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackage;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackageChecksum;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackageDir;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackageFile;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackageFormat;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackageFormatEntry;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackageFormatHeaderRange;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackageLocation;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackageRequirement;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackageSize;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackageTime;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackageVersion;

public class MapYumPackageToXmlYumPackage
{

    public List<XmlYumPackage> mapYumPackageList(final List<YumPackage> fromEntityList)
    {

        List<XmlYumPackage> toXmlDTOList = new ArrayList<XmlYumPackage>();
        for (YumPackage pkg : fromEntityList)
        {
            toXmlDTOList.add(mapXmlYumPackage(pkg, new XmlYumPackage()));
        }
        return toXmlDTOList;
    }

    private XmlYumPackage mapXmlYumPackage(final YumPackage fromEntity, final XmlYumPackage toXmlDTO)
    {
        toXmlDTO.setName(fromEntity.getName());
        toXmlDTO.setArch(fromEntity.getArch());
        toXmlDTO.setSummary(fromEntity.getSummary());
        toXmlDTO.setVersion(mapPackageVersion(fromEntity.getVersion()));
        toXmlDTO.setChecksum(mapPackageChecksum(fromEntity.getChecksum()));
        toXmlDTO.setDescription(fromEntity.getDescription());
        toXmlDTO.setPackager(fromEntity.getPackager());
        toXmlDTO.setUrl(fromEntity.getUrl());
        toXmlDTO.setTime(mapPackageTime(fromEntity.getTime()));
        toXmlDTO.setSize(mapPackageSize(fromEntity.getSize()));
        toXmlDTO.setLocation(mapPackageLocation(fromEntity.getLocation()));
        toXmlDTO.setPackageFormat(mapPackageFormat(fromEntity.getPackageFormat(), fromEntity.getPackageDirs()));

        return toXmlDTO;
    }

    private static XmlYumPackageFormat mapPackageFormat(final YumPackageFormat fromEntity, final YumPackageDir[] dirs)
    {
        XmlYumPackageFormat toXmlDTO = new XmlYumPackageFormat();
        toXmlDTO.setLicense(fromEntity.getLicense());
        toXmlDTO.setVendor(fromEntity.getVendor());
        toXmlDTO.setGroup(fromEntity.getGroup());
        toXmlDTO.setBuildHost(fromEntity.getBuildHost());
        toXmlDTO.setSourceRpm(fromEntity.getSourceRpm());
        toXmlDTO.setHeaderRange(new XmlYumPackageFormatHeaderRange(fromEntity.getHeaderStart(), fromEntity
            .getHeaderStart()));
        toXmlDTO.setProvides(mapPackageFormatEntryList(fromEntity.getProvides()));
        toXmlDTO.setRequires(mapPackageRequirementList(fromEntity.getRequires()));
        toXmlDTO.setObsoletes(mapPackageFormatEntryList(fromEntity.getObsoletes()));
        toXmlDTO.setConflicts(mapPackageFormatEntryList(fromEntity.getConflicts()));

        List<XmlYumPackageFile> xmlpkgfiles = new ArrayList<XmlYumPackageFile>();
        List<XmlYumPackageDir> xmlpkgdirs = new ArrayList<XmlYumPackageDir>();
        for (int i = 0; i < dirs.length; i++)
        {
            List<YumPackageFile> pkgfile = dirs[i].getFiles();
            for (YumPackageFile f : pkgfile)
            {
                if (f.getType() == DIR)
                {
                    xmlpkgdirs.add(new XmlYumPackageDir(f.getName()));
                }
                else
                {
                    xmlpkgfiles.add(new XmlYumPackageFile(f.getName()));
                }
            }
        }
        toXmlDTO.setFiles(xmlpkgfiles);
        toXmlDTO.setDirs(xmlpkgdirs);
        return toXmlDTO;
    }

    private static List<XmlYumPackageRequirement> mapPackageRequirementList(final List<YumPackageRequirement> fromEntity)
    {
        List<XmlYumPackageRequirement> toXmlDTO = new ArrayList<XmlYumPackageRequirement>();

        for (YumPackageRequirement fromEntityRequirement : fromEntity)
        {
            toXmlDTO.add(mapPackageRequirement(fromEntityRequirement));
        }
        return toXmlDTO;
    }

    private static XmlYumPackageRequirement mapPackageRequirement(final YumPackageRequirement fromEntity)
    {
        XmlYumPackageRequirement toXmlDTO = new XmlYumPackageRequirement();

        if (fromEntity.isPre() != null && fromEntity.isPre())
        {
            toXmlDTO.setPre("1");
        }
        toXmlDTO.setName(fromEntity.getName());
        toXmlDTO.setFlags(fromEntity.getFlags());
        if (fromEntity.getVersion() != null)
        {
            toXmlDTO.setEpoch(fromEntity.getVersion().getEpoch());
            toXmlDTO.setVer(fromEntity.getVersion().getVer());
            toXmlDTO.setRel(fromEntity.getVersion().getRel());
        }
        return toXmlDTO;
    }

    private static List<XmlYumPackageFormatEntry> mapPackageFormatEntryList(final List<YumPackageFormatEntry> fromEntity)
    {
        List<XmlYumPackageFormatEntry> toXmlDTO = new ArrayList<XmlYumPackageFormatEntry>();
        if (!(fromEntity == null) && !fromEntity.isEmpty())
        {
            for (YumPackageFormatEntry fromEntityEntry : fromEntity)
            {
                toXmlDTO.add(mapPackageFormatEntry(fromEntityEntry));
            }
        }
        return toXmlDTO;
    }

    private static XmlYumPackageFormatEntry mapPackageFormatEntry(final YumPackageFormatEntry fromEntity)
    {
        XmlYumPackageFormatEntry toXmlDTO = new XmlYumPackageFormatEntry();
        toXmlDTO.setName(fromEntity.getName());
        toXmlDTO.setFlags(fromEntity.getFlags());
        if (fromEntity.getVersion() != null)
        {
            toXmlDTO.setEpoch(fromEntity.getVersion().getEpoch());
            toXmlDTO.setVer(fromEntity.getVersion().getVer());
            toXmlDTO.setRel(fromEntity.getVersion().getRel());
        }
        return toXmlDTO;
    }

    private static XmlYumPackageLocation mapPackageLocation(final YumPackageLocation fromEntity)
    {
        XmlYumPackageLocation toXmlDTO = new XmlYumPackageLocation();
        toXmlDTO.setHref(fromEntity.getHref());
        return toXmlDTO;
    }

    private static XmlYumPackageSize mapPackageSize(final YumPackageSize fromEntity)
    {
        XmlYumPackageSize toXmlDTO = new XmlYumPackageSize();
        toXmlDTO.setArchive(fromEntity.getArchive());
        toXmlDTO.setInstalled(fromEntity.getInstalled());
        toXmlDTO.setPackaged(fromEntity.getPackaged());
        return toXmlDTO;
    }

    private static XmlYumPackageVersion mapPackageVersion(final YumPackageVersion fromEntity)
    {
        XmlYumPackageVersion toXmlDTO = new XmlYumPackageVersion();
        toXmlDTO.setEpoch(fromEntity.getEpoch());
        toXmlDTO.setRel(fromEntity.getRel());
        toXmlDTO.setVer(fromEntity.getVer());
        return toXmlDTO;
    }

    private static XmlYumPackageChecksum mapPackageChecksum(final YumPackageChecksum fromEntity)
    {
        return new XmlYumPackageChecksum(fromEntity.getType(), fromEntity.getChecksum());
    }

    private static XmlYumPackageTime mapPackageTime(final YumPackageTime fromEntity)
    {
        XmlYumPackageTime toXmlDTO = new XmlYumPackageTime();
        toXmlDTO.setBuild(fromEntity.getBuild());
        toXmlDTO.setFile(fromEntity.getFile());
        return toXmlDTO;
    }
}
