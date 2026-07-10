package ui;
import java.util.Scanner;
import services.StatisticService;

public class MenuThongKe {
    private StatisticService statisticService;
    private Scanner sc = new Scanner(System.in);

    public MenuThongKe(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    public void hienThi() {
        int choice;
        do {
            Utils.printHeader("MENU THỐNG KÊ");
            Utils.printOption(1, "Thống kê tồn kho");
            Utils.printOption(2, "Thống kê số lượng trà & ấm");
            Utils.printOption(3, "Công thức pha phổ biến");
            Utils.printOption(4, "Báo cáo tổng hợp");
            Utils.printOption(5, "Xuất báo cáo ra file");
            Utils.printOption(0, "Quay lại Menu Chính");
            Utils.printFooter();
            System.out.print("Chọn → ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1: statisticService.thongKeTonKho(); break;
                case 2: statisticService.thongKeSoLuong(); break;
                case 3: statisticService.thongKeCongThuc(); break;
                case 4: statisticService.baoCaoTongHop(); break;
                case 5: statisticService.xuatBaoCao(); break;
                case 0: System.out.println(Utils.GREEN + "← Quay lại..." + Utils.RESET); break;
                default: System.out.println(Utils.RED + "❌ Sai lựa chọn!" + Utils.RESET);
            }
        } while (choice != 0);
    }
}
