
<%@ page import="theDecider.UserSystem" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="core-admin">
		<g:set var="entityName" value="${message(code: 'userSystem.label', default: 'UserSystem')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul class="nav navbar-left navbar-nav">
				<li><h1><g:message code="default.list.label" args="[entityName]" /></h1></li>
			</ul>
			<ul class="nav navbar-right">
				<li><g:link class="create btn btn-xs btn-default" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li></li>
			</ul>
		</div>
		<div id="list-userSystem" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table class="table">
			<thead>
					<tr>
					
						<g:sortableColumn property="isPerson" title="${message(code: 'userSystem.smiles.label', default: 'Smiles')}" />
					
						<g:sortableColumn property="testerGuess" title="${message(code: 'userSystem.testerGuess.label', default: 'Tester Guess')}" />
					
						<g:sortableColumn property="basisSet" title="${message(code: 'userSystem.basisSet.label', default: 'Basis Set')}" />
					
						<g:sortableColumn property="functional" title="${message(code: 'userSystem.functional.label', default: 'Functional')}" />
					
						<g:sortableColumn property="image" title="${message(code: 'userSystem.image.label', default: 'Image')}" />
					
						<g:sortableColumn property="smiles" title="${message(code: 'userSystem.isPerson.label', default: 'Is Person')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userSystemInstanceList}" status="i" var="userSystemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userSystemInstance.id}">${fieldValue(bean: userSystemInstance, field: "smiles")}</g:link></td>
					
						<td><g:formatBoolean boolean="${userSystemInstance.testerGuess}" /></td>
					
						<td>${fieldValue(bean: userSystemInstance, field: "basisSet")}</td>
					
						<td>${fieldValue(bean: userSystemInstance, field: "functional")}</td>
					
						<td><img class="Photo" style="max-width:150px; max-height:150px" src="${createLink(action:'displayImage', id:userSystemInstance.id) }"/></td>
					
						<td><g:formatBoolean boolean="${userSystemInstance.isPerson}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userSystemInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
