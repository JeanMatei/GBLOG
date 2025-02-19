package dao;

import connection.ConexaoMySQL;
import model.Entrega;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EntregaDAO implements DAO<Entrega> {

    public ArrayList<Entrega> selecionar() throws Exception {
        try {
            String sql = "SELECT" +
                    "cdEntrega, " +
                    "origem, " +
                    "destino," +
                    "nmCliente, " +
                    "nmDestinatario     , " +
                    "descricao, " +
                    "ptcarga " +
                    "veiculo" +
                    "status" +
                    "saida" +
                    "chegada" +
                    "FROM pessoa";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Entrega> entregas = new ArrayList<>();
            while (resultado.next()) {
                Entrega entrega = new Entrega(
                        resultado.getString("id"),
                        resultado.getString(),
                        );
                entregas.add(entrega);
            }
            return entregas;

        } catch (SQLException e) {
            throw new Exception("Erro desconhecido! Por favor, tente novamente!");

        }

    }
}
