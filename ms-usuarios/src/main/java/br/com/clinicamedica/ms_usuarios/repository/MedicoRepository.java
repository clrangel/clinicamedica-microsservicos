package br.com.clinicamedica.ms_usuarios.repository;

import br.com.clinicamedica.ms_usuarios.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByCrm(String crm);
}
