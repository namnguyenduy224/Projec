import java.util.ArrayList;
import java.util.List;

public class BrewingService implements IBrewingService {
    private final BrewingRecipeRepository brewingRecipeRepository;

    public BrewingService(BrewingRecipeRepository brewingRecipeRepository) {
        this.brewingRecipeRepository = brewingRecipeRepository;
    }

    @Override
    public BrewingRecipe createRecipe(
            Tea tea,
            Teapot teapot,
            int teaGram,
            int waterMl,
            int temperature,
            int timeSecond
    ) {
        if (tea == null || teapot == null) {
            throw new IllegalArgumentException("Tra va am khong duoc null");
        }

        if (teaGram <= 0 || waterMl <= 0 || temperature <= 0 || timeSecond <= 0) {
            throw new IllegalArgumentException("Thong so pha tra phai lon hon 0");
        }

        BrewingRecipe recipe = new BrewingRecipe(
                tea,
                teapot,
                teaGram,
                waterMl,
                temperature,
                timeSecond
        );

        brewingRecipeRepository.getAll().add(recipe);
        return recipe;
    }

    @Override
    public List<BrewingRecipe> getRecipesByTeaType(TeaType teaType) {
        List<BrewingRecipe> result = new ArrayList<>();

        if (teaType == null) {
            return result;
        }

        for (BrewingRecipe recipe : brewingRecipeRepository.getAll()) {
            if (recipe.getTea() != null && recipe.getTea().getType().equalsIgnoreCase(teaType.toString())) {
                result.add(recipe);
            }
        }

        return result;
    }

    @Override
    public String getBrewingGuide(Tea tea) {
        if (tea == null || tea.getType() == null) {
            return "Khong co thong tin tra de goi y cach pha.";
        }

        String type = tea.getType().toUpperCase();

        switch (type) {
            case "LUC_TRA":
                return "Luc tra: dung 5g tra, 150ml nuoc, 75-85 do C, ham 25-35 giay.";
            case "HONG_TRA":
                return "Hong tra: dung 6g tra, 150ml nuoc, 90-95 do C, ham 40-60 giay.";
            case "BACH_TRA":
                return "Bach tra: dung 5g tra, 180ml nuoc, 80-90 do C, ham 60-90 giay.";
            case "HOANG_TRA":
                return "Hoang tra: dung 5g tra, 150ml nuoc, 80-85 do C, ham 45-60 giay.";
            case "O_LONG":
                return "O Long: dung 7-8g tra, 120ml nuoc, 90-100 do C, ham 30-50 giay.";
            case "PHO_NHI_SONG":
                return "Pho Nhi song: dung 7-8g tra, 150ml nuoc, 95-100 do C, trang tra nhanh roi ham 10-20 giay lan dau.";
            case "PHO_NHI_CHIN":
                return "Pho Nhi chin: dung 7-8g tra, 150ml nuoc, 95-100 do C, trang tra nhanh roi ham 20-40 giay.";
            default:
                return "Chua co huong dan pha cho loai tra nay.";
        }
    }

    @Override
    public boolean isValidRecipe(BrewingRecipe recipe) {
        if (recipe == null) {
            return false;
        }

        if (recipe.getTea() == null || recipe.getTeapot() == null) {
            return false;
        }

        if (recipe.getTeaGram() <= 0) {
            return false;
        }

        if (recipe.getWaterMl() <= 0) {
            return false;
        }

        if (recipe.getTemperature() <= 0) {
            return false;
        }

        return recipe.getTimeSecond() > 0;
    }
}
