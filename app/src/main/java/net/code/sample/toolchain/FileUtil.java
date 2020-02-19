package net.code.sample.toolchain;

import android.webkit.MimeTypeMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Helpful link:
 * https://github.com/apache/commons-io/blob/master/src/main/java/org/apache/commons/io/FileUtils.java
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class FileUtil {
    private static final String TAG = FileUtil.class.getSimpleName();

    /**
     * Deletes a directory recursively.
     *
     * @param directory directory to delete
     * @throws IOException              in case deletion is unsuccessful
     * @throws IllegalArgumentException if {@code directory} does not exist or is not a directory
     */
    public static void deleteDirectory(@NonNull File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }

        cleanDirectory(directory);

        if (!directory.delete()) {
            throw new IOException("Unable to delete directory " + directory + ".");
        }
    }

    /**
     * Deletes a file, never throwing an exception. If file is a directory, delete it and all sub-directories.
     * <p>
     * The difference between File.delete() and this method are:
     * </p>
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>No exceptions are thrown when a file or directory cannot be deleted.</li>
     * </ul>
     *
     * @param file file or directory to delete, can be {@code null}
     * @return {@code true} if the file or directory was deleted, otherwise
     * {@code false}
     * @since 1.4
     */
    public static boolean deleteQuietly(@Nullable File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        } catch (Exception ignored) {
            // ignore
        }

        try {
            return file.delete();
        } catch (Exception ignored) {
            return false;
        }
    }

    /**
     * Cleans a directory without deleting it.
     *
     * @param directory directory to clean
     * @throws IOException              in case cleaning is unsuccessful
     * @throws IllegalArgumentException if {@code directory} does not exist or is not a directory
     */
    public static void cleanDirectory(@NonNull File directory) throws IOException {
        File[] files = verifiedListFiles(directory);

        IOException exception = null;
        for (File file : files) {
            try {
                forceDelete(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (exception != null) {
            throw exception;
        }
    }

    /**
     * Lists files in a directory, asserting that the supplied directory satisfies exists and is a directory.
     *
     * @param directory The directory to list
     * @return The files in the directory, never null.
     * @throws IOException if an I/O error occurs
     */
    @NonNull
    public static File[] verifiedListFiles(@NonNull File directory) throws IOException {
        if (!directory.exists()) {
            throw new IllegalArgumentException(directory + " does not exist");
        }

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory + " is not a directory");
        }

        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }
        return files;
    }

    /**
     * Deletes a file. If file is a directory, delete it and all sub-directories.
     * <p>
     * The difference between File.delete() and this method are:
     * </p>
     * <ul>
     * <li>A directory to be deleted does not have to be empty.</li>
     * <li>You get exceptions when a file or directory cannot be deleted.
     * (java.io.File methods returns a boolean)</li>
     * </ul>
     *
     * @param file file or directory to delete, must not be {@code null}
     * @throws NullPointerException  if the directory is {@code null}
     * @throws FileNotFoundException if the file was not found
     * @throws IOException           in case deletion is unsuccessful
     */
    public static void forceDelete(@NonNull File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            boolean filePresent = file.exists();
            if (!file.delete()) {
                if (!filePresent) {
                    throw new FileNotFoundException("File does not exist: " + file);
                }
                throw new IOException("Unable to delete file: " + file);
            }
        }
    }

    @NonNull
    public static String getFileExtension(@NonNull File file) {
        String extension = "";
        try {
            extension = MimeTypeMap.getFileExtensionFromUrl(file.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            WLog.e(TAG, e);
        }
        return extension;
    }

    /**
     * Makes sure that the file is:
     * <ul>
     *     <li>exist</li>
     *     <li>not a directory</li>
     *     <li>not null value </li>
     * </ul>
     * @return true value if the file is valid.
     */
    public static boolean isFileValid(@Nullable File file) {
        return file != null && file.exists() && file.isFile();
    }
}
