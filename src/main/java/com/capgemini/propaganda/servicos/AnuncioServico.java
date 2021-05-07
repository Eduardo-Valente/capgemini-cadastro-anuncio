package com.capgemini.propaganda.servicos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.propaganda.entidades.Anuncio;
import com.capgemini.propaganda.repositorios.AnuncioRepositorio;
import com.capgemini.propaganda.servicos.excecoes.BancoDeDadosException;
import com.capgemini.propaganda.servicos.excecoes.RecursoNaoEncontradoException;

@Service
public class AnuncioServico {

	@Autowired
	private AnuncioRepositorio repositorio;

	public List<Anuncio> encontrarTodosOsDados() {
		return repositorio.findAll();
	}

	public Anuncio encontrarPeloId(Long id) {
		Optional<Anuncio> anuncio = repositorio.findById(id);
		return anuncio.orElseThrow(() -> new RecursoNaoEncontradoException(id));
	}
	
	public Anuncio inserirNoBancoDeDados(Anuncio anu) {
		return repositorio.save(anu);
	}
	
	public void removerDoBancoDeDados(Long id) {

		try {
			repositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException(id);
		} catch (DataIntegrityViolationException e) {
			throw new BancoDeDadosException(e.getMessage());
		}
	}
	
	public Anuncio atualizarDados(Long id, Anuncio anu) {

		try {
			Anuncio entidade = repositorio.getOne(id);
			atualizarDadosEspecificos(entidade, anu);
			return repositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new RecursoNaoEncontradoException(id);
		}
	}
	
	private void atualizarDadosEspecificos(Anuncio entidade, Anuncio anu) {
		
		entidade.setNome(anu.getNome());
		entidade.setDataInicio(anu.getDataInicio());
		entidade.setDataTermino(anu.getDataTermino());
		entidade.setInvestimentoDia(anu.getInvestimentoDia());
	}
}
