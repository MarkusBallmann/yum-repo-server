package de.is24.infrastructure.gridfs.http.metadata.generation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumMetadata;
import de.is24.infrastructure.gridfs.http.jaxb.primary.XmlYumPackage;
import de.is24.infrastructure.gridfs.http.storage.FileStorageService;

@Service
public class PrimaryXmlGenerator {
    public static final int INITIAL_BUFFER_SIZE = 4 * 1024;
    private final FileStorageService fileStorageService;
    private final String name ="primary";

    /* for cglib */
    protected PrimaryXmlGenerator() {
      fileStorageService = null;
    }

    public String getName()
    {
        return name;
    }

    @Autowired
    public PrimaryXmlGenerator(FileStorageService fileStorageService) {
      this.fileStorageService = fileStorageService;
    }

    public void generatePrimaryXml(File xmlFile, String reponame, XmlYumMetadata metadata, List<XmlYumPackage> xmlPkgList) {
      ByteArrayOutputStream tempOutputStream = new ByteArrayOutputStream(INITIAL_BUFFER_SIZE);
      JAXBContext context;
      try {
          context = JAXBContext.newInstance(XmlYumMetadata.class);
          Marshaller xmlm = context.createMarshaller();
          xmlm.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
          xmlm.marshal(metadata, tempOutputStream);

          context = JAXBContext.newInstance(XmlYumPackage.class);
          Marshaller yumpkgmarshaller = context.createMarshaller();
          yumpkgmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
          yumpkgmarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
          for(XmlYumPackage e:xmlPkgList){
              yumpkgmarshaller.marshal(e, tempOutputStream);
          }
          OutputStream outputStream;
          outputStream = new FileOutputStream (xmlFile);
          outputStream.write(tempOutputStream.toByteArray());
          outputStream.close();
      } catch (JAXBException e) {
          throw new IllegalStateException("Unable to marshall RepoMd object.", e);
      }
      catch (FileNotFoundException e)
      {
          throw new IllegalStateException("File not found with path"+ xmlFile.getAbsolutePath(), e);
      }
      catch (IOException e)
      {
          throw new IllegalStateException("Exception wile writing to file"+ xmlFile.getAbsolutePath(), e);
      }
    }
}
