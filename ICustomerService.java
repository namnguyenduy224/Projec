import java.util.List;

public interface ICustomerService extends IBaseService<Customer> {
    Customer findByPhone(String phone);
    boolean addPoint(String customerId, int point);
    boolean usePoint(String customerId, int point);
    List<Customer> sortByPointDescending();
}
