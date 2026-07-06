import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileHelper {
    private FileHelper() {
    }

    public static boolean createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists()) {
            return folder.mkdirs();
        }

        return true;
    }

    public static boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }

    public static boolean writeText(String filePath, String content) {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
                writer.print(content);
            }

            return true;
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
            return false;
        }
    }

    public static String readText(String filePath) {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                return "";
            }

            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
            return "";
        }
    }

    public static boolean copyFile(String sourcePath, String targetPath) {
        try {
            File source = new File(sourcePath);
            File target = new File(targetPath);

            if (!source.exists()) {
                return false;
            }

            File parent = target.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            System.out.println("Loi copy file: " + e.getMessage());
            return false;
        }
    }

    public static String createBackupFileName(String originalFileName) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return "backup_" + time + "_" + originalFileName;
    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            return false;
        }

        return file.delete();
    }
}
