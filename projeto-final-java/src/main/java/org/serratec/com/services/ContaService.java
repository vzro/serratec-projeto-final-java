package org.serratec.com.services;

import java.util.ArrayList;
import java.util.List;

import org.serratec.com.domain.Conta;
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

	public Conta atualizarConta(Integer numero, Conta contaAtualizada) throws ContaInvalida {
		Conta encontrada = obterConta(numero);
		encontrada.setNumero(contaAtualizada.getNumero());
		encontrada.setTitular(contaAtualizada.getTitular());
		return encontrada;
	}

	public Conta inserirConta(Conta novaConta) {
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
			throw new QuantiaInvalida();
		} else {
			encontrada.setSaldo(encontrada.getSaldo()-quantia);
		}
		return encontrada;
	}

	public Conta depositar(Integer numero, Double quantia) throws ContaInvalida, QuantiaInvalida {
		Conta encontrada = obterConta(numero);
		if (quantia < 50) {
			throw new QuantiaInvalida();
		} else {
			encontrada.setSaldo(encontrada.getSaldo()+quantia);
		}
		return encontrada;	
	}

}
