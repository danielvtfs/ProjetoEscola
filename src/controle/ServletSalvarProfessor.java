package controle;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class ServletSalvarProfessor
 */
@WebServlet("/salvarProfessor")
public class ServletSalvarProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSalvarProfessor() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendError(403, "Acesso proibido para método GET. Use o formulário para enviar.");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> erros = new ArrayList<>();
		
		// ler os campos do formulário
		String disciplinaStr = request.getParameter("disciplina");
		String codigoStr = request.getParameter("codigo");
		String nome = request.getParameter("nome");

		
		// converter os tipos numéricos

		Integer codigo;
		try {
			codigo = Integer.parseInt(codigoStr);
		} catch (NumberFormatException e) {
			codigo = null;
		}
		
		Integer idDisciplina;
		Disciplina disc;
		try {
			idDisciplina = Integer.parseInt(disciplinaStr);
			
			// ler a categoria escolhida do banco
			DisciplinaDao daoCat = new DisciplinaDao();
			disc = daoCat.obter(idDisciplina);
			
		} catch (NumberFormatException e) {
			idDisciplina = null;
			disc = null;
		}



		if (disc == null)
			erros.add("A disciplina escolhida é inválida.");
		
		if (nome == null || nome.trim().length() == 0)
			erros.add("O campo nome é obrigatório.");

		
		// criar instância do DAO para persistência
		ProfessorDao dao = new ProfessorDao();
		// transferir os dados para o objeto do Modelo
		Professor prof;
		
		if (codigo == null)
			prof = new Professor();
		else
			prof = dao.obter(codigo);
		
		// alterar os dados do objeto
		prof.setDisciplina(disc);
		prof.setCodigo(codigo);
		prof.setNome(nome);

		// testar se os dados enviados estão corretos
		if (erros.size() == 0)
		{
			// salvar o objeto no banco de dados
			prof = dao.salvar(prof);

			// fazer redirect para listar os professores, a fim de evitar envios repetidos
			response.sendRedirect("listarProfessores");
		}
		else
		{
			// guardar o produto no request para ser lido pela página
			request.setAttribute("prof", prof);
			request.setAttribute("erros", erros);
			
			// ler a lista de disciplinas
			DisciplinaDao daoDisc = new DisciplinaDao();
			List<Disciplina> listaCat = daoDisc.listar();
			// guardar a lista de categorias no request
			request.setAttribute("listaCat", listaCat);
			
			// criar um objeto para despachar a requisição
			RequestDispatcher desp = request.getRequestDispatcher("professorEditar.jsp");
			// encaminhar para o servlet ou página selecionada
			desp.forward(request, response);
		}
		
	}

}