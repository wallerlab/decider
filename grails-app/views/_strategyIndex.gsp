    <div class="box">
      <div class="box-header">
        <span class="title">Your Options</span>
        
        <ul class="box-toolbar">
      <li class="toolbar-link">
        <a href="#" data-toggle="dropdown"><i class="icon-cog"></i></a>
        <ul class="dropdown-menu">
          <li><a href="/reactor/reaction/userIndex"><i class="icon-beaker"></i>Go to Reactions</a></li>
        </ul>
      </li>
    </ul>
      </div>
      <div class="box-content padded scrollable text-center" style="height: 552px; overflow-y: auto">
<script type="text/javascript" class="init">
		$(document).ready(function() {
			$('#example').DataTable({
				"paging": false,
				"info": false,
				"bDestroy": true
				});
			$('#example tbody').on('click', 'tr', function () {
		        var name = $('td', this).eq(0).text();
		        alert( 'You clicked on '+name+'\'s row' );
		    } );
		});
	</script>
	
	
	<table id="example" class="dTable responsive">
			<thead>
				<tr>
					<th>Basis Set</th>
					<th>Functional</th>
				</tr>
			</thead>
			<tbody>
			<g:each var="strategy" in="${strategies }">
				<tr>
					<td>${strategy.basisSet }</td>
					<td>${strategy.functional }</td>
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
