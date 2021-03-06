package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {
	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;
	
	
	public void avalia(Leilao leilao) {
		List<Lance> lances = leilao.getLances();
		
		if(leilao.getLances().size() == 0) {
			throw new RuntimeException("N�o � possivel avaliar leil�es sem lances");
		}
		
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
		
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>(){

			public int compare(Lance o1, Lance o2) {
				if(o1.getValor() < o2.getValor()) {
					return 1;
				}
				if(o1.getValor() > o2.getValor()) {
					return -1;
				}
				return 0;
			}
		});
		
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}
	
	public double getMaiorLance() {
		return maiorLance;
	}
	
	public double getMenorLance() {
		return menorLance;
	}
	
	public List<Lance> getTresMaiores() {
		return maiores;
	}
}

