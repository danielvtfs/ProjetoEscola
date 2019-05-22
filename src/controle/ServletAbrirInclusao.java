package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.DisciplinaDao;
import modelo.dominio.Disciplina;
import modelo.dominio.Professor;

/**
 * Servlet implementation class ServletAbrirInclusao
 */
@WebServlet("/abrirInclusao")
public class ServletAbrirInclusao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAbrirInclusao() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// criar um novo objeto vazio
		Professor prof = new Professor();
		
		// guardar o novo produto no request para ser lido pela página
		request.setAttribute("prof", prof);
		
		// ler a lista de Disciplinas
		DisciplinaDao daoDisc = new DisciplinaDao();
		List<Disciplina> listaDisc = daoDisc.listar();
		// guardar a lista de Disciplinas no request
		request.setAttribute("listaDisc", listaDisc);
		
		// enviar o processamento para a pagina
		RequestDispatcher desp = request.getRequestDispatcher("professorEditar.jsp");
		desp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
