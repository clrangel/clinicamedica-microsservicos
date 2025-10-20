package br.com.clinicamedica.ms_usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    @NotBlank
    private String telefone;

    @Embedded
    private Endereco endereco;
}
