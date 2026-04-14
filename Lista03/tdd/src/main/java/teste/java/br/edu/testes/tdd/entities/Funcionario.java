package teste.java.br.edu.testes.tdd.entities;

public class Funcionario {

    private String nome;
    private int horasTrabalhadas;
    private double valorPorHora;

    private static final double SALARIO_MINIMO = 1518.00;

    public void setHorasTrabalhadas(int horas) {
        if (horas < 20 || horas > 160) {
            throw new IllegalArgumentException("Horas devem estar entre 20 e 160 por mês.");
        }
        this.horasTrabalhadas = horas;
    }

    public void setValorPorHora(double valor) {
        double min = SALARIO_MINIMO * 0.01;
        double max = SALARIO_MINIMO * 0.10;

        if (valor < min || valor > max) {
            throw new IllegalArgumentException("Valor por hora inválido.");
        }
        this.valorPorHora = valor;
    }

    public double calcularPagamento() {
        double pagamento = horasTrabalhadas * valorPorHora;

        if (pagamento < SALARIO_MINIMO) {
            throw new IllegalArgumentException("Pagamento abaixo do mínimo.");
        }

        if (pagamento > 10000.00) {
            throw new IllegalArgumentException("Pagamento ultrapassa o teto permitido.");
        }

        return pagamento;
    }
}