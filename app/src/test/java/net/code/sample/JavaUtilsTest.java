package net.code.sample;

import androidx.annotation.NonNull;

import net.code.sample.toolchain.FileUtil;
import net.code.sample.toolchain.GetAbbrUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;

import static net.code.sample.TestConstants.FIRST_NAME;
import static net.code.sample.TestConstants.LAST_NAME;
import static net.code.sample.TestConstants.TEST_DIR_NAME;
import static net.code.sample.TestConstants.TEST_IMAGE_SMALL;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class JavaUtilsTest {

    @Rule
    public TemporaryFolder folder = TemporaryFolder.builder()
            .assureDeletion()
            .build();
    private File pictureFile;

    @Before
    public void initializeFiles() {
        pictureFile = TestUtils.getTestResourceFile(TEST_IMAGE_SMALL);
    }

    @Test
    public void setAbbreviation() {
        assertNotNull(GetAbbrUtil.setAbbr(FIRST_NAME, LAST_NAME));
        assertNotNull(GetAbbrUtil.setAbbr(FIRST_NAME, null));
        assertNotNull(GetAbbrUtil.setAbbr(null, LAST_NAME));
        assertNotNull(GetAbbrUtil.setAbbr(null, null));
    }

    @Test
    public void isFileValid() {
        assertTrue(FileUtil.isFileValid(pictureFile));
    }

    @Test
    public void forceDeleteFileOrDirectory() throws IOException {
        File tempFile = createTempFile();
        File tempDir = createChildDir(TEST_DIR_NAME);
        FileUtil.forceDelete(tempFile);
        FileUtil.forceDelete(tempDir);
        assertFalse(tempFile.exists());
        assertFalse(tempDir.isDirectory());
    }

    @Test
    public void deleteQuietly() throws IOException {
        assertTrue(FileUtil.deleteQuietly(createTempFile()));
        File dirWithFiles = generateDirWithTempFiles(TEST_DIR_NAME);
        assertTrue(FileUtil.deleteQuietly(dirWithFiles));
    }

    @Test
    public void cleanDirectory() throws IOException {
        File tempFile = createTempFile();
        FileUtil.cleanDirectory(getTempDir());
        assertFalse(tempFile.exists());
    }

    @Test
    public void verifiedListFiles() throws IOException {
        generateTempFilesForRootDir();
        assertNotNull(FileUtil.verifiedListFiles(getTempDir()));
    }

    @NonNull
    private File getTempDir() {
        return new File(folder.getRoot().getPath());
    }

    @NonNull
    private File createTempFile() {
        try {
            return folder.newFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //noinspection ConstantConditions
        return null;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createTempFile(@NonNull File dir) throws IOException {
        dir.createNewFile();
    }

    @NonNull
    private File createChildDir(@NonNull String path) {
        try {
            return folder.newFolder(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //noinspection ConstantConditions
        return null;
    }

    private void generateTempFilesForRootDir() {
        for (int i = 0; i < 5; i++) {
            createTempFile();
        }
    }

    @SuppressWarnings("SameParameterValue")
    private File generateDirWithTempFiles(@NonNull String path) throws IOException {
        File tempDir = createChildDir(path);
        for (int i = 0; i < 5; i++) {
            createTempFile(tempDir);
        }
        return tempDir;
    }
}
