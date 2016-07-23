<!doctype html>
<html>
<head>

  <meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,800">


  <meta charset="utf-8">

  <!-- Always force latest IE rendering engine or request Chrome Frame -->
  <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">

  <!-- Use title if it's in the page YAML frontmatter -->
  <title>The Decider</title>

  <link href="${request.contextPath}/core-admin/release_bs3/build/stylesheets/application.css" media="screen" rel="stylesheet" type="text/css" />
  <script src="${request.contextPath}/core-admin/release_bs3/build/javascripts/application.js" type="text/javascript"></script>
</head>

<body>

<nav class="navbar navbar-default navbar-inverse navbar-static-top" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <a class="navbar-brand" href="${createLink(controller: "main") }">The Decider</a>
  </div>
</nav>

<div class="container">
  
<div class="col-md-4 col-md-offset-4">


  <div class="padded">
    <div class="login box" style="margin-top: 80px;">

      <div class="box-header">
        <span class="title">Reset Password</span>
      </div>
      
      <div class="box-content padded">
      <g:form action='resetPassword' name='resetPasswordForm' autocomplete='off'>
      <g:hiddenField name='t' value='${token}'/>
	<div class="sign-in">

	<h4><g:message code='spring.security.ui.resetPassword.description'/></h4>

		<div class="input-group addon-left">
            <span class="input-group-addon" href="#">
              <i class="icon-key"></i>
            </span>
            <s2ui:passwordFieldRow name='password' labelCode='resetPasswordCommand.password.label' bean="${command}"
                             placeholder="password" value="${command?.password}"/>
          </div>
          
          <div class="input-group addon-left">
            <span class="input-group-addon" href="#">
              <i class="icon-key"></i>
            </span>
            <s2ui:passwordFieldRow name='password2' labelCode='resetPasswordCommand.password2.label' bean="${command}"
                             placeholder="password (again)" value="${command?.password2}"/>
          </div>

	<s2ui:submitButton class="btn btn-blue btn-block" elementId='reset' form='resetPasswordForm' messageCode='spring.security.ui.resetPassword.submit'/>

	</div>
	</g:form>
	</div>
    </div>
  </div>
</div>
</div>

<script>
$(document).ready(function() {
	$('#password').focus();
});
</script>
</body>
</html>
