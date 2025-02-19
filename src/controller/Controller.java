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

    public Integer selecionarFuncionalidade() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Selecione uma funcionalidade: %n1 - Gerenciar Entregas %n2 - Gerenciar Veículos %n3 - Gerenciar Filiais %n4 - Relatórios %n5 - Sair%n ");
        Integer funcionalidade = scanner.nextInt();
        return funcionalidade;
    }

    public void inicializar(Integer funcionalidade) {
        switch (funcionalidade) {
            case 1:
                entregaController.selecionarFuncionalidadeEntrega();
                break;
            case 2:
                veiculoController.selecionarFuncionalidadeVeiculo();
                break;
            case 3:
                filialController.selecionarFuncionalidadeFilial();
                break;
            case 4:
                //Aqui virá a funcionalidade de mostrar os relatórios
                break;
            case 5:
                break;

        }
    }
}
