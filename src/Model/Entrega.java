package Model;

import java.time.LocalDateTime;

public class Entrega {

    private String cdentrega;
    private String origem;
    private String destino;
    private String nmcliente;
    private String nmdestinatario;
    private String descricao;
    private Double ptcarga;
    private String veiculodes; // Veículo designado
    private String motoristarp;
    private String statusentrega;
    private LocalDateTime saida;
    private LocalDateTime chegada;

    public Entrega() {}

    public Entrega(String origem, String destino, String nmcliente, String nmdestinatario, String descricao, Double ptcarga, String veiculodes, String motoristarp, String statusentrega, LocalDateTime saida, LocalDateTime chegada) {
        this.origem = origem;
        this.destino = destino;
        this.nmcliente = nmcliente;
        this.nmdestinatario = nmdestinatario;
        this.descricao = descricao;
        this.ptcarga = ptcarga;
        this.veiculodes = veiculodes;
        this.motoristarp = motoristarp;
        this.statusentrega = statusentrega;
        this.saida = saida;
        this.chegada = chegada;
    }

    public Entrega(String cdentrega, String origem, String destino, String nmcliente, String nmdestinatario, String descricao, Double ptcarga, String veiculodes, String motoristarp, String statusentrega, LocalDateTime saida, LocalDateTime chegada) {
        this.cdentrega = cdentrega;
        this.origem = origem;
        this.destino = destino;
        this.nmcliente = nmcliente;
        this.nmdestinatario = nmdestinatario;
        this.descricao = descricao;
        this.ptcarga = ptcarga;
        this.veiculodes = veiculodes;
        this.motoristarp = motoristarp;
        this.statusentrega = statusentrega;
        this.saida = saida;
        this.chegada = chegada;
    }

    public String getCdentrega() {
        return cdentrega;
    }

    public void setCdentrega(String cdentrega) {
        this.cdentrega = cdentrega;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getNmcliente() {
        return nmcliente;
    }

    public void setNmcliente(String nmcliente) {
        this.nmcliente = nmcliente;
    }

    public String getNmdestinatario() {
        return nmdestinatario;
    }

    public void setNmdestinatario(String nmdestinatario) {
        this.nmdestinatario = nmdestinatario;
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

    public String getVeiculodes() {
        return veiculodes;
    }

    public void setVeiculodes(String veiculodes) {
        this.veiculodes = veiculodes;
    }

    public String getMotoristarp() {
        return motoristarp;
    }

    public void setMotoristarp(String motoristarp) {
        this.motoristarp = motoristarp;
    }

    public String getStatusentrega() {
        return statusentrega;
    }

    public void setStatusentrega(String statusentrega) {
        this.statusentrega = statusentrega;
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
        return "cdentrega: " + cdentrega  +
                "| origem: " + origem +
                "| destino: " + destino +
                "| nmcliente: " + nmcliente +
                "| nmdestinatario: " + nmdestinatario  +
                "| descricao: " + descricao +
                "| ptcarga: " + ptcarga +
                "| veiculodes: " + veiculodes +
                "| motoristarp: " + motoristarp +
                "| statusentrega: " + statusentrega +
                "| saida: " + saida +
                "| chegada: " + chegada;
    }
}