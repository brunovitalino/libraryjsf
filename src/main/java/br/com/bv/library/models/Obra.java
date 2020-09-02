package br.com.bv.library.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//Data de publicação - obrigatória caso a data de exposição não seja informada (é utilizada mais para livros e demais publicações escritas)
//Data de exposição - obrigatória caso a data de publicação não seja informada (é utilizada mais para obras que são expostas, como pinturas, esculturas e demais)
@Entity
public class Obra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@NotBlank
	private String titulo;
	
	@Size(max = 240)
	private String descricao;
	
//	@NotNull
//	private Arquivo imagem;

	@Temporal(TemporalType.DATE)
	private Calendar dataPublicacao = Calendar.getInstance();
	
	@Temporal(TemporalType.DATE)
	private Calendar dataExposicao = Calendar.getInstance();

	@ManyToMany//(cascade = { CascadeType.ALL })
//	@JoinTable(
//		name = "Obra_Autor", 
//		joinColumns = { @JoinColumn(name = "obra_id") }, 
//		inverseJoinColumns = { @JoinColumn(name = "autor_id") }
//	)
	private List<Autor> autores = new ArrayList<Autor>();

	public Obra() {
	}

	public Obra(Long id) {
		this.id = id;
	}

	public Obra(Long id, String titulo, String descricao, //Arquivo imagem,
			Calendar dataPublicacao, Calendar dataExposicao) {
		this(id);
		this.titulo = titulo;
		this.setDescricao(descricao);
//		this.imagem = imagem;
		this.dataPublicacao = dataPublicacao;
		this.dataExposicao = dataExposicao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

//	public Arquivo getImagem() {
//		return imagem;
//	}
//
//	public void setImagem(Arquivo imagem) {
//		this.imagem = imagem;
//	}

	public Calendar getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Calendar dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Calendar getDataExposicao() {
		return dataExposicao;
	}

	public void setDataExposicao(Calendar dataExposicao) {
		this.dataExposicao = dataExposicao;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	@Override
	public String toString() {
		return "Obra [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", dataPublicacao="
				+ dataPublicacao + ", dataExposicao=" + dataExposicao + ", autores=" + autores + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Obra other = (Obra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	// Extra methods

	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}

	public void removeAutor(Autor autor) {
		this.autores.remove(autor);
	}

}