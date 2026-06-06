package org.iftm.gerenciadorveterinarios.servicies;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.iftm.gerenciadorveterinarios.repositories.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository repository;

    @InjectMocks
    private FuncionarioService service;

    @Test
    public void deveLancarExcecaoQuandoSalarioForMenorQueMinimo() {

        // ARRANGE
        Funcionario funcionario = new Funcionario(
                1,
                "Carlos",
                "Analista",
                BigDecimal.valueOf(1000),
                false
        );

        // ACT + ASSERT
        assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(funcionario);
        });

        // VERIFY
        verify(repository, never()).save(any());
    }
}