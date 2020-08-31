package br.com.bv.library.models;

import java.util.Date;

import br.com.bv.library.models.enums.SexoEnum;

//Nome - obrigatório
//Sexo
//E-mail - não obrigatório, deve ser validado caso preenchido (não pode haver dois cadastros com mesmo e-mail)
//Data de nascimento - obrigatório, deve ser validada
//País de origem - obrigatório (deve ser um país existente)
//CPF - somente deve ser informado caso país de origem seja o Brasil, desta forma torna-se obrigatório. Deve ser validado (formatado e não pode haver dois cadastros com mesmo CPF)
public class Autor {

	private Long id;
	private SexoEnum sexo;
	private String email;
	private Date dataNascimento;
	private Pais pais;
	private String cpf;

	public Autor() {
	}

	public Autor(Long id, SexoEnum sexo, String email, Date dataNascimento, Pais pais, String cpf) {
		this.id = id;
		this.sexo = sexo;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.pais = pais;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", sexo=" + sexo + ", email=" + email + ", dataNascimento=" + dataNascimento
				+ ", pais=" + pais + ", cpf=" + cpf + "]";
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
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
