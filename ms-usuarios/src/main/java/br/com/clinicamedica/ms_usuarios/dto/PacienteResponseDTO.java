package br.com.clinicamedica.ms_usuarios.dto;

public record PacienteResponseDTO(

        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        EnderecoResponseDTO endereco
) {}
