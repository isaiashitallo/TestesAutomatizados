package org.iftm.gerenciadorveterinarios.servicies;

import org.iftm.gerenciadorveterinarios.entities.Servico;
import org.iftm.gerenciadorveterinarios.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public Servico cadastrar(Servico servico) {

        // VALIDAÇÃO
        if (servico.getTempoMinutos() <= 0) {
            throw new IllegalArgumentException("Tempo inválido");
        }

        servico.setDisponivel(true);

        return repository.save(servico);
    }

    public Servico indisponibilizar(Integer id) {

        Servico servico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        servico.setDisponivel(false);

        return repository.save(servico);
    }
}