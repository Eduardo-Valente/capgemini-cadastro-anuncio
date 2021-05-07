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

import com.capgemini.propaganda.entidades.Anuncio;
import com.capgemini.propaganda.servicos.AnuncioServico;

@RestController
@RequestMapping(value = "/anuncios")
public class AnuncioRecurso {

		@Autowired 
		private AnuncioServico servico;

		@GetMapping
		public ResponseEntity<List<Anuncio>> findAll() {
			List<Anuncio> list = servico.encontrarTodosOsDados();
			return ResponseEntity.ok().body(list);
		}

		@GetMapping(value = "/{id}")
		public ResponseEntity<Anuncio> findById(@PathVariable Long id) {
			Anuncio anuncio = servico.encontrarPeloId(id);
			return ResponseEntity.ok().body(anuncio);
		}
		
		@PostMapping
		public ResponseEntity<Anuncio> insert(@RequestBody Anuncio anu) {
			anu = servico.inserirNoBancoDeDados(anu);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(anu.getId()).toUri();
			return ResponseEntity.created(uri).body(anu);
		}
		
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			servico.removerDoBancoDeDados(id);
			return ResponseEntity.noContent().build();
		}
		
		@PutMapping(value = "/{id}")
		public ResponseEntity<Anuncio> update(@PathVariable Long id, @RequestBody Anuncio anu) {
			anu = servico.atualizarDados(id, anu);
			return ResponseEntity.ok().body(anu);
		}
}
