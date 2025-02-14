package Controller;

import enumeration.Funcionalidade;

public class EntregaController {

    private String pessoaTest;

    //Gerenciador de Tarefas
    public String testar(Funcionalidade funcionalidade) {
        switch (funcionalidade){
            case LISTAR:
                return pessoaTest.listar();
            case CADASTRAR:
                return pessoaTest.cadastrar();
            case ALTERAR:
                return pessoaTest.alterar();
            case EXCLUIR:
                return pessoaTest.excluir();
            default:
                return null;
        }
    }
}
