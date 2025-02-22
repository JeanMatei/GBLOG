package dao;

import connection.ConexaoMySQL;
import model.Filial;

import java.sql.*;
import java.util.ArrayList;

public class FilialDAO implements DAO<Filial> {


    public ArrayList<Filial> selecionar() throws Exception {
        try {
            String sql = "SELECT " +
                    "id_filial, " +
                    "cidade, " +
                    "estado" +
                    " FROM filial";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Filial> filials = new ArrayList<>();
            while (resultado.next()) {
              Filial filial = new Filial(
                        resultado.getString("id_filial"),
                        resultado.getString("cidade"),
                        resultado.getString("estado")
                );
                filials.add(filial);
            }
            return filials;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Erro desconhecido! Por favor, tente novamente!" + e.getMessage());

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
                        filial.setId(generatedKeys.getString(1)); // Define o ID gerado
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
                        "cidade = ?, " +
                        "estado = ?" +
                        "WHERE id_filial = ?";

                //Preparando e passando os parâmetros
                PreparedStatement declaracao = ConexaoMySQL.get().prepareStatement(sql);
                declaracao.setString(1, filial.getCidade());
                declaracao.setString(2, filial.getEstado());
                declaracao.setString(3, filial.getId());
                return declaracao.executeUpdate() > 0;

            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Erro ao atualizar a filial. Tente novamente mais tarde!" + e.getMessage());
            }
        }

        //Deletando diretamente no banco de dados
        public Boolean deletar(String id_filial) throws Exception {
            try {
                //Comando sql com DELETE
                String sql = "DELETE FROM filial WHERE id_filial = ?";

                //Passando o id para o WHERE
                PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
                preparacao.setString(1, id_filial);
                return preparacao.executeUpdate() > 0;

            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Erro ao deletar a filial. Por favor, tente novamente mais tarde." + e.getMessage());
            }
        }

    @Override
    public Filial selecionarPorId(String id) throws Exception {
        return null;
    }

    public Filial selecionarFilialPorId(String id_filial) throws Exception {
        try {
            String sql = "SELECT " +
                    "id_filial, " +
                    "cidade, " +
                    "estado " +
                    "FROM filial WHERE id_filial = ?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, id_filial);
            ResultSet resultado = preparacao.executeQuery();

            //Selecionando todos os atributos e criando uma filial
            if (resultado.next()) {
                return new Filial(
                        resultado.getString("id_filial"),
                        resultado.getString("cidade"),
                        resultado.getString("estado")
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Não foi possível selecionar a filial." + e.getMessage());
        }
    }
    public String selecionarPorId() {
        return "";
    }

}