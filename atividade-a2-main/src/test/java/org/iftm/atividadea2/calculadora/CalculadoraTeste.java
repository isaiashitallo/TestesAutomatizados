package org.iftm.atividadea2.calculadora;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.iftm.atividadea2.Calculadora;

public class CalculadoraTeste {

    @Test
    public void testeConstrutorVazioMemoriaZero() {

        Calculadora calculadora = new Calculadora();

        int memoriaAtual = calculadora.getMemoria();

        assertEquals(0, memoriaAtual);
    }

    @Test
    void deveInicializarMemoriaComValorPositivo() {
        Calculadora calc = new Calculadora(3);

        assertEquals(3, calc.getMemoria());
    }

    @Test
    void deveInicializarMemoriaComValorNegativo() {
        Calculadora calc = new Calculadora(-3);

        assertEquals(-3, calc.getMemoria());
    }

    @Test
    void deveSomarNumeroPositivo() {
        // Arrange
        Calculadora calc = new Calculadora(3);

        // Act
        calc.somar(5);

        // Assert
        assertEquals(8, calc.getMemoria());
    }

    @Test
    void deveSomarNumeroNegativo() {
        // Arrange
        Calculadora calc = new Calculadora(3);

        // Act
        calc.somar(-2);

        // Assert
        assertEquals(1, calc.getMemoria());
    }

    @Test
    void deveMultiplicarPorNumeroPositivo() {
        Calculadora calc = new Calculadora(3);

        calc.multiplicar(2);

        assertEquals(6, calc.getMemoria());
    }

    @Test
    void deveMultiplicarPorNumeroNegativo() {
        Calculadora calc = new Calculadora(3);

        calc.multiplicar(-2);

        assertEquals(-6, calc.getMemoria());
    }

    @Test
    void deveDividirPorNumeroPositivo() {
        Calculadora calc = new Calculadora(6);

        calc.dividir(2);

        assertEquals(3, calc.getMemoria());
    }

    @Test
    void deveDividirPorNumeroNegativo() {
        Calculadora calc = new Calculadora(6);

        calc.dividir(-2);

        assertEquals(-3, calc.getMemoria());
    }

    @Test
    void deveLancarExcecaoAoDividirPorZero() {
        Calculadora calc = new Calculadora(6);

        assertThrows(ArithmeticException.class, () -> {
            calc.dividir(0);
        });
    }

    @Test
    void deveExponenciarElevadoA1() {
        Calculadora calc = new Calculadora(3);

        calc.exponenciar(1);

        assertEquals(3, calc.getMemoria());
    }

    @Test
    void deveExponenciarElevadoA10() {
        Calculadora calc = new Calculadora(2);

        calc.exponenciar(10);

        assertEquals(1024, calc.getMemoria());
    }

    @Test
    void deveLancarExcecaoParaExpoenteMaiorQue10() {
        Calculadora calc = new Calculadora(2);

        assertThrows(ArithmeticException.class, () -> {
            calc.exponenciar(20);
        });
    }

    @Test
    void deveZerarMemoria() {
        Calculadora calc = new Calculadora(10);

        calc.zerarMemoria();

        assertEquals(0, calc.getMemoria());
    }
}
