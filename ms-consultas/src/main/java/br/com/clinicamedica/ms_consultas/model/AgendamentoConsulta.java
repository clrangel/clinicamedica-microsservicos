package br.com.clinicamedica.ms_consultas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class AgendamentoConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O ID do paciente é obrigatório.")
    private Long pacienteId;

    @NotNull(message = "O ID do médico é obrigatório.")
    private Long medicoId;

    @NotNull(message = "A data da consulta é obrigatória.")
    @FutureOrPresent(message = "A data da consulta deve ser hoje ou uma data futura.")
    private LocalDate data;

    @NotNull(message = "O horário da consulta é obrigatório.")
    private LocalTime horario;

    @Size(max = 255, message = "As observações devem ter no máximo 255 caracteres.")
    private String observacoes;

    // private String status; (Campo opcional para futuras melhorias)
}
