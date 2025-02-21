package service;

import dao.FilialDAO;
import dao.VeiculoDAO;
import model.Veiculo;

import java.time.LocalDate;
import java.util.ArrayList;

public class VeiculoService {
    private VeiculoDAO veiculoDAO;
    private FilialDAO filialDAO;
    public VeiculoService(VeiculoDAO veiculoDAO, FilialDAO filialDAO) {
        this.filialDAO = filialDAO;
        this.veiculoDAO = veiculoDAO;
    }

    public String inserirVeiculo( Double capacidade , String modelo, String tipoVeiculo, String ano, String disponibilidade, Double quilometragem, LocalDate manutencao) {
        return null;
    }

    public String listarVeiculo() throws Exception {
        ArrayList<Veiculo> veiculos = veiculoDAO.selecionar();
        StringBuilder sbVeiculo = new StringBuilder();
        if (veiculos.size() > 0) {
            veiculos.forEach(veiculo -> {
                sbVeiculo.append(veiculo);
                sbVeiculo.append("\n");
            });
        }
        return sbVeiculo.toString();
    }

    public String excluirVeiculo(Long idEntrega) {
        return "";
    }

    public String alterarVeiculo() {
        return "";
    }


}
