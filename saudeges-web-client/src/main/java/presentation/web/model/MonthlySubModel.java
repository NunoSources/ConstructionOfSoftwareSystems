package presentation.web.model;


import java.util.LinkedList;
import java.util.List;

import facade.dtos.AtividadeDTO;
import facade.dtos.HorarioDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoVendaRemote;

/**
 * Helper class to assist in the response of novo cliente.
 * This class is the response information expert.
 * 
 * @author fmartins
 *
 */
public class MonthlySubModel extends Model {

	private String designacao;
	private String preco;
	private List<HorarioDTO> horario;
	
	private String startDate;
	private String duracao;
	private String email;
	private int horarioId;
	
	//private Iterable<HorariosDisponiveisDTO> horariosAssociados;
	
	private IServicoVendaRemote salesService;
		
	public void setActivityService(IServicoVendaRemote salesService) {
		this.salesService = salesService;
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
	 * @return the horario
	 */
	public List<HorarioDTO> getHorario() {
		return horario;
	}



	/**
	 * @param horario the horario to set
	 */
	public void setHorario(List<HorarioDTO> horario) {
		this.horario = horario;
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



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the horarioId
	 */
	public int getHorarioId() {
		return horarioId;
	}



	/**
	 * @param horarioId the horarioId to set
	 */
	public void setHorarioId(int horarioId) {
		this.horarioId = horarioId;
	}


	public Iterable<AtividadeDTO> getAtividades() {
		try {
			return salesService.getAtividades();
		} catch (ApplicationException e) {
			return new LinkedList<> ();		
		}
	}
	
	
	public void clearFields() {
		designacao = preco = startDate = duracao = preco = email = "";
		
		if(horario != null)
			horario.clear();
	}
	 

	
}
