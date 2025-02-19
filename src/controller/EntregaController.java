package controller;

import java.util.Scanner;

public class EntregaController {

    public Integer selecionarFuncionalidadeEntrega() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Selecione uma funcionalidade: %n1 - Cadastrar Entrega %n2 - Alterar Entrega %n3 - Remover Entregas %n4 - Listar Entregas%n ");
        Integer funcionalidadeEntrega = scanner.nextInt();
        return funcionalidadeEntrega;
    }

    public void inicializarEntrega(Integer funcionalidadeEntrega) {
        switch (funcionalidadeEntrega) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }
}
