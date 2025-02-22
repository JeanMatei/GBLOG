package service;

import dao.FilialDAO;
import dao.VeiculoDAO;
import model.Filial;
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

    public String inserirVeiculo(String placa, Double capacidade , String modelo, String tipoVeiculo, String ano, String disponibilidade, Double quilometragem, LocalDate manutencao, String idFilial) throws Exception {
        Filial filial = filialDAO.selecionarFilialPorId(idFilial);
        Veiculo veiculo = new Veiculo(placa, capacidade, modelo, tipoVeiculo, ano, disponibilidade, quilometragem, manutencao, filial);
        if (veiculoDAO.inserir(veiculo)) {
            return "Veiculo cadastrado com sucesso!";
        } else {
            return "Erro ao cadastrar o veiculo.";
        }
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

    public String excluirVeiculo(String placa) throws Exception {
        if (veiculoDAO.deletar(placa)) {
            return "Veículo excluído com sucesso!";
        } else {
            return "Erro ao excluir veículo.";
        }
    }

    public String alterarVeiculo(String placa, Double capacidade , String modelo, String tipoVeiculo, String ano, String disponibilidade, Double quilometragem, LocalDate manutencao, String idFilial) throws Exception {
        Filial filial = filialDAO.selecionarFilialPorId(idFilial);
        Veiculo veiculo = new Veiculo(placa, capacidade, modelo, tipoVeiculo, ano, disponibilidade, quilometragem, manutencao, filial);
        if (veiculoDAO.atualizar(veiculo)) {
            return "Veículo alterado com sucesso!";
        } else {
            return "Erro ao alterar o veículo";
        }
    }


}
