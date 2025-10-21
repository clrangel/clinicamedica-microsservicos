package br.com.clinicamedica.ms_usuarios.mapper;

import br.com.clinicamedica.ms_usuarios.dto.MedicoRequestDTO;
import br.com.clinicamedica.ms_usuarios.dto.MedicoResponseDTO;
import br.com.clinicamedica.ms_usuarios.model.Medico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoMapper {

    // Converte do DTO de requisição para a entidade
    Medico toEntity(MedicoRequestDTO dto);

    // Converte da entidade para o DTO de resposta
    MedicoResponseDTO toDto(Medico entity);
}
