package ui;
import java.util.Scanner;
import services.SearchService;

public class MenuTimKiem {
    private SearchService searchService;
    private Scanner sc = new Scanner(System.in);

    public MenuTimKiem(SearchService searchService) {
        this.searchService = searchService;
    }

    public void hienThi() {
        int choice;
        do {
            Utils.printHeader("MENU TÌM KIẾM NÂNG CAO");
            Utils.printOption(1, "Tìm trà theo tên/loại");
            Utils.printOption(2, "Tìm ấm theo tên/dung tích");
            Utils.printOption(3, "Tìm theo nguyên liệu");
            Utils.printOption(4, "Tìm công thức pha");
            Utils.printOption(5, "Tìm kiếm nâng cao (kết hợp)");
            Utils.printOption(0, "Quay lại Menu Chính");
            Utils.printFooter();
            System.out.print("Chọn → ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1: searchService.timTra(); break;
                case 2: searchService.timAm(); break;
                case 3: searchService.timNguyenLieu(); break;
                case 4: searchService.timCongThuc(); break;
                case 5: searchService.timKiemNangCao(); break;
                case 0: System.out.println(Utils.GREEN + "← Quay lại..." + Utils.RESET); break;
                default: System.out.println(Utils.RED + "❌ Sai lựa chọn!" + Utils.RESET);
            }
        } while (choice != 0);
    }
}
