package model;

public class Filial {

    private Long id;
    private String cidade;
    private String estado;

    public Filial() {}

    public Filial(String cidade, String estado) {
        this.cidade = cidade;
        this.estado = estado;
    }

    public Filial(Long id, String cidade, String estado) {
        this.id = id;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "| Cidade: " + cidade  +
                "| Estado: " + estado;
    }

}
