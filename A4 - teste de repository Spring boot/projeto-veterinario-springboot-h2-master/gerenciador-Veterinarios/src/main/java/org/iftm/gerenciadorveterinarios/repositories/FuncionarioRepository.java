package org.iftm.gerenciadorveterinarios.repositories;

import org.iftm.gerenciadorveterinarios.entities.Funcionario;

import java.util.Optional;

public interface FuncionarioRepository {

    Funcionario save(Funcionario funcionario);

    Optional<Funcionario> findById(Integer id);
}