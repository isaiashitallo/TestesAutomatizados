package org.iftm.gerenciadorveterinarios.entities;

import java.math.BigDecimal;

public class Funcionario {

    private Integer id;
    private String nome;
    private String cargo;
    private BigDecimal salario;
    private Boolean emFerias;

    public Funcionario() {
    }

    public Funcionario(Integer id,
                        String nome,
                        String cargo,
                        BigDecimal salario,
                        Boolean emFerias) {

        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.emFerias = emFerias;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Boolean getEmFerias() {
        return emFerias;
    }

    public void setEmFerias(Boolean emFerias) {
        this.emFerias = emFerias;
    }
}