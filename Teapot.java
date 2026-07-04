/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LOQ
 */
public class Teapot extends Utensil {

    private double capacity;
    private boolean isFull;

    public Teapot(String id, String name, String material, double capacity) {
        super(id, name, material);
        this.capacity = capacity;
        this.isFull = false;
    }

    public double getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return isFull;
    }

    public void fill() {
        this.isFull = true;
        System.out.println(getName() + " đã được châm đầy nước.");
    }

    public void pour() {
        if (isFull) {
            this.isFull = false;
            System.out.println("Đang rót trà từ ấm " + getName());
        } else {
            System.out.println("Ấm " + getName() + " đang hết nước!");
        }
    }

    @Override
    public void use() {
        System.out.println("Đang dùng ấm " + getName() + " để hãm trà.");
    }
}


