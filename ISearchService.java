import java.util.List;

public interface ISearchService {
    List<Tea> searchTea(String keyword);
    List<Teapot> searchTeapot(String keyword);
    List<Utensil> searchUtensil(String keyword);
    List<Customer> searchCustomer(String keyword);
    List<Staff> searchStaff(String keyword);
    List<Invoice> searchInvoice(String keyword);
}
