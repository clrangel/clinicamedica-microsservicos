package br.com.clinicamedica.ms_consultas.producer;

import br.com.clinicamedica.ms_consultas.dto.EmailDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PacienteProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("fila.mensagem.paciente")
    private String queue;

    public void enviarEmail(EmailDto email){
        rabbitTemplate.convertAndSend(queue, email);
        System.out.println(email);
    }
}
