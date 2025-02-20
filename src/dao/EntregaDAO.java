//package dao;

import connection.ConexaoMySQL;
import model.Entrega;
import model.Filial;
import model.Veiculo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

//public class EntregaDAO  {
//
//    public ArrayList<Entrega> selecionar() throws Exception {
//        try {
//            String sql = "SELECT " +
//                    "cdEntrega, " +
//                    "origem, " +
//                    "destino," +
//                    "nmCliente, " +
//                    "nmDestinatario, " +
//                    "descricao, " +
//                    "ptcarga " +
//                    "veiculo" +
//                    "status" +
//                    "saida" +
//                    "chegada" +
//                    "FROM pessoa";
//            Statement declaracao = ConexaoMySQL.get().createStatement();
//            ResultSet resultado = declaracao.executeQuery(sql);
//
//            ArrayList<Entrega> entregas = new ArrayList<>();
//            while (resultado.next()) {
//                Entrega entrega = new Entrega(
//                        resultado.getString("cdentrega"),
//                        resultado.getString("origem"),
//                        resultado.getString("destino"),
//                        resultado.getString("nmCliente"),
//                        resultado.getString("nmDestinatario"),
//                        resultado.getString("descricao"),
//                        resultado.getDouble("ptcarga"),
//                        resultado.getString("veiculo"),
//                        resultado.getString("status"),
//                        resultado.getDate("saida").toLocalDate(),
//                        resultado.getDate("chegada").toLocalDate()
//                        );
//                entregas.add(entrega);
////                private String cdEntrega;
//                private Filial origem;
//                private Filial destino;
//                private String nmCliente;
//                private String nmDestinatario;
//                private String descricao;
//                private Double ptcarga;
//                private Veiculo veiculo; // Veículo designado
//                private String status;
//                private LocalDateTime saida;
//                private LocalDateTime chegada;


//
//            }
//            return entregas;
//
//        } catch (SQLException e) {
//            throw new Exception("Erro desconhecido! Por favor, tente novamente!");
//
//        }
//
//    }
//}
