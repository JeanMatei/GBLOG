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
        } while (!opcao.equals("6"));
    }

    public String inicializarEntrega(String opcao) throws Exception {
        switch (opcao) {
            case "1":
                return
                entregaService.inserirEntrega(
                        "3",
                        "2",
                        "José Vitor",
                        "Gabriel Barbosa",
                        "Carga frágil",
                        12.6,
                        "QBT-0002",
                        "Em andamento",
                        LocalDate.of(2000, 3, 2),
                        LocalDate.of(2000, 3, 10)
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
                        "QBT-0002",
                        "Entregue",
                        LocalDate.of(2000,1,02),
                        LocalDate.of(2000, 12, 30)
                );
            case "3":
                Scanner scanner = new Scanner(System.in);
                String idEntrega;
                System.out.println("ID da entrega a ser removida: ");
                idEntrega = scanner.nextLine();
                return entregaService.excluirEntrega(idEntrega);
            case "4":
                return entregaService.listarEntrega();
            case "5":
                return entregaService.rastrearEntrega("F35D9D");
            case "6" :
                break;
            default:
                return "Opção inválida!";
        }
        return "";
    }
}
