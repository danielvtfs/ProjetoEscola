package modelo.dao;

import java.util.List;
import javax.persistence.EntityManager;
import modelo.dominio.Disciplina;

public class DisciplinaDao {

	private EntityManager manager;

	public DisciplinaDao() {
		super();
		
		this.manager = JPAUtil.getEntityManager();
	}

	public Disciplina salvar(Disciplina cat) {
		this.manager.getTransaction().begin();
		Disciplina novo = this.manager.merge(cat);
		this.manager.getTransaction().commit();
		return novo;
	}

	public void excluir(Disciplina cat) {
		this.manager.getTransaction().begin();
		this.manager.remove(cat);
		this.manager.getTransaction().commit();
	}

	public Disciplina obter(Integer codigo) {
		return this.manager.find(Disciplina.class, codigo);
	}

	public List<Disciplina> listar() {
		
		String jpql = "from Categoria p  order by p.nome";

		List<Disciplina> lista = this.manager.createQuery(jpql, Disciplina.class).getResultList();

		return lista;
	}

}
	
