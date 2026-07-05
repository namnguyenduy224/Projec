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
    private List<Utensil> utensils;
    private final InventoryRepository repository;

    public Inventory() {
        this.repository = new InventoryRepository();
        this.teas = repository.loadTeas();
        this.utensils = repository.loadTeapots();
    }

    public void addTea(Tea tea) {
        teas.add(tea);
        save(); 
    }

    public void addUtensil(Utensil utensil) {
        utensils.add(utensil);
        save();
    }

    public void save() {
        repository.saveInventory(teas, utensils);
    }

    public List<Tea> getTeas() { return teas; }
    public List<Utensil> getUtensils() { return utensils; }

    public void checkStock() {
        System.out.println("\n=== BÁO CÁO TỒN KHO HIỆN TẠI ===");
        System.out.println("[Danh sách các loại Trà]:");
        if (teas.isEmpty()) System.out.println("  (Kho trống)");
        for (Tea t : teas) {
            System.out.println("  - Mã: " + t.getId() + " | " + t.getType() + " | Khối lượng: " + t.getWeight() + "g");
        }

        System.out.println("[Danh sách Ấm Trà]:");
        if (utensils.isEmpty()) System.out.println("  (Kho trống)");
        for (Utensil u : utensils) {
            if (u instanceof Teapot) {
                Teapot pot = (Teapot) u;
                System.out.println("  - Mã: " + pot.getId() + " | " + pot.getName() 
                        + " | Chất liệu: " + pot.getMaterial().getDisplayName() 
                        + " | Dung tích: " + pot.getCapacity() + "ml");
            }
        }
        System.out.println("================================\n");
    }
    
}
