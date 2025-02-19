package dao;

import connection.ConexaoMySQL;
import model.Filial;

import java.sql.*;
import java.util.ArrayList;

public class FilialDAO {

    public ArrayList<Filial> selecionar() throws Exception {
        Filial filial;
        try {
            String sql = "SELECT" +
                    "id_filial, " +
                    "cidade, " +
                    "estado," +
                    "FROM filial";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Filial> filials = new ArrayList<>();
            while (resultado.next()) {
                filial = new Filial(
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

//        public Boolean inserir (Filial filial) throws Exception {
//            try {
//                String sql = "INSERT INTO filial" +
//                        "(nome_completo,data_nascimento,documento,pais,estado,cidade" + "VALUES(?,?)";
//                PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
//                preparacao.setString(5, filial.getEstado());
//                preparacao.setString(4, filial.getCidade());
//                return preparacao.executeUpdate() > 0;
//
//            } catch (SQLException e) {
//                throw new Exception("Erro desconhecido! Por favor, tente novamente!");
//            }
//        }
//        public Boolean atualizar (Filial filial) throws Exception {
//            return true;
//        }
//
//        public Boolean deletar (Long id) throws Exception {
//            return true;
//        }
//        public Filial selecionarPorId(Long id) throws Exception{
//            return null;
//        }
    }
}