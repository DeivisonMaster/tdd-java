package br.com.caelum.leilao.servico;

import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	
	
	public void avalia(Leilao leilao) {
		List<Lance> lances = leilao.getLances();
		
		for (Lance lance : lances) {
			// busca maior lance na lista de lances
			if(lance.getValor() > maiorLance) {
				maiorLance = lance.getValor();
			}
			
			// busca menor lance na lista de lances
			if(lance.getValor() < menorLance ) {
				menorLance = lance.getValor();
			}
		}
	}
	
	public double getMaiorLance() {
		return maiorLance;
	}
	
	public double getMenorLance() {
		return menorLance;
	}
}

