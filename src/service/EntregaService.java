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
        System.out.println("Entrou aqui");
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
        Filial filialDestino = filialDAO.selecionarFilialPorId(idFilialDestino);
        Veiculo veiculo = veiculoDAO.selecionarPorId(placaVeiculo);
        Entrega entrega = new Entrega(filialOrigem, filialDestino, nmCliente, nmDestinatario, descricao, pesoCarga, veiculo, status, saida, chegada );
        if (entregaDAO.inserir(entrega)) {
            return "Entrega cadastrada com sucesso.";
        } else {
            return "Erro ao cadastrar entrega.";
        }
    }

    public String excluirEntrega(Long idEntrega) {
        return "";
    }

    public String alterarEntrega() {
        return "";
    }


}
