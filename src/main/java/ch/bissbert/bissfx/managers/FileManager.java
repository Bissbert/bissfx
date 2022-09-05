package ch.bissbert.bissfx.managers;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

/**
 * A class to manage files and directories.
 * <p>
 * all methods are static and can be called without an instance of this class.
 *
 * @author Bissbert
 */
public class FileManager {
    private FileManager() {
    }

    /**
     * Extracts the files extension from its path.
     *
     * @param fileName the file name
     * @return the files extension
     */
    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * Returns the path of the file without its extension.
     *
     * @param fileName the file name
     * @return the path of the file without its extension
     */
    public static String getFilePathWithoutExtension(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /**
     * Returns the name of the file with its extension.
     *
     * @param fileName the file path
     * @return the name of the file with its extension
     */
    public static String getFileNameWithExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("/") + 1);
    }

    /**
     * Returns the name of the file without its extension.
     *
     * @param fileName the file path
     * @return the name of the file without its extension
     */
    public static String getFileNameWithoutExtension(String fileName) {
        return getFilePathWithoutExtension(getFileNameWithExtension(fileName));
    }

    /**
     * Returns the path of the file without the filename itself.
     *
     * @param fileName the file path
     * @return the path of the file without the filename itself
     */
    public static String getFilePath(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("/"));
    }

    /**
     * creates a new instance of file using the given path.
     *
     * @param fileName the file path(local file)
     * @return the new file instance
     */
    public static File getFile(String fileName) {
        return new File(fileName);
    }

    /**
     * creates a new instance of file using the given path and name.
     *
     * @param fileName the file name
     * @param path     the path of the directory the file is in
     * @return the new file instance
     */
    public static File getFile(String path, String fileName) {
        return new File(path, fileName);
    }

    /**
     * Fetches a file from the resources.
     *
     * @param fileName the file name
     * @return the file instance
     */
    public static File getFileFromResources(String fileName) {
        return new File(Objects.requireNonNull(FileManager.class.getClassLoader().getResource(fileName)).getFile());
    }

    /**
     * Fetches a file from the resources using the given path and name.
     *
     * @param path     the path of the directory the file is in
     * @param fileName the file name
     * @return the file instance
     */
    public static File getFileFromResources(String path, String fileName) {
        return new File(Objects.requireNonNull(FileManager.class.getClassLoader().getResource(path + "/" + fileName)).getFile());
    }

    /**
     * Fetches a file from the root of the resources using the given path.
     *
     * @param fileName the file name
     * @return the file instance
     */
    public static File getFileFromResourcesRoot(String fileName) {
        return new File(Objects.requireNonNull(FileManager.class.getClassLoader().getResource("/" + fileName)).getFile());
    }

    /**
     * Fetches a file from the root of the resources using the given path and name.
     *
     * @param path     the path of the directory the file is in
     * @param fileName the file name
     * @return the file instance
     */
    public static File getFileFromResourcesRoot(String path, String fileName) {
        return new File(Objects.requireNonNull(FileManager.class.getClassLoader().getResource("/" + path + "/" + fileName)).getFile());
    }

    /**
     * Fetches a file via a file chooser.
     * <p>
     * The file chooser will be opened sticking to the given stage.
     * <p>
     * The extensions will be used to filter the files possible to select.
     *
     * @param title      the title of the file chooser
     * @param stage      the stage the file chooser will be opened on
     * @param extensions the extensions to filter the files
     * @return the selected file
     */
    public static File getFileFromUser(String title, Stage stage, String... extensions) {
        FileChooser.ExtensionFilter[] extensionFilters = new FileChooser.ExtensionFilter[extensions.length];
        for (int i = 0; i < extensions.length; i++) {
            extensionFilters[i] = new FileChooser.ExtensionFilter(extensions[i], "*." + extensions[i]);
        }
        return getFileFromUser(title, stage, extensionFilters);
    }

    /**
     * Fetches a file via a file chooser.
     * <p>
     * The extensions will be used to filter the files possible to select.
     *
     * @param title      the title of the file chooser
     * @param extensions the extensions to filter the files
     * @return the selected file
     */
    public static File getFileFromUser(String title, String... extensions) {
        return getFileFromUser(title, null, extensions);
    }

    /**
     * Fetches a file via a file chooser.
     *
     * @param title the title of the file chooser
     * @return the selected file
     */
    public static File getFileFromUser(String title) {
        return getFileFromUser(title, new String[0]);
    }

    /**
     * Fetches a file via a file chooser.
     * <p>
     * The file chooser will be opened sticking to the given stage.
     * <p>
     * The extensions will be used to filter the files possible to select.
     *
     * @param title            the title of the file chooser
     * @param stage            the stage the file chooser will be opened on
     * @param extensionFilters the extensions to filter the files
     * @return the selected file
     */
    public static File getFileFromUser(String title, Stage stage, FileChooser.ExtensionFilter... extensionFilters) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select " + title);
        fileChooser.getExtensionFilters().addAll(extensionFilters);
        return fileChooser.showOpenDialog(stage);
    }
}
