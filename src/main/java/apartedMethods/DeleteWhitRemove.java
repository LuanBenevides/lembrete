package apartedMethods;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import devmedia.model.lembrete.Lembrete;

public class DeleteWhitRemove {
private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		EntityManager em = emf.createEntityManager();
		
		//Parametro para setar o registro a ser deletado, por ID;
		long paramId = 3;
		
		try {
			Lembrete lembrete = em.find(Lembrete.class, paramId);
			
			em.getTransaction().begin();
			em.remove(lembrete);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			
			System.out.println("REMOVE: " + e.getMessage());
		}finally {
			em.close();
		}
	}
}
