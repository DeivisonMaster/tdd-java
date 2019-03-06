package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class CriadorDeLeilao {
	private Lance lance;
	private Leilao leilao;

	public CriadorDeLeilao comADescricao(String descricao) {
		this.leilao = new Leilao(descricao);
		
		return this;
	}
	
	public CriadorDeLeilao comOLance(Usuario usuario, double valor) {
		this.lance = new Lance(usuario, valor);
		this.leilao.propoe(lance);
		
		return this;
	}
	
	public Leilao criaEAvaliaLeilao() {
		this.leilao.propoe(lance);
		
		return leilao;
	}
}
