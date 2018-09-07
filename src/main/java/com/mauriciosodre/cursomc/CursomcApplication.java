package com.mauriciosodre.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mauriciosodre.cursomc.domain.Categoria;
import com.mauriciosodre.cursomc.domain.Cidade;
import com.mauriciosodre.cursomc.domain.Estado;
import com.mauriciosodre.cursomc.domain.Produto;
import com.mauriciosodre.cursomc.repositories.CategoriaRepository;
import com.mauriciosodre.cursomc.repositories.CidadeRepository;
import com.mauriciosodre.cursomc.repositories.EstadoRepository;
import com.mauriciosodre.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Instanciando e testando as categorias
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		// Instanciando e testando os produtos
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		// Incluindo os produtos nas categorias
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		// Persistindo as categorias
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

		// Persistindo os produtos
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		// Instanciando e testando os estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		// Instanciando e testando as cidades
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		// Incluindo as cidades nos estados
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		// Persistindo os estados
		estadoRepository.saveAll(Arrays.asList(est1, est2));

		// Persistindo as cidades
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
	}
}
