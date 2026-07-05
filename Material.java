/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LOQ
 */
public enum Material {
    
    CLAY("Đất sét / Tử sa"),
    CERAMIC("Gốm sứ"),
    GLASS("Thủy tinh"),
    WOOD("Gỗ");

    private final String displayName;

    Material(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
}
