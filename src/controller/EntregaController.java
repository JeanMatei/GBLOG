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

    public Integer selecionarFuncionalidadeEntrega() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Selecione uma funcionalidade: %n1 - Cadastrar Entrega %n2 - Alterar Entrega %n3 - Remover Entregas %n4 - Listar Entregas%n5 - Sair%n ");
        Integer funcionalidadeEntrega = scanner.nextInt();
        return funcionalidadeEntrega;
    }

    public void inicializarEntrega(Integer funcionalidadeEntrega) {
        switch (funcionalidadeEntrega) {
            case 1:
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
            case 2:
                entregaService.alterarEntrega();
                break;
            case 3:
                entregaService.excluirEntrega(1L);
                break;
            case 4:
                entregaService.listarEntrega();
                break;
            case 5:
                break;
        }
    }
}
