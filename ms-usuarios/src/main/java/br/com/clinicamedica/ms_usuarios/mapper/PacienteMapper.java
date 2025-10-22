package br.com.clinicamedica.ms_usuarios.mapper;

import br.com.clinicamedica.ms_usuarios.dto.PacienteRequestDTO;
import br.com.clinicamedica.ms_usuarios.dto.PacienteResponseDTO;
import br.com.clinicamedica.ms_usuarios.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EnderecoMapper.class)
public interface PacienteMapper {

    // Converte do DTO de requisição para a entidade
    // Mapeia cada campo do DTO para a entidade
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "endereco", target = "endereco")
    Paciente toEntity(PacienteRequestDTO dto);

    // Converte da entidade para o DTO de resposta
    // Mapeia da entidade para o DTO de resposta
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "endereco", target = "endereco")
    PacienteResponseDTO toDto(Paciente entity);
}
