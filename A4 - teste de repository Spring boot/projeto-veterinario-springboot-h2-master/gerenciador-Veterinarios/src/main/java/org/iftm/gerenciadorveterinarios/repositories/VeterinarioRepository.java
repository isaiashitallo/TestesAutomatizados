package org.iftm.gerenciadorveterinarios.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer> {

   // public List<Veterinario> findByNomeContains(String nome);
   public List<Veterinario> findByNomeContainingIgnoreCase(String nome);

   public List<Veterinario> findByNome(String nome);

   public List<Veterinario> findByNomeIgnoreCase(String nome);

   public List<Veterinario> findBySalarioGreaterThan(BigDecimal salario);

   public List<Veterinario> findBySalarioLessThan(BigDecimal salario);

   public List<Veterinario> findBySalarioBetween(
         BigDecimal minimo,
         BigDecimal maximo);

}
