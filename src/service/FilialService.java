package service;

import dao.FilialDAO;
import exception.FilialException;
import model.Filial;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FilialService {

    private FilialDAO filialDAO;

    public FilialService(FilialDAO filialDAO) {
        this.filialDAO = filialDAO;
    }

    public String listarFilial() throws Exception {
        ArrayList<Filial> filiais = filialDAO.selecionar();
        StringBuilder sb = new StringBuilder();
        if (filiais.size() > 0) {
            for (Filial filial : filiais) {
                sb.append(filial).append('\n');
                System.out.println("Filial: " + filial);
            }
        } else {
            sb.append("Nenhuma filial encontrada");
        }
        return "--------------------------";
    }

    public String inserirFilial(String cidade, String estado) {
        return "";
    }

    public String excluirFilial(Long idEntrega) {
        return "";
    }

    public String alterarFilial() {
        return "";
    }

}

