<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="modelo.dominio.Disciplina"%>
<%@page import="modelo.dominio.Professor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Professor</title>
</head>

<body>
<jsp:useBean id="prof" scope="request" class="modelo.dominio.Professor"></jsp:useBean>

<%
	List<Disciplina> listaDisc = (List<Disciplina>) request.getAttribute("listaDisc");

	List<String> erros = (List<String>) request.getAttribute("erros");
	if (erros != null)
	{
		out.print("<ul>");
		for (String erro : erros)
			out.print("<li>" + erro + "</li>");
			
		out.print("</ul>");
	}
%>
	<form action="salvarProfessor" method="post">
	<input type="hidden" name="codigo" value="${prof.codigo}">
	<table>
		<tr>
			<td><label for="codigo">Codigo:</label></td>
			<td>${prof.codigo}</td>
		</tr>
		<tr>
			<td><label for="categoria">Categoria:</label></td>
			<td><select name="categoria" id="categoria">
					<option value="">Selecione...</option>

					<c:forEach var="cat" items="${listaCat}">
						<c:set var="selecao" value="" />
						<c:if test="${cat.equals(prof.categoria) }">
							<c:set var="selecao" value="selected='selected'" />
						</c:if>
					
						<option ${selecao} value="${cat.id}">${cat.nome}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td><label for="Nome">Nome:</label></td>
			<td><input type="text" name="nome" id="nome" 
					value="${prof.nome}" size="45" maxlength="120"></td>
		</tr>

		<tr>
			<td></td>
			<td><input type="submit" name="btnSalvar" value="Salvar" >
				<input type="button" value="Cancelar"
						onclick="window.location = 'listarProfessores'">
			</td>
		</tr>
	</table>
		
	</form>
</body>
</html>