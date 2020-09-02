package br.com.bv.library.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.bv.library.models.enums.SexoEnum;

//Nome - obrigatório
//Sexo
//E-mail - não obrigatório, deve ser validado caso preenchido (não pode haver dois cadastros com mesmo e-mail)
//Data de nascimento - obrigatório, deve ser validada
//País de origem - obrigatório (deve ser um país existente)
//CPF - somente deve ser informado caso país de origem seja o Brasil, desta forma torna-se obrigatório. Deve ser validado (formatado e não pode haver dois cadastros com mesmo CPF)
@Entity
public class Autor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	
	private String email;
	
	private Calendar dataNascimento;
	
//	private Pais pais;
	
	private String cpf;

//	@ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(
//            name = "Obra_Autor", 
//            joinColumns = { @JoinColumn(name = "autor_id") }, 
//            inverseJoinColumns = { @JoinColumn(name = "obra_id") }
//        )
//	private List<Obra> obras = new ArrayList<>();

	public Autor() {
	}

	public Autor(Long id, SexoEnum sexo, String nome, String email, Calendar dataNascimento, //Pais pais,
			String cpf) {
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.email = email;
		this.dataNascimento = dataNascimento;
//		this.pais = pais;
		this.cpf = cpf;
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

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

//	public Pais getPais() {
//		return pais;
//	}
//
//	public void setPais(Pais pais) {
//		this.pais = pais;
//	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

//	public List<Obra> getAutores() {
//		return obras;
//	}
//
//	public void setAutores(List<Obra> obras) {
//		this.obras = obras;
//	}
//
//	public void adicionaAutor(Obra obra) {
//		this.obras.add(obra);
//	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", email=" + email + ", dataNascimento="
				+ dataNascimento + ", cpf=" + cpf + "]";
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
