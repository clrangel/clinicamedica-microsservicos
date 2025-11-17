package br.com.clinicamedica.ms_usuarios.config;

import br.com.clinicamedica.ms_usuarios.dto.EmailDto;
import br.com.clinicamedica.ms_usuarios.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
public class EmailConfig {

    @Autowired
    private PacienteService service;

    @Value("fila.mensagem.paciente")
    private String queue;

    @Bean
    public Queue queue(){
        return new Queue(queue, true);
    }

    @RabbitListener(queues = "fila.mensagem.paciente")
    private void enviaEmail(@Payload EmailDto mensagem){
        System.out.println(mensagem);
        service.enviarMensagem(mensagem);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {

        ObjectMapper objectMapper = new ObjectMapper();

        // Corrige o problema do LocalDate e LocalTime
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
