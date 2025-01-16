package br.com.iouone.pagamento.config;

import br.com.iouone.pagamento.requests.CustomerRequest;
import br.com.iouone.pagamento.services.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "pessoa_registration_queue";
    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

    private ClienteService clienteService;

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                               Jackson2JsonMessageConverter jsonMessageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter);
        factory.setConcurrentConsumers(3);
        return factory;
    }

    @Component
    public class MyMessageReceiver {

        @Autowired
        private ClienteService clienteService;

        @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
        public void receiveMessage(CustomerRequest pessoaDTO) {
            if (pessoaDTO == null) {
                logger.error("Recebido PessoaDTO é null.");
                return;
            }

            logger.info("Mensagem recebida do RabbitMQ: Nome: {}, Email: {}, Documento: {}, Celular: {}, Niver: {}",
                    pessoaDTO.getName(), pessoaDTO.getEmail(), pessoaDTO.getDocument(), pessoaDTO.getCelular(), pessoaDTO.getBirthdate());

            try {
                ResponseEntity<String> response = clienteService.criarCliente(pessoaDTO);

                if (response.getStatusCode().is2xxSuccessful()) {
                    logger.info("Cliente criado com sucesso na API do Pagar.me. ID: {}", response.getBody());
                } else {
                    logger.error("Falha ao criar cliente na API do Pagar.me: {}", response.getBody());
                }
            } catch (Exception e) {
                logger.error("Erro ao processar a mensagem: ", e);
            }
        }
    }
}
