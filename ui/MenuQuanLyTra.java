package ui;
import java.util.Scanner;
import services.TeaService;
import models.Tea;
import java.util.List;

public class MenuQuanLyTra {
    private TeaService teaService;
    private Scanner sc = new Scanner(System.in);

    public MenuQuanLyTra(TeaService teaService) { this.teaService = teaService; }

    public void hienThi() {
        int choice;
        do {
            Utils.printHeader("MENU QUẢN LÝ TRÀ");
            Utils.printOption(1, "Thêm trà mới");
            Utils.printOption(2, "Xem danh sách trà (phân trang)");
            Utils.printOption(3, "Tìm kiếm trà");
            Utils.printOption(4, "Cập nhật trà");
            Utils.printOption(5, "Xóa trà");
            Utils.printOption(0, "Quay lại Menu Chính");
            Utils.printFooter();
            System.out.print("Chọn → ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1: teaService.themTra(); break;
                case 2: xemDanhSachPhanTrang(); break;
                case 3: teaService.timKiem(); break;
                case 4: teaService.capNhat(); break;
                case 5: teaService.xoa(); break;
                case 0: System.out.println(Utils.GREEN + "← Quay lại..." + Utils.RESET); break;
                default: System.out.println(Utils.RED + "❌ Sai lựa chọn!" + Utils.RESET);
            }
        } while (choice != 0);
    }

    private void xemDanhSachPhanTrang() {
        List<Tea> list = teaService.getAll();
        int pageSize = 8, totalPages = (int) Math.ceil((double) list.size() / pageSize), page = 1;

        while (true) {
            Utils.clearScreen();
            Utils.printHeader("DANH SÁCH TRÀ - TRANG " + page);
            int start = (page-1)*pageSize;
            int end = Math.min(start + pageSize, list.size());

            for (int i = start; i < end; i++) {
                Tea t = list.get(i);
                System.out.printf("%-4d | %-22s | %-12s | %8.0fđ%n", i+1, t.getTen(), t.getLoai(), t.getGia());
            }
            Utils.printFooter();
            Utils.printPagination(page, totalPages);

            String input = sc.nextLine().trim();
            if (input.equals("0")) break;
            if (!input.isEmpty()) {
                try { page = Integer.parseInt(input); if (page < 1 || page > totalPages) page = 1; } 
                catch (Exception e) { page++; if (page > totalPages) page = 1; }
            } else page++;
            if (page > totalPages) page = 1;
        }
    }
}
