package com.capgemini.propaganda.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.propaganda.entidades.Anuncio;

public interface AnuncioRepositorio extends JpaRepository<Anuncio, Long> {
	 
}
