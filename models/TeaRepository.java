/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LOQ
 */
public class TeaRepository {
    
   private final String FILE_NAME = "teas.txt";
    private final String BACKUP_DIR = "backup/teas/";

    public void saveAll(List<Tea> teas) {
        String tmpFileName = FILE_NAME + ".tmp";
        File tmpFile = new File(tmpFileName);

        try (PrintWriter writer = new PrintWriter(new FileWriter(tmpFile))) {
            for (Tea tea : teas) {
                writer.println(tea.toString());
            }
            writer.flush(); 
        } catch (IOException e) {
            System.out.println("[Lỗi] Không thể ghi file tạm cho Trà: " + e.getMessage());
            return;
        }

        try {
            Path mainPath = Paths.get(FILE_NAME);
            Path tmpPath = tmpFile.toPath();
            Files.move(tmpPath, mainPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(">> Đã cập nhật file dữ liệu Trà an toàn.");
        } catch (IOException e) {
            System.out.println("[Lỗi] Không thể ghi đè file chính cho Trà: " + e.getMessage());
        }
    }

    public List<Tea> loadAll() {
        List<Tea> teas = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return teas;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; 
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        teas.add(new Tea(parts[0], parts[1], Double.parseDouble(parts[2])));
                    } catch (NumberFormatException e) {
                        System.out.println("[Cảnh báo] Bỏ qua dòng dữ liệu Trà bị lỗi định dạng số: " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("[Lỗi] Không thể đọc file Trà: " + e.getMessage());
        }
        return teas;
    }

    public String backup() {
        File mainFile = new File(FILE_NAME);
        if (!mainFile.exists()) return "Không có dữ liệu Trà để sao lưu.";

        try {
            Files.createDirectories(Paths.get(BACKUP_DIR));
            
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String backupFileName = BACKUP_DIR + "teas_" + timestamp + ".bak";
            
            Files.copy(mainFile.toPath(), Paths.get(backupFileName), StandardCopyOption.REPLACE_EXISTING);
            return "Sao lưu Trà thành công -> " + backupFileName;
        } catch (IOException e) {
            return "Lỗi khi sao lưu dữ liệu Trà: " + e.getMessage();
        }
    }

    public boolean restore(String backupFilePath) {
        File backupFile = new File(backupFilePath);
        if (!backupFile.exists()) {
            System.out.println("[Lỗi] File sao lưu không tồn tại!");
            return false;
        }
        try {
            Files.copy(backupFile.toPath(), Paths.get(FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(">> Phục hồi file dữ liệu Trà thành công.");
            return true;
        } catch (IOException e) {
            System.out.println("[Lỗi] Không thể phục hồi dữ liệu Trà: " + e.getMessage());
            return false;
        }
    }
}
