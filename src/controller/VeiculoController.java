package controller;

import service.VeiculoService;

import java.util.Scanner;

public class VeiculoController {
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    public String selecionarFuncionalidadeVeiculo() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Selecione uma funcionalidade: %n1 - Cadastrar Veículo %n2 - Alterar Veículo %n3 - Remover Veículos %n4 - Listar Veículos%n5 - Sair%n ");
        Integer funcionalidadeVeiculo = scanner.nextInt();

        return inicializarVeiculo(funcionalidadeVeiculo);
    }

    public String inicializarVeiculo(Integer funcionalidadeVeiculo) throws Exception {
        switch (funcionalidadeVeiculo) {
            case 1:
                 return veiculoService.inserirVeiculo(null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
            case 2:
                return veiculoService.alterarVeiculo();
            case 3:
                return veiculoService.excluirVeiculo(1L);
            case 4:
                return veiculoService.listarVeiculo();
            case 5:
                break;
        }
        return "";
    }
}
