package org.iftm.gerenciadorveterinarios.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class VeterinarioRepositoryTests {

    @Autowired
    private VeterinarioRepository repository;

    // =========================
    // CICLO 1 - Busca por Nome
    // =========================

    @Test
    public void deveRetornarZeroQuandoBuscaExataNaoRespeitaCase() {

        // Arrange
        String nome = "pedro";

        // Act
        List<Veterinario> resultado = repository.findByNome(nome);

        // Assert
        assertEquals(0, resultado.size());
    }

    @Test
    public void deveRetornarVeterinarioQuandoNomeExatoExistir() {

        // Arrange
        String nome = "PEDRO";

        // Act
        List<Veterinario> resultado = repository.findByNome(nome);

        // Assert
        assertEquals(1, resultado.size());
    }

    @Test
    public void deveRetornarZeroQuandoNomeIgnoreCaseNaoExistir() {

        // Arrange
        String nome = "jose";

        // Act
        List<Veterinario> resultado =
                repository.findByNomeIgnoreCase(nome);

        // Assert
        assertEquals(0, resultado.size());
    }

    @Test
    public void deveBuscarNomeIgnorandoMaiusculasEMinusculas() {

        // Arrange
        String nome = "joao";

        // Act
        List<Veterinario> resultado =
                repository.findByNomeIgnoreCase(nome);

        // Assert
        assertEquals(1, resultado.size());
    }

    // =========================
    // CICLO 2 - LIKE
    // =========================

    @Test
    public void deveBuscarVeterinariosPorParteDoNome() {

        // Arrange
        String trecho = "ro";

        // Act
        List<Veterinario> resultado =
                repository.findByNomeContainingIgnoreCase(trecho);

        // Assert
        assertEquals(2, resultado.size());
    }

    @Test
    public void naoDeveRetornarVeterinariosQuandoNomeNaoExistir() {

        // Arrange
        String trecho = "Maria";

        // Act
        List<Veterinario> resultado =
                repository.findByNomeContainingIgnoreCase(trecho);

        // Assert
        assertEquals(0, resultado.size());
    }

    @Test
    public void deveRetornarTodosQuandoBuscaForVazia() {

        // Arrange
        String trecho = "";

        // Act
        List<Veterinario> resultado =
                repository.findByNomeContainingIgnoreCase(trecho);

        // Assert
        assertEquals(3, resultado.size());
    }

    // =========================
    // CICLO 3 - Salário
    // =========================

    @Test
    public void deveBuscarVeterinariosComSalarioMaiorQueValorInformado() {

        // Arrange
        BigDecimal salario = BigDecimal.valueOf(4000);

        // Act
        List<Veterinario> resultado =
                repository.findBySalarioGreaterThan(salario);

        // Assert
        assertEquals(2, resultado.size());
    }

    @Test
    public void deveBuscarVeterinariosComSalarioMenorQueValorInformado() {

        // Arrange
        BigDecimal salario = BigDecimal.valueOf(4000);

        // Act
        List<Veterinario> resultado =
                repository.findBySalarioLessThan(salario);

        // Assert
        assertEquals(1, resultado.size());
    }

    @Test
    public void deveBuscarVeterinariosDentroDaFaixaSalarial() {

        // Arrange
        BigDecimal minimo = BigDecimal.valueOf(4000);
        BigDecimal maximo = BigDecimal.valueOf(8000);

        // Act
        List<Veterinario> resultado =
                repository.findBySalarioBetween(minimo, maximo);

        // Assert
        assertEquals(2, resultado.size());
    }

    // =========================
    // CICLO 4 - UPDATE
    // =========================

    @Test
    public void deveAtualizarVeterinario() {

        // Arrange
        Veterinario veterinario =
                repository.findByNome("PEDRO").get(0);

        // Act
        veterinario.setNome("PEDRO NOVO");
        veterinario.setSalario(BigDecimal.valueOf(9000));

        repository.save(veterinario);

        // Assert
        assertEquals(
                0,
                repository.findByNome("PEDRO").size());

        assertEquals(
                1,
                repository.findByNome("PEDRO NOVO").size());

        assertEquals(
                1,
                repository.findBySalarioGreaterThan(
                        BigDecimal.valueOf(8000))
                        .size());
    }
}