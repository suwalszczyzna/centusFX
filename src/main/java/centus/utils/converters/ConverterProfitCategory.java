package centus.utils.converters;

import centus.database.model.ProfitCategory;
import centus.viewmodel.profitModels.ProfitCategoryFX;

public class ConverterProfitCategory {
    public static ProfitCategory convertToProfitCategory(ProfitCategoryFX profitCategoryFX){
        ProfitCategory profitCategory = new ProfitCategory();
        profitCategory.setId(profitCategoryFX.getId());
        profitCategory.setName(profitCategoryFX.getName());
        return profitCategory;
    }

    public static ProfitCategoryFX convertToProfitCategoryFx(ProfitCategory profitCategory){
        ProfitCategoryFX profitCategoryFX = new ProfitCategoryFX();
        profitCategoryFX.setId(profitCategory.getId());
        profitCategoryFX.setName(profitCategory.getName());
        return profitCategoryFX;
    }
}
