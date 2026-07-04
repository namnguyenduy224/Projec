public interface IFileService {
    boolean saveData(String filePath);
    boolean loadData(String filePath);
    boolean backupData(String backupPath);
    boolean restoreData(String backupPath);
}
