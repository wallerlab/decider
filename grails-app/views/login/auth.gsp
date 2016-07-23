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
    <a class="navbar-brand" href="${createLink(controller:'main')}">The Decider</a>

    
  </div>

  
</nav>
<div class="container">
  
<div class="col-md-4 col-md-offset-4">


  <div class="padded">
    <div class="login box" style="margin-top: 80px;">

      <div class="box-header">
        <span class="title">Login</span>
      </div>

      <div class="box-content padded">
        <form action='${postUrl}' method='POST' id="loginForm" name="loginForm" autocomplete='off'>
          <div class="input-group addon-left">
            <span class="input-group-addon" href="#">
              <i class="icon-user"></i>
            </span>
            <input type="text" placeholder="username" name="j_username" id="username">
          </div>

          <div class="input-group addon-left">
            <span class="input-group-addon" href="#">
              <i class="icon-key"></i>
            </span>
            <input type="password" placeholder="password" name="j_password">
          </div>

          <div>
            <g:submitButton class="btn btn-blue btn-block" name="login" value="Log in"/>
          </div>

        </form>
        <script>
$(document).ready(function() {
	$('#username').focus();
});
</script>

        <div>
          <a href="${createLink(controller:'register')}">
              Don't have an account? <strong>Sign Up</strong>
          </a>
        </div>
        
        <div>
			<strong><g:link controller='register' action='forgotPassword'><g:message code='spring.security.ui.login.forgotPassword'/></g:link></strong>
		</div>
      </div>

    </div>

  </div>
</div>
</div>

</body>
</html>
