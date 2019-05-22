package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.ProfessorDao;
import modelo.dominio.Professor;

/**
 * Servlet implementation class ServletProdutoListar
 */
@WebServlet("/listarProfessores")
public class ServletProfessorListar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfessorListar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// criar instância do DAO para persistência
		ProfessorDao dao = new ProfessorDao();
		
		// carregar do banco a lista de professores...
		List<Professor> lista = dao.listar();
		
		// enviar a lista de professores para a página...
		request.setAttribute("lista", lista);
		
		// enviar o processamento para a página
		RequestDispatcher desp = request.getRequestDispatcher("professorListar.jsp");
		desp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
		
	}

}