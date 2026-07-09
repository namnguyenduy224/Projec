package ui;

import java.util.Scanner;
import services.TeaService;
import services.TeaPotService;
import services.InventoryService;
import services.BrewingService;
import services.SearchService;
import services.StatisticService;

public class MainMenu {

    // Khởi tạo các service
    private TeaService teaService = new TeaService();
    private TeaPotService teapotService = new TeaPotService();
    private InventoryService inventoryService = new InventoryService();
    private BrewingService brewingService = new BrewingService();
    private SearchService searchService = new SearchService();
    private StatisticService statisticService = new StatisticService();

    // Khởi tạo các menu
    private MenuQuanLyTra menuTra = new MenuQuanLyTra(teaService);
    private MenuQuanLyAm menuAm = new MenuQuanLyAm(teapotService);
    private MenuKhoHang menuKho = new MenuKhoHang(inventoryService);
    private MenuCongThucPhaTra menuCongThuc = new MenuCongThucPhaTra(brewingService);
    private MenuTimKiem menuTimKiem = new MenuTimKiem(searchService);
    private MenuThongKe menuThongKe = new MenuThongKe(statisticService);

    public void hienThiMenuChinh() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            // Phần System.out được làm đẹp như bạn yêu cầu
            System.out.println("\n" + "=".repeat(60));
            System.out.println("          HỆ THỐNG QUẢN LÝ TRÀ & ẤM");
            System.out.println("=".repeat(60));
            System.out.println("1. Quản lý Trà");
            System.out.println("2. Quản lý Ấm");
            System.out.println("3. Quản lý Kho Hàng");
            System.out.println("4. Công thức Pha Trà");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Thống kê");
            System.out.println("0. Thoát chương trình");
            System.out.println("=".repeat(60));
            System.out.print("Chọn chức năng: ");

            choice = sc.nextInt();
            sc.nextLine();  // Xóa buffer

            switch (choice) {
                case 1:
                    menuTra.hienThi();
                    break;
                case 2:
                    menuAm.hienThi();
                    break;
                case 3:
                    menuKho.hienThi();
                    break;
                case 4:
                    menuCongThuc.hienThi();
                    break;
                case 5:
                    menuTimKiem.hienThi();
                    break;
                case 6:
                    menuThongKe.hienThi();
                    break;
                case 0:
                    System.out.println("\n" + "=".repeat(60));
                    System.out.println("          CẢM ƠN BẠN ĐÃ SỬ DỤNG!");
                    System.out.println("           Hẹn gặp lại lần sau 👋");
                    System.out.println("=".repeat(60));
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }
        } while (choice != 0);

        sc.close();
    }
}
