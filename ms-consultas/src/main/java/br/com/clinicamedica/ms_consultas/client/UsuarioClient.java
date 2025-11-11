package br.com.clinicamedica.ms_consultas.client;

import br.com.clinicamedica.ms_consultas.dto.feign.MedicoFeignResponseDTO;
import br.com.clinicamedica.ms_consultas.dto.feign.PacienteFeignResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-usuarios")
public interface UsuarioClient {

    @GetMapping("/medicos/{id}")
    MedicoFeignResponseDTO buscarMedicoPorId(@PathVariable Long id);

    @GetMapping("/pacientes/{id}")
    PacienteFeignResponseDTO buscarPacientePorId(@PathVariable Long id);
}
