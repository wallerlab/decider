<%@ page import="theDecider.UserSystem" %>
<!DOCTYPE html>
<html lang="en">
	<meta name="layout" content="core-admin">

<g:if test="${flash.error}">
<div class="col-md-12">
	<div class="alert alert-error text-center">
		<button type="button" class="close" data-dismiss="alert">x</button>
		<div class="message" role="status"><i class="icon-warning-sign"></i> ${flash.error}</div>
	</div>
</div>
</g:if>

<g:if test="${flash.message}">
<div class="col-md-12">
	<div class="alert alert-success text-center">
		<div class="row padded">
			<div class="message" role="status"><i class="icon-check"></i> ${flash.message}</div>
		</div>
		<div class="row">
			 <a href="" class="btn btn-lg btn-green">Go back</a>
		</div>
	</div>
</div>
</g:if>

<g:else>
<div class="col-md-push-2 col-md-7">
	<div class="box text-center">
		<div class="box-header">
    		<div class="title">Computer or Person?</div>
    	</div>
    	<div class="box-content padded">
    		<div class="row">
    			<div class="col-md-6">
    				<script type="text/javascript" SRC="${request.contextPath}/js/marvin/marvin.js"></script>
    				<%--TODO: check if this works
						mview_param("mol", "${atomInstance?.smiles}");--%>
    				<script type="text/javascript">
						mview_begin("${request.contextPath}/js/marvin", 200, 200);
						mview_param("mol", "c1ccccc1");
						mview_end();
					</script>
    			</div>
    			<div class="col-md-6">
    				<h4>Functional: </h4>
    				<h4>Basis Set: </h4>
    			</div>
			</div>
			<div class="row">
				<g:link class="btn btn-lg btn-blue" action="getAnswer" id="0">Computer</g:link>
				<g:link class="btn btn-lg btn-blue" action="getAnswer" id="1">Person</g:link>
			</div>
		</div>
	</div>
</div>
</g:else>