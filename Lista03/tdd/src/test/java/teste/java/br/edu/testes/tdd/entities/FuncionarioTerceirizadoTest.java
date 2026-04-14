package teste.java.br.edu.testes.tdd.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTerceirizadoTest {

    @Test
    void testarDespesaAcimaLimiteGeraErro() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado();

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            f.setDespesaAdicional(1500);
        });

        assertEquals("Despesa adicional não pode ultrapassar 1000.", ex.getMessage());
    }

    @Test
    void testarPagamentoComBonus() {
        FuncionarioTerceirizado f = new FuncionarioTerceirizado();
        f.setHorasTrabalhadas(160);
        f.setValorPorHora(40);
        f.setDespesaAdicional(500);

        double pagamento = f.calcularPagamento();

        assertTrue(pagamento > 0);
    }
}