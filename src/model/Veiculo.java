package model;

import java.time.LocalDate;

public class Veiculo {

   private String placa;
   private Double capacidade;
   private String modelo;
   private String tpVeiculo; //tipo de Veículo
   private String ano;
   private String disponibilidade;
   private Double quilometragem;
   private LocalDate manutencao;

    // Construtor vazio
    public Veiculo() {}

    // Construtor sem Placa
    public Veiculo(Double capacidade, String modelo, String tpveiculo, String anofb, String disponibilidade, Double quilometragem, LocalDate manutencao) {
        this.capacidade = capacidade;
        this.modelo = modelo;
        this.tpVeiculo = tpveiculo;
        this.ano = anofb;
        this.disponibilidade = disponibilidade;
        this.quilometragem = quilometragem;
        this.manutencao = manutencao;
    }

    // Construtor Completo
    public Veiculo(String placa, Double capacidade, String modelo, String tpveiculo, String anofb, String disponibilidade, Double quilometragem, LocalDate manutencao) {
        this.placa = placa;
        this.capacidade = capacidade;
        this.modelo = modelo;
        this.tpVeiculo = tpveiculo;
        this.ano = anofb;
        this.disponibilidade = disponibilidade;
        this.quilometragem = quilometragem;
        this.manutencao = manutencao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTpveiculo() {
        return tpVeiculo;
    }

    public void setTpveiculo(String tpveiculo) {
        this.tpVeiculo = tpveiculo;
    }

    public String getAnofb() {
        return ano;
    }

    public void setAnofb(String anofb) {
        this.ano = anofb;
    }

    public String getDisponivel() {
        return disponibilidade;
    }

    public void setDisponivel(String disponivel) {
        this.disponibilidade = disponivel;
    }

    public Double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public LocalDate getManutencao() {
        return manutencao;
    }

    public void setManutencao(LocalDate manutencao) {
        this.manutencao = manutencao;
    }

    @Override
    public String toString() {
        return "Placa: " + placa  +
                "| Capacidade: " + capacidade +
                "| Modelo: " + modelo  +
                "| Tipo: " + tpVeiculo  +
                "| Ano: " + ano +
                "| Disponibilidade: " + disponibilidade +
                "| KM: " + quilometragem +
                "| Manutenção:" + manutencao;
    }
}
