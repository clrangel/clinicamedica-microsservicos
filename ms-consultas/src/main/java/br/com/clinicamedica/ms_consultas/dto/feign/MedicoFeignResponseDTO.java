package br.com.clinicamedica.ms_consultas.dto.feign;

import br.com.clinicamedica.ms_consultas.model.enums.Especialidade;

public record MedicoFeignResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade) {
}
