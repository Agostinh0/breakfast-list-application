package br.com.mv.breakfast.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mv.breakfast.dtos.FuncionarioDto;
import br.com.mv.breakfast.mappers.FuncionarioMapper;
import br.com.mv.breakfast.models.Funcionario;
import br.com.mv.breakfast.repository.FuncionarioRepository;

@RestController
@RequestMapping("/listaCafeDaManha/funcionarios")
@CrossOrigin(origins="https://breakfast-list-ui.herokuapp.com", maxAge = 3600)
public class FuncionarioController {
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@PostMapping(path = "/salvar")
	public void inserirFuncionario(@RequestParam(name = "nome", required = true) String nome,
			@RequestParam(name = "cpf", required = false) String cpf) {
		 funcionarioRepository.inserirFuncionario(nome, cpf);
	}
	
	@GetMapping(path = "/listar")
	public List<FuncionarioDto> listarItens(){
		List<Funcionario> funcionarios = funcionarioRepository.listarFuncionarios();
	
		List<FuncionarioDto> funcionarioDtos = new ArrayList<>();
	
		for(Funcionario funcionario : funcionarios) {
			funcionarioDtos.add(FuncionarioMapper.toDtoMapping(funcionario));
		}
		
		return funcionarioDtos;
	}
	
	@GetMapping(path = "/buscar")
	public FuncionarioDto buscarPorCpf(@RequestParam(name = "cpf") String cpf) {
		Funcionario funcionario = funcionarioRepository.buscarPorCpf(cpf);
		
		return FuncionarioMapper.toDtoMapping(funcionario);
	}
	
	@PutMapping(path = "/atualizar")
	public void atualizarItem(@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "cpf", required = false) String cpfFuncionario) {
		funcionarioRepository.atualizarFuncionario(nome, cpfFuncionario);
	}
	
	@DeleteMapping(path = "remover")
	public void removerItem(@RequestParam(name = "cpf") String cpf) {
		funcionarioRepository.removerFuncionario(cpf);
	}
}
