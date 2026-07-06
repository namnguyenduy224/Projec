import java.util.List;

public interface IInventoryService {
    boolean addStock(String itemId, int quantity);
    boolean reduceStock(String itemId, int quantity);
    boolean checkStock(String itemId, int quantity);
    List<Inventory> getLowStockItems(int minQuantity);
    List<Inventory> getAllInventory();
}
