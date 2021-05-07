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
		
		Anuncio anu1 = new Anuncio(null, "Dog Show", Instant.parse("2019-06-18T19:53:07Z"), 
								Instant.parse("2019-06-20T19:53:07Z"), 28f, cli1);
		Anuncio anu2 = new Anuncio(null, "Neo Pharma", Instant.parse("2019-06-15T19:53:07Z"), 
								Instant.parse("2019-07-21T03:42:10Z"), 42f, cli2);
		Anuncio anu3 = new Anuncio(null, "Invisible Ink", Instant.parse("2019-06-10T19:53:07Z"), 
								Instant.parse("2019-07-22T15:21:22Z"), 35f, cli1);

		clienteRepositorio.saveAll(Arrays.asList(cli1, cli2));
		anuncioRepositorio.saveAll(Arrays.asList(anu1, anu2, anu3));
	}
}
