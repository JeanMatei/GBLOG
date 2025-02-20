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
            sb.append("LISTA DE FILIAIS\n");
            for (Filial filial : filiais) {
                sb.append(filial).append('\n');
            }
        } else {
            sb.append("Nenhuma filial encontrada");
        }
        return sb.toString();
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

