package dao;
import connection.ConexaoMySQL;
import model.Filial;
import model.Veiculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class VeiculoDAO implements DAO<Veiculo> {

    public ArrayList<Veiculo> selecionar() throws Exception {
        try {
            String sql = "SELECT " +
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

                        resultado.getString("1"),//  placa;
                        resultado.getDouble("1.000"),// capacidade
                        resultado.getString("Furgão Mercedes"),//modelo
                        resultado.getString("Furgão"),//tpveículo = tipo do veículo
                        resultado.getString("2007"),//ano
                        resultado.getString("Disponível"),//disponibilidade
                        resultado.getDouble("2500.00"),//quilometragem
                        resultado.getDate("2024-09-20").toLocalDate()//manutencao
                        );
                veiculos.add(veiculo);
            }
            return veiculos;

        } catch (SQLException e) {
            throw new Exception("Erro desconhecido! Por favor, tente novamente!");

        }
    }

    public Boolean inserir (Veiculo veiculo) throws Exception {
        try {
            String sql = "INSERT INTO veiculo " +
                    "(capacidade,modelo,tpveiculo,ano,quilometragem,disponibilidade,manutencao,) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           preparacao.setDouble(1,veiculo.getCapacidade());
           preparacao.setString(2, veiculo.getModelo());
           preparacao.setString(3, veiculo.getTpveiculo());
           preparacao.setString(4,veiculo.getAnofb());
           preparacao.setString(5, veiculo.getDisponivel());
           preparacao.setDouble(6,veiculo.getQuilometragem());
           preparacao.setDate(7,java.sql.Date.valueOf(veiculo.getManutencao()));

            int linhasAfetadas = preparacao.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new Exception("Erro ao inserir a veiculo no banco. Nenhuma linha foi inserida.");
            }

            try (ResultSet generatedKeys = preparacao.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    veiculo.setPlaca(generatedKeys.getString(1)); // Define o ID gerado
                } else {
                    throw new Exception("Falha ao obter o ID da veiculo inserida.");
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Erro desconhecido! Por favor, tente novamente!");
        }
    }

    public Boolean atualizar (Veiculo veiculo) throws Exception {
        try {
            String sql = "UPDATE veiculo " +
                    "SET " +
                    " capacidade = ?, " +
                    "modelo = ?, " +
                    "tpveiculo = ?, " +
                    "ano =?, "  +
                    "quilometragem =?, "  +
                    "disponibilidade =?, " +
                    "manutencao =?, "  +
                    "WHERE placa = ?";

            //Preparando e passando os parâmetros
            PreparedStatement declaracao = ConexaoMySQL.get().prepareStatement(sql);

            return declaracao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new Exception("Erro ao atualizar a veiculo. Tente novamente mais tarde!");
        }
    }

    //Deletando diretamente no banco de dados
    public Boolean deletar(String placa) throws Exception {
        try {
            //Comando sql com DELETE
            String sql = "DELETE FROM filial WHERE id = ?";

            //Passando o id para o WHERE
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, placa);
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new Exception("Erro ao deletar a placa. Por favor, tente novamente mais tarde.");
        }
    }

    public Veiculo selecionarPorId(String placa) throws Exception {
        try {
            String sql = "SELECT " +
                    "placa, " +
                    "capacidade, " +
                    "modelo, " +
                    "tpveiculo, " +
                    "ano, " +
                    "quilometragem, " +
                    "disponibilidade, " +
                    "manutencao, " +
                    "id_filial " +
                    "FROM filial";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, placa);
            ResultSet resultado = preparacao.executeQuery();

            //capacidade,modelo,tpveiculo,ano,disponibilidade,quilometragem,manutencao

            //Selecionando todos os atributos e criando uma filial
            if (resultado.next()) {
                return new Veiculo(
                        resultado.getDouble("capacidade"),
                        resultado.getString("modelo"),
                        resultado.getString("tpveiculo"),
                        resultado.getString("ano"),
                        resultado.getString("disponibilidade"),
                        resultado.getDouble("quilometragem"),
                        resultado.getDate("manutencao").toLocalDate()
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Não foi possível selecionar a veiculo.");
        }
    }

}
