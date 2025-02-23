package service;

//import dao.EntregaDAO;

import controller.EntregaController;
import dao.EntregaDAO;
import dao.FilialDAO;
import dao.VeiculoDAO;
import model.Entrega;
import model.Filial;
import model.Veiculo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class EntregaService {
    private final EntregaDAO entregaDAO;
    private final FilialDAO filialDAO;
    private final VeiculoDAO veiculoDAO;

    public EntregaService(EntregaDAO entregaDAO, FilialDAO filialDAO, VeiculoDAO veiculoDAO) {
        this.filialDAO = filialDAO;
        this.entregaDAO = entregaDAO;
        this.veiculoDAO = veiculoDAO;
    }

    public String listarEntrega() throws Exception {
        ArrayList<Entrega> entregas = entregaDAO.selecionar();
        StringBuilder sb = new StringBuilder();
        if (entregas.size() > 0) {
            sb.append("LISTA DE ENTREGAS\n");
            for (Entrega entrega : entregas) {
                sb.append(entrega).append('\n');
            }
        } else {
            sb.append("Nenhuma entrega encontrada");
        }
        return sb.toString();
    }

    public String inserirEntrega(String idFilialOrigem, String idFilialDestino, String nmCliente, String nmDestinatario, String descricao, Double pesoCarga, String placaVeiculo, String status,
                                 LocalDate saida, LocalDate chegada) throws Exception {

        Filial filialOrigem = filialDAO.selecionarFilialPorId(idFilialOrigem);
        if (filialOrigem == null) {
            throw new Exception("Filial de origem com ID: " + idFilialOrigem + " não encontrada.");
        }
        Filial filialDestino = filialDAO.selecionarFilialPorId(idFilialDestino);
        if (filialDestino == null) {
            throw new Exception("Filial de destino com ID: " + idFilialDestino + " não encontrada.");
        }
        Veiculo veiculo = veiculoDAO.selecionarPorId(placaVeiculo);
        if (veiculo == null) {
            throw new Exception("Veículo com a placa: " + placaVeiculo + " não encontrada.");
        }
        Entrega entrega = new Entrega(filialOrigem, filialDestino, nmCliente, nmDestinatario, descricao, pesoCarga, veiculo, status, saida, chegada );


        if (entregaDAO.inserir(entrega)) {
            return "Entrega cadastrada com sucesso.";
        } else {
            return "Erro ao cadastrar entrega.";
        }
    }

    public String excluirEntrega(String idEntrega) throws Exception {
        if (entregaDAO.deletar(idEntrega)) {
            return "Entrega excluída com sucesso.";
        } else {
            return "Erro ao excluir entrega.";
        }

    }

    public String alterarEntrega(String cdentrega, String idFilialOrigem, String idFilialDestino, String nmCliente, String nmDestinatario, String descricao, Double pesoCarga, String placaVeiculo, String status,
                LocalDate saida, LocalDate chegada) throws Exception {

            Filial filialOrigem = filialDAO.selecionarFilialPorId(idFilialOrigem);
            if (filialOrigem == null) {
                throw new Exception("Filial de origem com ID: " + idFilialOrigem + " não encontrada.");
            }
            Filial filialDestino = filialDAO.selecionarFilialPorId(idFilialDestino);
            if (filialDestino == null) {
                throw new Exception("Filial de destino com ID: " + idFilialDestino + " não encontrada.");
            }
            Veiculo veiculo = veiculoDAO.selecionarPorId(placaVeiculo);
            if (veiculo == null) {
                throw new Exception("Veículo com a placa: " + placaVeiculo + " não encontrada.");
            }
            Entrega entrega = new Entrega(cdentrega,filialOrigem, filialDestino, nmCliente, nmDestinatario, descricao, pesoCarga, veiculo, status, saida, chegada );


            if (entregaDAO.atualizar(entrega)) {
                return "Entrega cadastrada com sucesso.";
            } else {
                return "Erro ao cadastrar entrega.";
            }
    }

    public String rastrearEntrega(String idEntrega) throws Exception {
        Entrega entrega = entregaDAO.selecionarPorId(idEntrega);
        return entrega.toString();
    }

    public String gerarRelatorioEntrega(LocalDate dataInicio, LocalDate dataFim) throws Exception {
        ArrayList<Entrega> entregasEntregues = entregaDAO.gerarRelatorio(dataInicio, dataFim);
        long totalDias = ChronoUnit.DAYS.between(dataInicio, dataFim);
        int totalEntregas = entregasEntregues.size();
        Double media =  totalEntregas / Double.parseDouble(String.valueOf(totalDias));

        StringBuilder sb = new StringBuilder();
        if (entregasEntregues.size() == 0) {
            return "Nenhuma entrega encontrada.";
        }
        for (Entrega entrega : entregasEntregues) {
            sb.append(entrega).append('\n');
        }

        sb.append("Total de dias: ").append(totalDias).append('\n');
        sb.append("Total de entregas: ").append(totalEntregas).append('\n');
        sb.append("Média de entregas feitas no período: ").append(media).append("\n");
        return sb.toString();
    }

}
