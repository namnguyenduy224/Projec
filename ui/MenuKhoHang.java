package ui;

import java.util.Scanner;
import services.InventoryService;   // Giả sử có InventoryService

public class MenuKhoHang {
    private InventoryService inventoryService;
    private Scanner scanner = new Scanner(System.in);

    public MenuKhoHang(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void hienThi() {
        int choice;
        do {
            System.out.println("\n" + "=".repeat(45));
            System.out.println("              MENU KHO HÀNG");
            System.out.println("=".repeat(45));
            System.out.println("1. Xem tồn kho hiện tại");
            System.out.println("2. Nhập thêm nguyên liệu");
            System.out.println("3. Xuất nguyên liệu");
            System.out.println("4. Kiểm tra nguyên liệu sắp hết");
            System.out.println("5. Lịch sử nhập/xuất kho");
            System.out.println("0. Quay lại Menu Chính");
            System.out.println("=".repeat(45));
            System.out.print("Chọn chức năng: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: inventoryService.xemTonKho(); break;
                case 2: inventoryService.nhapKho(); break;
                case 3: inventoryService.xuatKho(); break;
                case 4: inventoryService.canhBaoHetHang(); break;
                case 5: inventoryService.xemLichSu(); break;
                case 0: System.out.println("← Quay lại..."); break;
                default: System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}
