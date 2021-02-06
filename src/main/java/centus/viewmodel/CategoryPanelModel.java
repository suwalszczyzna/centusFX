package centus.viewmodel;

import centus.database.dao.ExpenseCategoryDao;
import centus.database.dao.ProfitCategoryDao;
import centus.database.model.ExpenseCategory;
import centus.database.model.ProfitCategory;
import centus.utils.converters.ConverterExpenseCategory;
import centus.utils.converters.ConverterProfitCategory;
import centus.utils.exceptions.ApplicationException;
import centus.viewmodel.expenseModels.ExpenseCategoryFX;
import centus.viewmodel.profitModels.ProfitCategoryFX;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CategoryPanelModel {

    private ObjectProperty<ExpenseCategoryFX> expenseCategoryFXObjectProperty = new SimpleObjectProperty<>(new ExpenseCategoryFX());
    private ObservableList<ExpenseCategoryFX> expenseCategoryFXObservableList = FXCollections.observableArrayList();

    private ObjectProperty<ProfitCategoryFX> profitCategoryFXObjectProperty = new SimpleObjectProperty<>(new ProfitCategoryFX());
    private ObservableList<ProfitCategoryFX> profitCategoryFXObservableList = FXCollections.observableArrayList();

    public void init() throws ApplicationException {
        initExpenseCategories();
        initProfitCategories();
    }

    private void initExpenseCategories() throws ApplicationException {
        ExpenseCategoryDao expenseCategoryDao = new ExpenseCategoryDao();
        List<ExpenseCategory> expenseCategoryList = expenseCategoryDao.queryForAll(ExpenseCategory.class);
        this.expenseCategoryFXObservableList.clear();

        expenseCategoryList.forEach(expenseCategory -> {
            this.expenseCategoryFXObservableList.add(ConverterExpenseCategory.convertToExpenseCategoryFx(expenseCategory));
        });
    }

    private void initProfitCategories() throws ApplicationException {
        ProfitCategoryDao profitCategoryDao = new ProfitCategoryDao();
        List<ProfitCategory> profitCategoryList = profitCategoryDao.queryForAll(ProfitCategory.class);
        this.profitCategoryFXObservableList.clear();

        profitCategoryList.forEach(profitCategory -> {
            this.profitCategoryFXObservableList.add(ConverterProfitCategory.convertToProfitCategoryFx(profitCategory));
        });

    }

    public void saveNewExpenseCategoryToDB() throws ApplicationException {
        ExpenseCategory expenseCategory = ConverterExpenseCategory.convertToExpenseCategory(this.getExpenseCategoryFXObjectProperty());
        ExpenseCategoryDao expenseCategoryDao = new ExpenseCategoryDao();
        expenseCategoryDao.createOrUpdate(expenseCategory);
        initExpenseCategories();

    }

    public void saveNewProfitCategoryToDB() throws ApplicationException {
        ProfitCategory profitCategory = ConverterProfitCategory.convertToProfitCategory(this.getProfitCategoryFXObjectProperty());
        ProfitCategoryDao profitCategoryDao = new ProfitCategoryDao();
        profitCategoryDao.createOrUpdate(profitCategory);
        initProfitCategories();
    }

    public ProfitCategoryFX getProfitCategoryFXObjectProperty() {
        return profitCategoryFXObjectProperty.get();
    }

    public ObjectProperty<ProfitCategoryFX> profitCategoryFXObjectPropertyProperty() {
        return profitCategoryFXObjectProperty;
    }

    public void setProfitCategoryFXObjectProperty(ProfitCategoryFX profitCategoryFXObjectProperty) {
        this.profitCategoryFXObjectProperty.set(profitCategoryFXObjectProperty);
    }

    public ObservableList<ProfitCategoryFX> getProfitCategoryFXObservableList() {
        return profitCategoryFXObservableList;
    }

    public void setProfitCategoryFXObservableList(ObservableList<ProfitCategoryFX> profitCategoryFXObservableList) {
        this.profitCategoryFXObservableList = profitCategoryFXObservableList;
    }

    public ExpenseCategoryFX getExpenseCategoryFXObjectProperty() {
        return expenseCategoryFXObjectProperty.get();
    }

    public ObjectProperty<ExpenseCategoryFX> expenseCategoryFXObjectPropertyProperty() {
        return expenseCategoryFXObjectProperty;
    }

    public void setExpenseCategoryFXObjectProperty(ExpenseCategoryFX expenseCategoryFXObjectProperty) {
        this.expenseCategoryFXObjectProperty.set(expenseCategoryFXObjectProperty);
    }

    public ObservableList<ExpenseCategoryFX> getExpenseCategoryFXObservableList() {
        return expenseCategoryFXObservableList;
    }

    public void setExpenseCategoryFXObservableList(ObservableList<ExpenseCategoryFX> expenseCategoryFXObservableList) {
        this.expenseCategoryFXObservableList = expenseCategoryFXObservableList;
    }
}
