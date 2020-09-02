package br.com.bv.library.daos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.bv.library.models.Usuario;

@Repository
@SuppressWarnings("serial")
public class UsuarioDao implements Serializable {

	@PersistenceContext
	EntityManager manager;
	
	private DAO<Usuario> dao;

	// --- Custom - inicio
	@PostConstruct
	void init() {
		this.dao = new DAO<Usuario>(this.manager, Usuario.class);
	}

	public void adiciona(Usuario t) {
		dao.adiciona(t);
	}

	public void atualiza(Usuario t) {
		dao.atualiza(t);
	}
	// --- Custom - fim

	public boolean existe(Usuario usuario) {

		TypedQuery<Usuario> query = manager.createQuery(
				" select u from Usuario u "
						+ " where u.email = :pEmail and u.senha = :pSenha",
				Usuario.class);

		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			@SuppressWarnings("unused")
			Usuario resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}

		return true;
	}

	public boolean existeEmail(Usuario usuario) {

		TypedQuery<Usuario> query = manager.createQuery(
				" select u from Usuario u "
						+ " where u.email = :pEmail",
				Usuario.class);

		query.setParameter("pEmail", usuario.getEmail());
		try {
			@SuppressWarnings("unused")
			Usuario resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}

		return true;
	}

}
