package facade.services;

import java.util.List;

import javax.ejb.Remote;

import facade.dtos.AtividadeDTO;
import facade.dtos.DadosPagamentoDTO;
import facade.dtos.EspecialidadeDTO;
import facade.dtos.HorarioPrecoDTO;
import facade.dtos.InstrutorDTO;
import facade.exceptions.ApplicationException;

@Remote
public interface IServicoVendaRemote {
	
	public HorarioPrecoDTO indicaAtividadeParticipacaoMensal(String designacao) throws ApplicationException;
	
	public DadosPagamentoDTO escolheHorarioParticipacaoMensal(int horarioId, String dataInicio, int duracao, String email, String designacao) throws ApplicationException;
	
	public List<AtividadeDTO> agendaAtividadeOcasional(String especialidade) throws ApplicationException;
	
	public List<InstrutorDTO> enviaInformacaoAtividadeOcasional(String atividadeEscolhida, String[] sessoes) throws ApplicationException;
	
	public DadosPagamentoDTO indicaDadosAtividadeOcasional(String atividadeEscolhida, String[] sessoes, int instrutor, String emailUtente) throws ApplicationException;

	public List<AtividadeDTO> getAtividades() throws ApplicationException;
	
	public List<EspecialidadeDTO> getEspecialidades() throws ApplicationException;

}
 