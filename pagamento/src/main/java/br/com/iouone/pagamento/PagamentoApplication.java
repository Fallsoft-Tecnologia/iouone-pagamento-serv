package br.com.iouone.pagamento;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagamentoApplication.class, args);
	}

}
