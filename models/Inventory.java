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
}
