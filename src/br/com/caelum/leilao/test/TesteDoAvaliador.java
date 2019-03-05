package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("Jose");
		
		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
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
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("Playstation 3 novo");
		leilao.propoe(new Lance(joao, 1000.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 3 novo");
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, tresMaiores.size());
		assertEquals(400, tresMaiores.get(0).getValor(), 0.00001);
		assertEquals(300, tresMaiores.get(1).getValor(), 0.00001);
		assertEquals(200, tresMaiores.get(2).getValor(), 0.00001);
	}
}











