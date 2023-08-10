/**
 * 
 */
package com.br.springbootrest.jdevtreinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.springbootrest.jdevtreinamento.Repository.UsuarioRepository;
import com.br.springbootrest.jdevtreinamento.model.Usuario;

/**
 * 
 */
@RestController
public class GreetingsController {

	@Autowired // IC/CD ou CDI Injeção de depedência
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greengText(@PathVariable String name) {
		return "Curso Spring Boot API: " + name + "!";
	}

	// um metedo
	@RequestMapping(value = "/olamundo/{nome}")
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) {

		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuarioRepository.save(usuario); /* Gravar no banco de dados */

		return "Ola Mundo" + nome;
	}

	@GetMapping(value = "listatodos") /* Nosso primeiro metod de api */
	@ResponseBody /* Retorna os dados par ao corpo da resposta */
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();/* Executar a consulta no banco de dados */

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/* Retorna a lista em Json */
	}

	@PostMapping(value = "salvar")
	@ResponseBody /* Descrição da reposta */
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { // Recebe os dados para salvar

		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}
	
 
	@DeleteMapping(value = "delete")
	@ResponseBody /* Descrição da reposta */
	public ResponseEntity<String> delete(@RequestParam Long iduser) {

		usuarioRepository.deleteById(iduser);

		return new ResponseEntity<String>("Usuario deletado", HttpStatus.OK);

	}

	@GetMapping(value = "buscar")
	@ResponseBody /* Descrição da reposta */
	public ResponseEntity<Usuario> buscar(@RequestParam(name = "iduser") Long iduser) {

		Usuario usuario = usuarioRepository.findById(iduser).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}
	
	@GetMapping(value = "buscarPorNome")
	@ResponseBody /* Descrição da reposta */
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name")String name) {

		List <Usuario> usuario = usuarioRepository.buscarPorNome(name.trim());

		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);

	}

	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {

		if (usuario.getId() ==  null) {
			return new ResponseEntity<String>("Id não foi informado", HttpStatus.CREATED);
		}
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}
	
	
}
