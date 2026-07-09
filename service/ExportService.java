import java.util.List;
import java.util.Map;

public class ExportService implements IExportService {
    private IStatisticService statisticService;
    private Inventory inventory;

    public ExportService() {
    }

    public ExportService(IStatisticService statisticService, Inventory inventory) {
        this.statisticService = statisticService;
        this.inventory = inventory;
    }

    public void setStatisticService(IStatisticService statisticService) {
        this.statisticService = statisticService;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean exportInvoiceToTxt(Invoice invoice, String filePath) {
        if (invoice == null || isEmpty(filePath)) {
            return false;
        }

        StringBuilder content = new StringBuilder();
        content.append("===== HOA DON =====\n");
        content.append(invoice.toString()).append("\n");

        return FileHelper.writeText(filePath, content.toString());
    }

    @Override
    public boolean exportStatisticToTxt(String filePath) {
        if (isEmpty(filePath)) {
            return false;
        }

        StringBuilder content = new StringBuilder();
        content.append("===== BAO CAO THONG KE =====\n");

        if (statisticService == null) {
            content.append("Chua co StatisticService de lay du lieu thong ke.\n");
            return FileHelper.writeText(filePath, content.toString());
        }

        content.append("Tong doanh thu: ").append(statisticService.getTotalRevenue()).append("\n");
        content.append("Tong so hoa don: ").append(statisticService.getTotalInvoiceCount()).append("\n");
        content.append("Tong so buoi thuong tra: ").append(statisticService.getTotalSessionCount()).append("\n");
        content.append("Tong gram tra da dung: ").append(statisticService.getTotalTeaUsedGram()).append("\n");
        content.append("Tra ban/chay nhat: ").append(statisticService.getBestSellingTea()).append("\n");

        content.append("\n--- Thong ke tra ---\n");
        appendMap(content, statisticService.getTeaUsageStatistic());

        content.append("\n--- Thong ke doanh thu nhan vien ---\n");
        appendMap(content, statisticService.getStaffRevenueStatistic());

        return FileHelper.writeText(filePath, content.toString());
    }

    @Override
    public boolean exportInventoryToTxt(String filePath) {
        if (isEmpty(filePath)) {
            return false;
        }

        StringBuilder content = new StringBuilder();
        content.append("===== BAO CAO KHO HANG =====\n");

        if (inventory == null) {
            content.append("Chua co Inventory de lay du lieu kho.\n");
            return FileHelper.writeText(filePath, content.toString());
        }

        content.append("\n--- Danh sach tra ---\n");
        appendList(content, inventory.getTeas());

        content.append("\n--- Danh sach am tra ---\n");
        appendList(content, inventory.getTeapots());

        return FileHelper.writeText(filePath, content.toString());
    }

    private void appendList(StringBuilder content, List list) {
        if (list == null || list.isEmpty()) {
            content.append("Khong co du lieu.\n");
            return;
        }

        for (Object item : list) {
            content.append("- ").append(item).append("\n");
        }
    }

    private void appendMap(StringBuilder content, Map map) {
        if (map == null || map.isEmpty()) {
            content.append("Khong co du lieu.\n");
            return;
        }

        for (Object key : map.keySet()) {
            content.append("- ").append(key).append(": ").append(map.get(key)).append("\n");
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
