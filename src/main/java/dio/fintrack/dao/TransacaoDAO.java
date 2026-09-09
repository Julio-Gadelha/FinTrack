package dio.fintrack.dao;

import dio.fintrack.db.ConexaoBancoDados;
import dio.fintrack.model.Transacao;
import dio.fintrack.model.Tipo;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {

    public void insert(Transacao t) throws SQLException {
        String sql = "INSERT INTO transactions (descricao, valor, tipo, data) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConexaoBancoDados.conection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, t.getDescricao());
            ps.setBigDecimal(2, t.getValor());
            ps.setString(3, t.getTipo().name());
            ps.setString(4, t.getData().toString());
            ps.executeUpdate();
        }
    }

    public List<Transacao> findAll() throws SQLException {
        List<Transacao> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";

        try (Connection connection = ConexaoBancoDados.conection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Transacao t = new Transacao("Salário", new BigDecimal("3000.00"), Tipo.RECEITA, LocalDate.now());
                t.setId(rs.getInt("id"));
                t.setDescricao(rs.getString("descrição"));
                t.setValor(rs.getBigDecimal("valor"));
                t.setTipo(Tipo.valueOf(rs.getString("tipo")));
                t.setData(LocalDate.parse(rs.getString("data")));
                transactions.add(t);
            }
        }
        return transactions;
    }

    public void update(Transacao t) throws SQLException {
        String sql = "UPDATE transactions SET descricao = ?, valor = ?, tipo = ?, data = ? WHERE id = ?";
        try (Connection connection = ConexaoBancoDados.conection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, t.getDescricao());
            ps.setBigDecimal(2, t.getValor());
            ps.setString(3, t.getTipo().name());
            ps.setString(4, t.getData().toString());
            ps.setInt(5, t.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM transactions WHERE id = ?";
        try (Connection connection = ConexaoBancoDados.conection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}