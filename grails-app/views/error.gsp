<!doctype html>
<html>
<head>

	<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,800">


	<meta charset="utf-8">

	<!-- Always force latest IE rendering engine or request Chrome Frame -->
	<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">

	<!-- Use title if it's in the page YAML frontmatter -->
	<title>Decider</title>

	<link href="${request.contextPath}/core-admin/release_bs3/build/stylesheets/application.css" media="screen" rel="stylesheet" type="text/css" />
	<script src="${request.contextPath}/core-admin/release_bs3/build/javascripts/application.js" type="text/javascript"></script>
</head>

<body>

<nav class="navbar navbar-default navbar-inverse navbar-static-top" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<a class="navbar-brand" href="/main/index">Decider</a>


	</div>


</nav>
<div class="container">

	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<div class="error-box">
				<g:if env="development">
					<g:renderException exception="${exception}" />
				</g:if>
				<div class="message-small">Whoa! What are you doing here?</div>
				<div class="message-big">404</div>
				<div class="message-small">You are not where you're supposed to be</div>

				<div style="margin-top: 50px">
					<a class="btn btn-blue" href="/main/index">
						<i class="icon-arrow-left"></i> Back to Decider
					</a>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>
