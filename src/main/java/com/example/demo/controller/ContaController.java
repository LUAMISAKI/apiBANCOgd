package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.demo.model.Conta;
import com.example.demo.repository.ContaRepository;
import com.example.demo.service.ContaService;


@RestController
@RequestMapping("/conta")
public class ContaController {
	

	@Autowired
    private ContaService contaService;
	
	@Autowired
	private ContaRepository contaRepository;

	@GetMapping("/listar")
	public List<Conta> listar() {		
	return contaService.listar();

	}
	@GetMapping("/listar/{numero}")
	public Optional<Conta> pegarId(@PathVariable Integer numero) {
		return contaService.pegarId(numero);
		
	}
	@PostMapping("/adicionar")
	public Conta adicionar(@RequestBody Conta conta) {
		return contaService.adicionar(conta);
		
	}
	@DeleteMapping("/deletar/{numero}")
	public void deletar(@PathVariable Integer numero) {
		 contaService.deletar(numero);
		
	}
	@PutMapping("/atualizar/{numero}")
	public Conta atualizar(@PathVariable Integer numero,@RequestBody Conta conta) {
		return contaService.atualizar(numero, conta);
	}
	@PutMapping("/operacao/{numero}/{operacao}")
	public Conta operacoes(@PathVariable Integer numero, @PathVariable String operacao,@RequestParam double valor) {
	Conta conta = contaRepository.findByNumero(numero);
		
		if(operacao.equals("credito")) {
			contaService.opCred(valor,numero);
			return conta;
			
		}else if(operacao.equals("debito")){
			contaService.opDeb(valor,numero);
			return conta;
		}
		throw new IllegalArgumentException();
	}

}
