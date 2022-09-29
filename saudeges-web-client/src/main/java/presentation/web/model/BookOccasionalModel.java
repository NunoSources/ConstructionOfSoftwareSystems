package presentation.web.model;


import java.util.LinkedList;
import java.util.List;

import facade.dtos.AtividadeDTO;
import facade.dtos.EspecialidadeDTO;
import facade.dtos.HorarioDTO;
import facade.dtos.InstrutorDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoVendaRemote;

/**
 * Helper class to assist in the response of novo cliente.
 * This class is the response information expert.
 * 
 * @author fmartins
 *
 */
public class BookOccasionalModel extends Model {

	private String designacao;
	private String especialidade;
	private String preco;
	private List<HorarioDTO> horario;
	private List<AtividadeDTO> atividades;
	private List<InstrutorDTO> instrutores;
	
	private String startDate;
	private String[] sessoes;
	
	private String email;
	private int instrutorId;
	
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
	 * @return the especializacao
	 */
	public String getEspecialidade() {
		return especialidade;
	}




	/**
	 * @param especializacao the especializacao to set
	 */
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
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
	 * @return the sessoes
	 */
	public String[] getSessoes() {
		return sessoes;
	}




	/**
	 * @param sessoes the sessoes to set
	 */
	public void setSessoes(String[] sessoes) {
		this.sessoes = sessoes;
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
	 * @return the instrutorId
	 */
	public int getInstrutorId() {
		return instrutorId;
	}




	/**
	 * @param instrutorId the instrutorId to set
	 */
	public void setInstrutorId(int instrutorId) {
		this.instrutorId = instrutorId;
	}

	/**
	 * @return the atividades
	 */
	public List<AtividadeDTO> getAtividades() {
		return atividades;
	}


	/**
	 * @param atividades the atividades to set
	 */
	public void setAtividades(List<AtividadeDTO> atividades) {
		this.atividades = atividades;
	}


	/**
	 * @return the instrutores
	 */
	public List<InstrutorDTO> getInstrutores() {
		return instrutores;
	}


	/**
	 * @param instrutores the instrutores to set
	 */
	public void setInstrutores(List<InstrutorDTO> instrutores) {
		this.instrutores = instrutores;
	}


	public Iterable<EspecialidadeDTO> getEspecialidades() {
		try {
			return salesService.getEspecialidades();
		} catch (ApplicationException e) {
			return new LinkedList<> ();		
		}
	}
	
	
	public void clearFields() {
		designacao = preco = startDate = especialidade = email = "";
		instrutorId = 0;
		sessoes = new String[50];
		
		if(horario != null)
			horario.clear();
		
		if(atividades != null)
			atividades.clear();
		
		if(instrutores != null) 
			instrutores.clear();
		
	}
	 

	
}
