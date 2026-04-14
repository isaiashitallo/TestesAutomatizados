package teste.java.br.edu.testes.tdd.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

    @Test
    void testarModificarHorasAbaixoLimiteGeraErro() {
        Funcionario f = new Funcionario();

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            f.setHorasTrabalhadas(10); // menor que 20 mensais
        });

        assertEquals("Horas devem estar entre 20 e 160 por mês.", ex.getMessage());
    }

    @Test
    void testarModificarHorasValidas() {
        Funcionario f = new Funcionario();
        f.setHorasTrabalhadas(160);
        f.setValorPorHora(50);

        double pagamento = f.calcularPagamento();

        assertTrue(pagamento >= 1518.00 && pagamento <= 10000.00);
    }

    @Test
    void testarValorHoraAbaixoLimite() {
        Funcionario f = new Funcionario();

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            f.setValorPorHora(5); // abaixo de 1% do salário mínimo
        });

        assertEquals("Valor por hora inválido.", ex.getMessage());
    }

    @Test
    void testarPagamentoAcimaTeto() {
        Funcionario f = new Funcionario();
        f.setHorasTrabalhadas(160);
        f.setValorPorHora(200); // força ultrapassar 10k

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            f.calcularPagamento();
        });

        assertEquals("Pagamento ultrapassa o teto permitido.", ex.getMessage());
    }
}