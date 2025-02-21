package controller;

import service.VeiculoService;

import java.util.Scanner;

public class VeiculoController {
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    public void selecionarFuncionalidadeVeiculo() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String opcao;
        do {
            System.out.printf(
                    "Selecione uma funcionalidade: %n" +
                            "1 - Cadastrar Veículo %n" +
                            "2 - Alterar Veículo %n" +
                            "3 - Remover Veículo %n" +
                            "4 - Listar Veículo %n" +
                            "5 - Sair %n" +
                            "Opção: ");

            opcao = scanner.nextLine();
            String resultado = inicializarVeiculo(opcao);
            System.out.println("\n" + resultado + "\n");

        } while (!opcao.equals("5"));
    }

    public String inicializarVeiculo(String opcao) throws Exception {
        switch (opcao) {
            case "1":
                 return veiculoService.inserirVeiculo(null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
            case "2":
                return veiculoService.alterarVeiculo();
            case "3":
                return veiculoService.excluirVeiculo(1L);
            case "4":
                return veiculoService.listarVeiculo();
            case "5":
                break;
        }
        return "";
    }
}
