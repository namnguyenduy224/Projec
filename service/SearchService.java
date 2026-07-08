import java.util.ArrayList;
import java.util.List;

public class SearchService implements ISearchService {
    private List teas;
    private List teapots;
    private List utensils;
    private List customers;
    private List staffs;
    private List invoices;

    public SearchService(
            List teas,
            List teapots,
            List utensils,
            List customers,
            List staffs,
            List invoices
    ) {
        this.teas = teas;
        this.teapots = teapots;
        this.utensils = utensils;
        this.customers = customers;
        this.staffs = staffs;
        this.invoices = invoices;
    }

    @Override
    public List searchTea(String keyword) {
        return searchInList(teas, keyword);
    }

    @Override
    public List searchTeapot(String keyword) {
        return searchInList(teapots, keyword);
    }

    @Override
    public List searchUtensil(String keyword) {
        return searchInList(utensils, keyword);
    }

    @Override
    public List searchCustomer(String keyword) {
        return searchInList(customers, keyword);
    }

    @Override
    public List searchStaff(String keyword) {
        return searchInList(staffs, keyword);
    }

    @Override
    public List searchInvoice(String keyword) {
        return searchInList(invoices, keyword);
    }

    private List searchInList(List source, String keyword) {
        List result = new ArrayList();

        if (source == null || keyword == null) {
            return result;
        }

        String key = normalize(keyword);

        if (key.isEmpty()) {
            return result;
        }

        for (Object item : source) {
            if (item != null && normalize(item.toString()).contains(key)) {
                result.add(item);
            }
        }

        return result;
    }

    private String normalize(String text) {
        return text == null ? "" : text.trim().toLowerCase();
    }
}
