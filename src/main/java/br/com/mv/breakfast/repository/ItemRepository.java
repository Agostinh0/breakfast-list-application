package br.com.mv.breakfast.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mv.breakfast.models.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer>, 
	PagingAndSortingRepository<Item, Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO item (nome, cpf_funcionario) VALUES (:nome, :cpfFuncionario)", nativeQuery = true)
	void salvarItem(@Param("nome") String nome, @Param("cpfFuncionario") String cpfFuncionario);
	
	@Query(value = "SELECT * FROM item", nativeQuery = true)
	List<Item> obterTodosOsItens();
	
	@Query(value = "SELECT * FROM item WHERE item.id = :id", nativeQuery = true)
	Item buscarPorId(@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE item SET nome = ?1 WHERE id = ?2", nativeQuery = true)
	void atualizarNomeItem(String nome, int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE item SET cpf_funcionario = ?1 WHERE id = ?2", nativeQuery = true)
	void associarComFuncionario(String cpfFuncionario, int id);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM item WHERE id = :id", nativeQuery = true)
	void removerItem(@Param("id") int id);
	
}
