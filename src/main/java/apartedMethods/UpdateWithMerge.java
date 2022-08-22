package apartedMethods;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import devmedia.model.lembrete.Lembrete;

public class UpdateWithMerge {

	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		EntityManager em = emf.createEntityManager();
		
		//Parametro para setar o registro a ser alterado, por ID;
		long paramId = 3;
		
		try {
			Lembrete lembrete = em.find(Lembrete.class, paramId);
			
			lembrete.setTitulo("Comprar cafe");
			lembrete.setDescricao("Hoje, as 16h40");
			
			em.getTransaction().begin();
			em.merge(lembrete);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			
			System.out.println("UPDATE: " + e.getMessage());
		}finally {
			em.close();
		}
	}
}
