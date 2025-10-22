package br.com.clinicamedica.ms_usuarios.dto;

public record PacienteRequestDTO(

        String nome,
        String email,
        String cpf,
        String telefone,
        EnderecoRequestDTO endereco
) {}
