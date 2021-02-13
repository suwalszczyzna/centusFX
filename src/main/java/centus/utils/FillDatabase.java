package centus.utils;


import centus.database.dao.*;
import centus.database.model.*;
import centus.utils.converters.ConverterDate;
import centus.utils.exceptions.ApplicationException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FillDatabase {
    public static double randomDouble(double min, double max) {
        double result = min + Math.random() * (max - min);
        return Math.round(result * 100.0) / 100.0;
    }

    public static void fillDatabase() {
        User damian = new User();
        damian.setName("damian");
        damian.setPassword("test");

        UserDao userDao = new UserDao();
        try {
            userDao.createOrUpdate(damian);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        ExpenseCategory spozywcze = new ExpenseCategory();
        spozywcze.setName("Produkty spożywcze");

        ExpenseCategory chemia = new ExpenseCategory();
        chemia.setName("Chemia");

        ExpenseCategory edukacja = new ExpenseCategory();
        edukacja.setName("Edukacja");

        ExpenseCategory elektronika = new ExpenseCategory();
        elektronika.setName("Elektronika");

        ExpenseCategory hobby = new ExpenseCategory();
        hobby.setName("Hobby");

        ExpenseCategoryDao expenseCategoryDao = new ExpenseCategoryDao();
        try {
            expenseCategoryDao.createOrUpdate(spozywcze);
            expenseCategoryDao.createOrUpdate(chemia);
            expenseCategoryDao.createOrUpdate(edukacja);
            expenseCategoryDao.createOrUpdate(elektronika);
            expenseCategoryDao.createOrUpdate(hobby);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }


        ProfitCategory wyplata = new ProfitCategory();
        wyplata.setName("Wypłata");

        ProfitCategory zwrotZaZakupy = new ProfitCategory();
        zwrotZaZakupy.setName("Zwrot za zakupy");

        ProfitCategoryDao profitCategoryDao = new ProfitCategoryDao();
        try {
            profitCategoryDao.createOrUpdate(wyplata);
            profitCategoryDao.createOrUpdate(zwrotZaZakupy);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }


        ProfitDao profitDao = new ProfitDao();
        Random rand = new Random();
        List<ExpenseCategory> expenseCategoryList = new ArrayList<>();
        expenseCategoryList.add(chemia);
        expenseCategoryList.add(edukacja);
        expenseCategoryList.add(elektronika);
        expenseCategoryList.add(hobby);

        ExpenseDao expenseDao = new ExpenseDao();

        for (int i = 1; i <= 12; i++) {
            LocalDate localDate = LocalDate.of(2021, i, 1);
            Month month = localDate.getMonth();

            Profit profit = new Profit();
            profit.setUser(damian);
            profit.setProfitCategory(wyplata);
            profit.setDate(ConverterDate.convertToDate(localDate));
            profit.setAmount(2500);
            try {
                profitDao.createOrUpdate(profit);
            } catch (ApplicationException e) {
                e.printStackTrace();
            }

            for (int j = 1; j < month.length(localDate.isLeapYear()); j = j+2) {
                LocalDate expenseLocalDate = LocalDate.of(2021, i, j);
                double value = FillDatabase.randomDouble(10, 200);
                Expense expense = new Expense();
                expense.setUser(damian);
                expense.setAmount(value);
                expense.setExpenseCategory(spozywcze);
                expense.setDate(ConverterDate.convertToDate(expenseLocalDate));

                try {
                    expenseDao.createOrUpdate(expense);
                } catch (ApplicationException e) {
                    e.printStackTrace();
                }
            }

            for (int k = 1; k < month.length(localDate.isLeapYear()); k = k + 5) {
                LocalDate expenseLocalDate = LocalDate.of(2021, i, k);
                double value = FillDatabase.randomDouble(20, 30);

                ExpenseCategory randomCategory = expenseCategoryList.get(rand.nextInt(expenseCategoryList.size()));
                Expense expense = new Expense();
                expense.setUser(damian);
                expense.setAmount(value);
                expense.setExpenseCategory(randomCategory);
                expense.setDate(ConverterDate.convertToDate(expenseLocalDate));

                try {
                    expenseDao.createOrUpdate(expense);
                } catch (ApplicationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
