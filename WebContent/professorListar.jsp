 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<%@page import="modelo.dominio.Professor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function confirmar(codigo, nome)
{
	if (confirm('Deseja realmente excluir o professor ['+ nome +']?'))
	{
		// modelo DOM
		window.location = 'excluir?codigo=' + codigo;
	}
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Professor</title>
</head>
<%
//    TYPE CAST  /  CASTING
List<Professor> lista = (List<Professor>) request.getAttribute("lista");
Professor prof=null;
%>
<body>
	Opções:<br>
	<a href="abrirInclusao">Novo Professor</a>

	<br><br>
	Lista de Professores:<br>
	<table border="1" cellpadding="4" cellspacing="0">
		<tr>
			<th>Opções</th>
			<th>Codigo</th>
			<th>Nome</th>
		</tr>
		<c:forEach var="prof" items="${lista}" >
			<tr>
				<td><a href="editar?codigo=${prof.codigo}">Alterar</a>
					<a href="javascript:confirmar('${prof.codigo}', '${prof.nome}')">Excluir</a>
				</td>
				<td>${prof.codigo}</td>
				<td>${prof.nome}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>