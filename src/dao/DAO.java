package dao;

import java.util.ArrayList;

public interface DAO<T> {

    public ArrayList<T> selecionar ()throws Exception;
    public Boolean inserir(T obj) throws Exception;
    public Boolean atualizar(T obj) throws Exception;
    public Boolean deletar(String id) throws Exception;
//    public T selecionarPorId(String id) throws Exception;
}
