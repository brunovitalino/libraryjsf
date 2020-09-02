package br.com.bv.library.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.bv.library.daos.AutorDao;
import br.com.bv.library.daos.ObraDao;
import br.com.bv.library.models.Autor;
import br.com.bv.library.models.Obra;

@Controller
public class ObraBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Obra obra = new Obra();

	private Long autorId;

	private List<Obra> obras;

	@Inject
	ObraDao obraDao;

	@Inject
	AutorDao autorDao;

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public Long getAutorId() {
		return autorId;
	}

	public Obra getObra() {
		return obra;
	}

	public List<Obra> getObras() {
		if (this.obras == null) {
			this.obras = obraDao.listaTodos();
		}
		return obras;
	}

	public List<Autor> getAutores() {
		return autorDao.listaTodos();
	}

	public List<Autor> getAutoresDoObra() {
		return this.obra.getAutores();
	}

	public void carregarObraPelaId() {
		this.obra = obraDao.buscaPorId(this.obra.getId());
	}

	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(this.autorId);
		this.obra.adicionaAutor(autor);
		System.out.println("Escrito por: " + autor.getNome());
	}

	// begin
	@Transactional
	public void gravar() {
		System.out.println("Gravando obra " + this.obra.getTitulo());

		if (obra.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Obra deve ter pelo menos um Autor."));
			return;
		}

		if (this.obra.getId() == null) {
			obraDao.adiciona(this.obra);
			this.obras = obraDao.listaTodos();
		} else {
			obraDao.atualiza(this.obra);
		}

		this.obra = new Obra();
	}

	// commit

	@Transactional
	public void remover(Obra obra) {
		System.out.println("Removendo obra");
		obraDao.remove(obra);
		this.obras = obraDao.listaTodos();
	}

	public void removerAutorDoObra(Autor autor) {
		this.obra.removeAutor(autor);
	}

	public void carregar(Obra obra) {
		System.out.println("Carregando obra");
		this.obra = this.obraDao.buscaPorId(obra.getId());
	}

	public String formAutor() {
		System.out.println("Chamanda do formulário do Autor.");
		return "autor?faces-redirect=true";
	}

}
