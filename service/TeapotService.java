import java.util.ArrayList;
import java.util.List;

public class TeapotService implements ITeapotService {
    private final TeapotRepository teapotRepository;

    public TeapotService(TeapotRepository teapotRepository) {
        this.teapotRepository = teapotRepository;
    }

    @Override
    public void add(Teapot teapot) {
        if (teapot == null) {
            throw new IllegalArgumentException("Am tra khong duoc null");
        }

        if (findById(teapot.getId()) != null) {
            throw new IllegalArgumentException("Ma am da ton tai: " + teapot.getId());
        }

        teapotRepository.getAll().add(teapot);
    }

    @Override
    public boolean update(String id, Teapot newTeapot) {
        Teapot oldTeapot = findById(id);

        if (oldTeapot == null || newTeapot == null) {
            return false;
        }

        oldTeapot.setName(newTeapot.getName());
        oldTeapot.setClayType(newTeapot.getClayType());
        oldTeapot.setDesign(newTeapot.getDesign());
        oldTeapot.setCapacityMl(newTeapot.getCapacityMl());
        oldTeapot.setQuantity(newTeapot.getQuantity());
        oldTeapot.setRentalFee(newTeapot.getRentalFee());

        return true;
    }

    @Override
    public boolean delete(String id) {
        Teapot teapot = findById(id);

        if (teapot == null) {
            return false;
        }

        return teapotRepository.getAll().remove(teapot);
    }

    @Override
    public Teapot findById(String id) {
        if (Validator.isEmpty(id)) {
            return null;
        }

        for (Teapot teapot : teapotRepository.getAll()) {
            if (teapot.getId().equalsIgnoreCase(id.trim())) {
                return teapot;
            }
        }

        return null;
    }

    @Override
    public List<Teapot> findAll() {
        return new ArrayList<>(teapotRepository.getAll());
    }

    @Override
    public List<Teapot> search(String keyword) {
        List<Teapot> result = new ArrayList<>();

        if (Validator.isEmpty(keyword)) {
            return result;
        }

        String key = keyword.toLowerCase();

        for (Teapot teapot : teapotRepository.getAll()) {
            String data = (
                    teapot.getId() + " " +
                    teapot.getName() + " " +
                    teapot.getClayType().getDisplayName() + " " +
                    teapot.getDesign().getDisplayName() + " " +
                    teapot.getCapacityMl()
            ).toLowerCase();

            if (data.contains(key)) {
                result.add(teapot);
            }
        }

        return result;
    }

    @Override
    public List<Teapot> findByClayType(ClayType clayType) {
        List<Teapot> result = new ArrayList<>();

        if (clayType == null) {
            return result;
        }

        for (Teapot teapot : teapotRepository.getAll()) {
            if (teapot.getClayType() == clayType) {
                result.add(teapot);
            }
        }

        return result;
    }

    @Override
    public List<Teapot> findByDesign(TeapotDesign design) {
        List<Teapot> result = new ArrayList<>();

        if (design == null) {
            return result;
        }

        for (Teapot teapot : teapotRepository.getAll()) {
            if (teapot.getDesign() == design) {
                result.add(teapot);
            }
        }

        return result;
    }

    @Override
    public List<Teapot> findByCapacity(int capacityMl) {
        List<Teapot> result = new ArrayList<>();

        for (Teapot teapot : teapotRepository.getAll()) {
            if (teapot.getCapacityMl() == capacityMl) {
                result.add(teapot);
            }
        }

        return result;
    }

    @Override
    public List<Teapot> findAvailableTeapots() {
        List<Teapot> result = new ArrayList<>();

        for (Teapot teapot : teapotRepository.getAll()) {
            if (teapot.getQuantity() > 0) {
                result.add(teapot);
            }
        }

        return result;
    }
}
