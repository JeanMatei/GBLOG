package dao;

import connection.ConexaoMySQL;
import model.Filial;

import java.sql.*;
import java.util.ArrayList;

public class FilialDAO implements DAO<Filial> {


    public ArrayList<Filial> selecionar() throws Exception {
        try {
            String sql = "SELECT" +
                    "id_filial, " +
                    "cidade, " +
                    "estado" +
                    "FROM filial";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Filial> filials = new ArrayList<>();
            while (resultado.next()) {
              Filial filial = new Filial(
                        resultado.getLong("id"),
                        resultado.getString("cidade"),
                        resultado.getString("estado")
                );
                filials.add(filial);
            }
            return filials;

        } catch (SQLException e) {
            throw new Exception("Erro desconhecido! Por favor, tente novamente!");

        }
    }
        public Boolean inserir (Filial filial) throws Exception {
            try {
                String sql = "INSERT INTO filial" +
                        "(cidade,estado) VALUES(?,?)";

                PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparacao.setString(1, filial.getCidade());
                preparacao.setString(2, filial.getEstado());

                int linhasAfetadas = preparacao.executeUpdate();
                if (linhasAfetadas == 0) {
                    throw new Exception("Erro ao inserir a filial no banco. Nenhuma linha foi inserida.");
                }

                try (ResultSet generatedKeys = preparacao.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        filial.setId(generatedKeys.getLong(1)); // Define o ID gerado
                    } else {
                        throw new Exception("Falha ao obter o ID da filial inserida.");
                    }
                }

                return true;

            } catch (SQLException e) {
                e.printStackTrace();
                throw new Exception("Erro desconhecido! Por favor, tente novamente!");
            }
        }


        public Boolean atualizar (Filial filial) throws Exception {
            try {
                String sql = "UPDATE filial " +
                        "SET " +
                        " id_filial = ?, " +
                        "cidade = ?, " +
                        "estado = ?" +
                        "WHERE id = ?";

                //Preparando e passando os parâmetros
                PreparedStatement declaracao = ConexaoMySQL.get().prepareStatement(sql);
                declaracao.setString(1, filial.getCidade());
                declaracao.setString(2, filial.getEstado());
                return declaracao.executeUpdate() > 0;

            } catch (Exception e) {
                throw new Exception("Erro ao atualizar a filial. Tente novamente mais tarde!");
            }
        }

        //Deletando diretamente no banco de dados
        public Boolean deletar(Long id_filial) throws Exception {
            try {
                //Comando sql com DELETE
                String sql = "DELETE FROM filial WHERE id = ?";

                //Passando o id para o WHERE
                PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
                preparacao.setLong(1, id_filial);
                return preparacao.executeUpdate() > 0;

            } catch (Exception e) {
                throw new Exception("Erro ao deletar a filial. Por favor, tente novamente mais tarde.");
            }
        }

    public Filial selecionarPorId(Long id_filial) throws Exception {
        try {
            String sql = "SELECT" +
                    "id_filial, " +
                    "cidade, " +
                    "estado" +
                    "FROM filial";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id_filial);
            ResultSet resultado = preparacao.executeQuery();

            //Selecionando todos os atributos e criando uma filial
            if (resultado.next()) {
                return new Filial(
                        resultado.getLong("id"),
                        resultado.getString("cidade"),
                        resultado.getString("estado")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            throw new Exception("Não foi possível selecionar a filial.");
        }
    }

}