<!DOCTYPE html>
<html lang="en">
<head>
<meta name="layout" content="core-admin">
<style>
#cutoffSlider {
	width: 90%;
	margin: 15px;
}
</style>
</head>

<div id="messageDiv" class="col-md-12"></div>

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
			<g:render template="marvinjs"/>
			<script>
				$(function() {
					$("#cutoffSlider")
						.slider(
							{
								value : 50,
								slide : function() {
									$("#cutoff").val($("#cutoffSlider").slider("value") / 100);
								}
						});
					$("#cutoff").val($("#cutoffSlider").slider("value") / 100);
				});
			</script>
			<p>
				<label for="cutoff">System Similarity Cutoff:</label>
				<input type="text" id="cutoff" readonly
					style="border: 0; color: #f6931f; font-weight: bold;" />
			</p>
			<div id="cutoffSlider"></div>
			<p>
				<button class="btn btn-blue" id="showsetup">Submit</button>
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