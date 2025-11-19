package br.com.clinicamedica.ms_usuarios.controller;

import br.com.clinicamedica.ms_usuarios.dto.MedicoRequestDTO;
import br.com.clinicamedica.ms_usuarios.dto.MedicoResponseDTO;
import br.com.clinicamedica.ms_usuarios.model.Medico;
import br.com.clinicamedica.ms_usuarios.service.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> cadastrarMedico(@Valid @RequestBody MedicoRequestDTO dto){
        MedicoResponseDTO medicoCriado = service.cadastrarMedico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoCriado);
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> listarTodos() {
        List<MedicoResponseDTO> medicos = service.listarTodosMedicos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> buscarMedicoPorId(@PathVariable Long id) {
        MedicoResponseDTO medico = service.buscarMedicoPorId(id);
        return ResponseEntity.ok(medico);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody MedicoRequestDTO dto
    ) {
        MedicoResponseDTO atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }
}
