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
public class InventoryRepository {

    private final String TEA_FILE = "teas.txt";
    private final String TEAPOT_FILE = "teapots.txt";

    public void saveInventory(List<Tea> teas, List<Utensil> utensils) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(TEA_FILE))) {
            for (Tea tea : teas) {
                writer.println(tea.toString());
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file trà: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(TEAPOT_FILE))) {
            for (Utensil u : utensils) {
                if (u instanceof Teapot) {
                    writer.println(u.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file ấm trà: " + e.getMessage());
        }
        System.out.println(">> Đã tự động lưu toàn bộ dữ liệu vào file thành công!");
    }

    public List<Tea> loadTeas() {
        List<Tea> teas = new ArrayList<>();
        File file = new File(TEA_FILE);
        if (!file.exists()) {
            return teas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    teas.add(new Tea(parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file trà: " + e.getMessage());
        }
        return teas;
    }

    public List<Utensil> loadTeapots() {
        List<Utensil> utensils = new ArrayList<>();
        File file = new File(TEAPOT_FILE);
        if (!file.exists()) {
            return utensils;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    Material material = Material.valueOf(parts[2]);
                    double capacity = Double.parseDouble(parts[3]);
                    utensils.add(new Teapot(id, name, material, capacity));
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file ấm trà: " + e.getMessage());
        }
        return utensils;
    }

}
