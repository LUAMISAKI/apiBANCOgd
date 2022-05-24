package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.model.Conta;
import com.example.demo.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	public List<Conta> listar() {
		return contaRepository.findAll();

	}

	public Optional<Conta> pegarId(Integer numero ) {
		return contaRepository.findById(numero);

	}

	public Conta adicionar(Conta conta) {
		contaRepository.save(conta);
		return conta;
	}

	public void deletar(Integer numero) {
		contaRepository.deleteById(numero);

	}

	public Conta atualizar(Integer id,Conta conta) {
		 Conta cont = new Conta();
		 contaRepository.getById(id);
		 cont= conta;
		 cont.setId(id);
		 contaRepository.save(cont);
		 return cont;
	}

	
public void opDeb(double valor, Integer numero) {
		
		Conta conta= contaRepository.findByNumero(numero);
		double saldo=conta.getSaldo();
		if (saldo>= valor) {
			saldo-=valor;
			conta.setSaldo(saldo);
		
		}else
			throw new IllegalArgumentException("Saldo insuficiente.");
		contaRepository.save(conta);
	}
	
	public void opCred(double valor,Integer numero) {
		Conta conta= contaRepository.findByNumero(numero);
		double saldo=conta.getSaldo();
		if (saldo>= valor) {
			saldo-=valor;
			conta.setSaldo(saldo-0.5);
			//0.5 é a taxa de crédito.
		
		}else
			throw new IllegalArgumentException("Saldo insuficiente.");
		
		contaRepository.save(conta);
	}
	

}
