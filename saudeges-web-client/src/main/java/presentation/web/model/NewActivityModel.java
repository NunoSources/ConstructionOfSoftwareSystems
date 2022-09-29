package presentation.web.model;


import java.util.LinkedList;

import facade.dtos.EspecialidadeDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoAtividadeRemote;

/**
 * Helper class to assist in the response of novo cliente.
 * This class is the response information expert.
 * 
 * @author fmartins
 *
 */
public class NewActivityModel extends Model {

	private String especialidadeTipo;
	private String designacao;
	private String isRegular;
	private String nrSessoes;
	private String preco;
	private String maxParticipantes;	
	private String duracao;
	//private Iterable<HorariosDisponiveisDTO> horariosAssociados;
	
	private IServicoAtividadeRemote activityService;
		
	public void setActivityService(IServicoAtividadeRemote activityService) {
		this.activityService = activityService;
	}
	
	
	/**
	 * @return the especialidade
	 */
	public String getEspecialidade() {
		return especialidadeTipo;
	}

	/**
	 * @param especialidade the especialidade to set
	 */
	public void setEspecialidade(String especialidade) {
		this.especialidadeTipo = especialidade;
	}


	/**
	 * @return the designacao
	 */
	public String getDesignacao() {
		return designacao;
	}

	/**
	 * @param designacao the designacao to set
	 */
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	/**
	 * @return the isRegular
	 */
	public String getIsRegular() {
		return isRegular;
	}

	/**
	 * @param isRegular the isRegular to set
	 */
	public void setIsRegular(String isRegular) {
		this.isRegular = isRegular;
	}

	/**
	 * @return the nrSessoes
	 */
	public String getNrSessoes() {
		return nrSessoes;
	}

	/**
	 * @param nrSessoes the nrSessoes to set
	 */
	public void setNrSessoes(String nrSessoes) {
		this.nrSessoes = nrSessoes;
	}

	/**
	 * @return the preco
	 */
	public String getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(String preco) {
		this.preco = preco;
	}

	/**
	 * @return the maxParticipantes
	 */
	public String getMaxParticipantes() {
		return maxParticipantes;
	}

	/**
	 * @param maxParticipantes the maxParticipantes to set
	 */
	public void setMaxParticipantes(String maxParticipantes) {
		this.maxParticipantes = maxParticipantes;
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

	/**
	 * @return the horariosAssociados
	 */
//	public Iterable<HorariosDisponiveisDTO> getHorariosAssociados() {
//		return horariosAssociados;
//	}
//
//	/**
//	 * @param horariosAssociados the horariosAssociados to set
//	 */
//	public void setHorariosAssociados(Iterable<HorariosDisponiveisDTO> horariosAssociados) {
//		this.horariosAssociados = horariosAssociados;
//	}
	
	public Iterable<EspecialidadeDTO> getEspecialidades () {
		try {
			return activityService.criaAtividade();
		} catch (ApplicationException e) {
			return new LinkedList<> ();		
		}
	}
	
	
	public void clearFields() {
		especialidadeTipo = designacao = isRegular = nrSessoes = preco = maxParticipantes = duracao = "";
	}
	 

	
}
