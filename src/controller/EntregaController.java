package controller;

import service.EntregaService;
import service.FilialService;
import service.VeiculoService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EntregaController {
    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    public void selecionarFuncionalidadeEntrega() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String opcao;
        do {
            System.out.printf(
                    "Selecione uma funcionalidade: %n" +
                            "1 - Cadastrar Filial %n" +
                            "2 - Alterar Filial %n" +
                            "3 - Remover Filiais %n" +
                            "4 - Listar Filiais %n" +
                            "5 - Sair %n" +
                            "Opção: ");

            opcao = scanner.nextLine();
            inicializarEntrega(opcao);
        } while (!opcao.equals("5"));
    }

    public void inicializarEntrega(String funcionalidadeEntrega) throws Exception {
        switch (funcionalidadeEntrega) {
            case "1":
                entregaService.inserirEntrega(
                        1L,
                        2L,
                        "asdasd",
                        "asdsad",
                        "asdasdas",
                        12.6,
                        "QBX-0001",
                        "Entregue",
                        null,
                        null
                );
                break;
            case "2":
                entregaService.alterarEntrega();
                break;
            case "3":
                entregaService.excluirEntrega(1L);
                break;
            case "4":
                entregaService.listarEntrega();
                break;
            case "5":
                break;
        }
    }
}
