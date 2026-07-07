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
public class TeapotRepository {
    
    private final String FILE_NAME = "teapots.txt";
    private final String BACKUP_DIR = "backup/teapots/";

    public void saveAll(List<Teapot> teapots) {
        String tmpFileName = FILE_NAME + ".tmp";
        File tmpFile = new File(tmpFileName);

        try (PrintWriter writer = new PrintWriter(new FileWriter(tmpFile))) {
            for (Teapot teapot : teapots) {
                writer.println(teapot.toString());
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("[Lỗi] Không thể ghi file tạm cho Ấm trà: " + e.getMessage());
            return;
        }

        try {
            Files.move(tmpFile.toPath(), Paths.get(FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(">> Đã cập nhật file dữ liệu Ấm trà an toàn.");
        } catch (IOException e) {
            System.out.println("[Lỗi] Không thể ghi đè file chính cho Ấm trà: " + e.getMessage());
        }
    }

    public List<Teapot> loadAll() {
        List<Teapot> teapots = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return teapots;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try {
                        String id = parts[0];
                        String name = parts[1];
                        Material material = Material.valueOf(parts[2]);
                        double capacity = Double.parseDouble(parts[3]);
                        teapots.add(new Teapot(id, name, material, capacity));
                    } catch (IllegalArgumentException e) {
                        System.out.println("[Cảnh báo] Bỏ qua dòng Ấm trà lỗi định dạng số hoặc lỗi Enum: " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("[Lỗi] Không thể đọc file Ấm trà: " + e.getMessage());
        }
        return teapots;
    }

    public String backup() {
        File mainFile = new File(FILE_NAME);
        if (!mainFile.exists()) return "Không có dữ liệu Ấm trà để sao lưu.";

        try {
            Files.createDirectories(Paths.get(BACKUP_DIR));
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String backupFileName = BACKUP_DIR + "teapots_" + timestamp + ".bak";
            
            Files.copy(mainFile.toPath(), Paths.get(backupFileName), StandardCopyOption.REPLACE_EXISTING);
            return "Sao lưu Ấm trà thành công -> " + backupFileName;
        } catch (IOException e) {
            return "Lỗi khi sao lưu dữ liệu Ấm trà: " + e.getMessage();
        }
    }

    public boolean restore(String backupFilePath) {
        File backupFile = new File(backupFilePath);
        if (!backupFile.exists()) {
            System.out.println("[Lỗi] File sao lưu ấm trà không tồn tại!");
            return false;
        }
        try {
            Files.copy(backupFile.toPath(), Paths.get(FILE_NAME), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(">> Phục hồi file dữ liệu Ấm trà thành công.");
            return true;
        } catch (IOException e) {
            System.out.println("[Lỗi] Không thể phục hồi dữ liệu Ấm trà: " + e.getMessage());
            return false;
        }
    }
    
}
