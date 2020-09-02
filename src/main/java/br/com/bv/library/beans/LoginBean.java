package br.com.bv.library.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.bv.library.daos.ConfigDao;
import br.com.bv.library.daos.UsuarioDao;
import br.com.bv.library.models.Usuario;

@Controller
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	@Inject
	UsuarioDao dao;

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {
		System.out.println("fazendo login do usuario "
				+ this.usuario.getEmail());

		FacesContext context = FacesContext.getCurrentInstance();

		boolean existe = dao.existe(this.usuario);
		if (existe) {
			context.getExternalContext().getSessionMap()
					.put("usuarioLogado", this.usuario);
			return "obra?faces-redirect=true";
		}

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado"));

		return "login?faces-redirect=true";
	}

	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

	@Transactional
	public String gerarUsuario() {
		this.usuario.setEmail("teste@teste.com");
		this.usuario.setSenha("123");
		System.out.println("Gravando usuario " + this.usuario.getEmail());

		if (this.usuario.getId() == null) {
			this.dao.adiciona(this.usuario);
		} else {
			this.dao.atualiza(this.usuario);
		}

		this.usuario = new Usuario();

		return "login?faces-redirect=true";
	}

}
