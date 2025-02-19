package dao;

import connection.ConexaoMySQL;
import model.Filial;
import model.Veiculo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VeiculoDAO {

    public ArrayList<Veiculo> selecionar() throws Exception {
        try {
            String sql = "SELECT" +
                    "placa, " +
                    "modelo, " +
                    "anoFabricacao, " +
                    "capacidadedeCarga, " +
                    "tipoVeiculo, " +
                    "situacao, " +
                    "quilometragemAtual, " +
                    "dataUltimaManutencao, " +
                    "id_filial, " +
                    "FROM veiculo";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Veiculo> veiculos = new ArrayList<>();
            while (resultado.next()) {
                Veiculo veiculo = new Veiculo(
                        resultado.getString("id"),
                        resultado.getString("cidade"),
                        resultado.getString("estado")
                );
                veiculos.add(veiculo);
            }
            return veiculos;

        } catch (SQLException e) {
            throw new Exception("Erro desconhecido! Por favor, tente novamente!");

        }
    }

}
