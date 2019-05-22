package modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import modelo.dominio.Professor;

public class ProfessorDao {

	private EntityManager manager;

	public ProfessorDao() {
		super();
		this.manager = JPAUtil.getEntityManager();
	}

	public Professor salvar(Professor prof) {
		this.manager.getTransaction().begin();
		Professor novo = this.manager.merge(prof);
		this.manager.getTransaction().commit();
		return novo;
	}

	public void excluir(Professor prof) {
		this.manager.getTransaction().begin();
		this.manager.remove(prof);
		this.manager.getTransaction().commit();
	}

	public Professor obter(Integer codigo) {
		return this.manager.find(Professor.class, codigo);
	}

	public List<Professor> listar() {
		
		String jpql = "from Produto p  order by p.descricao";

		List<Professor> lista = this.manager.createQuery(jpql, Professor.class).getResultList();

		return lista;
	}

}
