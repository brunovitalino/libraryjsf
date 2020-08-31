package br.com.bv.library.models;

import java.util.Date;

//	Nome - obrigatório
//	Descrição - obrigatório (deve conter no máximo 240 caracteres)
//	Imagem - obrigatório
//	Data de publicação - obrigatória caso a data de exposição não seja informada (é utilizada mais para livros e demais publicações escritas)
//	Data de exposição - obrigatória caso a data de publicação não seja informada (é utilizada mais para obras que são expostas, como pinturas, esculturas e demais)
public class Obra {
	private Long id;
	private String nome;
	private String descricao;
	private Arquivo imagem;
	private Date dataPublicacao;
	private Date dataExposicao;

	public Obra() {
	}

	public Obra(Long id, String nome, String descricao, Arquivo imagem, Date dataPublicacao, Date dataExposicao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.imagem = imagem;
		this.dataPublicacao = dataPublicacao;
		this.dataExposicao = dataExposicao;
	}

	public Obra(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Arquivo getImagem() {
		return imagem;
	}

	public void setImagem(Arquivo imagem) {
		this.imagem = imagem;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Date getDataExposicao() {
		return dataExposicao;
	}

	public void setDataExposicao(Date dataExposicao) {
		this.dataExposicao = dataExposicao;
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

}
