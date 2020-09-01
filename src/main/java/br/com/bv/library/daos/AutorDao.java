package br.com.bv.library.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bv.library.models.Autor;

public class AutorDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(Autor autor) {
		entityManager.getTransaction().begin();
		entityManager.persist(autor);
		entityManager.getTransaction().commit();
	}

}
