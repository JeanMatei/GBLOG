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
    private final FilialDAO filialDAO;

    public VeiculoDAO(FilialDAO filialDAO) {
        this.filialDAO = filialDAO;
    }

    public ArrayList<Veiculo> selecionar() throws Exception {
        try {
            String sql = "SELECT " +
                    "v.placa, " +
                    "v.modelo, " +
                    "v.ano, " +
                    "v.capacidade, " +
                    "v.tipo, " +
                    "v.situacao, " +
                    "v.quilometragematual, " +
                    "v.dataUltimaManutencao, " +
                    "v.id_filial, " +

                    "f.id_filial, " +
                    "f.cidade," +
                    "f.estado " +
                    "FROM veiculo v JOIN filial f ON v.id_filial = f.id_filial";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Veiculo> veiculos = new ArrayList<>();
            while (resultado.next()) {
                Filial filial = new Filial(
                        resultado.getString("f.id_filial"),
                        resultado.getString("f.cidade"),
                        resultado.getString("f.estado")
                );
                Veiculo veiculo = new Veiculo(
                        resultado.getString("placa"),//  placa;
                        resultado.getDouble("capacidade"),// capacidade
                        resultado.getString("modelo"),//modelo
                        resultado.getString("tipo"),//tpveículo = tipo do veículo
                        resultado.getString("ano"),//ano
                        resultado.getString("situacao"),//disponibilidade
                        resultado.getDouble("quilometragematual"),//quilometragem
                        resultado.getDate("dataUltimaManutencao").toLocalDate(),
                        filial// filial passagem
                        );
                veiculos.add(veiculo);
            }
            return veiculos;

        } catch (SQLException e) {
            throw new Exception("Erro desconhecido! Por favor, tente novamente!" + e.getMessage());

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
            throw new Exception("Erro desconhecido! Por favor, tente novamente!" + e.getMessage());
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
                        resultado.getDate("manutencao").toLocalDate(),
                        new Filial()
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Não foi possível selecionar a veiculo.");
        }
    }



}
