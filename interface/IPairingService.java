import java.util.List;

public interface IPairingService {
    List<Teapot> suggestTeapotsForTea(Tea tea, List<Teapot> teapots);
    boolean isCompatible(Tea tea, Teapot teapot);
    String explainPairingRule(Tea tea, Teapot teapot);
}
