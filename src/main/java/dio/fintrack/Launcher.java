package dio.fintrack;

import dio.fintrack.model.Transaction;
import dio.fintrack.model.Type;
import dio.fintrack.repository.RepositoryGeneric;
import javafx.application.Application;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Launcher {
    public static void main(String[] args) {

        RepositoryGeneric<Transaction> repo = new RepositoryGeneric<>();
        repo.add(new Transaction(1,"Salário", new BigDecimal("3000.00"), Type.INCOME, LocalDate.now()));
        repo.add(new Transaction(2,"Mercado", new BigDecimal("450.00"), Type.EXPENSE, LocalDate.now()));
        repo.listAll().forEach(System.out::println);


        Application.launch(FinApp.class, args);
    }
}