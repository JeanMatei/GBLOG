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

    public String selecionarFuncionalidade() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Selecione uma funcionalidade: %n1 - Gerenciar Entregas %n2 - Gerenciar Veículos %n3 - Gerenciar Filiais %n4 - Relatórios %n5 - Sair%n ");
        String funcionalidade = scanner.nextLine();
        return inicializar(funcionalidade);
    }

    public String inicializar(String funcionalidade) throws Exception {
        switch (funcionalidade) {
            case "1":
                entregaController.selecionarFuncionalidadeEntrega();
            case "2":
                return veiculoController.selecionarFuncionalidadeVeiculo();
            case "3":
                return filialController.selecionarFuncionalidadeFilial();
            case "4":
                //Aqui virá a funcionalidade de mostrar os relatórios
                break;
            case "5":
                break;

        }
        return "123";
    }
}
