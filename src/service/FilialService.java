package service;

import dao.FilialDAO;
import model.Filial;

import java.util.ArrayList;

public class FilialService {

    private FilialDAO filialDAO;

    public FilialService(FilialDAO filialDAO) {
        this.filialDAO = filialDAO;
    }

    //Métodos públicos
    public String listar() throws Exception {
        ArrayList<Filial> filials = filialDAO.selecionar();
        String lista = "";
        if (filials.size() > 0) {
            for (Filial filial : filials) {
                lista += filial + "\n";
            }
        } else {
            lista = "Nenhuma pessoa encotrada.";
        }
        return lista;
    }

    public String cadastrar(
            String estado,
            String cidade) throws Exception {
        Filial filial = new Filial(
                estado,
                cidade
        );
        if (filialDAO.inserir(filial)) {
            return "Pessoa cadastrada com sucesso!";
        } else {
            throw new Exception("Não foi possível cadastrar a pessoa!");
        }

    }

//    public String alterar(Long id, String cidade, String estado) throws Exception {
//    }
}

