package dio.fintrack.controller;

import dio.fintrack.dao.TransacaoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RelatorioController {

    @FXML
    private Label labelReceitas;

    @FXML
    private Label labelDespesas;

    @FXML
    private Label labelSaldoFinal;

    private final TransacaoDAO dao = new TransacaoDAO();

    @FXML
    public void initialize() {
        try {
            labelReceitas.setText("Total de Receitas: R$ " + dao.getTotalReceitas());
            labelDespesas.setText("Total de Despesas: R$ " + dao.getTotalDespesas());
            labelSaldoFinal.setText("Saldo Final: R$ " + dao.getSaldo());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao carregar relatório: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    protected void fechar() {
        Stage stage = (Stage) labelReceitas.getScene().getWindow();
        stage.close();
    }
}