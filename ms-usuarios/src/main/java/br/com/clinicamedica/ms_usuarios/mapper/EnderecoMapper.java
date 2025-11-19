package br.com.clinicamedica.ms_usuarios.mapper;

import br.com.clinicamedica.ms_usuarios.dto.EnderecoRequestDTO;
import br.com.clinicamedica.ms_usuarios.dto.EnderecoResponseDTO;
import br.com.clinicamedica.ms_usuarios.model.Endereco;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoRequestDTO dto);

    EnderecoResponseDTO toDto(Endereco entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(EnderecoRequestDTO dto, @MappingTarget Endereco entity);
}
