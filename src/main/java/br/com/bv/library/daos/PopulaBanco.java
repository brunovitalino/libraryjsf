package br.com.bv.library.daos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.bv.library.models.Autor;
import br.com.bv.library.models.Obra;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		
		Autor assis = geraAutor("Machado de Assis");
		em.persist(assis);

		Autor amado = geraAutor("Jorge Amado");
		em.persist(amado);

		Autor coelho = geraAutor("Paulo Coelho");
		em.persist(coelho);

		Obra casmurro = geraObra("Dom Casmurro", "", // img
				parseDate("10/01/1899"), null, assis);
		Obra memorias = geraObra("Memorias Postumas de Bras Cubas", "", // img
				parseDate("01/01/1881"), null, assis);
		Obra quincas = geraObra("Quincas Borba", "", // img
				parseDate("01/01/1891"), null, assis);

		em.persist(casmurro);
		em.persist(memorias);
		em.persist(quincas);

		Obra alquemista = geraObra("O Alquimista", "", // img
				parseDate("01/01/1988"), null, coelho);
		Obra brida = geraObra("Brida", "", // img
				parseDate("01/01/1990"), null, coelho);
		Obra valkirias = geraObra("As Valkirias", "", // img
				parseDate("01/01/1992"), null, coelho);
		Obra maao = geraObra("O Diario de um Mago", "", // img
				parseDate("01/01/1987"), null, coelho);

		em.persist(alquemista);
		em.persist(brida);
		em.persist(valkirias);
		em.persist(maao);

		Obra capitaes = geraObra("Capitaes da Areia", "", // img
				parseDate("01/01/1937"), null, amado);
		Obra flor = geraObra("Dona Flor e Seus Dois Maridos", "", // img
				parseDate("01/01/1966"), null, amado);

		em.persist(capitaes);
		em.persist(flor);

		em.getTransaction().commit();
		em.close();
		
		System.exit(0);
	}

	private static Autor geraAutor(String nome) {
		Autor autor = new Autor();
		autor.setNome(nome);
		return autor;
	}

	private static Obra geraObra(String nome, String descricao, //Imagem imagem,
			Date dataPublicacao, Date dataExposicao, Autor autor) {
		Obra obra = new Obra();
		obra.setNome(nome);
		obra.setDescricao(descricao);
//		obra.setImagem(imagem);
		obra.setDataPublicacao(dataPublicacao);
		obra.setDataPublicacao(dataExposicao);
		obra.adicionaAutor(autor);
		return obra;
	}

	@SuppressWarnings("unused")
	private static Calendar parseCalendar(String data) {
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
	private static Date parseDate(String data) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
