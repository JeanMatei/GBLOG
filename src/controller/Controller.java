package controller;

import model.Filial;

import java.util.Scanner;

public class Controller {

    private final FilialController filialController;
    private final EntregaController entregaController;
    private final VeiculoController veiculoController;

    public Controller(FilialController filialController, EntregaController entregaController, VeiculoController veiculoController) {
        this.filialController = filialController;
        this.entregaController = entregaController;
        this.veiculoController = veiculoController;
    }

    public void selecionarFuncionalidade() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String funcionalidade;
        do {
            System.out.printf("" +
                    "Selecione uma funcionalidade: %n1 - Gerenciar Entregas " +
                    "%n2 - Gerenciar Veículos " +
                    "%n3 - Gerenciar Filiais " +
                    "%n4 - Relatórios " +
                    "%n5 - Sair%n ");

            funcionalidade = scanner.nextLine();
            inicializar(funcionalidade);
        } while(!funcionalidade.equals("5"));
    }

    public void inicializar(String funcionalidade) throws Exception {
        switch (funcionalidade) {
            case "1":
                entregaController.selecionarFuncionalidadeEntrega();
                break;
            case "2":
                veiculoController.selecionarFuncionalidadeVeiculo();
                break;
            case "3":
                filialController.selecionarFuncionalidadeFilial();
                break;
            case "4":
                // Implementar relatórios
                System.out.println("Funcionalidade de relatórios não implementada");
                break;
            case "5":
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
}
