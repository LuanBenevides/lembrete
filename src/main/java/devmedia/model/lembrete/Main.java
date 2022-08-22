package devmedia.model.lembrete;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		int escolha_usuario;
			
		//user's initial vision
		System.out.println("|----------Aplicativo de lembretes----------|");
		System.out.println("|Escolha uma opcao...                       |");
		System.out.println("|1.Inserir um lembrete                      |");
		System.out.println("|2.Consultar um lembrete                    |");
		System.out.println("|3.Alterar um lembrete                      |");
		System.out.println("|4.Remover um lembrete                      |");
		System.out.println("|-------------------------------------------|");
			
		escolha_usuario = entrada.nextInt();
			
		switch (escolha_usuario) {
		case 1: 
			insertLembrete();		
			break;
		case 2:
			int queryUserOption;
			
			System.out.println("|----------Consultar de lembretes-----------|");
			System.out.println("|1.Consultar todos os lembretes             |");
			System.out.println("|2.Consultar um lembrete por ID             |");
			System.out.println("|3.Consultar um lembrete pelo TITULO        |");
			queryUserOption = entrada.nextInt();
				
			if(queryUserOption == 1) {
				queryAllLembretes();
			}else if(queryUserOption == 2) {
				queryLembreteById();
			}else if(queryUserOption == 3) {
				queryLembreteByName();
			}
			
			break;
		case 3:
			updateLembrete();
			break;
		case 4:
			removeLembrete();
			break;
		}

		entrada.close();
}
	//Method of entering reminders - Option 1
	public static void insertLembrete() {
		Scanner entrada = new Scanner(System.in);
		
		EntityManagerFactory entityManagerFactory;
		entityManagerFactory = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		Lembrete lembrete = new Lembrete();
		
		String title;
		String description;
		
		System.out.println("|----------Inserir lembrete------------------|");
		System.out.println("|Informe o titulo do lembrete:               |");
		title = entrada.nextLine();
		System.out.println("|--------------------------------------------|");
		System.out.println("|Informe a descricao do lembrete:            |");
		description = entrada.nextLine();
		System.out.println("|--------------------------------------------|");
		
		
		lembrete.setTitulo(title);
		lembrete.setDescricao(description);
		
		EntityManager em = entityManagerFactory.createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(lembrete);
			em.getTransaction().commit();
			
			System.out.println("Lembrete registrado!");
			System.out.println(lembrete.toString());
		}catch (Exception e) {
			em.getTransaction().rollback();
			
			System.out.println("INSERT: " + e.getMessage());
		}finally {
			em.close();
		}
		
		entrada.close();
	}

	//Method for querying all reminders - Option 2.1 
	public static void queryAllLembretes() {

		EntityManagerFactory entityManagerFactory;
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

	//Method to query a reminder by ID - Option 2.2
	public static void queryLembreteById() {
		Scanner entrada = new Scanner(System.in);
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		EntityManager em = emf.createEntityManager();
		
		Lembrete lembrete = null;
		long param;
		System.out.println("Informe o ID do lembrete: ");
		param = entrada.nextLong();
		
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
		entrada.close();
	}

	//Method to query a reminder by NAME - Option 2.3
	public static void queryLembreteByName() {
		Scanner entrada = new Scanner(System.in);
		
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		List<Lembrete> lembretes = null;
		
		EntityManager em = emf.createEntityManager();
		String param;
		
		System.out.println("Informe o titulo do lembrete:  ");
		
		param = entrada.nextLine();
		
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
		
		entrada.close();
	}

	//Method to update reminder - Option 3
	public static void updateLembrete() {
		
		Scanner entradaN = new Scanner(System.in);
		
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		EntityManager em = emf.createEntityManager();
		
		long paramId;
				
		System.out.println("Informe o ID do lembrete a ser alterado:  ");
		paramId = entradaN.nextLong();

		
		try {
			Lembrete lembrete = em.find(Lembrete.class, paramId);
			
			String novoTitulo;
			String novaDescricao;
			
			System.out.println("Informe o novo titulo: ");
			novoTitulo = entradaN.nextLine();
			
			lembrete.setTitulo(novoTitulo);
			
			System.out.println("Informe a nova descricao:  ");
			novaDescricao = entradaN.nextLine();
			
			lembrete.setDescricao(novaDescricao);
			
			em.getTransaction().begin();
			em.merge(lembrete);
			em.getTransaction().commit();
			
			System.out.println("Lembre atualizado...");
			System.out.println(lembrete.toString());
		} catch (Exception e) {
			em.getTransaction().rollback();
			
			System.out.println("UPDATE: " + e.getMessage());
		}finally {
			em.close();
		}
		
		entradaN.close();
	}

	//Method to remove reminder
	public static void removeLembrete() {
		Scanner entrada = new Scanner(System.in);
		
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("exercicios-jpa");
		
		EntityManager em = emf.createEntityManager();
		
		//Parametro para setar o registro a ser deletado, por ID;
		long paramId;
		
		System.out.println("Informe o ID do lembre a ser removido: ");
		paramId = entrada.nextLong();
		
		try {
			Lembrete lembrete = em.find(Lembrete.class, paramId);
			
			em.getTransaction().begin();
			em.remove(lembrete);
			em.getTransaction().commit();
			
			System.out.println("Lembrete removido!");
		} catch (Exception e) {
			em.getTransaction().rollback();
			
			System.out.println("REMOVE: " + e.getMessage());
		}finally {
			em.close();
		}
		entrada.close();
	}

}


