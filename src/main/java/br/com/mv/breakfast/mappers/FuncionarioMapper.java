package br.com.mv.breakfast.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.mv.breakfast.dtos.FuncionarioDto;
import br.com.mv.breakfast.models.Funcionario;
import br.com.mv.breakfast.models.Item;

public class FuncionarioMapper {
	
	public static FuncionarioDto toDtoMapping(Funcionario funcionario) {
		FuncionarioDto dto = new FuncionarioDto(funcionario.getCpf(), funcionario.getNome());
		
		List<String> itens = new ArrayList<>(); 
		
		for(Item item : funcionario.getItems()) {
			itens.add(item.getNome());
		}
		
		dto.setItens(itens);
		
		return dto;
	}
}
