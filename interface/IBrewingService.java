import java.util.List;

public interface IBrewingService {
    BrewingRecipe createRecipe(Tea tea, Teapot teapot, int teaGram, int waterMl, int temperature, int timeSecond);
    List<BrewingRecipe> getRecipesByTeaType(TeaType teaType);
    String getBrewingGuide(Tea tea);
    boolean isValidRecipe(BrewingRecipe recipe);
}
