import java.util.List;

public interface ITeaService extends IBaseService<Tea> {
    List<Tea> findByType(TeaType type);
    List<Tea> findLowStock(int minGram);
    boolean importTea(String teaId, int gram);
    boolean exportTea(String teaId, int gram);
    double calculateTeaPrice(String teaId, int gram);
}
