package br.com.clinicamedica.ms_usuarios.controller;

import br.com.clinicamedica.ms_usuarios.dto.PacienteRequestDTO;
import br.com.clinicamedica.ms_usuarios.dto.PacienteResponseDTO;
import br.com.clinicamedica.ms_usuarios.model.Paciente;
import br.com.clinicamedica.ms_usuarios.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> cadastrarPaciente(@Valid @RequestBody PacienteRequestDTO dto){
        PacienteResponseDTO pacienteCriado = service.cadastrarPaciente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteCriado);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodosPacientes() {
        List<Paciente> pacientes = service.listarTodosPacientes();
        return ResponseEntity.ok(pacientes);
    }
}

