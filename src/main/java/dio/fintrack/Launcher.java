package dio.fintrack;

import dio.fintrack.dao.TransacaoDAO;
import dio.fintrack.model.Tipo;
import dio.fintrack.model.Transacao;
import javafx.application.Application;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

public class Launcher {
    public static void main(String[] args) {

        try {
            TransacaoDAO dao = new TransacaoDAO();
            dao.insert(new Transacao("Salário", new BigDecimal("3000.00"), Tipo.RECEITA, LocalDate.now()));
            dao.insert(new Transacao("Mercado", new BigDecimal("450.00"), Tipo.DESPESA, LocalDate.now()));

            dao.findAll().forEach(System.out::println);

            System.out.println("Saldo atual: " + dao.getSaldo());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Application.launch(FinApp.class, args);
    }
}