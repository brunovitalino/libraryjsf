package br.com.bv.library.daos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.bv.library.models.Obra;

@Repository
@SuppressWarnings("serial")
public class ObraDao implements Serializable {

	@PersistenceContext
	EntityManager manager;

	private DAO<Obra> dao;

	@PostConstruct
	void init() {
		this.dao = new DAO<Obra>(this.manager, Obra.class);
	}

	public void adiciona(Obra t) {
		dao.adiciona(t);
	}

	public void remove(Obra t) {
		dao.remove(t);
	}

	public void atualiza(Obra t) {
		dao.atualiza(t);
	}

	public List<Obra> listaTodos() {
		return dao.listaTodos();
	}

	public Obra buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

}
