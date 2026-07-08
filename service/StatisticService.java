import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticService implements IStatisticService {
    private List invoices;
    private List sessions;

    public StatisticService(List invoices, List sessions) {
        this.invoices = invoices;
        this.sessions = sessions;
    }

    @Override
    public double getTotalRevenue() {
        double total = 0;

        if (invoices == null) {
            return total;
        }

        for (Object invoice : invoices) {
            total += getInvoiceTotal(invoice);
        }

        return total;
    }

    @Override
    public int getTotalInvoiceCount() {
        return invoices == null ? 0 : invoices.size();
    }

    @Override
    public int getTotalSessionCount() {
        return sessions == null ? 0 : sessions.size();
    }

    @Override
    public int getTotalTeaUsedGram() {
        int total = 0;

        if (sessions == null) {
            return total;
        }

        for (Object session : sessions) {
            total += getTeaGram(session);
        }

        return total;
    }

    @Override
    public Map getTeaUsageStatistic() {
        Map result = new HashMap();

        if (sessions == null) {
            return result;
        }

        for (Object session : sessions) {
            String teaName = getTeaNameFromSession(session);
            int gram = getTeaGram(session);

            Integer oldValue = (Integer) result.get(teaName);
            if (oldValue == null) {
                oldValue = 0;
            }

            result.put(teaName, oldValue + gram);
        }

        return result;
    }

    @Override
    public Map getStaffRevenueStatistic() {
        Map result = new HashMap();

        if (invoices == null) {
            return result;
        }

        for (Object invoice : invoices) {
            String staffName = getStaffNameFromInvoice(invoice);
            double revenue = getInvoiceTotal(invoice);

            Double oldValue = (Double) result.get(staffName);
            if (oldValue == null) {
                oldValue = 0.0;
            }

            result.put(staffName, oldValue + revenue);
        }

        return result;
    }

    @Override
    public String getBestSellingTea() {
        Map statistic = getTeaUsageStatistic();

        if (statistic.isEmpty()) {
            return "Chua co du lieu";
        }

        String bestTea = "Chua co du lieu";
        int maxGram = -1;

        for (Object key : statistic.keySet()) {
            String teaName = String.valueOf(key);
            Integer gram = (Integer) statistic.get(key);

            if (gram != null && gram > maxGram) {
                maxGram = gram;
                bestTea = teaName;
            }
        }

        return bestTea;
    }

    private double getInvoiceTotal(Object invoice) {
        Object value = callFirstExistingMethod(
                invoice,
                "getTotal",
                "getTotalAmount",
                "calculateTotal",
                "getAmount",
                "getTotalPrice"
        );

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        return 0;
    }

    private int getTeaGram(Object session) {
        Object value = callFirstExistingMethod(
                session,
                "getTeaGram",
                "getTeaUsedGram",
                "getUsedGram",
                "getGram"
        );

        if (value instanceof Number) {
            return ((Number) value).intValue();
        }

        return 0;
    }

    private String getTeaNameFromSession(Object session) {
        Object directName = callFirstExistingMethod(
                session,
                "getTeaName",
                "getTeaType"
        );

        if (directName != null) {
            return String.valueOf(directName);
        }

        Object tea = callFirstExistingMethod(session, "getTea");

        if (tea != null) {
            Object teaName = callFirstExistingMethod(
                    tea,
                    "getName",
                    "getType",
                    "getId"
            );

            if (teaName != null) {
                return String.valueOf(teaName);
            }

            return tea.toString();
        }

        return "Khong ro";
    }

    private String getStaffNameFromInvoice(Object invoice) {
        Object directName = callFirstExistingMethod(
                invoice,
                "getStaffName",
                "getEmployeeName"
        );

        if (directName != null) {
            return String.valueOf(directName);
        }

        Object staff = callFirstExistingMethod(
                invoice,
                "getStaff",
                "getEmployee"
        );

        if (staff != null) {
            Object staffName = callFirstExistingMethod(
                    staff,
                    "getName",
                    "getFullName",
                    "getId"
            );

            if (staffName != null) {
                return String.valueOf(staffName);
            }

            return staff.toString();
        }

        return "Khong ro";
    }

    private Object callFirstExistingMethod(Object target, String... methodNames) {
        if (target == null || methodNames == null) {
            return null;
        }

        for (String methodName : methodNames) {
            try {
                Method method = target.getClass().getMethod(methodName);
                return method.invoke(target);
            } catch (Exception e) {
                // Bo qua va thu ten ham tiep theo
            }
        }

        return null;
    }
}
