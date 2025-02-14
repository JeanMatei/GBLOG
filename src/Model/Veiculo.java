package Model;

import java.time.LocalDateTime;

public class Veiculo {

   private String placa;
   private Double capacidade;
   private String modelo;
   private String tpveiculo; //tipo de Veículo
   private LocalDateTime anofb;
   private Boolean disponivel;
   private Double quilometragem;
   private LocalDateTime manutencao;

    // Construtor vazio
    public Veiculo() {}

    // Construtor sem Placa
    public Veiculo(Double capacidade, String modelo, String tpveiculo, LocalDateTime anofb, Boolean disponivel, Double quilometragem, LocalDateTime manutencao) {
        this.capacidade = capacidade;
        this.modelo = modelo;
        this.tpveiculo = tpveiculo;
        this.anofb = anofb;
        this.disponivel = disponivel;
        this.quilometragem = quilometragem;
        this.manutencao = manutencao;
    }

    // Construtor Completo
    public Veiculo(String placa, Double capacidade, String modelo, String tpveiculo, LocalDateTime anofb, Boolean disponivel, Double quilometragem, LocalDateTime manutencao) {
        this.placa = placa;
        this.capacidade = capacidade;
        this.modelo = modelo;
        this.tpveiculo = tpveiculo;
        this.anofb = anofb;
        this.disponivel = disponivel;
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
        return tpveiculo;
    }

    public void setTpveiculo(String tpveiculo) {
        this.tpveiculo = tpveiculo;
    }

    public LocalDateTime getAnofb() {
        return anofb;
    }

    public void setAnofb(LocalDateTime anofb) {
        this.anofb = anofb;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public LocalDateTime getManutencao() {
        return manutencao;
    }

    public void setManutencao(LocalDateTime manutencao) {
        this.manutencao = manutencao;
    }

    @Override
    public String toString() {
        return "placa: " + placa  +
                "| capacidade: " + capacidade +
                "| modelo: " + modelo  +
                "| tpveiculo: " + tpveiculo  +
                "| anofb=" + anofb +
                "| disponivel=" + disponivel +
                "| quilometragem=" + quilometragem +
                "| manutencao=" + manutencao;
    }
}
