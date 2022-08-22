package apartedMethods;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import devmedia.model.lembrete.Lembrete;

public class Query {

	private static EntityManagerFactory entityManagerFactory;
	

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		List<Lembrete> lembretes = null;
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			lembretes= em.createQuery("from Lembrete", Lembrete.class).getResultList();
		}catch (Exception e) {
			System.out.println("LIST ALL: " + e.getMessage());
		}finally {
			em.close();
		}
		
		for(Lembrete l: lembretes) {
			System.out.println(l);
		}
	}
}
