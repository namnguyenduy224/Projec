import java.util.Map;

public interface IStatisticService {
    double getTotalRevenue();
    int getTotalInvoiceCount();
    int getTotalSessionCount();
    int getTotalTeaUsedGram();
    Map<String, Integer> getTeaUsageStatistic();
    Map<String, Double> getStaffRevenueStatistic();
    String getBestSellingTea();
}
