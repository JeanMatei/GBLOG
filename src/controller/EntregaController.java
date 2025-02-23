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
                            "5 - Rastrear Entrega por ID %n" +
                            "6 - Sair %n" +
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
                        "1",
                        "2",
                        "João Neto da Silva",
                        "Maria de Lourdes Pacheco",
                        "Carga frágil",
                        12.6,
                        "QBT-0345",
                        "Em andamento",
                        LocalDate.of(2000,12,02),
                        LocalDate.of(2000, 12, 10)
                );
            case "2":
                return entregaService.alterarEntrega(
                        "32r2fa",
                        "1",
                        "2",
                        "Cliente de Teste",
                        "Destinatario de Teste",
                        "Carga leve",
                        12.0,
                        "QBT-0345",
                        "Entregue",
                        LocalDate.of(2000,12,02),
                        LocalDate.of(2000, 12, 10)
                );
            case "3":
                return entregaService.excluirEntrega("30f86d4126034488a5cebd939bef6b4b");
            case "4":
                return entregaService.listarEntrega();
            case "5":
                return entregaService.rastrearEntrega("32r2fa");
            default:
                return "Opção inválida!";
        }
    }
}
