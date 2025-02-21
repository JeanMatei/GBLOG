package model;

import java.sql.Date;

public class Entrega {
    private String cdEntrega;
    private Filial origem;
    private Filial destino;
    private String nmCliente;
    private String nmDestinatario;
    private String descricao;
    private Double ptcarga;
    private Veiculo veiculo; // Veículo designado
    private String status;
    private Date saida;
    private Date chegada;

    public Entrega() {}

    public Entrega(Filial origem, Filial destino, String nmCliente, String nmDestinatario, String descricao, Double ptcarga, Veiculo veiculo, String status, Date saida, Date chegada) {
        this.origem = origem;
        this.destino = destino;
        this.nmCliente = nmCliente;
        this.nmDestinatario = nmDestinatario;
        this.descricao = descricao;
        this.ptcarga = ptcarga;
        this.veiculo = veiculo;
        this.status = status;
        this.saida = saida;
        this.chegada = chegada;
    }

    public Entrega(String cdEntrega, Filial origem, Filial destino, String nmCliente, String nmDestinatario, String descricao, Double ptcarga, Veiculo veiculo, String status, Date saida, Date chegada) {
        this.cdEntrega = cdEntrega;
        this.origem = origem;
        this.destino = destino;
        this.nmCliente = nmCliente;
        this.nmDestinatario = nmDestinatario;
        this.descricao = descricao;
        this.ptcarga = ptcarga;
        this.veiculo = veiculo;
        this.status = status;
        this.saida = saida;
        this.chegada = chegada;
    }

    public String getCdEntrega() {
        return cdEntrega;
    }

    public void setCdentrega(String cdentrega) {
        this.cdEntrega = cdentrega;
    }

    public Filial getOrigem() {
        return origem;
    }

    public void setOrigem(Filial origem) {
        this.origem = origem;
    }

    public Filial getDestino() {
        return destino;
    }

    public void setDestino(Filial destino) {
        this.destino = destino;
    }

    public String getNmcliente() {
        return nmCliente;
    }

    public void setNmcliente(String nmcliente) {
        this.nmCliente = nmcliente;
    }

    public String getNmdestinatario() {
        return nmDestinatario;
    }

    public void setNmdestinatario(String nmdestinatario) {
        this.nmDestinatario = nmdestinatario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPtcarga() {
        return ptcarga;
    }

    public void setPtcarga(Double ptcarga) {
        this.ptcarga = ptcarga;
    }

    public Veiculo getVeiculodes() {
        return veiculo;
    }

    public void setVeiculodes(Veiculo veiculodes) {
        this.veiculo = veiculodes;
    }

    public String getStatusentrega() {
        return status;
    }

    public void setStatusentrega(String statusentrega) {
        this.status = statusentrega;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public Date getChegada() {
        return chegada;
    }

    public void setChegada(Date chegada) {
        this.chegada = chegada;
    }

    @Override
    public String toString() {
        return "Código: " + cdEntrega  +
                "| Origem: " + origem +
                "| Destino: " + destino +
                "| Remetente: " + nmCliente +
                "| Destinatário: " + nmDestinatario  +
                "| Descrição: " + descricao +
                "| Peso: " + ptcarga +
                "| Veículo: " + veiculo +
                "| Status: " + status +
                "| Horário de Saída: " + saida +
                "| Horário de Chegada: " + chegada;
    }
}