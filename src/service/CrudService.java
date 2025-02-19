package service;

import model.Identificavel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrudService<T extends Identificavel> {
    private List<T> entidades = new ArrayList<>();

    // Cadastra uma nova entidade
    public void cadastrar(T entidade) {
        entidades.add(entidade);
        System.out.println("Entidade cadastrada com sucesso!");
    }

    // Altera uma entidade existente com base no código único
    public void alterar(String codigoUnico, T novaEntidade) {
        Optional<T> entidadeExistente = buscarPorCodigoUnico(codigoUnico);
        if (entidadeExistente.isPresent()) {
            int indice = entidades.indexOf(entidadeExistente.get());
            entidades.set(indice, novaEntidade);
            System.out.println("Entidade alterada com sucesso!");
        } else {
            System.out.println("Entidade com código " + codigoUnico + " não encontrada.");
        }
    }

    // Lista todas as entidades
    public void listar() {
        if (entidades.isEmpty()) {
            System.out.println("Nenhuma entidade cadastrada.");
        } else {
            System.out.println("Lista de entidades:");
            for (T entidade : entidades) {
                System.out.println(entidade);
            }
        }
    }

    // Exclui uma entidade com base no código único
    public void excluir(String codigoUnico) {
        Optional<T> entidadeExistente = buscarPorCodigoUnico(codigoUnico);
        if (entidadeExistente.isPresent()) {
            entidades.remove(entidadeExistente.get());
            System.out.println("Entidade excluída com sucesso!");
        } else {
            System.out.println("Entidade com código " + codigoUnico + " não encontrada.");
        }
    }

    // Busca uma entidade pelo código único
    public Optional<T> buscarPorCodigoUnico(String codigoUnico) {
        return entidades.stream()
                .filter(entidade -> entidade.getCodigoUnico().equals(codigoUnico))
                .findFirst();
    }
}
