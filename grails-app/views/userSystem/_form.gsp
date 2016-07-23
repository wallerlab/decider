<%@ page import="theDecider.UserSystem" %>



<div class="fieldcontain ${hasErrors(bean: userSystemInstance, field: 'isPerson', 'error')} ">
	<label for="isPerson">
		<g:message code="userSystem.isPerson.label" default="Is Person" />
		
	</label>
	<g:checkBox name="isPerson" value="${userSystemInstance?.isPerson}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userSystemInstance, field: 'testerGuess', 'error')} ">
	<label for="testerGuess">
		<g:message code="userSystem.testerGuess.label" default="Tester Guess" />
		
	</label>
	<g:checkBox name="testerGuess" value="${userSystemInstance?.testerGuess}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userSystemInstance, field: 'basisSet', 'error')} ">
	<label for="basisSet">
		<g:message code="userSystem.basisSet.label" default="Basis Set" />
		
	</label>
	<g:textField name="basisSet" value="${userSystemInstance?.basisSet}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userSystemInstance, field: 'functional', 'error')} ">
	<label for="functional">
		<g:message code="userSystem.functional.label" default="Functional" />
		
	</label>
	<g:textField name="functional" value="${userSystemInstance?.functional}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userSystemInstance, field: 'image', 'error')} required">
	<label for="image">
		<g:message code="userSystem.image.label" default="Image" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="image" name="image" />

</div>

<div class="fieldcontain ${hasErrors(bean: userSystemInstance, field: 'smiles', 'error')} required">
	<label for="smiles">
		<g:message code="userSystem.smiles.label" default="Smiles" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="smiles" required="" value="${userSystemInstance?.smiles}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userSystemInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="userSystem.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${theDecider.security.User.list()}" optionKey="id" required="" value="${userSystemInstance?.user?.id}" class="many-to-one"/>

</div>

