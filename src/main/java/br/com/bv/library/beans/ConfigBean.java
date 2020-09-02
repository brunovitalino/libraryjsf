package br.com.bv.library.beans;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import br.com.bv.library.daos.ConfigDao;

@Controller
public class ConfigBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ConfigDao configDao;

	@Transactional
	public String popularBanco() {
		System.out.println("Populando banco.");

		this.configDao.popularBanco();

		return "login?faces-redirect=true";
	}
}
