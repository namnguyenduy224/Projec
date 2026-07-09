package ui;

import java.util.Scanner;
import services.StatisticService;   // Hoặc IStatisticService

public class MenuThongKe {
    private StatisticService statisticService;
    private Scanner scanner = new Scanner(System.in);

    public MenuThongKe(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    public void hienThi() {
        int choice;
        do {
            System.out.println("\n" + "=".repeat(45));
            System.out.println("              MENU THỐNG KÊ");
            System.out.println("=".repeat(45));
            System.out.println("1. Thống kê tồn kho");
            System.out.println("2. Thống kê số lượng trà/ấm");
            System.out.println("3. Thống kê công thức phổ biến");
            System.out.println("4. Báo cáo doanh thu (nếu có)");
            System.out.println("5. Xuất báo cáo thống kê");
            System.out.println("0. Quay lại Menu Chính");
            System.out.println("=".repeat(45));
            System.out.print("Chọn chức năng: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: statisticService.thongKeTonKho(); break;
                case 2: statisticService.thongKeSoLuong(); break;
                case 3: statisticService.thongKeCongThuc(); break;
                case 4: statisticService.baoCaoDoanhThu(); break;
                case 5: statisticService.xuatBaoCao(); break;
                case 0: System.out.println("← Quay lại..."); break;
                default: System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}
