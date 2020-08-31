package br.com.bv.library.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.bv.library.models.Obra;

@RequestScoped
@Named
public class ObraBean {
	
	private Obra obra = new Obra();

	public Obra getLivro() {
		return obra;
	}

	public void setLivro(Obra livro) {
		this.obra = livro;
	}

	public void gravar() {
		System.out.println("foi gravado! ");
	}

}
