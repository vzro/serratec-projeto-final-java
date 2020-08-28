package org.serratec.com.controllers;

import org.serratec.com.exceptions.ContaInvalida;
import org.serratec.com.exceptions.QuantiaInvalida;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {
	@ExceptionHandler(ContaInvalida.class)
	public ResponseEntity<String> contaInvalida(ContaInvalida exception) {
		String mensagem = "Conta inválida";
		return ResponseEntity.notFound()
				.header("X-ERROR", mensagem)
				.build();
	}

	@ExceptionHandler(QuantiaInvalida.class)
	public ResponseEntity<String> quantiaInvalida(QuantiaInvalida exception) {
		String mensagem = "Quantia inválida";
		return ResponseEntity.notFound()
				.header("X-ERROR", mensagem)
				.build();
	}
}
