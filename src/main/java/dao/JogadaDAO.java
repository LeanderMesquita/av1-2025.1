package dao;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Jogada;
import jpaUtil.JPAUtil;

public class JogadaDAO {
	
	public static List<Jogada> listAll(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select jogada from Jogada jogada");
		List<Jogada> jogadas = q.getResultList();
		em.close();
		return jogadas;
	}
	
	public static void save(Jogada jogada) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		jogada.setData(new Date());
		em.persist(jogada);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void update(Jogada jogada) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		
		Jogada jogadaToUpdate = em.find(Jogada.class, jogada.getId());
		jogadaToUpdate.setId(jogadaToUpdate.getId());
		
		if(jogadaToUpdate.getResultado().contains(jogadaToUpdate.getJogador1())) {
			jogadaToUpdate.setResultado(jogada.getJogador1()+" venceu!");
			
		} else if (jogadaToUpdate.getResultado().contains(jogadaToUpdate.getJogador2())) {
			jogadaToUpdate.setResultado(jogada.getJogador2()+" venceu!");
		}
		
		
		
		jogadaToUpdate.setJogador1(jogada.getJogador1());
		jogadaToUpdate.setJogador2(jogada.getJogador2());
		
		em.merge(jogadaToUpdate);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public static void delete(Jogada jogada) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		
		Jogada jogadaToDelete = em.find(Jogada.class, jogada.getId());
		em.remove(jogadaToDelete);
		em.getTransaction().commit();
		em.close();
		
	}
}
