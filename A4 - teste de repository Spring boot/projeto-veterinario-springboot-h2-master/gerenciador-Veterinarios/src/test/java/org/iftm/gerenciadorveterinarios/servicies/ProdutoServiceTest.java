package org.iftm.gerenciadorveterinarios.servicies;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

        @Mock
        private ProdutoRepository repository;

        @InjectMocks
        private ProdutoService service;

        // @Test
        // public void deveCadastrarProdutoComStatusAtivo() {

        // // ARRANGE
        // Produto produto = new Produto(
        // null,
        // "Ração Premium",
        // BigDecimal.valueOf(150),
        // 10,
        // false);

        // when(repository.save(produto))
        // .thenReturn(produto);

        // // ACT
        // Produto resultado = service.cadastrar(produto);

        // // ASSERT
        // assertTrue(resultado.getAtivo());

        // verify(repository).save(produto);
        // }

        @Test
        public void deveCadastrarProdutoComStatusAtivo() {

                Produto produto = new Produto(
                                1,
                                "Notebook",
                                BigDecimal.valueOf(5000),
                                10,
                                false);

                when(repository.save(produto))
                                .thenReturn(produto);

                Produto resultado = service.cadastrar(produto);

                assertTrue(resultado.getAtivo());

                verify(repository).save(produto);
        }

        @Test
        public void deveLancarExcecaoQuandoPrecoForNegativo() {

                // ARRANGE
                Produto produto = new Produto(
                                1,
                                "Notebook",
                                BigDecimal.valueOf(-500),
                                10,
                                false);

                // ACT + ASSERT
                assertThrows(IllegalArgumentException.class, () -> {
                        service.cadastrar(produto);
                });

                // VERIFY
                verify(repository, never()).save(any());
        }

        @Test
        public void deveInativarProduto() {

                // ARRANGE
                Integer id = 1;

                Produto produto = new Produto(
                                id,
                                "Notebook",
                                BigDecimal.valueOf(5000),
                                10,
                                true);

                when(repository.findById(id))
                                .thenReturn(Optional.of(produto));

                // ACT
                service.inativar(id);

                // ASSERT
                assertFalse(produto.getAtivo());

                // VERIFY
                verify(repository).save(produto);
        }
}