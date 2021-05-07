package com.capgemini.propaganda.recursos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capgemini.propaganda.entidades.Cliente;
import com.capgemini.propaganda.servicos.ClienteServico;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteRecurso {

	@Autowired 
	private ClienteServico servico;
	
		@GetMapping
		public ResponseEntity<List<Cliente>> findAll() {
			List<Cliente> list = servico.encontrarTodosOsDados();
			return ResponseEntity.ok().body(list);
		}
		
		@GetMapping(value = "/{id}")
		public ResponseEntity<Cliente> findById(@PathVariable Long id) {
			Cliente cli = servico.encontrarPeloId(id);
			return ResponseEntity.ok().body(cli);
		}
		
		@PostMapping
		public ResponseEntity<Cliente> insert(@RequestBody Cliente cli) {
			cli = servico.inserirNoBancoDeDados(cli);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(cli.getId()).toUri();
			return ResponseEntity.created(uri).body(cli);
		}
		
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			servico.removerDoBancoDeDados(id);
			return ResponseEntity.noContent().build();
		}
		
		@PutMapping(value = "/{id}")
		public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cli) {
			cli = servico.atualizarDados(id, cli);
			return ResponseEntity.ok().body(cli);
		}
}
