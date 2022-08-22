package apartedMethods;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import devmedia.model.lembrete.Lembrete;

public class QueryForName {
	
	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		List<Lembrete> lembretes = null;
		
		EntityManager em = emf.createEntityManager();
		String param = "Estudar";
		try {
			lembretes= em.createQuery("from Lembrete l where l.titulo LIKE '%" + param + "%'", Lembrete.class).getResultList();
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
