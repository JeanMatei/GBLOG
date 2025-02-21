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

    public String cadastrarFilial(String cidade, String estado) throws Exception {

        Filial filial = new Filial(cidade, estado);
        if (filialDAO.inserir(filial)) {
            return "Filial cadastrada com sucesso!";
        } else  {
            throw new FilialException("Erro ao cadastrar a filial.");
        }
    }

    public String excluirFilial(String idFilial) throws Exception {
        if (filialDAO.deletar(idFilial)) {
            return "Filial excluída com sucesso!";
        } else {
            return "Erro ao excluir a filial.";
        }
    }

    public String alterarFilial(String idFilial, String cidade, String estado) throws Exception {

        Filial filial = new Filial(idFilial, cidade, estado);
        if (filialDAO.atualizar(filial)) {
            return "Filial alterada com sucesso!";
        } else {
            return "Erro ao alterar a filial.";
        }
    }

}

