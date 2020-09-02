package br.com.bv.library.daos;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.bv.library.models.Autor;
import br.com.bv.library.models.Obra;

@Repository
@SuppressWarnings("serial")
public class ConfigDao implements Serializable {

	@PersistenceContext
	EntityManager em;

	public ConfigDao() {
	}

	public void popularBanco() {

//		em.getTransaction().begin();
		
		Autor assis = geraAutor("Machado de Assis");
		em.persist(assis);

		Autor amado = geraAutor("Jorge Amado");
		em.persist(amado);

		Autor coelho = geraAutor("Paulo Coelho");
		em.persist(coelho);

		Obra casmurro = geraObra("Dom Casmurro", "", // img
				parseCalendar("10/01/1899"), null, assis);
		Obra memorias = geraObra("Memorias Postumas de Bras Cubas", "", // img
				parseCalendar("01/01/1881"), null, assis);
		Obra quincas = geraObra("Quincas Borba", "", // img
				parseCalendar("01/01/1891"), null, assis);

		em.persist(casmurro);
		em.persist(memorias);
		em.persist(quincas);

		Obra alquemista = geraObra("O Alquimista", "", // img
				parseCalendar("01/01/1988"), null, coelho);
		Obra brida = geraObra("Brida", "", // img
				parseCalendar("01/01/1990"), null, coelho);
		Obra valkirias = geraObra("As Valkirias", "", // img
				parseCalendar("01/01/1992"), null, coelho);
		Obra maao = geraObra("O Diario de um Mago", "", // img
				parseCalendar("01/01/1987"), null, coelho);

		em.persist(alquemista);
		em.persist(brida);
		em.persist(valkirias);
		em.persist(maao);

		Obra capitaes = geraObra("Capitaes da Areia", "", // img
				parseCalendar("01/01/1937"), null, amado);
		Obra flor = geraObra("Dona Flor e Seus Dois Maridos", "", // img
				parseCalendar("01/01/1966"), null, amado);

		em.persist(capitaes);
		em.persist(flor);

//		em.getTransaction().commit();
//		em.close();
	}

	private Autor geraAutor(String nome) {
		Autor autor = new Autor();
		autor.setNome(nome);
		return autor;
	}

	private Obra geraObra(String titulo, String descricao, //Imagem imagem,
			Calendar dataPublicacao, Calendar dataExposicao, Autor autor) {
		Obra obra = new Obra();
		obra.setTitulo(titulo);
		obra.setDescricao(descricao);
//		obra.setImagem(imagem);
		obra.setDataPublicacao(dataPublicacao);
		obra.setDataExposicao(dataExposicao);
		obra.adicionaAutor(autor);
		return obra;
	}

	@SuppressWarnings("unused")
	private Calendar parseCalendar(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@SuppressWarnings("unused")
	private Date parseDate(String data) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
