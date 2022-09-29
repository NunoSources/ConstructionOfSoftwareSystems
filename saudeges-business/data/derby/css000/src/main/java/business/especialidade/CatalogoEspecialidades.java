package business.especialidade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import facade.exceptions.ApplicationException;

@Stateless
public class CatalogoEspecialidades {

	@PersistenceContext
	private EntityManager em;

	public List<Especialidade> getEspecialidades() throws ApplicationException {
		try {
			TypedQuery<Especialidade> query = em.createNamedQuery(Especialidade.FIND_ALL, Especialidade.class);
			return query.getResultList();
		}catch(Exception e) {
			throw new ApplicationException("Erro ao procurar a lista de especialidades", e);
		}
	}

	public Especialidade findByName(Especialidade nome) {
		return em.find(Especialidade.class, nome.getId());
	}
	
	
	public Especialidade getEspecialidadeByNome(String nome) throws ApplicationException {
		try {
			TypedQuery<Especialidade> query = em.createNamedQuery(Especialidade.FIND_BY_NOME, Especialidade.class);
			query.setParameter(Especialidade.NOME_ESPECIALIDADE, nome);
			return query.getSingleResult();
		} catch (Exception e) {
			throw new ApplicationException ("Erro ao procurar especialidade pelo nome", e);
		}
	}

}