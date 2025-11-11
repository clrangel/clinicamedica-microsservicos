package br.com.clinicamedica.ms_consultas.service;

import br.com.clinicamedica.ms_consultas.client.UsuarioClient;
import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaRequestDTO;
import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaResponseDTO;
import br.com.clinicamedica.ms_consultas.dto.feign.MedicoFeignResponseDTO;
import br.com.clinicamedica.ms_consultas.dto.feign.PacienteFeignResponseDTO;
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
    private final UsuarioClient usuarioClient;

    public AgendamentoConsultaService(AgendamentoConsultaRepository repository, AgendamentoConsultaMapper mapper, UsuarioClient usuarioClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioClient = usuarioClient;
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

        // Busca informações do médico e do paciente via Feign Client
        MedicoFeignResponseDTO medico = usuarioClient.buscarMedicoPorId(dto.medicoId());
        PacienteFeignResponseDTO paciente = usuarioClient.buscarPacientePorId(dto.pacienteId());

        // Cria e salva a consulta
        // O mapper já instancia e preenche os campos da entidade
        AgendamentoConsulta consulta = mapper.toEntity(dto);
        AgendamentoConsulta saved = repository.save(consulta);

        // Retorna o DTO da consulta com informações adicionais
        return new AgendamentoConsultaResponseDTO(
                saved.getId(),
                paciente.nome(),
                medico.nome(),
                saved.getEspecialidade(),
                saved.getData(),
                saved.getHorario(),
                saved.getObservacoes()
        );

    }
}
