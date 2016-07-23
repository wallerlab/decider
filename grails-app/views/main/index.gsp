<!DOCTYPE html>
<html lang="en">
<head>
<meta name="layout" content="core-admin">
<style>
#alphaSlider, #betaSlider, #gammaSlider, #cutoffSlider {
	width: 90%;
	margin: 15px;
}
</style>
</head>

<g:if test="${flash.error}">
	<div class="col-md-12">
		<div class="alert alert-error text-center">
			<button type="button" class="close" data-dismiss="alert">x</button>
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
			<button type="button" class="close" data-dismiss="alert">x</button>
			<div class="message" role="status">
				<i class="icon-check"></i>
				${flash.message}
			</div>
		</div>
	</div>
</g:if>

<div class="col-md-7">
	<div class="box text-center">
		<div class="box-header">
			<div class="title">Draw your system here, then click the submit
				button.</div>
		</div>
		<div class="box-content padded">
			<script type="text/javascript"
				src="${request.contextPath}/js/marvin/marvin.js"></script>
			<script type="text/javascript">
				function exportParams() {
					if (document.MSketch != null) {
						var s = document.MSketch.getMol("smiles");
						document.MolForm.smiles.value = s;
					} else {
						alert("Cannot import molecule:\n"
							+ "no JavaScript to Java communication in your browser.\n");
					}
				}

				msketch_name = "MSketch";
				msketch_begin("${request.contextPath}/js/marvin", 445, 400);
				msketch_end();
			</script>
			<script>
				$(function() {
					$("#alphaSlider, #betaSlider, #gammaSlider, #cutoffSlider")
						.slider(
							{
								value : 50,
								slide : function() {
									$("#alpha").val($("#alphaSlider").slider("value"));
									$("#beta").val($("#betaSlider").slider("value"));
									$("#gamma").val($("#gammaSlider").slider("value"));
									$("#cutoff").val($("#cutoffSlider").slider("value") / 100);
									document.MolForm.alpha.value = $("#alpha").val();
									document.MolForm.beta.value = $("#beta").val();
									document.MolForm.gamma.value = $("#gamma").val();
									document.MolForm.cutoff.value = $("#cutoff").val();
								}
						});
					$("#alpha").val($("#alphaSlider").slider("value"));
					$("#beta").val($("#betaSlider").slider("value"));
					$("#gamma").val($("#gammaSlider").slider("value"));
					$("#cutoff").val($("#cutoffSlider").slider("value") / 100);
					document.MolForm.alpha.value = $("#alpha").val();
					document.MolForm.beta.value = $("#beta").val();
					document.MolForm.gamma.value = $("#gamma").val();
					document.MolForm.cutoff.value = $("#cutoff").val();
				});
			</script>
			<p>
				<label for="cutoff">System Similarity Cutoff:</label> <input
					type="text" id="cutoff" readonly
					style="border: 0; color: #f6931f; font-weight: bold;" />
			</p>
			<div id="cutoffSlider"></div>
			<p>
				<label for="alpha">Zeta:</label> <input type="text" id="alpha"
					readonly style="border: 0; color: #f6931f; font-weight: bold;" />
			</p>
			<div id="alphaSlider"></div>
			<p>
				<label for="beta">Polarity:</label> <input type="text" id="beta"
					readonly style="border: 0; color: #f6931f; font-weight: bold;" />
			</p>
			<div id="betaSlider"></div>
			<p>
				<label for="gamma">Diffusivity:</label> <input type="text" id="gamma" 
					readonly style="border: 0; color: #f6931f; font-weight: bold;" />
			</p>
			<div id="gammaSlider"></div>
			<p>
				<g:form name="MolForm" controller="main">
					<g:hiddenField name="smiles" />
					<g:hiddenField name="alpha" />
					<g:hiddenField name="beta" />
					<g:hiddenField name="gamma" />
					<g:hiddenField name="cutoff" />
					<g:actionSubmit class="btn btn-blue" value="Submit"
						onClick="exportParams()" action="getStrategies"></g:actionSubmit>
				</g:form>
			</p>
		</div>
	</div>
</div>

<%--Reaction Viewer--%>
<div class="col-md-5">
	<div class="col-md-12">
		<g:render template="/main/strategyIndex" />
	</div>
</div>
</html>