package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import br.com.caelum.leilao.servico.CriadorDeLeilao;

public class TesteDoAvaliador {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario maria;
	private Usuario jose;

	@Before // inicializa os atributos de classe atribuindo uma nova referencia
	public void inicializaAvaliador() {
		leiloeiro = new Avaliador();
		
		joao = new Usuario("João");
		maria = new Usuario("Maria");
		jose = new Usuario("Jose");
	}
	
	@Test(expected = RuntimeException.class) // exceção esperada
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
		Leilao leilao = new CriadorDeLeilao().comADescricao("Playstation 3 novo").criaEAvaliaLeilao();
		
		leiloeiro.avalia(leilao);
	}
	

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenario
		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		
		// parte 2: ação
		leiloeiro.avalia(leilao);
		
		// parte 3: validação
		double maiorEsperado = 400; 
		double menorEsperado = 250;
		
		System.out.println();
		
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
	
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Leilao leilao = new Leilao("Playstation 3 novo");
		leilao.propoe(new Lance(joao, 1000.0));
		
		leiloeiro.avalia(leilao);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().comADescricao("Playstation 3 novo")
				.comOLance(joao, 100.0)
				.comOLance(maria, 200.0)
				.comOLance(joao, 300.0)
				.comOLance(maria, 400.0)
				.criaEAvaliaLeilao();
		
		
		leiloeiro.avalia(leilao);
		
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, tresMaiores.size());
		assertEquals(400, tresMaiores.get(0).getValor(), 0.00001);
		assertEquals(300, tresMaiores.get(1).getValor(), 0.00001);
		assertEquals(200, tresMaiores.get(2).getValor(), 0.00001);
	}
}











