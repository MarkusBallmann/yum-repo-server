package de.is24.infrastructure.gridfs.http.mongo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;

import de.is24.infrastructure.gridfs.http.gridfs.GridFsFileStorageService;
import de.is24.infrastructure.gridfs.http.gridfs.StorageService;
import de.is24.infrastructure.gridfs.http.metadata.MetadataService;
import de.is24.infrastructure.gridfs.http.metadata.RepoEntriesRepository;
import de.is24.infrastructure.gridfs.http.metadata.YumEntriesHashCalculator;
import de.is24.infrastructure.gridfs.http.metadata.YumEntriesRepository;
import de.is24.infrastructure.gridfs.http.metadata.YumEntriesRepositoryImpl;
import de.is24.infrastructure.gridfs.http.metadata.generation.PrimaryXmlGenerator;
import de.is24.infrastructure.gridfs.http.metadata.generation.RepoMdGenerator;
import de.is24.infrastructure.gridfs.http.repos.RepoCleaner;
import de.is24.infrastructure.gridfs.http.repos.RepoService;
import de.is24.infrastructure.gridfs.http.security.PGPSigner;
import de.is24.infrastructure.gridfs.http.storage.FileStorageService;
import de.is24.infrastructure.gridfs.http.storage.StorageTestUtils;
import de.is24.util.monitoring.InApplicationMonitor;


/**
 *  <strong>Attention</strong>
 *  If used as {@link org.junit.ClassRule} all test of the class use the same mongoDB instance!
 */
public class IntegrationTestContext extends MongoTestContext {
  public static final String RPM_DB = "rpm_db";

  private MongoTemplate mongoTemplate;
  private FileStorageService fileStorageService;
  private GridFS gridFs;
  private GridFsTemplate gridFsTemplate;
  private StorageService storageService;
  private YumEntriesRepository yumEntriesRepository;
  private RepoEntriesRepository repoEntriesRepository;
  private RepoService repoService;
  private RepoCleaner repoCleaner;

  private RepoMdGenerator repoMdGenerator;
  private PrimaryXmlGenerator primaryXmlGenerator;

  private MetadataService metadataService;
  private YumEntriesHashCalculator entriesHashCalculator;
  private StorageTestUtils storageTestUtils;

  public GridFS gridFs() {
    if (gridFs == null) {
      gridFs = new GridFS(getMongo().getDB(RPM_DB));
    }
    return gridFs;
  }

  public FileStorageService fileStorageService() {
    if (fileStorageService == null) {
      fileStorageService = new GridFsFileStorageService(gridFs(), gridFsTemplate(), mongoTemplate());
    }
    return fileStorageService;
  }

  public StorageService gridFsService() {
    if (storageService == null) {
      storageService = new StorageService(fileStorageService(), yumEntriesRepository(),
        repoService());
    }
    return storageService;
  }

  public YumEntriesRepository yumEntriesRepository() {
    if (yumEntriesRepository == null) {
      yumEntriesRepository = new MongoRepositoryFactory(mongoTemplate()).getRepository(YumEntriesRepository.class, new YumEntriesRepositoryImpl(mongoTemplate()));
    }
    return yumEntriesRepository;
  }

  public MongoTemplate mongoTemplate() {
    if (mongoTemplate == null) {
      mongoTemplate = mongoTemplate(getMongo());
    }
    return mongoTemplate;
  }

  public GridFsTemplate gridFsTemplate() {
    if (gridFsTemplate == null) {
      gridFsTemplate = gridFsTemplate(getMongo());
    }
    return gridFsTemplate;
  }

  public RepoEntriesRepository repoEntriesRepository() {
    if (repoEntriesRepository == null) {
      repoEntriesRepository = new MongoRepositoryFactory(mongoTemplate()).getRepository(RepoEntriesRepository.class);
    }
    return repoEntriesRepository;
  }

  public RepoService repoService() {
    if (repoService == null) {
      repoService = new RepoService(repoEntriesRepository());
    }
    return repoService;
  }

  public PGPSigner pgpSigner() {
    return new PGPSigner(new ClassPathResource("/gpg/secring.gpg"), "yum-repo-server");
  }

  public RepoCleaner repoCleaner() {
    if (repoCleaner == null) {
      repoCleaner = new RepoCleaner(mongoTemplate(), yumEntriesRepository(), fileStorageService(), repoService());
    }

    return repoCleaner;
  }

  public RepoMdGenerator repoMdGenerator() {
    if (repoMdGenerator == null) {
      repoMdGenerator = new RepoMdGenerator(fileStorageService(), pgpSigner());
    }
    return repoMdGenerator;
  }

  public PrimaryXmlGenerator primaryXmlGenerator(){
      if (primaryXmlGenerator == null){
          primaryXmlGenerator = new PrimaryXmlGenerator(fileStorageService());
      }
      return primaryXmlGenerator;
  }

  public YumEntriesHashCalculator entriesHashCalculator() {
    if (entriesHashCalculator == null) {
      entriesHashCalculator = new YumEntriesHashCalculator(mongoTemplate());
    }
    return entriesHashCalculator;
  }

  public MetadataService metadataService() {
    if (metadataService == null) {
      entriesHashCalculator = new YumEntriesHashCalculator(mongoTemplate());
      metadataService = new MetadataService(gridFsService(), fileStorageService(), yumEntriesRepository(), repoMdGenerator(),
        repoService(), repoCleaner(), entriesHashCalculator(), InApplicationMonitor.getInstance(), primaryXmlGenerator());
    }
    return metadataService;
  }

  public static GridFsTemplate gridFsTemplate(Mongo mongo) {
    SimpleMongoDbFactory dbFactory = new SimpleMongoDbFactory(mongo, RPM_DB);
    return new GridFsTemplate(dbFactory, new MappingMongoConverter(dbFactory, new MongoMappingContext()));
  }

  public static MongoTemplate mongoTemplate(Mongo mongo) {
    SimpleMongoDbFactory dbFactory = new SimpleMongoDbFactory(mongo, RPM_DB);
    return new MongoTemplate(dbFactory);
  }

  public StorageTestUtils storageTestUtils() {
    if (storageTestUtils == null) {
      storageTestUtils = new StorageTestUtils(gridFsService(), fileStorageService());
    }
    return storageTestUtils;
  }
}
