package br.com.clinicamedica.ms_consultas.service;

import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaRequestDTO;
import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaResponseDTO;
import br.com.clinicamedica.ms_consultas.mapper.AgendamentoConsultaMapper;
import br.com.clinicamedica.ms_consultas.model.AgendamentoConsulta;
import br.com.clinicamedica.ms_consultas.repository.AgendamentoConsultaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AgendamentoConsultaService {

    private final AgendamentoConsultaRepository repository;
    private final AgendamentoConsultaMapper mapper;

    public AgendamentoConsultaService(AgendamentoConsultaRepository repository, AgendamentoConsultaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public AgendamentoConsultaResponseDTO agendarConsulta(AgendamentoConsultaRequestDTO dto) {

        // validações simples
        if (dto.data().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data da consulta deve ser futura.");
        }
        if (dto.horario() == null) {
            throw new IllegalArgumentException("O horário da consulta é obrigatório.");
        }

        // O mapper já instancia e preenche os campos da entidade
        AgendamentoConsulta consulta = mapper.toEntity(dto);
        AgendamentoConsulta saved = repository.save(consulta);
        return mapper.toDTO(saved);
    }
}
