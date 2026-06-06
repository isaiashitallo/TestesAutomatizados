package org.iftm.gerenciadorveterinarios.repositories;

import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Produto;

public interface ProdutoRepository {

    Produto save(Produto produto);

    Optional<Produto> findById(Integer id);
}