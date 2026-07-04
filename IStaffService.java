import java.util.List;

public interface IStaffService extends IBaseService<Staff> {
    List<Staff> findByRole(StaffRole role);
    List<Staff> findByStatus(StaffStatus status);
    List<Staff> findActiveStaff();
}
