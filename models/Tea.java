/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LOQ
 */
public class Tea {
    
    private String id;
    private String type; 
    private double weight; 

    public Tea(String id, String type, double weight) {
        this.id = id;
        this.type = type;
        this.weight = weight;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public double getWeight() { return weight; }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "id: '" + id + "', type: '" + type + "', weight: " + weight;
    }
    
}
