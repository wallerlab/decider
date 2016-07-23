    <div class="box">
      <div class="box-header">
        <span class="title">Your Options</span>
      </div>
      <div class="box-content padded text-center">
	<script type="text/javascript" class="init">
		$(document).ready(function() {
			$('#example').DataTable({
				"paging": false, 
				"filter": false, 
				"scrollY":"400px",
				"scrollCollapse": true,
				"order": [[2, "desc"]],
				"columnDefs": [
					{"render": function(data, type, row){
						return data = data.substring(0,5);},
					"targets": 2}]
				});
			$('#example tbody').on('click', 'tr', function () {
		        var basisSet = $('td', this).eq(0).text();
		        document.strategy.basisSet.value = basisSet;
		        var functional = $('td', this).eq(1).text();
		        document.strategy.functional.value = functional;
		        $("#showSelection").html(
					$("<p/>", {text: 'Your selection is currently:'})).append(
					$("<p/>", {text: basisSet + ' ' + functional}))
		    } );
		});
	</script>
			
	<div id=showSelection>
	</div>
			<g:form name="strategy" controller="main">
				<g:hiddenField name="basisSet" />
				<g:hiddenField name="functional" />
				<g:actionSubmit class="btn btn-blue" value="Submit"
				action="sendMethod"></g:actionSubmit>
			</g:form>
	
	<table id="example" class="display">
		<thead>
			<tr>
				<th>Functional</th>
				<th>Basis Set</th>
				<th>Weight</th>
			</tr>
		</thead>
		<tbody>
		<g:each var="strategy" in="${strategies }">
			<tr>
				<td>${strategy.functional }</td>
				<td>${strategy.basisSet }</td>
				<td>${strategy.normWeight }</td>
			</tr>
		</g:each>
		</tbody>
	</table>

	
	<!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.3/js/jquery.dataTables.js"></script>
    
    <!-- datatables bootstrap -->
	<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/plug-ins/a5734b29083/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	
      </div>
    </div>
