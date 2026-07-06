import java.util.List;

public interface ISessionService extends IBaseService<TeaSession> {
    TeaSession createSession(Customer customer, Staff staff, Tea tea, Teapot teapot, List<Utensil> utensils);
    boolean finishSession(String sessionId);
    boolean cancelSession(String sessionId);
    double calculateSessionTotal(String sessionId);
    List<TeaSession> findByCustomer(String customerId);
}
