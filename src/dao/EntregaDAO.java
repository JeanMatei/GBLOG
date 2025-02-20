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
                    "e.veiculo"+
                    "e.status" +
                    "e.saida" +
                    "e.chegada" +
                    "e.id_filial" +
                    "e.placa" +

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

                        Filial filial = new Filial(
                        resultado.getString("f.id_filial"),
                        resultado.getString("origem"),
                        resultado.getString("destino")
                );

                        Veiculo veiculo = new Veiculo(
                        resultado.getString("v.placa"),//  placa;
                        resultado.getDouble("v.capacidade"),// capacidade
                        resultado.getString("v.modelo"),//modelo
                        resultado.getString("v.tipo"),//tpveículo = tipo do veículo
                        resultado.getString("v.ano"),//ano
                        resultado.getString("v.situacao"),//disponibilidade
                        resultado.getDouble("v.quilometragematual"),//quilometragem
                        resultado.getDate("v.dataUltimaManutencao").toLocalDate(),
                        filial// filial passagem

                );
                Entrega entrega = new Entrega(

                        resultado.getString("e.cdentrega"),
                        filial,
                        filial,
                        resultado.getString("e.nmCliente"),
                        resultado.getString("e.nmDestinatario"),
                        resultado.getString("e.descricao"),
                        resultado.getDouble("e.ptCarga"),
                        veiculo,
                        resultado.getString("e.status"),
                        resultado.getDate("e.saida").toLocalDate().atTime(10,0),
                        resultado.getDate("e.chegada").toLocalDate().atTime(10,0)

                );

                entregas.add(entrega);

            }
            return entregas;

        } catch (SQLException e) {
            throw new Exception("Erro desconhecido! Por favor, tente novamente!");

        }

    }
}
