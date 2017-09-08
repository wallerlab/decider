<header>
    <script src="${request.contextPath}/js/marvinjs/gui/lib/promise-1.0.0.min.js"></script>
    <script src="${request.contextPath}/js/marvinjs/js/marvinjslauncher.js"></script>
</header>

<iframe id="sketch" src="${request.contextPath}/js/marvinjs/editor.html"
        style="overflow: hidden; min-width: 500px; min-height:
        450px; border: 1px solid darkgray;"></iframe>


<script>
    var marvinSketcherInstance;
    $(document).ready(function handleDocumentReady (e) {
        MarvinJSUtil.getEditor("#sketch").then(function (marvinPackage) {
            marvinSketcherInstance = marvinPackage;
            initControl();
        }, function (error) {
            alert("Marvin package is not available:" + error);
        });
    })

    function initControl(){
        $("#showsetup").click(function() {
            post();
        });
    }

    function post(){
        marvinSketcherInstance.exportStructure("mrv").then(function(source) {
            $.post("${createLink(controller: 'main', action: 'getStrategies')}",
                {
                    mrv:source, cutoff: document.getElementById("cutoff").value
                },
                function(data){
                    handleData(data);
                }
            );
        }, function(error) {
            alert("Molecule export failed:"+error);
        });
    }

    function handleData(data){
        /*var col=document.createElement("div");
        col.className="col-md-12";
        var msg=document.createElement("div");
        msg.className="message";
        msg.id="message";
        msg.setAttribute("role", "status");
        var text;
        var icon = document.createElement("i");
        var messageDiv = document.getElementById("messageDiv");
        if(!data.wso.length > 0) {
            msg.setAttribute("style", "color:red");
            icon.className = "fa fa-2x fa-warning";
            text = document.createTextNode(" An error occurred");
            messageDiv.className="alert alert-danger";
        } else{
            msg.setAttribute("style", "color:green");
            icon.className = "fa fa-2x fa-check-square";
            text = document.createTextNode(" Success!");
            messageDiv.className="alert alert-success";
        }
        if(messageDiv.childNodes.length > 0){
            messageDiv.removeChild(messageDiv.childNodes[0]);
        }
        messageDiv.appendChild(col);
        col.appendChild(msg);
        msg.appendChild(icon);
        msg.appendChild(text);*/
        updateTable(data);

    }
</script>