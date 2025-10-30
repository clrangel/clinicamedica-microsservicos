package br.com.clinicamedica.ms_consultas.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoConsultaRequestDTO(

        Long pacienteId,
        Long medicoId,
        LocalDate data,
        LocalTime horario,
        String observacoes
) {}
