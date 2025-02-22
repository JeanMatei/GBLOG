package dao;

import connection.ConexaoMySQL;
import model.Entrega;
import model.Filial;
import model.Veiculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class EntregaDAO {


    public ArrayList<Entrega> selecionar() throws Exception {
        try {
            String sql = "SELECT " +

                    //Entrega
                    "e.cdEntrega, " +
                    "e.id_filial, " +
                    "e.id_filial," +
                    "e.nomeCliente, " +
                    "e.nomeDestinatario, " +
                    "e.descricaoCarga, " +
                    "e.peso, " +
                    "e.placa," +
                    "e.statusEntrega," +
                    "e.dataHoraSaida," +
                    "e.dataHoraChegada," +

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

                    "FROM entrega e JOIN filial f ON e.id_filial = f.id_filial " +
                    "JOIN veiculo v ON v.placa = e.placa";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Entrega> entregas = new ArrayList<>();
            while (resultado.next()) {

                Filial filial = new Filial(
                        resultado.getString("f.id_filial"),
                        resultado.getString("cidade"),
                        resultado.getString("estado")
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
                        resultado.getString("e.nomeCliente"),
                        resultado.getString("e.nomeDestinatario"),
                        resultado.getString("e.descricaoCarga"),
                        resultado.getDouble("e.peso"),
                        veiculo,
                        resultado.getString("e.statusEntrega"),
                        resultado.getDate("e.dataHoraSaida").toLocalDate(),
                        resultado.getDate("e.dataHoraChegada").toLocalDate()

                );

                entregas.add(entrega);

            }
            return entregas;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Erro desconhecido! Por favor, tente novamente!" + e.getMessage());

        }

    }

    public Boolean inserir(Entrega entrega) throws Exception {
        try {
            String sql = "INSERT INTO entrega " +
                    "(cdEntrega,nomeCliente,nomeDestinatario,descricaocarga,peso,statusEntrega,dataHoraSaida,dataHoraChegada,id_filial, id_filial, placa) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparacao.setString(1, entrega.getCdEntrega());
            preparacao.setString(2, entrega.getNmcliente());
            preparacao.setString(3, entrega.getNmdestinatario());
            preparacao.setString(4, entrega.getDescricao());
            preparacao.setDouble(5, entrega.getPtcarga());
            preparacao.setString(6, entrega.getStatusentrega());
            preparacao.setDate(7, java.sql.Date.valueOf(entrega.getSaida()));
            preparacao.setDate(8, java.sql.Date.valueOf(entrega.getChegada()));
            preparacao.setString(9, entrega.getOrigem().getId());
            preparacao.setString(10, entrega.getDestino().getId());
            preparacao.setString(11, entrega.getVeiculodes().getPlaca());

            int linhasAfetadas = preparacao.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new Exception("Erro ao inserir a veiculo no banco. Nenhuma linha foi inserida.");
            }

            try (ResultSet generatedKeys = preparacao.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entrega.setCdentrega(generatedKeys.getString(1)); // Define o ID gerado
                } else {
                    throw new Exception("Falha ao obter o ID da entrega inserida.");
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Erro desconhecido! Por favor, tente novamente!" + e.getMessage());
        }
    }

    public Boolean atualizar (Entrega entrega) throws Exception {
        try {
            String sql = "UPDATE entrega  " +
                    "SET " +
                    "cdEntrega = ?, " +
                    "origem = ?, " +
                    "destino = ?," +
                    "nmCliente = ?, " +
                    "nmDestinatario = ?, " +
                    "descricao = ?, " +
                    "ptcarga = ?, " +
                    "veiculo = ?, " +
                    "status = ?, " +
                    "saida = ?, " +
                    "chegada = ?, " +
                    "id_filial = ?, " +
                    "placa = ? "+
            "WHERE cdentrega = ?";

            //Preparando e passando os parâmetros
            PreparedStatement declaracao = ConexaoMySQL.get().prepareStatement(sql);

            declaracao.setString(1, entrega.getNmcliente());
            declaracao.setString(2,entrega.getNmdestinatario());
            declaracao.setString(3,entrega.getDescricao());
            declaracao.setDouble(4, entrega.getPtcarga());
            declaracao.setDate(5,java.sql.Date.valueOf(entrega.getSaida()));
            declaracao.setDate(6,java.sql.Date.valueOf(entrega.getChegada()));

            return declaracao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new Exception("Erro ao atualizar a entrega. Tente novamente mais tarde!");
        }
    }

    //Deletando diretamente no banco de dados
    public Boolean deletar(String cdentrega) throws Exception {
        try {
            //Comando sql com DELETE
            String sql = "DELETE FROM entrega WHERE cdentrega = ?";

            //Passando o id para o WHERE
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, cdentrega);
            return preparacao.executeUpdate() > 0;

        } catch (Exception e) {
            throw new Exception("Erro ao deletar a entrega. Por favor, tente novamente mais tarde.");
        }
    }

    public Entrega selecionarPorId(String cdentrega) throws Exception {
        try {
            String sql = "SELECT " +
                    "e.cdEntrega, " +
                    "e.id_filial, " +
                    "e.id_filial," +
                    "e.nomeCliente, " +
                    "e.nomeDestinatario, " +
                    "e.descricaoCarga, " +
                    "e.peso, " +
                    "e.placa," +
                    "e.status," +
                    "e.saida," +
                    "e.chegada," +
                    "e.id_filial," +
                    "e.placa" +
                    "FROM entrega WHERE e.placa = ?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, cdentrega);
            ResultSet resultado = preparacao.executeQuery();

            //Selecionando todos os atributos e criando uma filial
            if (resultado.next()) {

                return new Entrega(
                        new Filial(),
                        new Filial(),
                        resultado.getString("nmCliente"),
                        resultado.getString("nmdestinatario"),
                        resultado.getString("descricao"),
                        resultado.getDouble("manutencao"),
                        new Veiculo(),
                        resultado.getString("status"),
                        resultado.getDate("saida").toLocalDate(),
                        resultado.getDate("chegada").toLocalDate()

                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Não foi possível selecionar a veiculo.");
        }
    }


}
