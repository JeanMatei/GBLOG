package controller;

import service.VeiculoService;

import java.util.Scanner;

public class VeiculoController {
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    public Integer selecionarFuncionalidadeVeiculo() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Selecione uma funcionalidade: %n1 - Cadastrar Veículo %n2 - Alterar Veículo %n3 - Remover Veículos %n4 - Listar Veículos%n5 - Sair%n ");
        Integer funcionalidadeVeiculo = scanner.nextInt();

        return funcionalidadeVeiculo;
    }

    public void inicializarVeiculo(Integer funcionalidadeVeiculo) {
        switch (funcionalidadeVeiculo) {
            case 1:
                veiculoService.inserirVeiculo(null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                break;
            case 2:
                veiculoService.alterarVeiculo();
                break;
            case 3:
                veiculoService.excluirVeiculo(1L);
                break;
            case 4:
                veiculoService.listarVeiculo();
                break;
            case 5:
                break;
        }
    }
}
