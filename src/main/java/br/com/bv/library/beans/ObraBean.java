package br.com.bv.library.beans;

import javax.faces.bean.ManagedBean;

import br.com.bv.library.models.Obra;

@ManagedBean
public class ObraBean {
	
	private Obra obra = new Obra();

	public Obra getObra() {
		return obra;
	}

	public void gravar() {
		System.out.println("foi gravado! " + obra.toString());
	}

}
