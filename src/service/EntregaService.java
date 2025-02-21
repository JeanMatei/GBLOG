package service;

//import dao.EntregaDAO;

import controller.EntregaController;
import dao.EntregaDAO;
import model.Entrega;
import model.Filial;
import model.Veiculo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class EntregaService {
    private final EntregaDAO entregaDAO;

    public EntregaService(EntregaDAO entregaDAO) {
        this.entregaDAO = entregaDAO;
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

    public String inserirEntrega(Long idFilialOrigem, Long idFilialDestino, String nmCliente, String nmDestinatario, String descricao, Double pesoCarga, String placaVeiculo, String status,
                                 LocalDateTime saida, LocalDateTime chegada) {
        return "";
    }

    public String excluirEntrega(Long idEntrega) {
        return "";
    }

    public String alterarEntrega() {
        return "";
    }


}
