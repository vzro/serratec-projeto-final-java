package org.serratec.com.controllers;

import java.util.List;

import org.serratec.com.domain.Conta;
import org.serratec.com.exceptions.ContaExistente;
import org.serratec.com.exceptions.ContaInvalida;
import org.serratec.com.exceptions.QuantiaInvalida;
import org.serratec.com.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {
	@Autowired
	private ContaService contaService;
	
	@GetMapping
	public List<Conta> listarContas() {
		return contaService.listarContas();
	}

	@GetMapping("/{numero}")
	public Conta obterConta(@PathVariable Integer numero) throws ContaInvalida {
		return contaService.obterConta(numero);
	}

	@PutMapping("/{numero}")
	public Conta atualizarConta(@PathVariable Integer numero, @RequestBody Conta conta) throws ContaInvalida, ContaExistente {
		return contaService.atualizarConta(numero, conta);
	}

	@PostMapping
	public Conta inserirConta(@RequestBody Conta conta) {
		return contaService.inserirConta(conta);
	}

	@PostMapping("/{numero}/sacar")
	public Conta sacar(@PathVariable Integer numero, @RequestParam Double quantia) throws ContaInvalida, QuantiaInvalida {
		return contaService.sacar(numero, quantia);
	}

	@PostMapping("/{numero}/depositar")
	public Conta depositar(@PathVariable Integer numero, @RequestParam Double quantia) throws ContaInvalida, QuantiaInvalida {
		return contaService.depositar(numero, quantia);
	}

	@DeleteMapping("/{numero}")
	public void apagarConta(@PathVariable Integer numero) throws ContaInvalida {
		contaService.apagarConta(numero);
	}
}
