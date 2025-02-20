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

    public String selecionarFuncionalidadeFilial() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Selecione uma funcionalidade: %n1 - Cadastrar Filial %n2 - Alterar Filial %n3 - Remover Filiais %n4 - Listar Filiais%n5 - Sair%n ");
        String funcionalidadeFilial = scanner.nextLine();
        inicializarFilial(funcionalidadeFilial);
        return "PAROU NO SELECIONAR";
    }

    public String inicializarFilial(String funcionalidadeFilial) throws Exception {
        switch (funcionalidadeFilial) {
            case "1":
                return filialService.inserirFilial("", "");
            case "2":
                return filialService.alterarFilial();
            case "3":
                return filialService.excluirFilial(1L);
            case "4":
                System.out.println("teste");
                return filialService.listarFilial();
            case "5":
            break;
        }
        return "";
    }
}
