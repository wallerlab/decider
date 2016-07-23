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
        <span class="title">Sign Up</span>
      </div>

		<g:if test='${emailSent}'>
			<br/>
			<g:message code='spring.security.ui.register.sent'/>
		</g:if>

		<g:else>
      <div class="box-content padded">

		<g:form action='register' name='registerForm' autocomplete='off'>

          <div class="input-group addon-left">
          <span class="input-group-addon" href="#">
            <i class="icon-user"></i>
          </span>
            <s2ui:textFieldRow name='username' labelCode='user.username.label' bean="${command}" 
            	class="form-control" value="${command.username}" placeholder="username"/>
          </div>
          
          <div class="input-group addon-left form-group">
          <span class="input-group-addon" href="#">
            <i class="icon-envelope"></i>
          </span>
			<s2ui:textFieldRow name='email' bean="${command}" value="${command.email}" class="form-control"
				labelCode='user.email.label' placeholder="e-mail"/>
          </div>

          <div class="input-group addon-left">
          <span class="input-group-addon" href="#">
            <i class="icon-key"></i>
          </span>
            <s2ui:passwordFieldRow name='password' labelCode='user.password.label' bean="${command}"
             	class="form-control" value="${command.password}" placeholder="password"/>
          </div>

          <div class="input-group addon-left">
          <span class="input-group-addon" href="#">
            <i class="icon-key"></i>
          </span>
            <s2ui:passwordFieldRow name='password2' labelCode='user.password2.label' bean="${command}" 
            	class="form-control" value="${command.password2}" placeholder="confirm password"/>
          </div>

          <div>
            <g:submitButton class="btn btn-block btn-blue" name="register" value="Sign Up"/>
          </div>

        </g:form>
<script>
$(document).ready(function() {
	$('#username').focus();
});
</script>
        <div>
          <a href="${createLink(uri:'/login')}">
              Already have an account? <strong>Sign In</strong>
          </a>
        </div>
      </div>
      </g:else>

    </div>

    
  </div>
</div>
</div>

</body>
</html>
