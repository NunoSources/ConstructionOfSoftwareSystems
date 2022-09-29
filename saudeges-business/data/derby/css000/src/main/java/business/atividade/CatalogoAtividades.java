package business.atividade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import business.especialidade.Especialidade;
import facade.exceptions.ApplicationException;

@Stateless
public class CatalogoAtividades {

	@PersistenceContext
	private EntityManager em;

	public boolean ehUnica(String designacao) throws ApplicationException  {
		try {
			TypedQuery<Atividade> query = em.createNamedQuery(Atividade.FIND_BY_DESIGNACAO, Atividade.class);
			query.setParameter(Atividade.ATIVIDADE_DESIGNACAO, designacao);
			query.getSingleResult();
			return false;
		} catch (NoResultException e) {
			return true;
		} catch (Exception e) {
			throw new ApplicationException ("Erro ao procurar a atividade pela designacao", e);
		}
	}
	
	public List<Atividade> getAtividadesRegulares() throws ApplicationException {
		try {
			TypedQuery<Atividade> query = em.createNamedQuery(Atividade.GET_ALL_REGULARES, Atividade.class);
			return query.getResultList();
		}catch(Exception e) {
			throw new ApplicationException("Erro ao procurar atividades regulares", e);
		}
	}
	
	public List<Atividade> getAtividadesOcasionaisByEspecialidade(String especialidade) throws ApplicationException {
		try {
			TypedQuery<Atividade> query = em.createNamedQuery(Atividade.GET_ALL_OCASIONAIS, Atividade.class);
			
			List<Atividade> ativ = query.getResultList();
			List<Atividade> result = new ArrayList<>();
			
			for(Atividade e: ativ) {
				if (e.getEspecialidade().getNome().equals(especialidade))
					result.add(e);
			}
			return result;
		}catch(Exception e) {
			throw new ApplicationException("Erro ao procurar a lista de especialidades", e);
		}
	}

	public Atividade getAtividadeByDesignacao(String designacao) throws ApplicationException {
		try {
			TypedQuery<Atividade> query = em.createNamedQuery(Atividade.FIND_BY_DESIGNACAO, Atividade.class);
			query.setParameter(Atividade.ATIVIDADE_DESIGNACAO, designacao);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new ApplicationException ("Erro ao procurar a atividade pela designacao", e);
		}
	}
	
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public Atividade criarAtividade(Especialidade nome, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco) {
		Atividade newActivity = new Atividade(nome, designacao, isRegular, nrSessoes, duracao, preco);
		em.persist(newActivity);
		return newActivity;
	}
	
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public Atividade criarAtividade(Especialidade nome, String designacao, boolean isRegular, int nrSessoes, int duracao, float preco, int maxParticipantes) {
		Atividade newActivity = new Atividade(nome, designacao, isRegular, nrSessoes, duracao, preco, maxParticipantes);
		em.persist(newActivity);
		return newActivity;
	}

}
