import java.util.List;

public interface ITeapotService extends IBaseService<Teapot> {
    List<Teapot> findByClayType(ClayType clayType);
    List<Teapot> findByDesign(TeapotDesign design);
    List<Teapot> findByCapacity(int capacityMl);
    List<Teapot> findAvailableTeapots();
}
