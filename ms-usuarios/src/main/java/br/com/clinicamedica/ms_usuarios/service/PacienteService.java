package br.com.clinicamedica.ms_usuarios.service;

import br.com.clinicamedica.ms_usuarios.dto.PacienteRequestDTO;
import br.com.clinicamedica.ms_usuarios.dto.PacienteResponseDTO;
import br.com.clinicamedica.ms_usuarios.mapper.PacienteMapper;
import br.com.clinicamedica.ms_usuarios.model.Paciente;
import br.com.clinicamedica.ms_usuarios.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private PacienteMapper mapper;

    public PacienteResponseDTO cadastrarPaciente(PacienteRequestDTO dto){

        Paciente paciente = mapper.toEntity(dto);
        repository.save(paciente);
        return mapper.toDto(paciente);
    }
}
