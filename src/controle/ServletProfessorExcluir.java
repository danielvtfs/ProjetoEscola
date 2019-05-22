package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.ProfessorDao;
import modelo.dominio.Professor;

/**
 * Servlet implementation class ServletProdutoExcluir
 */
@WebServlet("/excluir")
public class ServletProfessorExcluir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletProfessorExcluir() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codigoStr = request.getParameter("codigo");
		Integer codigo = Integer.parseInt(codigoStr);

		// criar instância do DAO para persistência
		ProfessorDao dao = new ProfessorDao();

		// carregar o produto escolhido do banco
		Professor prof = dao.obter(codigo);

		// excluir o professor do banco de dados
		dao.excluir(prof);

		// fazer redirect para listar os professores, a fim de evitar
		// vários envios repetidos
		response.sendRedirect("listarProfessores");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
