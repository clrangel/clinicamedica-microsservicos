package br.com.clinicamedica.ms_usuarios.service;

import br.com.clinicamedica.ms_usuarios.dto.*;
import br.com.clinicamedica.ms_usuarios.mapper.PacienteMapper;
import br.com.clinicamedica.ms_usuarios.model.Medico;
import br.com.clinicamedica.ms_usuarios.model.Paciente;
import br.com.clinicamedica.ms_usuarios.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private PacienteMapper mapper;

    @Autowired
    private JavaMailSender sender;

    public PacienteResponseDTO cadastrarPaciente(PacienteRequestDTO dto){

        Paciente paciente = mapper.toEntity(dto);
        repository.save(paciente);
        return mapper.toDto(paciente);
    }

    public List<Paciente> listarTodosPacientes() {
        return repository.findAll();
    }

    public Paciente buscarPacientePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO dto) {

        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado: " + id));

        // Aqui o MapStruct atualiza o objeto existente
        mapper.updateFromDto(dto, paciente);

        Paciente pacienteSalvo = repository.save(paciente);

        return mapper.toDto(pacienteSalvo);
    }

    public void enviarMensagem(EmailDto mensagem) {
        Optional<Paciente> paciente = repository.findByCpf(mensagem.cpf());

        if (paciente.isPresent()) {
            SimpleMailMessage enviaMensagem = new SimpleMailMessage();

            enviaMensagem.setFrom("EMAIL"); //Apagar email antes dos commits
            enviaMensagem.setTo(paciente.get().getEmail());
            enviaMensagem.setSubject("Informações sobre a consulta " + mensagem.id());

            enviaMensagem.setText(gerarCorpoConsulta(mensagem));

            try {
                sender.send(enviaMensagem);
                System.out.println("Mensagem enviada com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao enviar mensagem!");
            }
        }
    }

    private String gerarCorpoConsulta(EmailDto mensagem) {
        return """
                Olá %s,

                Sua consulta está marcada com o Dr(a). %s.
                Especialidade: %s
                Data: %s
                Horário: %s
                Observações: %s

                Atenciosamente,
                Clínica Médica
                """.formatted(
                mensagem.nomePaciente(),
                mensagem.nomeMedico(),
                mensagem.especialidade(),
                mensagem.data(),
                mensagem.horario(),
                mensagem.observacoes()
        );
    }
}
