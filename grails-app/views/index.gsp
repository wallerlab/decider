<!DOCTYPE html>
<html lang="en">
	<meta name="layout" content="core-admin">

<g:if test="${flash.message}">
<div class="col-md-12">
	<div class="box text-center">
		<div class="box-content padded">
			<div class="message" role="status" style="color:red">${flash.message}</div>
		</div>
	</div>
</div>
</g:if>

<div class="col-md-7">
	<div class="box text-center">
		<div class="box-header">
    		<div class="title">Draw your system here, then click the submit button.</div>
    	</div>	
    	<div class="box-content padded">
		<script type="text/javascript" src="${request.contextPath}/js/marvin/marvin.js"></script>
		<script type="text/javascript">
			function exportMol() {
				if (document.MSketch != null) {
					if(document.MSketch.getAtomCount() != 0){
						var s = document.MSketch.getMol('smiles');
						document.MolForm.smiles.value = s;
					}
					else{
						var s = null
						}
				} else {
					alert("Cannot import molecule:\n"
							+ "no JavaScript to Java communication in your browser.\n");
				}
			}

			msketch_name = "MSketch";
			msketch_begin("${request.contextPath}/js/marvin", 520, 445);
			msketch_end();
		</script>
		<p>
			<g:form name="MolForm" controller="main">
				<g:hiddenField name="smiles" />
				<g:actionSubmit class="btn btn-blue" value="Submit" onClick="exportMol()"
				action="getStrategies"></g:actionSubmit>
			</g:form>
		</p>
		</div>
	</div>
	
	<div class="col-md-5">
		<div class="box text-center">
		<div class="box-header">
    		<div class="title">here's a new box</div>
    	</div>	
<%--		<script>--%>
<%--  $(function() {--%>
<%--    $( "#slider" ).slider();--%>
<%--  });--%>
<%--  </script>--%>
<%--  --%>
<%--				<div id="slider"></div>--%>
	</div>
	</div>
</div>

                
		<%--Reaction Viewer--%>
 <div class="col-md-5">
	<div class="col-md-12">
        <g:render template="main/strategyIndex"/>
  	</div>
  </div>
</html>