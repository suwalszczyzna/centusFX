package centus.viewmodel.budgetModels;

import javafx.beans.property.*;

import java.time.LocalDate;

public class BudgetFx {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty budgetValue = new SimpleStringProperty();
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();

    public BudgetFx() {
    }

    public BudgetFx(StringProperty budgetValue, ObjectProperty<LocalDate> date) {
        this.budgetValue = budgetValue;
        this.date = date;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getBudgetValue() {
        return budgetValue.get();
    }

    public StringProperty budgetValueProperty() {
        return budgetValue;
    }

    public void setBudgetValue(String budgetValue) {
        this.budgetValue.set(budgetValue);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }
}
