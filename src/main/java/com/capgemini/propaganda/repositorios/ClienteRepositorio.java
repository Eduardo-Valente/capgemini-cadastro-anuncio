package com.capgemini.propaganda.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.propaganda.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
	
	
}
