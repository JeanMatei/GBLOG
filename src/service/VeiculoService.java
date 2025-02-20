package service;

import dao.VeiculoDAO;
import model.Veiculo;

import java.time.LocalDate;
import java.util.ArrayList;

public class VeiculoService {
    private VeiculoDAO veiculoDAO;

    public VeiculoService(VeiculoDAO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }

    public String inserirVeiculo( Double capacidade , String modelo, String tipoVeiculo, String ano, String disponibilidade, Double quilometragem, LocalDate manutencao) {
        return "";
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
