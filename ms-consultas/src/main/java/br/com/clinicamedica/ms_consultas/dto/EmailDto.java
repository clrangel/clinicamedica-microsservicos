package br.com.clinicamedica.ms_consultas.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record EmailDto(

        Long id,
        String nomePaciente,
        String cpf,
        String nomeMedico,
        String especialidade,
        LocalDate data,
        LocalTime horario,
        String observacoes
) {}
