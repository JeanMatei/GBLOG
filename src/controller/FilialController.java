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

    public Integer selecionarFuncionalidadeFilial() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Selecione uma funcionalidade: %n1 - Cadastrar Filial %n2 - Alterar Filial %n3 - Remover Filiais %n4 - Listar Filiais%n5 - Sair%n ");
        Integer funcionalidadeFilial = scanner.nextInt();

        return funcionalidadeFilial;
    }

    public void inicializarFilial(Integer funcionalidadeFilial) {
        switch (funcionalidadeFilial) {
            case 1:
                filialService.inserirFilial("", "");
                break;
            case 2:
                filialService.alterarFilial();
                break;
            case 3:
                filialService.excluirFilial(1L);
                break;
            case 4:
                filialService.listarFilial();
                break;
            case 5:
                break;
        }
    }
}
