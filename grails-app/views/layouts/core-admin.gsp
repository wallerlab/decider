<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0">

  <!-- Always force latest IE rendering engine or request Chrome Frame -->
  <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">

  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,800">

  <!-- Use title if it's in the page YAML frontmatter -->
  <title>The Decider</title>
  <asset:link rel="shortcut icon" href="brain2.ico" type="image/x-icon"/>
  
  <link href="${request.contextPath}/core-admin/release_bs3/build/stylesheets/application.css" media="screen" rel="stylesheet" type="text/css" />
  
  <!-- DataTables CSS -->
  <link rel="stylesheet" href="//cdn.datatables.net/plug-ins/a5734b29083/integration/bootstrap/3/dataTables.bootstrap.css">

  <script src="${request.contextPath}/core-admin/release_bs3/build/javascripts/application.js" type="text/javascript"></script>
  
  <!-- JQuery slider -->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  
  <style type="text/css" class="init">
</style>
  
</head>



<body>



<nav class="navbar navbar-default navbar-inverse navbar-static-top" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header">
    <a class="navbar-brand" href="${createLink(controller: "main") }">The Decider</a>

    
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse-primary">
          <span class="sr-only">Toggle Side Navigation</span>
          <i class="icon-th-list"></i>
        </button>

        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse-top">
          <span class="sr-only">Toggle Top Navigation</span>
          <i class="icon-align-justify"></i>
        </button>
    
  </div>

  
      

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse navbar-collapse-top">
        <div class="navbar-right">

          <ul class="nav navbar-nav navbar-left">
          </ul>

          <ul class="nav navbar-nav navbar-left">
			<sec:ifLoggedIn>
				<li class="cdrop active">
					<g:form controller="logout">
						<g:submitButton name="Submit" value="Log out"/>
					</g:form>
				</li>
            	<li class="dropdown">
              		<a href="#" class="dropdown-toggle dropdown-avatar" data-toggle="dropdown">
              			<span>
                			<img class="menu-avatar" src="${request.contextPath}/images/theDecider.png" /> 
                				<span> <i class="icon-caret-down"></i></span>
              			</span>
              		</a>
              		<ul class="dropdown-menu">

                	<!-- the first element is the one with the big avatar, add a with-image class to it -->

                		<li class="with-image">
                  			<div class="avatar">
                    			<img src="${request.contextPath}/images/theDecider.png" />
                  			</div>
                 			<span></span>
                		</li>

                		<li class="divider"></li>
						<li>
							<g:remoteLink class="logout" controller="logout" method="post" asynchronous="false" 
									onSuccess="location.assign('${createLink(controller: "main") }')">
                				<i class="icon-off"></i>Log Out
                			</g:remoteLink></li>
                		<li><g:link controller="register" action="changePassword">Reset Password</g:link></li>
              		</ul>
            	</li>
			</sec:ifLoggedIn>
          </ul>
        </div>




      </div><!-- /.navbar-collapse -->

  
</nav>
<div class="sidebar-background">
  <div class="primary-sidebar-background"></div>
</div>

<div class="primary-sidebar">

  <!-- Main nav -->
  <ul class="nav navbar-collapse collapse navbar-collapse-primary">

        <li class="active dark-nav">
          <span class="glow"></span>
          <a class="accordion-toggle collapsed " data-toggle="collapse" href="#yJ6h3Npe7C">
              <i class="icon-legal icon-2x"></i>
                    <span>
                      The Decider
                      <i class="icon-caret-down"></i>
                    </span>
          </a>
          <ul id="yJ6h3Npe7C" class="collapse ">
                <sec:ifNotGranted roles="ROLE_ADMIN, ROLE_JUDGE">
                	<li class="">
                  		<a href="${createLink(controller: 'turingTest')}">
                      		<i class="icon-pencil"></i> Turing Test
                  		</a>
                	</li>
                </sec:ifNotGranted>
                <sec:ifAnyGranted roles="ROLE_ADMIN">
        			<li class="">
          				<a class="accordion-toggle collapsed " data-toggle="collapse" href="#ttopts">
              				<i class="icon-pencil icon-2x"></i>
                    		<span>Turing Test<i class="icon-caret-down"></i></span>
          				</a>
          				<ul id="ttopts" class="collapse">
               				<li class="">
                  				<a href="${createLink(controller: 'turingTest')}">
                      				<i class="icon-group"></i> Turing test main page
                  				</a>
                			</li>
               				<li class="">
                  				<a href="${createLink(controller: 'turingTest', action: 'question')}">
                      				<i class="icon-group"></i> Turing test question
                  				</a>
                			</li>
                			<li class="">
                  				<a href="${createLink(controller: 'turingTest', action: 'answer')}">
                      				<i class="icon-cogs"></i> Turing test answer
                 				</a>
                			</li>
          				</ul>
          			</li>
                </sec:ifAnyGranted>
                <li class="">
                  <a href="${createLink(controller: 'main')}">
                      <i class="icon-sitemap"></i> Get a Method
                  </a>
                </li>
                <sec:ifNotLoggedIn>
                <li class="">
                  <a href="${createLink(controller: 'login')}">
                      <i class="icon-unlock"></i> Log in
                  </a>
                </li>
                </sec:ifNotLoggedIn>
                <sec:ifAllGranted roles="ROLE_ADMIN">
                <li class="">
                  <a href="${createLink(controller: 'user', action: 'showAllUsers')}">
                      <i class="icon-group"></i> Users
                  </a>
                </li>
                <li class="">
                  <a href="${createLink(controller: 'userSystem')}">
                      <i class="icon-cogs"></i> User Systems
                  </a>
                </li>
                </sec:ifAllGranted>
          </ul>
        </li>

  </ul>

    </div>
 
<div class="main-content">
  <div class="container">
    <div class="row">

      <div class="area-top clearfix">
        <div class="pull-left header">
          <h3 class="title">
            <i class="icon-legal"></i>
            Welcome to The Decider
          </h3>
          <h5>
            <span>Life is just a game</span>
          </h5>
        </div>

      </div>
    </div>
  </div>

  <div class="container padded">
    <div class="row">

      <!-- Breadcrumb line -->

      <div id="breadcrumbs">
        <div class="breadcrumb-button blue">
          <span class="breadcrumb-label"><i class="icon-home"></i> Home</span>
          <span class="breadcrumb-arrow"><span></span></span>
        </div>

      </div>
    </div>
  </div>
  <script>
  	$(document).ready(function(){
  	  	var urlSplit = document.URL.split("/");
  	  	var usefulUrl = urlSplit.slice(3);
  	  	var icons = ["icon-legal", "icon-pencil", "icon-screenshot"];
  	  	for(index = 0; index < usefulUrl.length; index++){
  	  	  	if(usefulUrl[index].length == 0){
  	  	  	  	return
  	  	  	}
			$('#breadcrumbs').append(
				$('<div/>', {class: 'breadcrumb-button'}).append(
					$('<span/>', {class: 'breadcrumb-label', 
						html: "<i class="+icons[index]+"></i> " + usefulUrl[index]})).append(
					$('<span/>', {class: 'breadcrumb-arrow'}).append(
						$("<span/>"))))
  	  	}});

	</script>

  <div class="container">
    <div class="row">
    

<div id="testURL"></div>
    
    <g:layoutBody/>

</div>
</div>
</div>
</body>
</html>
