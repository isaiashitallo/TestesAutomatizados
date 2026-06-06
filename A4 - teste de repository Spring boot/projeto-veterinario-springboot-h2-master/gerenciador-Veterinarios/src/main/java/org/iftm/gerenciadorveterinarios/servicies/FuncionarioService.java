package org.iftm.gerenciadorveterinarios.servicies;

import java.math.BigDecimal;

import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.iftm.gerenciadorveterinarios.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public Funcionario cadastrar(Funcionario funcionario) {

        // VALIDAÇÃO
        if (funcionario.getSalario()
                .compareTo(BigDecimal.valueOf(1518)) < 0) {

            throw new IllegalArgumentException("Salário inválido");
        }

        funcionario.setEmFerias(false);

        return repository.save(funcionario);
    }

    public Funcionario concederFerias(Integer id) {

        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        funcionario.setEmFerias(true);

        return repository.save(funcionario);
    }
}