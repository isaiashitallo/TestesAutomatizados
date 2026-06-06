package org.iftm.gerenciadorveterinarios.servicies;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class VeterinarioServiceTest {

    // cria a simulação/mock de todas as classes que serão injetadas na classe a ser
    // testada
    @Mock
    private VeterinarioRepository repositorio;

    // classe a ser testada que receberá todas as injeções de classes mock
    @InjectMocks
    private VeterinarioService service;

    /*
     * Validar se a busca por veterinario retorna o veterinário correto e o nome com
     * no máximo 10 caracteres.
     */
    @Test
    public void testarBuscarVeterinarioPorIDExistenteRetornaVeterinarioComTruncado() {
        // arrange
        String nomeExistente = "Erica Queiroz Pinto";
        int tamanhoEsperadoNome = 10;
        String nomeEsperado = "Erica Quei";
        Veterinario veterinarioEsperado = new Veterinario(null, nomeExistente, "", "", BigDecimal.valueOf(0));

        // configurar o mock - criando o cenário de teste
        when(repositorio.findById(Mockito.anyInt())).thenReturn(Optional.of(veterinarioEsperado));

        // act
        Optional<Veterinario> resposta = service.buscaVeterinariosPeloId(Mockito.anyInt());
        Veterinario veterinarioRetornado = resposta.get();

        // assert
        assertTrue(resposta.isPresent());
        assertEquals(tamanhoEsperadoNome, veterinarioRetornado.getNome().length());
        assertEquals(nomeEsperado, veterinarioRetornado.getNome());

        verify(repositorio).findById(Mockito.anyInt());
    }

@Test
public void testarApagarRealmenteApagaRegistro() {

    // ARRANGE
    Integer idExistente = 2;

    String nomeExistente = "Erica Queiroz Pinto";

    Veterinario veterinarioExcluido =
            new Veterinario(idExistente,
                    nomeExistente,
                    "",
                    "",
                    null);

    // mockando busca do veterinário existente
    when(repositorio.findById(idExistente))
            .thenReturn(Optional.of(veterinarioExcluido));

    // ACT + ASSERT
    assertDoesNotThrow(() -> {
        service.apagar(idExistente);
    });

    // VERIFY
    verify(repositorio).findById(idExistente);

    verify(repositorio).delete(veterinarioExcluido);
}

    @Test
    public void testarBuscaVeterinariosComParteNomeRetornaListaCom2Veterinarios() {

        // ARRANGE -- AQ SIMULA UM DB
        Veterinario vet1 = new Veterinario(
                1,
                "Joao Silva",
                "joao@email.com",
                "Cirurgia",
                BigDecimal.valueOf(5000));

        Veterinario vet2 = new Veterinario(
                2,
                "Maria Silva",
                "maria@email.com",
                "Dermatologia",
                BigDecimal.valueOf(6000));

        List<Veterinario> listaVeterinarios = List.of(vet1, vet2);

        // Treinando o mock
        // QND ESSE METODO FOR CHAMADO, COM ESSE NOME, DEVOLVE LISTAVETERINARIOS
        when(repositorio.findByNomeContainingIgnoreCase("Silva"))
                .thenReturn(listaVeterinarios);

        // ACT
        List<Veterinario> resultado = service.buscaVeterinariosComParteNome("Silva");

        // ASSERT
        assertEquals(2, resultado.size());

        // VERIFICA SE O METODO FOI CHAMADO COM O PRM CORRETO
        verify(repositorio).findByNomeContainingIgnoreCase("Silva");
    }
    // DESAFIO 1
    // ESTRUTURA DE TESTES MOCKITO
    // 1. Arrange
    // - cria objetos
    // - configura mocks

    // 2. Act
    // - executa método

    // 3. Assert
    // - valida resultado

    // 4. Verify
    // - verifica interação com mock

    // DESAFIO 2
        // ETAPA 1 RED
    @Test
    public void deveLancarExcecaoAoApagarQuandoIdNaoExistir() {

        // ARRANGE
        Integer idInexistente = 999;

        // mockando busca vazia
        when(repositorio.findById(idInexistente))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThrows(RuntimeException.class, () -> {
            service.apagar(idInexistente);
        });

        // VERIFY
        verify(repositorio).findById(idInexistente);

        // garante que delete nunca foi chamado
        verify(repositorio, never()).delete(any());
    }
}
