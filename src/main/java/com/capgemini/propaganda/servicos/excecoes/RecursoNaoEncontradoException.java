package com.capgemini.propaganda.servicos.excecoes;

public class RecursoNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public RecursoNaoEncontradoException(Object id) {
		super("Recurso não encontrado. Id " + id);
	}
}
