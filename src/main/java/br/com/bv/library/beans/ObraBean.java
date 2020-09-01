package br.com.bv.library.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.bv.library.daos.DAO;
import br.com.bv.library.models.Autor;
import br.com.bv.library.models.Obra;

@ManagedBean
@ViewScoped
public class ObraBean {
	
	private Obra obra = new Obra();
	private Long autorId;

	public Obra getObra() {
		return obra;
	}

	public Long getAutorId() {
		return autorId;
	}
	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}


	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void adicionarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		System.out.println("Usando autor " + autor.getNome());
		this.obra.adicionaAutor(autor);
	}

	public List<Autor> getAutoresDaObra() {
		return this.obra.getAutores();
	}

	public void gravar() {
		System.out.println("Gravando obra " + obra.getTitulo());
		if (obra.getAutores().isEmpty()) {
			throw new RuntimeException("Obra deve ter pelo menos um Autor.");
		}
		new DAO<Obra>(Obra.class).adiciona(this.obra);
	}

	public List<Obra> getObras() {
		return new DAO<Obra>(Obra.class).listaTodos();
	}

}
