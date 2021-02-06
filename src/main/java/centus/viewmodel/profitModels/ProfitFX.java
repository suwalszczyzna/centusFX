package centus.viewmodel.profitModels;

import javafx.beans.property.*;

import java.time.LocalDate;

public class ProfitFX {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty amount = new SimpleStringProperty();
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private ObjectProperty<ProfitCategoryFX> profitCategory = new SimpleObjectProperty<>();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
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

    public ProfitCategoryFX getProfitCategory() {
        return profitCategory.get();
    }

    public ObjectProperty<ProfitCategoryFX> profitCategoryProperty() {
        return profitCategory;
    }

    public void setProfitCategory(ProfitCategoryFX profitCategory) {
        this.profitCategory.set(profitCategory);
    }
}
