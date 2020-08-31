package br.com.bv.library.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bv.library.models.Obra;

public class ObraDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(Obra obra) {
		entityManager.getTransaction().begin();
		entityManager.persist(obra);
		entityManager.getTransaction().commit();
	}

}
