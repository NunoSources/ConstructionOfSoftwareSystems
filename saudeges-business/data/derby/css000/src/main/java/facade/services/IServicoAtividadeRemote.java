package facade.services;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import facade.dtos.AtividadeDTO;
import facade.dtos.EspecialidadeDTO;
import facade.dtos.InstrutorDTO;
import facade.exceptions.ApplicationException;

@Remote
public interface IServicoAtividadeRemote {
	
	public List<EspecialidadeDTO> criaAtividade() throws ApplicationException;
	
	public AtividadeDTO addAtividade(String nome, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco, int maxParticipantes) throws ApplicationException;
	
	public void enviaInformacaoCriarAtividade(String nome, String designacao, boolean isRegular, int nrSessoes, int duracao, 
			float preco) throws ApplicationException;
	
	public void enviaInformacaoCriarAtividade(String nome, String designacao, boolean isRegular, int nrSessoes, int duracao, 
			float preco, int maxParticipantes) 
			throws ApplicationException;
	
	public List<AtividadeDTO> definirHorario() throws ApplicationException;
	
	public void enviaInformacaoDefinirHorario(String designacao, int nrInstrutor, int duracaoInstrutor, String[] sessoes, String startDate) throws ApplicationException;
	
	
	public Collection<EspecialidadeDTO> getEspecialidades() throws ApplicationException;
	
	public Collection<InstrutorDTO> getInstrutores() throws ApplicationException;
}
