package br.com.bv.library.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.bv.library.daos.ConfigDao;
import br.com.bv.library.daos.ObraDao;
import br.com.bv.library.models.Obra;

@Controller
public class ConfigBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ConfigDao configDao;

	@Inject
	ObraDao obraDao;

	@Transactional
	public String popularBanco() {
		System.out.println("Populando banco.");

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		List<Obra> obras = this.obraDao.listaTodos();
		
		if (obras.size() > 0) {
			context.addMessage(null, new FacesMessage("Banco já estava populado!"));
		} else {
			this.configDao.popularBanco();
			context.addMessage(null, new FacesMessage("Banco foi populado."));
		}

		return "login?faces-redirect=true";
	}
}
