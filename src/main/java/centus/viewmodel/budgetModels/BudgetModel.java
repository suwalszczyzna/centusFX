package centus.viewmodel.budgetModels;

import centus.database.dao.BudgetDao;
import centus.database.model.Budget;
import centus.utils.converters.ConverterBudget;
import centus.utils.exceptions.ApplicationException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class BudgetModel {
    private ObjectProperty<BudgetFx> budgetObjectProperty = new SimpleObjectProperty<>(new BudgetFx());
    private ObservableList<BudgetFx> budgetFxObservableList = FXCollections.observableArrayList();


    public void init() throws ApplicationException, SQLException {
        this.initBudgetList();
    }

    public void initBudgetList() throws ApplicationException {
        BudgetDao budgetDao = new BudgetDao();
        List<Budget> budgetList = budgetDao.queryForAll(Budget.class);

        this.budgetFxObservableList.clear();
        budgetList.forEach(b -> {
            BudgetFx budgetFx = ConverterBudget.convertToBudgetFx(b);
            this.budgetFxObservableList.add(budgetFx);
        });
    }

    public void saveBudgetToDb() throws ApplicationException {
        Budget budget = ConverterBudget.convertToBudget(this.getBudgetObjectProperty());

        BudgetDao budgetDao = new BudgetDao();
        budgetDao.createOrUpdate(budget);
        this.initBudgetList();
    }

    public BudgetFx getBudgetObjectProperty() {
        return budgetObjectProperty.get();
    }

    public ObjectProperty<BudgetFx> budgetObjectPropertyProperty() {
        return budgetObjectProperty;
    }

    public void setBudgetObjectProperty(BudgetFx budgetObjectProperty) {
        this.budgetObjectProperty.set(budgetObjectProperty);
    }

    public ObservableList<BudgetFx> getBudgetFxObservableList() {
        return budgetFxObservableList;
    }

    public void setBudgetFxObservableList(ObservableList<BudgetFx> budgetFxObservableList) {
        this.budgetFxObservableList = budgetFxObservableList;
    }

    public void deleteBudget(BudgetFx budgetFx) throws ApplicationException {
        BudgetDao budgetDao = new BudgetDao();
        budgetDao.deleteById(Budget.class, budgetFx.getId());
        initBudgetList();
    }
}
