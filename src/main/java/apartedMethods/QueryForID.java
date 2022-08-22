package apartedMethods;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import devmedia.model.lembrete.Lembrete;

public class QueryForID {

	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		EntityManager em = emf.createEntityManager();
		
		Lembrete lembrete = null;
		long param = 3L;
		
		try {
			lembrete = em.find(Lembrete.class, param);
			
			if(lembrete == null) {
				System.out.println("Lembrete não identificado na base!");
			}else {
				System.out.println(lembrete);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			em.close();
		}
	}
}
