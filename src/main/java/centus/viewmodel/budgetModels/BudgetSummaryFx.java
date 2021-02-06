package centus.viewmodel.budgetModels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BudgetSummaryFx {
    private StringProperty mainValue = new SimpleStringProperty();
    private StringProperty spentValue = new SimpleStringProperty();
    private StringProperty restValue = new SimpleStringProperty();

    public BudgetSummaryFx() {
    }

    public String getMainValue() {
        return mainValue.get();
    }

    @Override
    public String toString() {
        return "BudgetSummaryFx{" +
                "mainValue=" + mainValue +
                ", spentValue=" + spentValue +
                ", restValue=" + restValue +
                '}';
    }

    public StringProperty mainValueProperty() {
        return mainValue;
    }

    public void setMainValue(String mainValue) {
        this.mainValue.set(mainValue);
    }

    public String getSpentValue() {
        return spentValue.get();
    }

    public StringProperty spentValueProperty() {
        return spentValue;
    }

    public void setSpentValue(String spentValue) {
        this.spentValue.set(spentValue);
    }

    public String getRestValue() {
        return restValue.get();
    }

    public StringProperty restValueProperty() {
        return restValue;
    }

    public void setRestValue(String restValue) {
        this.restValue.set(restValue);
    }
}
