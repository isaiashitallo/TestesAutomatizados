package org.iftm.gerenciadorveterinarios.servicies;

import org.iftm.gerenciadorveterinarios.entities.Produto;
import org.iftm.gerenciadorveterinarios.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto cadastrar(Produto produto) {
        // VALIDAÇÃO
        if (produto.getPreco().doubleValue() < 0) {
            throw new IllegalArgumentException("Preço inválido");
        }

        produto.setAtivo(true);

        return repository.save(produto);
    }

    public Produto inativar(Integer id) {

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setAtivo(false);

        return repository.save(produto);
    }
    
}