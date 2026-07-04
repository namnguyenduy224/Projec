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

    public Inventory() {
        this.teas = new ArrayList<>();
        this.utensils = new ArrayList<>();
    }

    public void addTea(Tea tea) {
        teas.add(tea);
        System.out.println("Đã thêm vào kho: " + tea.getType());
    }

    public void addUtensil(Utensil utensil) {
        utensils.add(utensil);
        System.out.println("Đã thêm dụng cụ vào kho: " + utensil.getName());
    }

    public List<Tea> getTeas() { return teas; }
    public List<Utensil> getUtensils() { return utensils; }

    public void checkStock() {
        System.out.println("\n--- BÁO CÁO TỒN KHO ---");
        System.out.println("Các loại trà:");
        for (Tea t : teas) {
            System.out.println("- " + t.getType() + ": " + t.getWeight() + "g");
        }
        System.out.println("Các dụng cụ:");
        for (Utensil u : utensils) {
            System.out.println("- " + u.getName() + " (" + u.getMaterial() + ")");
        }
        System.out.println("-----------------------\n");
    }
    
}
