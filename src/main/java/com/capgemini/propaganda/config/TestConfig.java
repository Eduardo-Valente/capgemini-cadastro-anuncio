package com.capgemini.propaganda.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.capgemini.propaganda.entidades.Anuncio;
import com.capgemini.propaganda.entidades.Cliente;
import com.capgemini.propaganda.repositorios.AnuncioRepositorio;
import com.capgemini.propaganda.repositorios.ClienteRepositorio;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Autowired
	private AnuncioRepositorio anuncioRepositorio;

	@Override
	public void run(String... args) throws Exception {

		Cliente cli1 = new Cliente(null, "Maria Brown", "maria@gmail.com", "988888888");
		Cliente cli2 = new Cliente(null, "Alex Green", "alex@gmail.com", "977777777");
		
		Anuncio anu1 = new Anuncio(null, "Tintas Ad Infinitum", Instant.parse("18/05/2019"), 
								Instant.parse("20/06/2019"), 28f, cli1);
		Anuncio anu2 = new Anuncio(null, "Neo Pharma", Instant.parse("15/02/2019"), 
								Instant.parse("21/07/2019"), 42f, cli2);
		Anuncio anu3 = new Anuncio(null, "Toalhas Emporium", Instant.parse("27/04/2019"), 
								Instant.parse("07/11/2019"), 35f, cli1);

		clienteRepositorio.saveAll(Arrays.asList(cli1, cli2));
		anuncioRepositorio.saveAll(Arrays.asList(anu1, anu2, anu3));
	}
}
