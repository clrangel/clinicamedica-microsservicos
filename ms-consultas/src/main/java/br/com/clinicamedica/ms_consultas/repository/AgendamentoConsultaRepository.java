package br.com.clinicamedica.ms_consultas.repository;

import br.com.clinicamedica.ms_consultas.model.AgendamentoConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoConsultaRepository extends JpaRepository<AgendamentoConsulta, Long> {
}
