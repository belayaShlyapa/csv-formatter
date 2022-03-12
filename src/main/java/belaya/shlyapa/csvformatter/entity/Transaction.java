package belaya.shlyapa.csvformatter.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;

import java.time.LocalDate;

public class Transaction {
    @CsvBindByName(column = "Date", required = true)
    @CsvDate("dd/MM/yyyy")
    private LocalDate date;

    @CsvBindByName(column = "Libellé", required = true)
    private String wording;

    @CsvBindByName(column = "Débit euros")
    @CsvNumber("#,##")
    private float expense;

    @CsvBindByName(column = "Crédit euros")
    @CsvNumber("#,##")
    private float income;

    public LocalDate getDate() {
        return date;
    }

    public String getWording() {
        return wording;
    }

    public float getExpense() {
        return expense;
    }

    public float getIncome() {
        return income;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public void setExpense(float expense) {
        this.expense = expense;
    }

    public void setIncome(float income) {
        this.income = income;
    }
}
