package br.com.mv.breakfast.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mv.breakfast.models.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, String>,
	PagingAndSortingRepository<Funcionario, String>{
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO funcionario (cpf, nome) VALUES (:cpf, :nome)", nativeQuery = true)
	void inserirFuncionario(@Param("nome") String nome, @Param("cpf") String cpf);
	
	@Query(value = "SELECT * FROM funcionario", nativeQuery = true)
	List<Funcionario> listarFuncionarios();
	
	@Query(value = "SELECT * FROM funcionario WHERE funcionario.cpf = :cpf", nativeQuery = true)
	Funcionario buscarPorCpf(@Param("cpf") String cpf);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE funcionario SET nome = ?1 WHERE cpf = ?2", nativeQuery = true)
	void atualizarFuncionario(String nome, String cpf);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM funcionario WHERE cpf = :cpf", nativeQuery = true)
	void removerFuncionario(@Param("cpf") String cpf);

}
