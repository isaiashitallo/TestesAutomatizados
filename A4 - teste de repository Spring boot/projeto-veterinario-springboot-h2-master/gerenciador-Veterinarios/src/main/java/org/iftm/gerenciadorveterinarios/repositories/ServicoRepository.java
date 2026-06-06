package org.iftm.gerenciadorveterinarios.repositories;

import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Servico;

public interface ServicoRepository {

    Servico save(Servico servico);

    Optional<Servico> findById(Integer id);
}