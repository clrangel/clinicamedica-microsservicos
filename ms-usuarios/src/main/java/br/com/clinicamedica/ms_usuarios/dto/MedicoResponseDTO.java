package br.com.clinicamedica.ms_usuarios.dto;

import br.com.clinicamedica.ms_usuarios.model.Endereco;
import br.com.clinicamedica.ms_usuarios.model.Especialidade;

public record MedicoResponseDTO(

        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        EnderecoResponseDTO endereco
) {}
