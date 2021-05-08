package br.com.mv.breakfast.mappers;

import br.com.mv.breakfast.dtos.ItemDto;
import br.com.mv.breakfast.models.Item;

public class ItemMapper {
	
	public static ItemDto toDtoMapping(Item item) {
		ItemDto dto = new ItemDto(item.getId(), item.getNome());
		
		try {
			String cpfFuncionario = item.getFuncionario().getCpf();
			dto.setCpfFuncionario(cpfFuncionario);
		}catch(NullPointerException e) {
			dto.setCpfFuncionario("Não selecionado por um funcionário ainda");
		}
		
		return dto;
	}
}
