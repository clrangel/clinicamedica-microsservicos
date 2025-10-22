package br.com.clinicamedica.ms_usuarios.dto;

public record EnderecoResponseDTO(
        String logradouro,
        String bairro,
        String cep,
        String numero,
        String complemento,
        String cidade,
        String uf
) {}
