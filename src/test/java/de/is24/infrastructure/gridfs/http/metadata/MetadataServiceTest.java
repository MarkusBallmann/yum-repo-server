package de.is24.infrastructure.gridfs.http.metadata;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.is24.infrastructure.gridfs.http.domain.RepoEntry;
import de.is24.infrastructure.gridfs.http.domain.RepoType;
import de.is24.infrastructure.gridfs.http.gridfs.StorageService;
import de.is24.infrastructure.gridfs.http.jaxb.Data;
import de.is24.infrastructure.gridfs.http.metadata.generation.PrimaryXmlGenerator;
import de.is24.infrastructure.gridfs.http.metadata.generation.RepoMdGenerator;
import de.is24.infrastructure.gridfs.http.repos.RepoCleaner;
import de.is24.infrastructure.gridfs.http.repos.RepoService;
import de.is24.infrastructure.gridfs.http.storage.FileStorageService;
import de.is24.util.monitoring.InApplicationMonitor;


@RunWith(MockitoJUnitRunner.class)
public class MetadataServiceTest {
  private static final String REGEX_REPODATA_SQLITE_FILES = "/repodata/.*sqlite.bz2";
  private static final int OUTDATED_META_DATA_SURVIVAL_TIME = 5;
  public static final String ENTRIES_HASH = "entriesHash";

  @InjectMocks
  private MetadataService service;
  private String reponame;
  @Mock
  private StorageService storageService;
  @Mock
  private RepoCleaner repoCleaner;
  @Mock
  private YumEntriesRepository yumEntriesRepository;
  @Mock
  private YumEntriesHashCalculator yumEntriesHashCalculator;
  @Mock
  private RepoService repoService;
  @Mock
  private RepoMdGenerator repoMdGenerator;
  @Mock
  private PrimaryXmlGenerator primaryXmlGenerator;
  @Mock
  private InApplicationMonitor inApplicationMonitor;
  @Mock
  private FileStorageService fileStorageService;

  private RepoEntry repoEntry;

  @Before
  public void setup() throws Exception {
    when(storageService.storeRepodataDbBz2(anyString(), any(File.class), anyString())).thenReturn(new Data());
    when(storageService.storeRepodataXmlGz(anyString(), any(File.class), anyString())).thenReturn(new Data());

    this.reponame = "any-reponame";

    repoEntry = new RepoEntry();
    repoEntry.setName(reponame);
    repoEntry.setType(RepoType.STATIC);
    repoEntry.setHashOfEntries("dirtyHash");

    when(repoService.ensureEntry(reponame, RepoType.STATIC, RepoType.SCHEDULED)).thenReturn(repoEntry);
    when(yumEntriesHashCalculator.hashForRepo(reponame)).thenReturn(ENTRIES_HASH);
  }

  @Test
  public void oldMetaDataFilesAreDeleted() throws Exception {
    this.service.generateYumMetadataIfNecessary(reponame);

    verify(fileStorageService).markForDeletionByFilenameRegex(eq(reponame + REGEX_REPODATA_SQLITE_FILES));
  }

  @Test
  public void cleanRepositoryBeforeGeneration() throws Exception {
    this.service.generateYumMetadataIfNecessary(reponame);
    verify(repoCleaner).cleanup(eq(reponame));
  }

  @Test
  public void doNothingIfNothingChanged() throws Exception {
    repoEntry.setHashOfEntries(ENTRIES_HASH);

    service.generateYumMetadataIfNecessary(reponame);

    verifyZeroInteractions(repoCleaner, storageService);
  }
}
