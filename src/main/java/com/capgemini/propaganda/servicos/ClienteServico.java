package com.capgemini.propaganda.servicos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.propaganda.entidades.Cliente;
import com.capgemini.propaganda.repositorios.ClienteRepositorio;
import com.capgemini.propaganda.servicos.excecoes.BancoDeDadosException;
import com.capgemini.propaganda.servicos.excecoes.RecursoNaoEncontradoException;

@Service
public class ClienteServico {

	@Autowired
	private ClienteRepositorio repositorio;

	public List<Cliente> encontrarTodosOsDados() {
		return repositorio.findAll();
	}

	public Cliente encontrarPeloId(Long id) {
		Optional<Cliente> cliente = repositorio.findById(id);
		return cliente.orElseThrow(() -> new RecursoNaoEncontradoException(id));
	}
	
	public Cliente inserirNoBancoDeDados(Cliente cli) {
		return repositorio.save(cli);
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
	
	public Cliente atualizarDados(Long id, Cliente cli) {

		try {
			Cliente entidade = repositorio.getOne(id);
			atualizarDadosEspecificos(entidade, cli);
			return repositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new RecursoNaoEncontradoException(id);
		}
	}
	
	private void atualizarDadosEspecificos(Cliente entidade, Cliente cli) {
		entidade.setNome(cli.getNome());
		entidade.setEmail(cli.getEmail());
		entidade.setTelefone(cli.getTelefone());
	} 
}
