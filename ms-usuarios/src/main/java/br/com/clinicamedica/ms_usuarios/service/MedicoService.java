package br.com.clinicamedica.ms_usuarios.service;

import br.com.clinicamedica.ms_usuarios.dto.MedicoRequestDTO;
import br.com.clinicamedica.ms_usuarios.dto.MedicoResponseDTO;
import br.com.clinicamedica.ms_usuarios.mapper.MedicoMapper;
import br.com.clinicamedica.ms_usuarios.model.Medico;
import br.com.clinicamedica.ms_usuarios.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MedicoService {

    private final MedicoRepository repository;
    private final MedicoMapper mapper;

    public MedicoResponseDTO cadastrarMedico(MedicoRequestDTO dto){

        Medico medico = mapper.toEntity(dto);
        repository.save(medico);
        return mapper.toDto(medico);
    }
}
