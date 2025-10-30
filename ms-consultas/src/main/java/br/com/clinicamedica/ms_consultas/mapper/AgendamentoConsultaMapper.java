package br.com.clinicamedica.ms_consultas.mapper;

import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaRequestDTO;
import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaResponseDTO;
import br.com.clinicamedica.ms_consultas.model.AgendamentoConsulta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AgendamentoConsultaMapper {

    AgendamentoConsultaMapper INSTANCE = Mappers.getMapper(AgendamentoConsultaMapper.class);

    AgendamentoConsulta toEntity(AgendamentoConsultaRequestDTO dto);

    AgendamentoConsultaResponseDTO toDTO(AgendamentoConsulta entity);
}
