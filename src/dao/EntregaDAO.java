package dao;

import connection.ConexaoMySQL;
import model.Entrega;
import model.Filial;
import model.Veiculo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

//public class EntregaDAO implements DAO<Entrega> {

//    public ArrayList<Entrega> selecionar() throws Exception {
//        try {
//            String sql = "SELECT" +
//                    "cdEntrega, " +
//                    "origem, " +
//                    "destino," +
//                    "nmCliente, " +
//                    "nmDestinatario     , " +
//                    "estado, " +
//                    "cidade " +
//                    "FROM pessoa";
//            Statement declaracao = ConexaoMySQL.get().createStatement();
//            ResultSet resultado = declaracao.executeQuery(sql);
//
//        }
//    }
//}
//private String cdEntrega;
//private Filial origem;
//private Filial destino;
//private String nmCliente;
//private String nmDestinatario;
//private String descricao;
//private Double ptcarga;
//private Veiculo veiculo; // Veículo designado
//private String status;
//private LocalDateTime saida;
//private LocalDateTime chegada;