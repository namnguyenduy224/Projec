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

        System.out.println("====== HỆ THỐNG QUẢN LÝ KHO TRÀ ======");

        do {
            System.out.println("\n--- MENU LỰA CHỌN ---");
            System.out.println("1. Nhập và thêm mới một loại TRÀ");
            System.out.println("2. Nhập và thêm mới một ẤM TRÀ (Utensil)");
            System.out.println("3. Xem báo cáo tồn kho hiện tại");
            System.out.println("4. Thử nghiệm sử dụng Ấm trà đầu tiên trong kho");
            System.out.println("0. Thoát chương trình");
            System.out.print("Chọn chức năng (0-4): ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n[NHẬP THÔNG TIN TRÀ]");
                    System.out.print("Nhập mã số trà (ID): ");
                    String teaId = scanner.nextLine();
                    System.out.print("Nhập tên/loại trà (VD: Trà Ô Long): ");
                    String teaType = scanner.nextLine();
                    System.out.print("Nhập khối lượng (grams): ");
                    double weight = scanner.nextDouble();

                    Tea newTea = new Tea(teaId, teaType, weight);
                    inventory.addTea(newTea);
                    break;

                case 2:
                    System.out.println("\n[NHẬP THÔNG TIN ẤM TRÀ]");
                    System.out.print("Nhập mã dụng cụ (ID): ");
                    String potId = scanner.nextLine();
                    System.out.print("Nhập tên ấm (VD: Ấm Tử Sa): ");
                    String potName = scanner.nextLine();
                    System.out.print("Nhập chất liệu (VD: Đất sét, Sứ): ");
                    String material = scanner.nextLine();
                    System.out.print("Nhập dung tích ấm (ml): ");
                    double capacity = scanner.nextDouble();

                    Teapot newTeapot = new Teapot(potId, potName, material, capacity);
                    inventory.addUtensil(newTeapot);
                    break;

                case 3:
                    inventory.checkStock();
                    break;

                case 4:
                    if (!inventory.getUtensils().isEmpty()) {
                        System.out.println("\n[THỬ NGHIỆM HÀNH ĐỘNG ẤM TRÀ]");
                        Utensil u = inventory.getUtensils().get(0);
                        if (u instanceof Teapot) {
                            Teapot pot = (Teapot) u;
                            pot.use();
                            pot.fill();
                            pot.pour();
                        } else {
                            System.out.println("Dụng cụ đầu tiên không phải là ấm trà!");
                        }
                    } else {
                        System.out.println("\nHện tại trong kho chưa có ấm trà nào! Vui lòng chọn 2 để nhập trước.");
                    }
                    break;

                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại từ 0 đến 4.");
            }
        } while (choice != 0);
    }
}
