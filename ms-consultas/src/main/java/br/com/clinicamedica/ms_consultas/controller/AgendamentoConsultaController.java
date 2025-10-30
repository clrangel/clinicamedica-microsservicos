package br.com.clinicamedica.ms_consultas.controller;

import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaRequestDTO;
import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaResponseDTO;
import br.com.clinicamedica.ms_consultas.service.AgendamentoConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class AgendamentoConsultaController {

    private final AgendamentoConsultaService service;

    public AgendamentoConsultaController(AgendamentoConsultaService service) {
        this.service = service;
    }

    @PostMapping("/agendar")
    public ResponseEntity<AgendamentoConsultaResponseDTO> agendarConsulta(@Valid @RequestBody AgendamentoConsultaRequestDTO dto) {

        AgendamentoConsultaResponseDTO response = service.agendarConsulta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
