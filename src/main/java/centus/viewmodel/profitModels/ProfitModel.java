package centus.viewmodel.profitModels;

import centus.database.dao.CommonDao;
import centus.database.dao.ProfitCategoryDao;
import centus.database.dao.ProfitDao;
import centus.database.model.Profit;
import centus.database.model.ProfitCategory;
import centus.utils.converters.ConverterProfit;
import centus.utils.converters.ConverterProfitCategory;
import centus.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ProfitModel {
    private ObjectProperty<ProfitFX> profitFxObjectProperty = new SimpleObjectProperty<>(new ProfitFX());
    private ObservableList<ProfitFX> profitFXObservableList = FXCollections.observableArrayList();
    private ObservableList<ProfitCategoryFX> profitCategoryFXObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        initProfitsList();
        initProfitsCategoryList();
    }

    private void initProfitsList() throws ApplicationException {
        CommonDao profitDao = new ProfitDao();
        List<Profit> profits = profitDao.queryForAll(Profit.class);
        this.profitFXObservableList.clear();

        profits.forEach(profit -> this.profitFXObservableList.add(ConverterProfit.convertToProfitFx(profit)));
    }

    private void initProfitsCategoryList() throws ApplicationException {
        ProfitCategoryDao profitCategoryDao = new ProfitCategoryDao();
        List<ProfitCategory> categoryList = profitCategoryDao.queryForAll(ProfitCategory.class);

        this.profitCategoryFXObservableList.clear();
        categoryList.forEach(cat -> {
            ProfitCategoryFX profitCategoryFX = ConverterProfitCategory.convertToProfitCategoryFx(cat);
            this.profitCategoryFXObservableList.add(profitCategoryFX);
        });
    }

    public void saveProfitToDb() throws ApplicationException {
        Profit profit = ConverterProfit.convertToProfit(this.getProfitFxObjectProperty());
        ProfitCategoryDao profitCategoryDao = new ProfitCategoryDao();
        ProfitCategory profitCategory = profitCategoryDao.findById(
                ProfitCategory.class, this.getProfitFxObjectProperty().getProfitCategory().getId());
        profit.setProfitCategory(profitCategory);

        ProfitDao profitDao = new ProfitDao();
        profitDao.createOrUpdate(profit);
        initProfitsList();
    }

    public ProfitFX getProfitFxObjectProperty() {
        return profitFxObjectProperty.get();
    }

    public ObjectProperty<ProfitFX> profitFxObjectPropertyProperty() {
        return profitFxObjectProperty;
    }

    public void setProfitFxObjectProperty(ProfitFX profitFXObjectProperty) {
        this.profitFxObjectProperty.set(profitFXObjectProperty);
    }

    public ObservableList<ProfitFX> getProfitFxObservableList() {
        return profitFXObservableList;
    }

    public void setProfitFxObservableList(ObservableList<ProfitFX> profitFXObservableList) {
        this.profitFXObservableList = profitFXObservableList;
    }

    public ObservableList<ProfitCategoryFX> getProfitCategoryFxObservableList() {
        return profitCategoryFXObservableList;
    }

    public void setProfitCategoryFxObservableList(ObservableList<ProfitCategoryFX> profitCategoryFXObservableList) {
        this.profitCategoryFXObservableList = profitCategoryFXObservableList;
    }

    public void deleteProfit(ProfitFX profitFX) throws ApplicationException {
        ProfitDao profitDao = new ProfitDao();
        profitDao.deleteById(Profit.class, profitFX.getId());
        initProfitsList();
    }
}
