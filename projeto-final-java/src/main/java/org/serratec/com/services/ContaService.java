package org.serratec.com.services;

import java.util.ArrayList;
import java.util.List;

import org.serratec.com.domain.Conta;
import org.serratec.com.exceptions.ContaExistente;
import org.serratec.com.exceptions.ContaInvalida;
import org.serratec.com.exceptions.QuantiaInvalida;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
	private List<Conta> contas = new ArrayList<Conta>();


	public ContaService() {
		contas.add(new Conta(1, "Assis", 4523.34));
		contas.add(new Conta(2, "Machado", 123.45));
		contas.add(new Conta(3, "Degeneres", 8000.00));
		contas.add(new Conta(4, "Pablo Vittar", 690.89));
	}

	public List<Conta> listarContas() {
		return contas;
	}

	public Conta obterConta(Integer numero) throws ContaInvalida {
		Conta encontrada = null;
		for (Conta conta : contas) {
			if(conta.getNumero().equals(numero)) {
				encontrada = conta;
				break;
			}
		}
		if(encontrada == null) {
			throw new ContaInvalida();
		} else {
			return encontrada;
		}
	}

	public Conta atualizarConta(Integer numero, Conta contaAtualizada) throws ContaInvalida, ContaExistente {
		Conta encontrada = obterConta(numero);

		for (Conta conta : contas) {
			if(conta.getNumero().equals(numero)) {
				encontrada = conta;
				break;
			}
		}
		/*
		 * FIXME	A lógica está errada neste ponto, a saber:
		 * 			Desta forma não será possível alterar o número da conta conforme proposta do trabalho:
		 * 				PUT /conta/numero - atualiza a conta (somente nome do titular e numero)
		 *			Toda vez que alterar o número da conta será executado o bloco Else (ContaExistente)
		 */
		if(encontrada.getNumero() == contaAtualizada.getNumero()) {
			encontrada.setNumero(contaAtualizada.getNumero());
			encontrada.setTitular(contaAtualizada.getTitular());
			return encontrada;
		} else {
			throw new ContaExistente();
		}

	}

	public Conta inserirConta(Conta novaConta) {
		//FIXME Deveria propagar uma Exception ao tentar criar uma conta com um número já existente.
		contas.add(novaConta);
		return novaConta;
	}

	public void apagarConta(Integer numero) throws ContaInvalida {
		Conta encontrada = obterConta(numero);
		contas.remove(encontrada);
	}

	public Conta sacar(Integer numero, Double quantia) throws ContaInvalida, QuantiaInvalida {
		Conta encontrada = obterConta(numero);
		if (encontrada.getSaldo() < quantia) {
			//TODO deveria ter 2 tipos de Exceptions: SaldoInvalido(débito) e QuantiaInvalida(crédito)
			throw new QuantiaInvalida();
		} else {
			encontrada.setSaldo(encontrada.getSaldo()-quantia);
		}
		return encontrada;
	}

	public Conta depositar(Integer numero, Double quantia) throws ContaInvalida, QuantiaInvalida {
		Conta encontrada = obterConta(numero);
		
		//TODO O limite mínimo poderia ser configurado em application.properties
		if (quantia < 50) {
			throw new QuantiaInvalida();
		} else {
			encontrada.setSaldo(encontrada.getSaldo()+quantia);
		}
		return encontrada;	
	}

}
