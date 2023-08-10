package com.br.springbootrest.jdevtreinamento.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.springbootrest.jdevtreinamento.model.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{

	@Query(value = "Select u from Usuario u Where u.nome like  %?1%")
	List<Usuario> buscarPorNome(String nome);
}
