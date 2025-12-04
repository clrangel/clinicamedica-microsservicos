package br.com.clinicamedica.ms_consultas.controller;

import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaRequestDTO;
import br.com.clinicamedica.ms_consultas.dto.AgendamentoConsultaResponseDTO;
import br.com.clinicamedica.ms_consultas.service.AgendamentoConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoConsultaResponseDTO> atualizarConsulta(
            @PathVariable Long id,
            @RequestBody AgendamentoConsultaRequestDTO dto) {
        AgendamentoConsultaResponseDTO updated = service.atualizarConsulta(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarConsulta(id);
        return ResponseEntity.noContent().build();
    }


    // Endpoint usado apenas para identificar visualmente de qual instância (porta) a resposta veio.
    // Útil para testes de balanceamento de carga entre múltiplas instâncias.
    @GetMapping("/response")
    public String obterPorta(@Value("${local.server.port}") String porta) {
        return String.format("Resposta vinda da porta %s", porta);

    }
}
