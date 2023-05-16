package application;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.DAO.EventoDAO;
import application.entities.Evento;
import application.entities.TipoEvento;
import application.utils.JpaUtil;

public class Application {
	public static Logger logger = LoggerFactory.getLogger(Application.class);
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		EventoDAO ed = new EventoDAO(em);
		Evento e1 = new Evento("Festival delle Serre", LocalDate.of(2023, 9, 11),
				"Il Festival più bello delle serre cosentine", TipoEvento.PUBBLICO, 5000);

		UUID id1 = UUID.fromString("49ef4227-5281-48da-84c2-dacb0b7d220d");
		Evento found = ed.getById(id1);
		logger.info("" + found);
		ed.save(e1);
		ed.refresh(id1, "Prova");
		ed.delete(id1);
		em.close();
		emf.close();

	}

}
