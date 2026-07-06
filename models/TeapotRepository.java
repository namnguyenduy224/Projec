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

    public void saveAll(List<Teapot> teapots) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Teapot teapot : teapots) {
                writer.println(teapot.toString());
            }
            System.out.println(">> Đã cập nhật file dữ liệu Ấm trà (" + FILE_NAME + ")");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file Ấm trà: " + e.getMessage());
        }
    }

    public List<Teapot> loadAll() {
        List<Teapot> teapots = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return teapots;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    Material material = Material.valueOf(parts[2]);
                    double capacity = Double.parseDouble(parts[3]);
                    teapots.add(new Teapot(id, name, material, capacity));
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file Ấm trà: " + e.getMessage());
        }
        return teapots;
    }
    
}
