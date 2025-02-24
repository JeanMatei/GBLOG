package controller;

import dao.VeiculoDAO;
import service.VeiculoService;

import java.time.LocalDate;
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
                            "5 - Buscar Veículo %n" +
                            "6 - Sair %n" +
                            "Opção: ");

            opcao = scanner.nextLine();
            String resultado = inicializarVeiculo(opcao);
            System.out.println("\n" + resultado + "\n");

        } while (!opcao.equals("6"));
    }

    public String inicializarVeiculo(String opcao) throws Exception {
        switch (opcao) {
            case "1":
                 return veiculoService.inserirVeiculo("QBX-0321",
                         500.00,
                        "Strada",
                        "Pick-Up",
                        "2007",
                        "Em trânsito",
                        123.000,
                         LocalDate.of(2007, 2, 1),
                         "2");
            case "2":
                return veiculoService.alterarVeiculo("QBT-0345"
                        ,500.00,
                        "Fiorino",
                        "Pick-Up",
                        "2007",
                        "Em trânsito",
                        123.000,
                        LocalDate.of(2007, 2, 1),
                        "2");
            case "3":
                return veiculoService.excluirVeiculo("QBX-0001");
            case "4":
                return veiculoService.listarVeiculo();
            case "5":
                return veiculoService.rastrearVeiculo("QBT-0002");
            case "6":
                break;
        }
        return "";
    }
}
