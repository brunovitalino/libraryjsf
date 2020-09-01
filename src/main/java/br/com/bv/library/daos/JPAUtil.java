package br.com.bv.library.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence
//			.createEntityManagerFactory("db_dev");
			.createEntityManagerFactory("db_prod");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void close(EntityManager em) {
		em.close();
	}
}
