package br.com.clinicamedica.ms_usuarios.dto;

import br.com.clinicamedica.ms_usuarios.model.Endereco;
import br.com.clinicamedica.ms_usuarios.model.Especialidade;

public record MedicoRequestDTO(

        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        EnderecoRequestDTO endereco
) {}
