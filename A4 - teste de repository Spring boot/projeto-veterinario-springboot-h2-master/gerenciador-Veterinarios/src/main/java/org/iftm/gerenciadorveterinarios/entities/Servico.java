package org.iftm.gerenciadorveterinarios.entities;

import java.math.BigDecimal;

public class Servico {

    private Integer id;
    private String nome;
    private BigDecimal valor;
    private Integer tempoMinutos;
    private Boolean disponivel;

    public Servico() {
    }

    public Servico(Integer id,
                    String nome,
                    BigDecimal valor,
                    Integer tempoMinutos,
                    Boolean disponivel) {

        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.tempoMinutos = tempoMinutos;
        this.disponivel = disponivel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getTempoMinutos() {
        return tempoMinutos;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}