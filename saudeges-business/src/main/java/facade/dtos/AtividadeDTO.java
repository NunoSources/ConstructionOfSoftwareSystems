package facade.dtos;

import java.io.Serializable;

public class AtividadeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String especialidade;
	private String designacao;
	private int nrSessoes;
	private int duracao;
	private float preco;
	private int maxParticipantes;
	private boolean isRegular;
	
	public AtividadeDTO(String especialidade, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco) {
		this.especialidade = especialidade;
		this.designacao = designacao;
		this.nrSessoes = nrSessoes;
		this.duracao = duracao;
		this.preco = preco;
		this.isRegular = isRegular;
	}
	
	public AtividadeDTO(String especialidade, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco, int maxParticipantes) {
		this.especialidade = especialidade;
		this.designacao = designacao;
		this.nrSessoes = nrSessoes;
		this.duracao = duracao;
		this.maxParticipantes = maxParticipantes;
		this.preco = preco;
		this.isRegular = isRegular;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public String getDesignacao() {
		return designacao;
	}

	public int getNrSessoes() {
		return nrSessoes;
	}

	public int getDuracao() {
		return duracao;
	}

	public float getPreco() {
		return preco;
	}

	public int getMaxParticipantes() {
		return maxParticipantes;
	}
	
	public boolean isRegular() {
		return isRegular;
	}
	
	
	
}
