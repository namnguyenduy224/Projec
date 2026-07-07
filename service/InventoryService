import java.util.ArrayList;
import java.util.List;

public class InventoryService implements IInventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public boolean addStock(String itemId, int quantity) {
        if (Validator.isEmpty(itemId) || quantity <= 0) {
            return false;
        }

        Inventory inventory = findInventoryByItemId(itemId);

        if (inventory == null) {
            return false;
        }

        inventory.setQuantity(inventory.getQuantity() + quantity);
        return true;
    }

    @Override
    public boolean reduceStock(String itemId, int quantity) {
        if (Validator.isEmpty(itemId) || quantity <= 0) {
            return false;
        }

        Inventory inventory = findInventoryByItemId(itemId);

        if (inventory == null) {
            return false;
        }

        if (inventory.getQuantity() < quantity) {
            return false;
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        return true;
    }

    @Override
    public boolean checkStock(String itemId, int quantity) {
        if (Validator.isEmpty(itemId) || quantity <= 0) {
            return false;
        }

        Inventory inventory = findInventoryByItemId(itemId);

        if (inventory == null) {
            return false;
        }

        return inventory.getQuantity() >= quantity;
    }

    @Override
    public List<Inventory> getLowStockItems(int minQuantity) {
        List<Inventory> result = new ArrayList<>();

        for (Inventory inventory : inventoryRepository.getAll()) {
            if (inventory.getQuantity() <= minQuantity) {
                result.add(inventory);
            }
        }

        return result;
    }

    @Override
    public List<Inventory> getAllInventory() {
        return new ArrayList<>(inventoryRepository.getAll());
    }

    private Inventory findInventoryByItemId(String itemId) {
        for (Inventory inventory : inventoryRepository.getAll()) {
            if (inventory.getItemId().equalsIgnoreCase(itemId.trim())) {
                return inventory;
            }
        }

        return null;
    }
}
