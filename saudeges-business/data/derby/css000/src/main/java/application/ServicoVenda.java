package application;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;

import business.atividade.Atividade;
import business.especialidade.Especialidade;
import business.handlers.AgendarAtividadeOcasionalHandler;
import business.handlers.ComprarParticipacaoMensalHandler;
import business.instrutor.Instrutor;
import facade.dtos.AtividadeDTO;
import facade.dtos.DadosPagamentoDTO;
import facade.dtos.EspecialidadeDTO;
import facade.dtos.HorarioPrecoDTO;
import facade.dtos.InstrutorDTO;
import facade.exceptions.ApplicationException;
import facade.services.IServicoVendaRemote;

@Stateless
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class ServicoVenda implements IServicoVendaRemote {


	@EJB
	private ComprarParticipacaoMensalHandler participacaoMensalHandler;
	
	@EJB
	private AgendarAtividadeOcasionalHandler atividadeOcasionalHandler;
	
	@Override
	public List<AtividadeDTO> agendaAtividadeOcasional(String especialidade) throws ApplicationException {
		List<AtividadeDTO> result = new LinkedList<>(); 
		for (Atividade atividade : atividadeOcasionalHandler.agendaAtividadeOcasional(especialidade))
			result.add(new AtividadeDTO(atividade.getEspecialidade().getNome(), atividade.getDesignacao(), atividade.isRegular(), atividade.getNrSessoes(), atividade.getDuracao(), atividade.getPreco(), atividade.getMaxParticipantes()));
		return result;
	}

	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public HorarioPrecoDTO indicaAtividadeParticipacaoMensal(String designacao) throws ApplicationException {
		return participacaoMensalHandler.indicaAtividadeParticipacaoMensal(designacao);
	}

	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public DadosPagamentoDTO escolheHorarioParticipacaoMensal(int horarioId, String dataInicio, int duracao, String email, String designacao)
			throws ApplicationException {
		return participacaoMensalHandler.escolheHorarioParticipacaoMensal(horarioId, dataInicio, duracao, email, designacao);
	}

	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public List<InstrutorDTO> enviaInformacaoAtividadeOcasional(String atividadeEscolhida, String[] horario)
			throws ApplicationException {
		List<InstrutorDTO> result = new LinkedList<>(); 
		for (Instrutor instrutor : atividadeOcasionalHandler.enviaInformacaoAtividadeOcasional(atividadeEscolhida, horario))
			result.add(new InstrutorDTO(instrutor.getNumero(), instrutor.getNome(), instrutor.getCertificacoes()));
		return result;
	}

	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public DadosPagamentoDTO indicaDadosAtividadeOcasional(String atividadeEscolhida, String[] sessoes, int instrutor, String emailUtente) throws ApplicationException {
		// TODO Auto-generated method stub
		return atividadeOcasionalHandler.indicaDadosAtividadeOcasional(atividadeEscolhida,sessoes, instrutor, emailUtente);

	}
	
	
	@Override
	public List<AtividadeDTO> getAtividades() throws ApplicationException {
		List<AtividadeDTO> result = new LinkedList<>(); 
		for (Atividade atividade : participacaoMensalHandler.getAtividades())
			result.add(new AtividadeDTO(atividade.getEspecialidade().getNome(), atividade.getDesignacao(), atividade.isRegular(), atividade.getNrSessoes(), atividade.getDuracao(), atividade.getPreco(), atividade.getMaxParticipantes()));
		return result;
	}
	
	@Override
	public List<EspecialidadeDTO> getEspecialidades() throws ApplicationException {
		List<EspecialidadeDTO> result = new LinkedList<>(); 
		for (Especialidade especialidade : participacaoMensalHandler.getEspecialidades())
			result.add(new EspecialidadeDTO(especialidade.getId(), especialidade.getNome(), especialidade.getDuracao(), especialidade.getCertificacaoNecessaria()));
		return result;
	}
}
