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
		<button type="button" class="close" data-dismiss="alert">x</button>
		<div class="message" role="status"><i class="icon-check"></i> ${flash.message}</div>
	</div>
</div>
</g:if>

<div class="col-md-push-2 col-md-7">
	<div class="box text-center">
		<div class="box-header">
    		<div class="title">Draw your system here, then click the "optimize" button.</div>
    	</div>	
    	<div class="box-content padded">
		<script type="text/javascript" src="${request.contextPath}/js/marvin/marvin.js"></script>
		<script type="text/javascript">
		function exportMolImage(){
				var s = document.MSketch.getMolImage("base64:jpeg");
				var img = document.getElementById('molImage');
				img.src = "data:image/jpeg;base64,"+s;
				return s;
			}
			function exportMol() {
				if (document.MSketch != null) {
					if(document.MSketch.getAtomCount() != 0){
						var s = document.MSketch.getMol('smiles');
						document.MolForm.smiles.value = s;
						document.MolForm.image.value = exportMolImage();
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
			<g:form name="MolForm" controller="turingTest">
				<g:hiddenField name="smiles" />
				<g:hiddenField name="image" />
				<g:actionSubmit class="btn btn-blue" value="Optimize" onClick="exportMol()"
				action="getQuestion"></g:actionSubmit>
			</g:form>
			<input TYPE=BUTTON VALUE="Export" onClick="exportMolImage()">
		</p>
		
			<div id="imageDiv"><img src="" id="molImage"></img></div>
		</div>
	</div>
	
</div>