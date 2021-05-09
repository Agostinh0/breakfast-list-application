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

import br.com.mv.breakfast.dtos.ItemDto;
import br.com.mv.breakfast.mappers.ItemMapper;
import br.com.mv.breakfast.models.Item;
import br.com.mv.breakfast.repository.ItemRepository;

@RestController
@RequestMapping("/listaCafeDaManha/itens")
@CrossOrigin(origins="https://breakfast-list-ui.herokuapp.com", maxAge = 3600)
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@PostMapping(path = "/salvar")
	public void inserirItem(@RequestParam(name = "nome", required = true) String nome,
			@RequestParam(name = "cpfFuncionario", required = false) String cpfFuncionario) {
		 itemRepository.salvarItem(nome, cpfFuncionario);
	}
	
	@GetMapping(path = "/listar")
	public List<ItemDto> listarItens(){
		List<Item> itens = itemRepository.obterTodosOsItens();
		
		List<ItemDto> itemDtos = new ArrayList<>();
		
		for(Item item: itens) {
			itemDtos.add(ItemMapper.toDtoMapping(item));
		}
		
		return itemDtos;
	}
	
	@GetMapping(path = "/buscar")
	public ItemDto buscarPorId(@RequestParam(name = "id") int id) {
		Item item = itemRepository.buscarPorId(id);
		
		return ItemMapper.toDtoMapping(item);
	}
	
	@PutMapping(path = "/atualizar")
	public void atualizarItem(@RequestParam(name="id") int id, 
			@RequestParam(name = "nome", required = true) String nome) {
		itemRepository.atualizarNomeItem(nome, id);
	}
	
	@PutMapping(path = "/associarComFuncionario")
	public void associarComFuncionario(@RequestParam(name="id") int id, 
			@RequestParam(name = "cpfFuncionario", required = true) String cpf) {
		itemRepository.associarComFuncionario(cpf, id);
	}
	
	@DeleteMapping(path = "remover")
	public void removerItem(@RequestParam(name = "id") int id) {
		itemRepository.removerItem(id);
	}
	
	
}
