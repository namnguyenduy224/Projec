import java.util.List;

public interface IUtensilService extends IBaseService<Utensil> {
    List<Utensil> findByType(UtensilType type);
    List<Utensil> findAvailableUtensils();
    boolean checkQuantity(String utensilId, int quantity);
}
