/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LOQ
 */
public class Inventory {
    
    private List<Tea> teas;
    private List<Teapot> teapots;
    
    private final TeaRepository teaRepository;
    private final TeapotRepository teapotRepository;

    public Inventory() {
        this.teaRepository = new TeaRepository();
        this.teapotRepository = new TeapotRepository();
        this.teas = teaRepository.loadAll();
        this.teapots = teapotRepository.loadAll();
    }

    public void addTea(Tea tea) {
        teas.add(tea);
        teaRepository.saveAll(teas);
    }

    public void addTeapot(Teapot teapot) {
        teapots.add(teapot);
        teapotRepository.saveAll(teapots);
    }

    public void backupAll() {
        System.out.println("\n--- TIẾN TRÌNH SAO LƯU HỆ THỐNG ---");
        String res1 = teaRepository.backup();
        String res2 = teapotRepository.backup();
        System.out.println(res1);
        System.out.println(res2);
        System.out.println("------------------------------------");
    }

    public void restoreTea(String filePath) {
        if (teaRepository.restore(filePath)) {
            this.teas = teaRepository.loadAll(); 
            System.out.println(">> Đã đồng bộ lại danh sách Trà trên bộ nhớ hệ thống.");
        }
    }

    public void restoreTeapot(String filePath) {
        if (teapotRepository.restore(filePath)) {
            this.teas = teaRepository.loadAll(); 
            System.out.println(">> Đã đồng bộ lại danh sách Ấm trà trên bộ nhớ hệ thống.");
        }
    }

    public List<Tea> getTeas() { return teas; }
    public List<Teapot> getTeapots() { return teapots; }

    public void checkStock() {
        System.out.println("\n=== BÁO CÁO TỒN KHO HIỆN TẠI ===");
        System.out.println("[Danh sách các loại Trà]:");
        if (teas.isEmpty()) System.out.println("  (Kho trống)");
        for (Tea t : teas) {
            System.out.println("  - Mã: " + t.getId() + " | " + t.getType() + " | Khối lượng: " + t.getWeight() + "g");
        }

        System.out.println("[Danh sách Ấm Trà]:");
        if (teapots.isEmpty()) System.out.println("  (Kho trống)");
        for (Teapot pot : teapots) {
            System.out.println("  - Mã: " + pot.getId() + " | " + pot.getName() 
                    + " | Chất liệu: " + pot.getMaterial().getDisplayName() 
                    + " | Dung tích: " + pot.getCapacity() + "ml");
        }
        System.out.println("================================\n");
    }
    private String itemId;
    private String itemName;
    private int quantity;
    private String unit;

    public Inventory(String itemId, String itemName, int quantity, String unit) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public static Inventory createEmptyInventory() {
        return new Inventory();
    }
    public void restoreTeapot(String filePath, boolean fixSync) {
        if (fixSync && teapotRepository.restore(filePath)) {
            this.teapots = teapotRepository.loadAll();
            System.out.println(">> [Hệ thống] Đã sửa lỗi đồng bộ: Danh sách Ấm trà đã cập nhật chính xác trên RAM.");
        }
    }
}
