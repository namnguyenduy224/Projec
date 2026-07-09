package ui;

import java.util.Scanner;
import services.SearchService;

public class MenuTimKiem {
    private SearchService searchService;
    private Scanner scanner = new Scanner(System.in);

    public MenuTimKiem(SearchService searchService) {
        this.searchService = searchService;
    }

    public void hienThi() {
        int choice;
        do {
            System.out.println("\n" + "=".repeat(45));
            System.out.println("              MENU TÌM KIẾM");
            System.out.println("=".repeat(45));
            System.out.println("1. Tìm trà theo tên");
            System.out.println("2. Tìm ấm theo tên/loại");
            System.out.println("3. Tìm theo nguyên liệu");
            System.out.println("4. Tìm công thức pha");
            System.out.println("5. Tìm kiếm nâng cao");
            System.out.println("0. Quay lại Menu Chính");
            System.out.println("=".repeat(45));
            System.out.print("Chọn chức năng: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: searchService.timTra(); break;
                case 2: searchService.timAm(); break;
                case 3: searchService.timNguyenLieu(); break;
                case 4: searchService.timCongThuc(); break;
                case 5: searchService.timKiemNangCao(); break;
                case 0: System.out.println("← Quay lại..."); break;
                default: System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}
