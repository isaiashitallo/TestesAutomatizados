package teste.java.br.edu.testes.tdd.entities;

public class FuncionarioTerceirizado extends Funcionario {

    private double despesaAdicional;

    public void setDespesaAdicional(double despesa) {
        if (despesa > 1000) {
            throw new IllegalArgumentException("Despesa adicional não pode ultrapassar 1000.");
        }
        this.despesaAdicional = despesa;
    }

    @Override
    public double calcularPagamento() {
        double base = super.calcularPagamento();
        double bonus = despesaAdicional * 1.1;

        double total = base + bonus;

        if (total > 10000.00) {
            throw new IllegalArgumentException("Pagamento ultrapassa o teto permitido.");
        }

        return total;
    }
}