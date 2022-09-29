package facade.dtos;

import java.io.Serializable;
import java.util.List;

public class HorarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nomeAtividade;
	private List<String> sessoes;
	private String startDate;
	private String duracao;
	
	public HorarioDTO(int id, String nomeAtividade, List<String> sessoes, String startDate, String duracao) {
		super();
		this.id = id;
		this.nomeAtividade = nomeAtividade;
		this.sessoes = sessoes;
		this.startDate = startDate;
		this.duracao = duracao;
	}
	
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @return the nomeAtividade
	 */
	public String getNomeAtividade() {
		return nomeAtividade;
	}

	/**
	 * @param nomeAtividade the nomeAtividade to set
	 */
	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	/**
	 * @return the sessoes
	 */
	public List<String> getSessoes() {
		return sessoes;
	}

	/**
	 * @param sessoes the sessoes to set
	 */
	public void setSessoes(List<String> sessoes) {
		this.sessoes = sessoes;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the duracao
	 */
	public String getDuracao() {
		return duracao;
	}

	/**
	 * @param duracao the duracao to set
	 */
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	
	
	
	
	
	
}
