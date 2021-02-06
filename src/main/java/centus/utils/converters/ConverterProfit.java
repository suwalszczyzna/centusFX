package centus.utils.converters;

import centus.database.model.Profit;
import centus.utils.exceptions.ApplicationException;
import centus.utils.users.UserUtils;
import centus.viewmodel.profitModels.ProfitFX;

public class ConverterProfit {
    public static Profit convertToProfit(ProfitFX profitFX) throws ApplicationException {
        Profit profit = new Profit();
        profit.setId(profitFX.getId());
        profit.setDate(ConverterDate.convertToDate(profitFX.getDate()));
        profit.setAmount(Double.parseDouble(profitFX.getAmount().replace(',', '.')));
        profit.setProfitCategory(ConverterProfitCategory.convertToProfitCategory(profitFX.getProfitCategory()));
        profit.setUser(UserUtils.getLoggedUser());
        return profit;
    }

    public static ProfitFX convertToProfitFx(Profit profit){
        ProfitFX profitFX = new ProfitFX();
        profitFX.setId(profit.getId());
        profitFX.setDate(ConverterDate.convertToLocalDate(profit.getDate()));
        profitFX.setAmount(String.valueOf(profit.getAmount()));
        profitFX.setProfitCategory(ConverterProfitCategory.convertToProfitCategoryFx(profit.getProfitCategory()));
        return profitFX;
    }
}
