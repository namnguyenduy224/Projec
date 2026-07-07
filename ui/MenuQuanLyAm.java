package ui;

import java.util.Scanner;
import services.TeaPotService;   // Hoặc TeapotService tùy tên class
import models.TeaPot;           // Hoặc Teapot

public class MenuQuanLyAm {
    private TeaPotService teapotService;
    private Scanner scanner = new Scanner(System.in);

    public MenuQuanLyAm(TeaPotService teapotService) {
        this.teapotService = teapotService;
    }

    public void hienThi() {
        int choice;
        do {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("          MENU QUẢN LÝ ẤM");
            System.out.println("=".repeat(40));
            System.out.println("1. Thêm ấm mới");
            System.out.println("2. Xem danh sách ấm");
            System.out.println("3. Tìm kiếm ấm");
            System.out.println("4. Cập nhật ấm");
            System.out.println("5. Xóa ấm");
            System.out.println("0. Quay lại Menu Chính");
            System.out.println("=".repeat(40));
            System.out.print("Chọn chức năng: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: teapotService.themAm(); break;
                case 2: teapotService.hienThiTatCa(); break;
                case 3: teapotService.timKiem(); break;
                case 4: teapotService.capNhat(); break;
                case 5: teapotService.xoa(); break;
                case 0: System.out.println("← Quay lại..."); break;
                default: System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}
