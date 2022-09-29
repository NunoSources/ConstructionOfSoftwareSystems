package facade.dtos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import business.utils.DateUtils;

public class DadosPagamentoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String emailAssociado;
	
	private String entity;

	private String reference;

	private double value;
	
	private Date dataPagamento;

	public DadosPagamentoDTO(String email, double value) {
		this.emailAssociado = email;
		this.entity = generateRandomNumber(5);
		this.reference = generateRandomNumber(9);
		this.value = value;
		
		Calendar cal = Calendar.getInstance();
		cal.set(DateUtils.getMockCurrentDate().getYear(), DateUtils.getMockCurrentDate().getMonthValue()-1,DateUtils.getMockCurrentDate().getDayOfMonth(), DateUtils.getMockCurrentDate().getHour(), DateUtils.getMockCurrentDate().getMinute(), DateUtils.getMockCurrentDate().getSecond());
		cal.add(Calendar.HOUR, 24);
		this.dataPagamento = cal.getTime();
	}
	
	public String getEmailAssociado() {
		return emailAssociado;
	}

	public String getEntity() {
		return entity;
	}

	public String getReference() {
		return reference;
	}

	public double getValue() {
		return value;
	}
	
	

	/**
	 * @return the dataPagamento
	 */
	public Date getDataPagamento() {
		return dataPagamento;
	}

	@Override
	public String toString() {
		return "Utilizador com email: " + emailAssociado + "\nEntidade: "+ entity + "\n\tReferencia: " + reference + "\n\tValor: " + value + "\n\tPagar at�: " + dataPagamento.toString();
	}
	
	public String toHTML() {
		return "Utilizador com email: " + emailAssociado + "<br>Entidade: "+ entity + "<br>Referencia: " + reference + "<br>Valor: " + value + "<br>Pagar ate: " + dataPagamento.toString();
	}
	
	private String generateRandomNumber(int n){

	    String randomNumString = "";
	    Random r = new Random();
	    randomNumString += (r.nextInt(9) + 1);
	    
	    for(int x = 1; x < n; x++){
	        randomNumString += r.nextInt(9);
	    }
	    
	    return randomNumString;
	}

}
