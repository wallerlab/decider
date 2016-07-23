<!DOCTYPE html>
<html lang="en">
<meta name="layout" content="core-admin">

<g:if test="${flash.error}">
	<div class="col-md-12">
		<div class="alert alert-error text-center">
			<div class="message" role="status">
				<i class="icon-warning-sign"></i>
				${flash.error}
			</div>
		</div>
	</div>
</g:if>

<g:if test="${flash.message}">
	<div class="col-md-12">
		<div class="alert alert-success text-center">
			<div class="message" role="status">
				<i class="icon-thumbs-up"></i>
				${flash.message}
			</div>
		</div>
	</div>
</g:if>

<div class="col-md-12">
	<div class="alert alert-block text-center">
		<h4>Coming Soon: The Decider!</h4>
		<p>The Decider is a DFT method selection application. Give it a
			set of molecules and it will calculate the optimal density functional
			 and basis set to use.</p>
	</div>

</div>


<div class="col-md-12">

	<div class="box-content scrollable alert alert-info"
		style="overflow-y: auto">
		<h4 class="text-center">We are accepting volunteers to register
			as testers for our DFT method "Turing test"!</h4>

		<div class="box-section news with-icons">
			<div class="avatar blue">
				<i class="icon-trophy icon-2x"></i>
			</div>
			<div class="news-content">
				<h4>What you'll be doing:</h4>
				<div class="news-text">
					As a volunteer, you will be randomly assigned to be either a <b>Judge</b>
					or a <b>Player</b>.
				</div>
			</div>

			<div class="box-section news with-icons">
				<div class="avatar purple">
					<i class="icon-legal icon-2x"></i>
				</div>
				<div class="news-content">
					<h4>Judges</h4>
					<div class="news-text">
						A <b>Judge</b> will draw a molecule or set of molecules in a
						molecule sketcher and await a response from either The Decider or
						a <b>Player</b>. When they receive the response, they will be
						asked to determine whether the answer came from The Decider or a
						human.
					</div>
				</div>
			</div>

			<div class="box-section news with-icons">
				<div class="avatar green">
					<i class="icon-user icon-2x"></i>
				</div>
				<div class="news-content">
					<h4>Players</h4>
					<div class="news-text">
						A <b>Player</b> will receive an email with a molecule or set of
						molecules and will be asked to give a combination of a basis set
						and functional.
					</div>
				</div>


			</div>





			<div class="box-section news with-icons text-center">
				<p>
					<a href="${createLink(controller:'register')}" class="btn btn-lg btn-blue">Register as a
						Tester</a>
				</p>
			</div>

		</div>
	</div>
</div>

</div>