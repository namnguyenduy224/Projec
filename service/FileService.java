public class FileService implements IFileService {
    private String currentFilePath;
    private String data;

    public FileService() {
        this.data = "";
    }

    public FileService(String data) {
        this.data = data == null ? "" : data;
    }

    public void setData(String data) {
        this.data = data == null ? "" : data;
    }

    public String getData() {
        return data;
    }

    public String getCurrentFilePath() {
        return currentFilePath;
    }

    @Override
    public boolean saveData(String filePath) {
        if (isEmpty(filePath)) {
            return false;
        }

        boolean success = FileHelper.writeText(filePath, data);

        if (success) {
            currentFilePath = filePath;
        }

        return success;
    }

    @Override
    public boolean loadData(String filePath) {
        if (isEmpty(filePath) || !FileHelper.fileExists(filePath)) {
            return false;
        }

        data = FileHelper.readText(filePath);
        currentFilePath = filePath;
        return true;
    }

    @Override
    public boolean backupData(String backupPath) {
        if (isEmpty(backupPath) || isEmpty(currentFilePath)) {
            return false;
        }

        if (!FileHelper.fileExists(currentFilePath)) {
            return false;
        }

        return FileHelper.copyFile(currentFilePath, backupPath);
    }

    @Override
    public boolean restoreData(String backupPath) {
        if (isEmpty(backupPath) || !FileHelper.fileExists(backupPath)) {
            return false;
        }

        if (isEmpty(currentFilePath)) {
            currentFilePath = backupPath;
        }

        boolean success = FileHelper.copyFile(backupPath, currentFilePath);

        if (success) {
            data = FileHelper.readText(currentFilePath);
        }

        return success;
    }

    public boolean backupDataToFolder(String folderPath) {
        if (isEmpty(folderPath) || isEmpty(currentFilePath)) {
            return false;
        }

        FileHelper.createFolderIfNotExists(folderPath);
        String backupFileName = FileHelper.createBackupFileName(getFileName(currentFilePath));
        String backupPath = folderPath + "/" + backupFileName;

        return backupData(backupPath);
    }

    private String getFileName(String path) {
        if (path == null) {
            return "data.txt";
        }

        int slashIndex = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));

        if (slashIndex >= 0 && slashIndex < path.length() - 1) {
            return path.substring(slashIndex + 1);
        }

        return path;
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
