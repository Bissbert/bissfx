package ch.bissbert.bissfx.managers;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class FileManager {
    private FileManager() {
    }

    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String getFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static String getFileNameWithExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("/") + 1);
    }

    public static String getFileNameWithoutExtension(String fileName) {
        return getFileName(getFileNameWithExtension(fileName));
    }

    public static String getFilePath(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("/"));
    }

    public static File getFile(String fileName) {
        return new File(fileName);
    }

    public static File getFile(String path, String fileName) {
        return new File(path, fileName);
    }

    public static File getFileFromResources(String fileName) {
        return new File(Objects.requireNonNull(FileManager.class.getClassLoader().getResource(fileName)).getFile());
    }

    public static File getFileFromResources(String path, String fileName) {
        return new File(Objects.requireNonNull(FileManager.class.getClassLoader().getResource(path + "/" + fileName)).getFile());
    }

    public static File getFileFromResourcesRoot(String fileName) {
        return new File(Objects.requireNonNull(FileManager.class.getClassLoader().getResource("/" + fileName)).getFile());
    }

    public static File getFileFromResourcesRoot(String path, String fileName) {
        return new File(Objects.requireNonNull(FileManager.class.getClassLoader().getResource("/" + path + "/" + fileName)).getFile());
    }

    public static File getFileFromUser(String title, Stage stage, String... extensions) {
        FileChooser.ExtensionFilter[] extensionFilters = new FileChooser.ExtensionFilter[extensions.length];
        for (int i = 0; i < extensions.length; i++) {
            extensionFilters[i] = new FileChooser.ExtensionFilter(extensions[i], "*." + extensions[i]);
        }
        return getFileFromUser(title, stage, extensionFilters);
    }

    public static File getFileFromUser(String title, String... extensions) {
        return getFileFromUser(title, null, extensions);
    }

    public static File getFileFromUser(String title) {
        return getFileFromUser(title, new String[0]);
    }

    public static File getFileFromUser(String title, Stage stage, FileChooser.ExtensionFilter... extensionFilters) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select " + title);
        fileChooser.getExtensionFilters().addAll(extensionFilters);
        return fileChooser.showOpenDialog(stage);
    }
}
