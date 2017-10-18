    <div class="box">
      <div class="box-header">
        <span class="title">Your Options</span>
      </div>
      <div class="box-content padded text-center">
	<script type="text/javascript" class="init">
        var table;
        $(document).ready(function(){
            var dataset = [];
            table = $('#example').DataTable({
                "data": dataset,
                "paging": false,
                "filter": false,
                "scrollY":"400px",
                "scrollCollapse": true,
                "order": [[2, "desc"]],
                "columnDefs": [
                    {"render": function(data, type, row){
                        return data = data.toString().substring(0,5);},
                        "targets": 2}]
            });
        });

		function updateTable(data){
            var strategies = document.getElementById("strategies");
            var dataset = [];
            for(var i = 0; i < data.wso.length; i++){
                var optionArray = [data.wso[i].functional, data.wso[i].basisSet,
                    data.wso[i].normWeight];
                dataset.push(optionArray);
            }
            table.clear().rows.add(dataset).draw();
            $('#example tbody').on('click', 'tr', function () {
                var basisSet = $('td', this).eq(0).text();
                var functional = $('td', this).eq(1).text();
                $("#showSelection").html(
                    $("<p/>", {text: 'Your selection is currently:'})).append(
                    $("<p/>", {text: basisSet + ' ' + functional}))
            } );
		}
	</script>
			
	<div id=showSelection>
	</div>
	
	<table id="example" class="display">
		<thead>
			<tr>
				<th>Functional</th>
				<th>Basis Set</th>
				<th>Weight</th>
			</tr>
		</thead>
		<tbody id="strategies">
		</tbody>
	</table>

	
	<!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.3/js/jquery.dataTables.js"></script>
    
    <!-- datatables bootstrap -->
	<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/plug-ins/a5734b29083/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	
      </div>
    </div>
