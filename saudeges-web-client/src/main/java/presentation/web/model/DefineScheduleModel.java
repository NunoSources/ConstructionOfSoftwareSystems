package presentation.web.model;


import java.io.Serializable;
import java.util.LinkedList;

import facade.dtos.AtividadeDTO;
import facade.dtos.InstrutorDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoAtividadeRemote;

/**
 * Helper class to assist in the response of novo cliente.
 * This class is the response information expert.
 * 
 *
 */
public class DefineScheduleModel extends Model {

	
	private String designacao;
	private String duracao;
	private String numeroInstrutor;
	
	//HORARIO RELATED
	private String startDate;
	private String[] sessoes;
	
	private IServicoAtividadeRemote activityService;
		
	public void setActivityService(IServicoAtividadeRemote activityService) {
		this.activityService = activityService;
	}
	
	/**
	 * @return the designacao
	 */
	public String getDesignacao() {
		return designacao;
	}

	/**
	 * @return the duracao
	 */
	public String getDuracao() {
		return duracao;
	}

	/**
	 * @return the numeroInstrutor
	 */
	public String getNumeroInstrutor() {
		return numeroInstrutor;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @return the sessoes
	 */
	public String[] getSessoes() {
		return sessoes;
	}
	

	/**
	 * @param designacao the designacao to set
	 */
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	/**
	 * @param duracao the duracao to set
	 */
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	/**
	 * @param numeroInstrutor the numeroInstrutor to set
	 */
	public void setNumeroInstrutor(String numeroInstrutor) {
		this.numeroInstrutor = numeroInstrutor;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param sessoes the sessoes to set
	 */
	public void setSessoes(String[] sessoes) {
		this.sessoes = sessoes;
	}

	public Iterable<AtividadeDTO> getAtividadesRegulares() {
		try {
			return activityService.definirHorario();
		} catch (ApplicationException e) {
			return new LinkedList<> ();		
		}
	}
	
	public Iterable<InstrutorDTO> getInstrutores() {
		try {
			return activityService.getInstrutores();
		} catch (ApplicationException e) {
			return new LinkedList<> ();		
		}
	}
	
	
	public void clearFields() {
		designacao = duracao = numeroInstrutor = startDate = "";
		sessoes = new String[50];
	}
	
	
	
	public class SessoesAux implements Serializable {
			
			/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
			private int weekdayIndex;
			private String startHour;
			
			public SessoesAux(int weekdayIndex, String startHour) {
				this.weekdayIndex = weekdayIndex;
				this.startHour = startHour;
			}
	
			/**
			 * @return the weekdayIndex
			 */
			public int getWeekdayIndex() {
				return weekdayIndex;
			}
	
			/**
			 * @return the startHour
			 */
			public String getStartHour() {
				return startHour;
			}
			
			
			
		}
	 

	
}
