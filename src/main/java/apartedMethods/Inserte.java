package apartedMethods;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import devmedia.model.lembrete.Lembrete;

public class Inserte {
	private static EntityManagerFactory entityManagerFactory;
	public static void main(String[] args) {
		
		entityManagerFactory = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		Lembrete lembrete = new Lembrete();
		lembrete.setTitulo("Estudar");
		lembrete.setDescricao("Sexta-feira, de 19h00 as 00h00");
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(lembrete);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			
			System.out.println("INSERT: " + e.getMessage());
		}finally {
			em.close();
		}
	}
}
