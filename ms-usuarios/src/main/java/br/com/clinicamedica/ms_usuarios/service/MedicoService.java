package br.com.clinicamedica.ms_usuarios.service;

import br.com.clinicamedica.ms_usuarios.dto.MedicoRequestDTO;
import br.com.clinicamedica.ms_usuarios.dto.MedicoResponseDTO;
import br.com.clinicamedica.ms_usuarios.mapper.MedicoMapper;
import br.com.clinicamedica.ms_usuarios.model.Medico;
import br.com.clinicamedica.ms_usuarios.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private MedicoMapper mapper;

    public MedicoResponseDTO cadastrarMedico(MedicoRequestDTO dto){

        Medico medico = mapper.toEntity(dto);
        repository.save(medico);
        return mapper.toDto(medico);
    }

    public List<MedicoResponseDTO> listarTodosMedicos() {
        List<Medico> medicos = repository.findAll();
        return mapper.toDTOList(medicos);
    }

    public MedicoResponseDTO buscarMedicoPorId(Long id) {
        Medico medico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com o ID: " + id));
        return mapper.toDto(medico);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
