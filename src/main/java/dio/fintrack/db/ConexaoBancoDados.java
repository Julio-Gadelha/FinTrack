package dio.fintrack.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBancoDados {

    private static final String URL = "jdbc:sqlite:fintrack.db";

    public static Connection conection() throws SQLException {
        return conection(URL);
    }

    public static Connection conection(String url) throws SQLException {
        Connection conexao = DriverManager.getConnection(url);
        criarTabelaSeNaoExistir(conexao);
        return conexao;
    }

    private static void criarTabelaSeNaoExistir(Connection conexao) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS transacoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "descricao VARCHAR(100)," +
                "valor DECIMAL(10,2)," +
                "tipo VARCHAR(10)," +
                "data DATE" +
                ")";
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
        }
    }
}