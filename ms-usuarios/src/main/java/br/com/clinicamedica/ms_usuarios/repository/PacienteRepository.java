package br.com.clinicamedica.ms_usuarios.repository;

import br.com.clinicamedica.ms_usuarios.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByCrm(String cpf);
}
