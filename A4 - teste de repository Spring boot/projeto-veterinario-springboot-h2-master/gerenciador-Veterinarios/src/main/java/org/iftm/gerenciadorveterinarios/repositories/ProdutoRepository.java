package org.iftm.gerenciadorveterinarios.repositories;

import org.iftm.gerenciadorveterinarios.entities.Produto;

import java.util.Optional;

public interface ProdutoRepository {

    Produto save(Produto produto);
    
    Optional<Produto> findById(Integer id);

}