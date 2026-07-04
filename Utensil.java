/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LOQ
 */
public abstract class Utensil {
    
    private String id;
    private String name;
    private String material;

    public Utensil() {
    }

    public Utensil(String id, String name, String material) {
        this.id = id;
        this.name = name;
        this.material = material;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getMaterial() { return material; }

    public abstract void use();
    
}
