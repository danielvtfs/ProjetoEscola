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
import modelo.dao.ProfessorDao;
import modelo.dominio.Disciplina;
import modelo.dominio.Professor;

/**
 * Servlet implementation class ServletAbrirAlteracao
 */
@WebServlet("/editar")
public class ServletAbrirAlteracao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAbrirAlteracao() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String codigoStr = request.getParameter("codigo");
		Integer codigo = Integer.parseInt(codigoStr);
		
		// criar instância do DAO para persistência
		ProfessorDao dao = new ProfessorDao();

		// carregar o professor escolhido do banco
		Professor prof = dao.obter(codigo);
		
		// guardar o professor no request para ser lido pela página
		request.setAttribute("prof", prof);
		
		// ler a lista de disciplinas
		DisciplinaDao daoDisc = new DisciplinaDao();
		List<Disciplina> listaDisc = daoDisc.listar();
		// guardar a lista de disciplinas no request
		request.setAttribute("listaDisc", listaDisc);
		
		// criar um objeto para despachar a requisição
		RequestDispatcher desp = request.getRequestDispatcher("professorEditar.jsp");
		// encaminhar para o servlet ou página selecionada
		desp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
