package ui;
import java.util.Scanner;
import services.InventoryService;
import java.util.List;

public class MenuKhoHang {
    private InventoryService inventoryService;
    private Scanner sc = new Scanner(System.in);

    public MenuKhoHang(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void hienThi() {
        int choice;
        do {
            Utils.printHeader("MENU QUẢN LÝ KHO HÀNG");
            Utils.printOption(1, "Xem tồn kho hiện tại (phân trang)");
            Utils.printOption(2, "Nhập kho nguyên liệu");
            Utils.printOption(3, "Xuất kho nguyên liệu");
            Utils.printOption(4, "Cảnh báo nguyên liệu sắp hết");
            Utils.printOption(5, "Lịch sử nhập/xuất");
            Utils.printOption(0, "Quay lại Menu Chính");
            Utils.printFooter();
            System.out.print("Chọn → ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1: xemTonKhoPhanTrang(); break;
                case 2: inventoryService.nhapKho(); break;
                case 3: inventoryService.xuatKho(); break;
                case 4: inventoryService.canhBaoHetHang(); break;
                case 5: inventoryService.xemLichSu(); break;
                case 0: System.out.println(Utils.GREEN + "← Quay lại..." + Utils.RESET); break;
                default: System.out.println(Utils.RED + "❌ Sai lựa chọn!" + Utils.RESET);
            }
        } while (choice != 0);
    }

    private void xemTonKhoPhanTrang() {
        List<?> list = inventoryService.getAll(); // điều chỉnh generic nếu cần
        int pageSize = 8, totalPages = (int) Math.ceil((double) list.size() / pageSize), page = 1;
        while (true) {
            Utils.clearScreen();
            Utils.printHeader("TỒN KHO HIỆN TẠI - TRANG " + page);
            // In dữ liệu tồn kho (bạn điều chỉnh theo model Inventory)
            // Ví dụ: System.out.printf(...);
            Utils.printFooter();
            Utils.printPagination(page, totalPages);
            String input = sc.nextLine().trim();
            if (input.equals("0")) break;
            page = input.isEmpty() ? page + 1 : Integer.parseInt(input);
            if (page > totalPages) page = 1;
        }
    }
}
