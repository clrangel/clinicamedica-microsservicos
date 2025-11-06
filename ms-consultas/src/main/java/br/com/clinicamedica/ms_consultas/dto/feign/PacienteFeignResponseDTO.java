package br.com.clinicamedica.ms_consultas.dto.feign;

public record PacienteFeignResponseDTO(
        String nome,
        String email,
        String cpf,
        String telefone) {
}
