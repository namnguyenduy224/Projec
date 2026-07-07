package ui;

import services.TeaService;
import services.TeaPotService;

public class MainMenu {
    private TeaService teaService = new TeaService();
    private TeaPotService teapotService = new TeaPotService();

    private MenuQuanLyTra menuTra = new MenuQuanLyTra(teaService);
    private MenuQuanLyAm menuAm = new MenuQuanLyAm(teapotService);

    public void hienThiMenuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("          HỆ THỐNG QUẢN LÝ TRÀ & ẤM");
            System.out.println("=".repeat(50));
            System.out.println("1. Quản lý Trà");
            System.out.println("2. Quản lý Ấm");
            System.out.println("0. Thoát chương trình");
            System.out.println("=".repeat(50));
            System.out.print("Chọn: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: menuTra.hienThi(); break;
                case 2: menuAm.hienThi(); break;
                case 0: System.out.println("Cảm ơn bạn đã sử dụng! 👋"); break;
                default: System.out.println("❌ Lựa chọn sai!");
            }
        } while (choice != 0);
    }
}
