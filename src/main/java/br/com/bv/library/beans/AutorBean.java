package br.com.bv.library.beans;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.bv.library.daos.AutorDao;
import br.com.bv.library.models.Autor;

@SuppressWarnings("serial")
@Controller
public class AutorBean implements Serializable {

	private Autor autor = new Autor();

	@Inject
	private AutorDao dao;

	private Long autorId;

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPelaId() {
		this.autor = this.dao.buscaPorId(autorId);
	}

	@Transactional
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}

		this.autor = new Autor();

		return "obra?faces-redirect=true";
	}

	@Transactional
	public void remover(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome());
		this.dao.remove(autor);
	}

	public List<Autor> getAutores() {
		return this.dao.listaTodos();
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
