package br.com.mv.breakfast.dtos;

import java.util.List;

public class FuncionarioDto {
	
	private String cpf;
	private String nome;
	private List<String> itens;
	
	public FuncionarioDto(String cpf, String nome) {
		super();
		this.cpf = cpf;
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<String> getItens() {
		return itens;
	}
	public void setItens(List<String> itens) {
		this.itens = itens;
	}
	
	
}
