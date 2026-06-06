package org.iftm.gerenciadorveterinarios.servicies;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.iftm.gerenciadorveterinarios.entities.Servico;
import org.iftm.gerenciadorveterinarios.repositories.ServicoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ServicoServiceTest {

    @Mock
    private ServicoRepository repository;

    @InjectMocks
    private ServicoService service;

    @Test
    public void deveLancarExcecaoQuandoTempoForInvalido() {

        // ARRANGE
        Servico servico = new Servico(
                1,
                "Banho",
                BigDecimal.valueOf(80),
                0,
                true
        );

        // ACT + ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(servico);
        });

        // VERIFY
        verify(repository, never()).save(any());
    }
}