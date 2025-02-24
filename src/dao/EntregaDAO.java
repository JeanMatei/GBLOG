package dao;

import connection.ConexaoMySQL;
import model.Entrega;
import model.Filial;
import model.Veiculo;
import util.CodigoUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class EntregaDAO {
    private final FilialDAO filialDAO;
    private final VeiculoDAO veiculoDAO;

    public EntregaDAO(FilialDAO filialDAO, VeiculoDAO veiculoDAO) {
        this.filialDAO = filialDAO;
        this.veiculoDAO = veiculoDAO;
    }

    public ArrayList<Entrega> selecionar() throws Exception {
        try {
            String sql = "SELECT " +
                    // Entrega
                    "e.cdEntrega, " +
                    "e.id_filial_origem, " +
                    "e.id_filial_destino, " +
                    "e.nomeCliente, " +
                    "e.nomeDestinatario, " +
                    "e.descricaoCarga, " +
                    "e.peso, " +
                    "e.placa, " +
                    "e.statusEntrega, " +
                    "e.dataHoraSaida, " +
                    "e.dataHoraChegada, " +

                    // Veiculo
                    "v.placa AS placa_veiculo, " + // Alias para evitar conflito de colunas
                    "v.modelo, " +
                    "v.ano, " +
                    "v.capacidade, " +
                    "v.tipo, " +
                    "v.situacao, " +
                    "v.quilometragematual, " +
                    "v.dataUltimaManutencao, " +
                    "v.id_filial AS id_filial_veiculo, " + // Alias para evitar conflito de colunas

                    // Filial (origem)
                    "f_origem.id_filial AS id_filial_origem, " +
                    "f_origem.cidade AS cidade_origem, " +
                    "f_origem.estado AS estado_origem, " +

                    // Filial (destino)
                    "f_destino.id_filial AS id_filial_destino, " +
                    "f_destino.cidade AS cidade_destino, " +
                    "f_destino.estado AS estado_destino " +

                    "FROM entrega e " +
                    "LEFT JOIN filial f_origem ON e.id_filial_origem = f_origem.id_filial " + // LEFT JOIN para filial de origem
                    "LEFT JOIN filial f_destino ON e.id_filial_destino = f_destino.id_filial " + // LEFT JOIN para filial de destino
                    "LEFT JOIN veiculo v ON e.placa = v.placa"; // LEFT JOIN para veículo
            Statement declaracao = ConexaoMySQL.get().createStatement();
            ResultSet resultado = declaracao.executeQuery(sql);

            ArrayList<Entrega> entregas = new ArrayList<>();
            while (resultado.next()) {
                // Filial de origem (pode ser null)
                Filial filialOrigem = null;
                if (resultado.getString("id_filial_origem") != null) {
                    filialOrigem = new Filial(
                            resultado.getString("id_filial_origem"),
                            resultado.getString("cidade_origem"),
                            resultado.getString("estado_origem")
                    );
                }

                // Filial de destino (pode ser null)
                Filial filialDestino = null;
                if (resultado.getString("id_filial_destino") != null) {
                    filialDestino = new Filial(
                            resultado.getString("id_filial_destino"),
                            resultado.getString("cidade_destino"),
                            resultado.getString("estado_destino")
                    );
                }

                // Veículo (pode ser null)
                Veiculo veiculo = null;
                if (resultado.getString("placa_veiculo") != null) {
                    veiculo = new Veiculo(
                            resultado.getString("placa_veiculo"),
                            resultado.getDouble("capacidade"),
                            resultado.getString("modelo"),
                            resultado.getString("tipo"),
                            resultado.getString("ano"),
                            resultado.getString("situacao"),
                            resultado.getDouble("quilometragematual"),
                            resultado.getDate("dataUltimaManutencao") != null ?
                                    resultado.getDate("dataUltimaManutencao").toLocalDate() : null,
                            filialOrigem // Filial do veículo (assumindo que o veículo está na filial de origem)
                    );
                }

                // Entrega
                Entrega entrega = new Entrega(
                        resultado.getString("cdEntrega"),
                        filialOrigem,
                        filialDestino,
                        resultado.getString("nomeCliente"),
                        resultado.getString("nomeDestinatario"),
                        resultado.getString("descricaoCarga"),
                        resultado.getDouble("peso"),
                        veiculo,
                        resultado.getString("statusEntrega"),
                        resultado.getDate("dataHoraSaida") != null ?
                                resultado.getDate("dataHoraSaida").toLocalDate() : null,
                        resultado.getDate("dataHoraChegada") != null ?
                                resultado.getDate("dataHoraChegada").toLocalDate() : null
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
            // Gera o código da entrega (exemplo com UUID)
            String codigo = CodigoUtil.gerarCodigo12Caracteres();
            entrega.setCdentrega(codigo);

            String sql = "INSERT INTO entrega " +
                    "(cdentrega, nomeCliente, nomeDestinatario, descricaocarga, peso, statusEntrega, dataHoraSaida, dataHoraChegada, id_filial_origem, id_filial_destino, placa) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)"; // 11 parâmetros

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparacao.setString(1, entrega.getCdEntrega()); // cdentrega gerado
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
            System.out.println("Placa do veículo a ser inserido: " + entrega.getVeiculodes().getPlaca());

            int linhasAfetadas = preparacao.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new Exception("Erro ao inserir a entrega. Nenhuma linha afetada.");
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Erro ao inserir a entrega: " + e.getMessage());
        }
    }

    public Boolean atualizar(Entrega entrega) throws Exception {
        try {
            // Validação do veículo
            if (entrega.getVeiculodes() == null) {
                throw new Exception("Veículo não foi associado à entrega.");
            }

            String sql = "UPDATE entrega " +
                    "SET " +
                    "nomeCliente = ?, " +
                    "nomeDestinatario = ?, " +
                    "descricaocarga = ?, " + // Corrigido para match com o nome da coluna no banco (descricaocarga)
                    "peso = ?, " +
                    "statusEntrega = ?, " + // Corrigido para match com o nome da coluna no banco (statusEntrega)
                    "dataHoraSaida = ?, " +
                    "dataHoraChegada = ?, " +
                    "id_filial_origem = ?, " + // Vírgula adicionada
                    "id_filial_destino = ?, " + // Vírgula adicionada
                    "placa = ? " + // Vírgula removida (último campo)
                    "WHERE cdentrega = ?";

            PreparedStatement declaracao = ConexaoMySQL.get().prepareStatement(sql);

            // Passando os parâmetros na ordem correta
            declaracao.setString(1, entrega.getNmcliente());
            declaracao.setString(2, entrega.getNmdestinatario());
            declaracao.setString(3, entrega.getDescricao());
            declaracao.setDouble(4, entrega.getPtcarga());
            declaracao.setString(5, entrega.getStatusentrega());
            declaracao.setDate(6, java.sql.Date.valueOf(entrega.getSaida()));
            declaracao.setDate(7, java.sql.Date.valueOf(entrega.getChegada()));
            declaracao.setString(8, entrega.getOrigem().getId());
            declaracao.setString(9, entrega.getDestino().getId());
            declaracao.setString(10, entrega.getVeiculodes().getPlaca());
            declaracao.setString(11, entrega.getCdEntrega());

            return declaracao.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Erro ao atualizar a entrega: " + e.getMessage());
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
            // Query SQL corrigida
            String sql = "SELECT " +
                    "cdentrega, " +
                    "id_filial_origem, " +
                    "id_filial_destino, " +
                    "nomeCliente, " +
                    "nomeDestinatario, " +
                    "descricaoCarga, " +
                    "peso, " +
                    "placa, " +
                    "statusEntrega, " +
                    "dataHoraSaida, " +
                    "dataHoraChegada " +
                    "FROM entrega " +
                    "WHERE cdentrega = ?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, cdentrega);
            ResultSet resultado = preparacao.executeQuery();

            // Verifica se há resultados
            if (resultado.next()) {
                // Recupera as filiais de origem e destino
                Filial filialOrigem = filialDAO.selecionarPorId(resultado.getString("id_filial_origem"));
                Filial filialDestino = filialDAO.selecionarPorId(resultado.getString("id_filial_destino"));

                // Recupera o veículo
                Veiculo veiculo = veiculoDAO.selecionarPorId(resultado.getString("placa"));

                // Cria e retorna a entrega
                return new Entrega(
                        resultado.getString("cdentrega"),
                        filialOrigem,
                        filialDestino,
                        resultado.getString("nomeCliente"),
                        resultado.getString("nomeDestinatario"),
                        resultado.getString("descricaoCarga"),
                        resultado.getDouble("peso"),
                        veiculo,
                        resultado.getString("statusEntrega"),
                        resultado.getDate("dataHoraSaida").toLocalDate(),
                        resultado.getDate("dataHoraChegada").toLocalDate()
                );
            } else {
                return null; // Nenhuma entrega encontrada
            }

        } catch (SQLException e) {
            throw new Exception("Não foi possível selecionar a entrega.");
        }
    }

    public ArrayList<Entrega> gerarRelatorio(LocalDate dataInicio, LocalDate dataFim) throws Exception {
        try {
            String sql = "SELECT " +
                    "cdentrega, " +
                    "nomeCliente, " +
                    "nomeDestinatario, " +
                    "descricaoCarga, " +
                    "peso, " +
                    "placa, " +
                    "statusEntrega, " +
                    "dataHoraSaida, " +
                    "dataHoraChegada, " +
                    "id_filial_origem, " +
                    "id_filial_destino " +
                    "FROM entrega " +
                    "WHERE statusEntrega = 'Entregue' " +
                    "AND dataHoraChegada BETWEEN ? AND ?";

            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setDate(1, java.sql.Date.valueOf(dataInicio)); // Data de início
            preparacao.setDate(2, java.sql.Date.valueOf(dataFim)); // Data de fim

            ResultSet resultado = preparacao.executeQuery();

            ArrayList<Entrega> entregas = new ArrayList<>();
            while (resultado.next()) {
                // Recupera as filiais de origem e destino
                Filial filialOrigem = filialDAO.selecionarFilialPorId(resultado.getString("id_filial_origem"));
                Filial filialDestino = filialDAO.selecionarFilialPorId(resultado.getString("id_filial_destino"));

                // Recupera o veículo
                Veiculo veiculo = veiculoDAO.selecionarPorId(resultado.getString("placa"));

                // Cria a entrega
                Entrega entrega = new Entrega(
                        resultado.getString("cdentrega"),
                        filialOrigem,
                        filialDestino,
                        resultado.getString("nomeCliente"),
                        resultado.getString("nomeDestinatario"),
                        resultado.getString("descricaoCarga"),
                        resultado.getDouble("peso"),
                        veiculo,
                        resultado.getString("statusEntrega"),
                        resultado.getDate("dataHoraSaida").toLocalDate(),
                        resultado.getDate("dataHoraChegada").toLocalDate()
                );
                entregas.add(entrega);
            }

            return entregas;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Erro ao listar entregas entregues por período: " + e.getMessage());
        }
    }

}
