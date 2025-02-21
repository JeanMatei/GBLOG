package controller;

import dao.FilialDAO;
import service.EntregaService;
import service.FilialService;

import java.util.Scanner;

public class FilialController {
    private final FilialService filialService;

    public FilialController(FilialService filialService) {
        this.filialService = filialService;
    }

    public void selecionarFuncionalidadeFilial() throws Exception {
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
            String resultado = inicializarFilial(opcao);
            System.out.println("\n" + resultado + "\n");

        } while (!opcao.equals("5"));
    }

    public String inicializarFilial(String opcao) throws Exception {
        switch (opcao) {
            case "1":
                return filialService.cadastrarFilial("São Paulo", "SP");
            case "2":
                return filialService.alterarFilial("1", "Belo Horizonte", "MG");
            case "3":
                return filialService.excluirFilial("1");
            case "4":
                return filialService.listarFilial();
            case "5":
                return "Saindo do sistema de filiais.";
            default:
                return "Opção indisponível";
        }
    }
}
