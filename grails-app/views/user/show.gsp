
<%@ page import="theDecider.security.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="core-admin">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
	<header>
		<div id="show-user" class="content scaffold-show" role="main">
		<section>
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
				<div class="row">
					<g:if test="${userInstance?.username}">
						<div class="col-md-3 col-md-push-3 col-size-med">Username:</div>
						<div class="col-md-3 col-md-push-3 col-size-med"><g:fieldValue bean="${userInstance}" field="username"/></div>
					</g:if>
				</div>
				<div class="row">
					<g:if test="${userInstance?.email}">
						<div class="col-md-3 col-md-push-3 col-size-med">Email:</div>
						<div class="col-md-3 col-md-push-3 col-size-med"><g:fieldValue bean="${userInstance}" field="email"/></div>
					</g:if>
				</div>
				<div class="row">
					<g:if test="${userInstance?.accountExpired}">
						<div class="col-md-3 col-md-push-3 col-size-med">Account Expired:</div>
						<div class="col-md-3 col-md-push-3 col-size-med"><g:formatBoolean boolean="${userInstance?.accountExpired}" /></div>
					</g:if>
				</div>
				<div class="row">
					<g:if test="${userInstance?.accountLocked}">
						<div class="col-md-3 col-md-push-3 col-size-med">Account Locked:</div>
						<div class="col-md-3 col-md-push-3 col-size-med"><g:formatBoolean boolean="${userInstance?.accountLocked}" /></div>
					</g:if>
				</div>
				<div class="row">
					<g:if test="${userInstance?.enabled}">
						<div class="col-md-3 col-md-push-3 col-size-med">Account Enabled:</div>
						<div class="col-md-3 col-md-push-3 col-size-med"><g:formatBoolean boolean="${userInstance?.enabled}" /></div>
					</g:if>
				</div>
				<div class="row">
					<g:if test="${userInstance?.passwordExpired}">
						<div class="col-md-3 col-md-push-3 col-size-med">Password Expired:</div>
						<div class="col-md-3 col-md-push-3 col-size-med"><g:formatBoolean boolean="${userInstance?.passwordExpired}" /></div>
					</g:if>
				</div>
				<div class="row">
					<g:if test="${userInstance?.getAuthorities()}">
						<div class="col-md-3 col-md-push-3 col-size-med">Roles:</div>
						<g:each in="${userInstance.getAuthorities()}" var="r">
								<div class="row">
									<div class="col-md-3 col-md-offset-6 col-size-med">
										<g:fieldValue bean="${r}" field="authority"/>
									</div>
								</div>
						</g:each>
					</g:if>
				</div>
			<g:form url="[resource:userInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit btn btn-primary" action="edit" resource="${userInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete btn btn-primary" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
			</section>
		</div>
		</header>
	</body>
</html>
