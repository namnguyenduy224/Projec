/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.Scanner;

/**
 *
 * @author LOQ
 */
public class Main {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("--- MENU QUẢN LÝ ---");
            System.out.println("1. Thêm mới TRÀ");
            System.out.println("2. Thêm mới ẤM TRÀ");
            System.out.println("3. Kiểm tra TỒN KHO (Tự động load file cũ)");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Nhập ID Trà: "); String teaId = scanner.nextLine();
                    System.out.print("Nhập tên Trà: "); String teaType = scanner.nextLine();
                    System.out.print("Nhập khối lượng (g): "); double weight = scanner.nextDouble();
                    inventory.addTea(new Tea(teaId, teaType, weight));
                    break;

                case 2:
                    System.out.print("Nhập ID Ấm: "); String potId = scanner.nextLine();
                    System.out.print("Nhập tên Ấm: "); String potName = scanner.nextLine();
                    
                    System.out.println("Chọn chất liệu ấm:");
                    Material[] materials = Material.values();
                    for (int i = 0; i < materials.length; i++) {
                        System.out.println("  " + (i + 1) + ". " + materials[i].getDisplayName());
                    }
                    System.out.print("Nhập số lựa chọn: ");
                    int matChoice = scanner.nextInt();
                    Material selectedMaterial = materials[matChoice - 1]; // Lấy đúng enum theo index

                    System.out.print("Nhập dung tích (ml): "); double capacity = scanner.nextDouble();
                    
                    inventory.addUtensil(new Teapot(potId, potName, selectedMaterial, capacity));
                    break;

                case 3:
                    inventory.checkStock();
                    break;
                case 0:
                    System.out.println("Đã thoát ứng dụng.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
    }
