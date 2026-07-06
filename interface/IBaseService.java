import java.util.List;

public interface IBaseService<T> {
    void add(T item);
    boolean update(String id, T item);
    boolean delete(String id);
    T findById(String id);
    List<T> findAll();
    List<T> search(String keyword);
}
