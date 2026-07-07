package ui;

import java.util.Scanner;
import services.TeaService;   // Giả sử package services
import models.Tea;

public class MenuQuanLyTra {
    private TeaService teaService;
    private Scanner scanner = new Scanner(System.in);

    public MenuQuanLyTra(TeaService teaService) {
        this.teaService = teaService;
    }

    public void hienThi() {
        int choice;
        do {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("          MENU QUẢN LÝ TRÀ");
            System.out.println("=".repeat(40));
            System.out.println("1. Thêm trà mới");
            System.out.println("2. Xem danh sách trà");
            System.out.println("3. Tìm kiếm trà");
            System.out.println("4. Cập nhật trà");
            System.out.println("5. Xóa trà");
            System.out.println("0. Quay lại Menu Chính");
            System.out.println("=".repeat(40));
            System.out.print("Chọn chức năng: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1: teaService.themTra(); break;
                case 2: teaService.hienThiTatCa(); break;
                case 3: teaService.timKiem(); break;
                case 4: teaService.capNhat(); break;
                case 5: teaService.xoa(); break;
                case 0: System.out.println("← Quay lại..."); break;
                default: System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}
