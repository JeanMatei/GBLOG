package dao;

import connection.ConexaoMySQL;
import model.Entrega;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EntregaDAO implements DAO<Entrega> {

    public ArrayList<Entrega> selecionar() throws Exception {
        try {
            String sql = "SELECT" +
                    "id, " +
                    "nome_completo, " +
                    "data_nascimento," +
                    "documento, " +
                    "pais, " +
                    "estado, " +
                    "cidade " +
                    "FROM pessoa";
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

        }
    }
}