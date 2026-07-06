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

    public void saveAll(List<Tea> teas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Tea tea : teas) {
                writer.println(tea.toString());
            }
            System.out.println(">> Đã cập nhật file dữ liệu Trà (" + FILE_NAME + ")");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file Trà: " + e.getMessage());
        }
    }

    public List<Tea> loadAll() {
        List<Tea> teas = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return teas;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    teas.add(new Tea(parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file Trà: " + e.getMessage());
        }
        return teas;
    }
    
}
