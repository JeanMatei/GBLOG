package model;

import util.GerarCodigoEntrega;

import java.time.LocalDateTime;

public class Entrega implements Identificavel {
    private String cdEntrega;
    private Filial origem;
    private Filial destino;
    private String nmCliente;
    private String nmDestinatario;
    private String descricao;
    private Double ptcarga;
    private Veiculo veiculo; // Veículo designado
    private String status;
    private LocalDateTime saida;
    private LocalDateTime chegada;

    public Entrega() {}

    public Entrega(Filial origem, Filial destino, String nmcliente, String nmdestinatario, String descricao, Double ptcarga, Veiculo veiculo, String status, LocalDateTime saida, LocalDateTime chegada) {
        this.origem = origem;
        this.destino = destino;
        this.nmCliente = nmcliente;
        this.nmDestinatario = nmdestinatario;
        this.descricao = descricao;
        this.ptcarga = ptcarga;
        this.veiculo = veiculo;
        this.status = status;
        this.saida = saida;
        this.chegada = chegada;
        this.cdEntrega = GerarCodigoEntrega.gerarCodigo();
    }

    public Entrega(String cdentrega, Filial origem, Filial destino, String nmcliente, String nmdestinatario, String descricao, Double ptcarga, Veiculo veiculodes, String statusentrega, LocalDateTime saida, LocalDateTime chegada) {
        this.cdEntrega = cdentrega;
        this.origem = origem;
        this.destino = destino;
        this.nmCliente = nmcliente;
        this.nmDestinatario = nmdestinatario;
        this.descricao = descricao;
        this.ptcarga = ptcarga;
        this.veiculo = veiculodes;
        this.status = statusentrega;
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

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public LocalDateTime getChegada() {
        return chegada;
    }

    public void setChegada(LocalDateTime chegada) {
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

    public String getCodigoUnico() {
        return cdEntrega;
    }
}