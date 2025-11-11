package br.com.clinicamedica.ms_consultas.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoConsultaResponseDTO(

        Long id,
        String nomePaciente,
        String nomeMedico,
        String especialidade,
        LocalDate data,
        LocalTime horario,
        String observacoes
) {}
