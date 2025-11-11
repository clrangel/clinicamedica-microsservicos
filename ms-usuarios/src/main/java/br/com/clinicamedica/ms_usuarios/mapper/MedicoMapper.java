package br.com.clinicamedica.ms_usuarios.mapper;

import br.com.clinicamedica.ms_usuarios.dto.EnderecoRequestDTO;
import br.com.clinicamedica.ms_usuarios.dto.EnderecoResponseDTO;
import br.com.clinicamedica.ms_usuarios.dto.MedicoRequestDTO;
import br.com.clinicamedica.ms_usuarios.dto.MedicoResponseDTO;
import br.com.clinicamedica.ms_usuarios.model.Endereco;
import br.com.clinicamedica.ms_usuarios.model.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = EnderecoMapper.class)
public interface MedicoMapper {


    // Converte do DTO de requisição para a entidade
    // Mapeia cada campo do DTO para a entidade
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "crm", target = "crm")
    @Mapping(source = "especialidade", target = "especialidade")
    @Mapping(source = "endereco", target = "endereco")
    Medico toEntity(MedicoRequestDTO dto);

    // Converte da entidade para o DTO de resposta
    // Mapeia da entidade para o DTO de resposta
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "telefone", target = "telefone")
    @Mapping(source = "crm", target = "crm")
    @Mapping(source = "especialidade", target = "especialidade")
    @Mapping(source = "endereco", target = "endereco")
    MedicoResponseDTO toDto(Medico entity);

    // adicione isso — MapStruct gera a implementação automaticamente
    List<MedicoResponseDTO> toDTOList(List<Medico> medicos);
}
