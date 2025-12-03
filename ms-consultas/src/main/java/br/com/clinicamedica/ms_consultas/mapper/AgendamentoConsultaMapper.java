package br.com.clinicamedica.ms_consultas.mapper;

import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaRequestDTO;
import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaResponseDTO;
import br.com.clinicamedica.ms_consultas.model.AgendamentoConsulta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AgendamentoConsultaMapper {

    AgendamentoConsulta toEntity(AgendamentoConsultaRequestDTO dto);

    AgendamentoConsultaResponseDTO toDTO(AgendamentoConsulta entity);

    // Mapeia os campos do DTO para a entidade existente
    // @MappingTarget indica que o MapStruct deve atualizar a instância existente da entidade, em vez de criar uma nova.
    void updateEntityFromDTO(AgendamentoConsultaRequestDTO dto, @MappingTarget AgendamentoConsulta entity);
}
