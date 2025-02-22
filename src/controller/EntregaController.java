package controller;

import service.EntregaService;
import service.FilialService;
import service.VeiculoService;

import java.time.LocalDate;
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
                            "1 - Cadastrar Entrega %n" +
                            "2 - Alterar Entrega %n" +
                            "3 - Remover Entrega %n" +
                            "4 - Listar Entregas %n" +
                            "5 - Sair %n" +
                            "Opção: ");

            opcao = scanner.nextLine();
            String resultado = inicializarEntrega(opcao);
            System.out.println(resultado + "\n");
        } while (!opcao.equals("5"));
    }

    public String inicializarEntrega(String opcao) throws Exception {
        switch (opcao) {
            case "1":
                return
                entregaService.inserirEntrega(
                        "3",
                        "2",
                        "João Neto da Silva",
                        "Maria de Lourdes Pacheco",
                        "Carga frágil",
                        12.6,
                        "QBX-0001",
                        "Em andamento",
                        LocalDate.of(2000,12,02),
                        LocalDate.of(2000, 12, 10)
                );
            case "2":
                return entregaService.alterarEntrega();
            case "3":
                return entregaService.excluirEntrega(1L);
            case "4":
                return entregaService.listarEntrega();
            case "5":
                return "Saindo das entregas...";
            default:
                return "Opção inválida!";
        }
    }
}
