package com.mauriciosodre.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mauriciosodre.cursomc.domain.Categoria;
import com.mauriciosodre.cursomc.domain.Cidade;
import com.mauriciosodre.cursomc.domain.Cliente;
import com.mauriciosodre.cursomc.domain.Endereco;
import com.mauriciosodre.cursomc.domain.Estado;
import com.mauriciosodre.cursomc.domain.Pagamento;
import com.mauriciosodre.cursomc.domain.PagamentoComBoleto;
import com.mauriciosodre.cursomc.domain.PagamentoComCartao;
import com.mauriciosodre.cursomc.domain.Pedido;
import com.mauriciosodre.cursomc.domain.Produto;
import com.mauriciosodre.cursomc.domain.enums.EstadoPagamento;
import com.mauriciosodre.cursomc.domain.enums.TipoCliente;
import com.mauriciosodre.cursomc.repositories.CategoriaRepository;
import com.mauriciosodre.cursomc.repositories.CidadeRepository;
import com.mauriciosodre.cursomc.repositories.ClienteRepository;
import com.mauriciosodre.cursomc.repositories.EnderecoRepository;
import com.mauriciosodre.cursomc.repositories.EstadoRepository;
import com.mauriciosodre.cursomc.repositories.PagamentoRepository;
import com.mauriciosodre.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

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

		// Instanciando e testando os clientes
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		// Incluindo os telefones
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		// Instanciando e testando os endereços
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		// Incluindo os enderecos nos clientes
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		// Persistindo os clientes
		clienteRepository.saveAll(Arrays.asList(cli1));
		// Persistindo os enderecos
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		// Instanciando e testando os pedidos
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		//Instanciando e testando os pagamentos
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		//Incluindo pagamento nos pedidos
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		
		//Incluindo pedidos no cliente
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		//Persistindo os pedidos
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		//Persistindo os pagamentos
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
	}
}
