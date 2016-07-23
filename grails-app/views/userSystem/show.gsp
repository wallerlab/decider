
<%@ page import="theDecider.UserSystem" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userSystem.label', default: 'UserSystem')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-userSystem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-userSystem" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list userSystem">
			
				<g:if test="${userSystemInstance?.isPerson}">
				<li class="fieldcontain">
					<span id="isPerson-label" class="property-label"><g:message code="userSystem.isPerson.label" default="Is Person" /></span>
					
						<span class="property-value" aria-labelledby="isPerson-label"><g:formatBoolean boolean="${userSystemInstance?.isPerson}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userSystemInstance?.testerGuess}">
				<li class="fieldcontain">
					<span id="testerGuess-label" class="property-label"><g:message code="userSystem.testerGuess.label" default="Tester Guess" /></span>
					
						<span class="property-value" aria-labelledby="testerGuess-label"><g:formatBoolean boolean="${userSystemInstance?.testerGuess}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userSystemInstance?.basisSet}">
				<li class="fieldcontain">
					<span id="basisSet-label" class="property-label"><g:message code="userSystem.basisSet.label" default="Basis Set" /></span>
					
						<span class="property-value" aria-labelledby="basisSet-label"><g:fieldValue bean="${userSystemInstance}" field="basisSet"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userSystemInstance?.functional}">
				<li class="fieldcontain">
					<span id="functional-label" class="property-label"><g:message code="userSystem.functional.label" default="Functional" /></span>
					
						<span class="property-value" aria-labelledby="functional-label"><g:fieldValue bean="${userSystemInstance}" field="functional"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userSystemInstance?.image}">
				<li class="fieldcontain">
					<span id="image-label" class="property-label"><g:message code="userSystem.image.label" default="Image" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userSystemInstance?.smiles}">
				<li class="fieldcontain">
					<span id="smiles-label" class="property-label"><g:message code="userSystem.smiles.label" default="Smiles" /></span>
					
						<span class="property-value" aria-labelledby="smiles-label"><g:fieldValue bean="${userSystemInstance}" field="smiles"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userSystemInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="userSystem.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${userSystemInstance?.user?.id}">${userSystemInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:userSystemInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${userSystemInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
