package br.com.clinicamedica.ms_consultas.service;

import br.com.clinicamedica.ms_consultas.client.UsuarioClient;
import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaRequestDTO;
import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaResponseDTO;
import br.com.clinicamedica.ms_consultas.dto.EmailDto;
import br.com.clinicamedica.ms_consultas.dto.feign.MedicoFeignResponseDTO;
import br.com.clinicamedica.ms_consultas.dto.feign.PacienteFeignResponseDTO;
import br.com.clinicamedica.ms_consultas.mapper.AgendamentoConsultaMapper;
import br.com.clinicamedica.ms_consultas.model.AgendamentoConsulta;
import br.com.clinicamedica.ms_consultas.producer.PacienteProducer;
import br.com.clinicamedica.ms_consultas.repository.AgendamentoConsultaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AgendamentoConsultaService {

    private final AgendamentoConsultaRepository repository;
    private final AgendamentoConsultaMapper mapper;
    private final UsuarioClient usuarioClient;
    private final PacienteProducer producer;

    public AgendamentoConsultaService(AgendamentoConsultaRepository repository, AgendamentoConsultaMapper mapper, UsuarioClient usuarioClient, PacienteProducer producer) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioClient = usuarioClient;
        this.producer = producer;
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

        // ENVIA O EMAIL COM OS DADOS COMPLETOS
        producer.enviarEmail(new EmailDto(
                saved.getId(),
                paciente.nome(),
                paciente.cpf(),
                medico.nome(),
                medico.especialidade().toString(),
                saved.getData(),
                saved.getHorario(),
                saved.getObservacoes()
        ));

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

    @Transactional
    public AgendamentoConsultaResponseDTO atualizarConsulta(Long id, AgendamentoConsultaRequestDTO dto) {
        // Busca a consulta existente
        AgendamentoConsulta consultaExistente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada com id: " + id));

        // Validações
        if (dto.data() != null && dto.data().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data da consulta deve ser hoje ou futura.");
        }

        // Atualiza a entidade usando MapStruct
        mapper.updateEntityFromDTO(dto, consultaExistente);

        // Salva a atualização
        AgendamentoConsulta updated = repository.save(consultaExistente);

        // Retorna o DTO da consulta atualizado (sem enviar email ainda)
        // Busca os nomes do paciente e médico via Feign Client
        String nomePaciente = usuarioClient.buscarPacientePorId(updated.getPacienteId()).nome();
        String nomeMedico = usuarioClient.buscarMedicoPorId(updated.getMedicoId()).nome();

        return new AgendamentoConsultaResponseDTO(
                updated.getId(),
                nomePaciente,
                nomeMedico,
                updated.getEspecialidade(),
                updated.getData(),
                updated.getHorario(),
                updated.getObservacoes()
        );
    }

}
