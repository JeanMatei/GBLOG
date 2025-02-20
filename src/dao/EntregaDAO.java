package dao;

import connection.ConexaoMySQL;
import model.Entrega;
import model.Filial;
import model.Veiculo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EntregaDAO {

    public ArrayList<Entrega> selecionar() throws Exception {
        try {
            String sql = "SELECT " +
                    //Entrega
                    "e.cdEntrega, " +
                    "e.origem, " +
                    "e.destino," +
                    "e.nmCliente, " +
                    "e.nmDestinatario, " +
                    "e.descricao, " +
                    "e.ptcarga " +
                    "e.status" +
                    "e.saida" +
                    "e.chegada" +

                    //Veiculo
                    "v.placa, " +
                    "v.modelo, " +
                    "v.ano, " +
                    "v.capacidade, " +
                    "v.tipo, " +
                    "v.situacao, " +
                    "v.quilometragematual, " +
                    "v.dataUltimaManutencao, " +
                    "v.id_filial, " +

                    //Filial
                    "f.id_filial, " +
                    "f.cidade," +
                    "f.estado " +

                    "FROM entrega e JOIN filial f ON e.id_filial = f.id_filial"+
                    "FROM entrega e JOIN veiculo v ON v.placa = e.placa";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Entrega> entregas = new ArrayList<>();
            while (resultado.next()) {
                Entrega entrega = new Entrega(
                        resultado.getString("cdentrega"),
                        resultado.getString("origem"),
                        resultado.getString("destino"),
                        resultado.getString("nmCliente"),
                        resultado.getString("nmDestinatario"),
                        resultado.getString("descricao"),
                        resultado.getDouble("ptcarga"),
                        resultado.getString("veiculo"),
                        resultado.getString("status"),
                        resultado.getDate("saida").toLocalDate(),
                        resultado.getDate("chegada").toLocalDate()
                );
                entregas.add(entrega);
//                private String cdEntrega;
                private Filial origem;
                private Filial destino;
                private String nmCliente;
                private String nmDestinatario;
                private String descricao;
                private Double ptcarga;
                private Veiculo veiculo; // Veículo designado
                private String status;
                private LocalDateTime saida;
                private LocalDateTime chegada;


            }
            return entregas;

        } catch (SQLException e) {
            throw new Exception("Erro desconhecido! Por favor, tente novamente!");

        }

    }
}
