package ui;
import java.util.Scanner;
import services.TeaPotService;
import models.TeaPot;
import java.util.List;

public class MenuQuanLyAm {
    private TeaPotService teapotService;
    private Scanner sc = new Scanner(System.in);

    public MenuQuanLyAm(TeaPotService teapotService) { this.teapotService = teapotService; }

    public void hienThi() {
        int choice;
        do {
            Utils.printHeader("MENU QUẢN LÝ ẤM");
            Utils.printOption(1, "Thêm ấm mới");
            Utils.printOption(2, "Xem danh sách ấm (phân trang)");
            Utils.printOption(3, "Tìm kiếm ấm");
            Utils.printOption(4, "Cập nhật ấm");
            Utils.printOption(5, "Xóa ấm");
            Utils.printOption(0, "Quay lại Menu Chính");
            Utils.printFooter();
            System.out.print("Chọn → ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1: teapotService.themAm(); break;
                case 2: xemDanhSachPhanTrang(); break;
                case 3: teapotService.timKiem(); break;
                case 4: teapotService.capNhat(); break;
                case 5: teapotService.xoa(); break;
                case 0: System.out.println(Utils.GREEN + "← Quay lại..." + Utils.RESET); break;
                default: System.out.println(Utils.RED + "❌ Sai lựa chọn!" + Utils.RESET);
            }
        } while (choice != 0);
    }

    private void xemDanhSachPhanTrang() {
        List<TeaPot> list = teapotService.getAll();
        int pageSize = 8, totalPages = (int) Math.ceil((double) list.size() / pageSize), page = 1;
        while (true) {
            Utils.clearScreen();
            Utils.printHeader("DANH SÁCH ẤM - TRANG " + page);
            int start = (page-1)*pageSize;
            int end = Math.min(start + pageSize, list.size());
            for (int i = start; i < end; i++) {
                TeaPot a = list.get(i);
                System.out.printf("%-4d | %-20s | %-15s | %6dml%n", i+1, a.getTen(), a.getLoai(), a.getDungTich());
            }
            Utils.printFooter();
            Utils.printPagination(page, totalPages);
            String input = sc.nextLine().trim();
            if (input.equals("0")) break;
            page = input.isEmpty() ? page + 1 : Integer.parseInt(input);
            if (page > totalPages) page = 1;
        }
    }
}
