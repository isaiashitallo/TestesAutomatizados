package org.iftm.gerenciadorveterinarios.servicies;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.iftm.gerenciadorveterinarios.entities.Produto;
import org.iftm.gerenciadorveterinarios.repositories.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    public void deveCadastrarProdutoComStatusAtivo() {

        // ARRANGE
        Produto produto =
                new Produto(
                        null,
                        "Ração Premium",
                        BigDecimal.valueOf(150),
                        10,
                        false
                );

        when(repository.save(produto))
                .thenReturn(produto);

        // ACT
        Produto resultado = service.cadastrar(produto);

        // ASSERT
        assertTrue(resultado.getAtivo());

        verify(repository).save(produto);
    }
}