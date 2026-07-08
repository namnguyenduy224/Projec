import java.util.ArrayList;
import java.util.List;

public class TeaService implements ITeaService {
    private final TeaRepository teaRepository;

    public TeaService(TeaRepository teaRepository) {
        this.teaRepository = teaRepository;
    }

    @Override
    public void add(Tea tea) {
        if (tea == null) {
            throw new IllegalArgumentException("Tra khong duoc null");
        }

        if (findById(tea.getId()) != null) {
            throw new IllegalArgumentException("Ma tra da ton tai: " + tea.getId());
        }

        teaRepository.getAll().add(tea);
    }

    @Override
    public boolean update(String id, Tea newTea) {
        Tea oldTea = findById(id);

        if (oldTea == null || newTea == null) {
            return false;
        }

        oldTea.setName(newTea.getName());
        oldTea.setType(newTea.getType());
        oldTea.setYear(newTea.getYear());
        oldTea.setStockGram(newTea.getStockGram());
        oldTea.setPricePer100Gram(newTea.getPricePer100Gram());

        return true;
    }

    @Override
    public boolean delete(String id) {
        Tea tea = findById(id);

        if (tea == null) {
            return false;
        }

        return teaRepository.getAll().remove(tea);
    }

    @Override
    public Tea findById(String id) {
        if (Validator.isEmpty(id)) {
            return null;
        }

        for (Tea tea : teaRepository.getAll()) {
            if (tea.getId().equalsIgnoreCase(id.trim())) {
                return tea;
            }
        }

        return null;
    }

    @Override
    public List<Tea> findAll() {
        return new ArrayList<>(teaRepository.getAll());
    }

    @Override
    public List<Tea> search(String keyword) {
        List<Tea> result = new ArrayList<>();

        if (Validator.isEmpty(keyword)) {
            return result;
        }

        String key = keyword.toLowerCase();

        for (Tea tea : teaRepository.getAll()) {
            String data = (
                    tea.getId() + " " +
                    tea.getName() + " " +
                    tea.getType().getDisplayName() + " " +
                    tea.getYear()
            ).toLowerCase();

            if (data.contains(key)) {
                result.add(tea);
            }
        }

        return result;
    }

    @Override
    public List<Tea> findByType(TeaType type) {
        List<Tea> result = new ArrayList<>();

        if (type == null) {
            return result;
        }

        for (Tea tea : teaRepository.getAll()) {
            if (tea.getType() == type) {
                result.add(tea);
            }
        }

        return result;
    }

    @Override
    public List<Tea> findLowStock(int minGram) {
        List<Tea> result = new ArrayList<>();

        for (Tea tea : teaRepository.getAll()) {
            if (tea.getStockGram() <= minGram) {
                result.add(tea);
            }
        }

        return result;
    }

    @Override
    public boolean importTea(String teaId, int gram) {
        Tea tea = findById(teaId);

        if (tea == null || gram <= 0) {
            return false;
        }

        tea.increaseStock(gram);
        return true;
    }

    @Override
    public boolean exportTea(String teaId, int gram) {
        Tea tea = findById(teaId);

        if (tea == null || gram <= 0 || tea.getStockGram() < gram) {
            return false;
        }

        tea.decreaseStock(gram);
        return true;
    }

    @Override
    public double calculateTeaPrice(String teaId, int gram) {
        Tea tea = findById(teaId);

        if (tea == null || gram <= 0) {
            return 0;
        }

        return tea.calculatePrice(gram);
    }
}
