import java.util.ArrayList;
import java.util.List;

public class BrewingRecipeRepository {
    private List<BrewingRecipe> recipes = new ArrayList<>();

    public List<BrewingRecipe> getAll() {
        return recipes;
    }
}
