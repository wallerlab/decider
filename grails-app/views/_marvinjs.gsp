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
                    mrv: source, cutoff: document.getElementById("cutoff").value,
                    zeta: document.getElementById("alpha").value,
                    polarizability: document.getElementById("beta").value,
                    diffuse: document.getElementById("gamma").value
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
        var col=document.createElement("div");
        col.className="col-lg-1";
        var msg=document.createElement("div");
        var text=document.createTextNode(" "+data);
        msg.className="message";
        msg.id="message";
        msg.setAttribute("role", "status");
        var icon = document.createElement("i");
        if(data != "Your calculation has been added to the queue") {
            msg.setAttribute("style", "color:red");
            icon.className = "fa fa-2x fa-warning";
        } else{
            msg.setAttribute("style", "color:green");
            icon.className = "fa fa-2x fa-check-square";
        }
        var messageDiv = document.getElementById("messageDiv");
        messageDiv.className="row alert alert-danger";
        if(messageDiv.childNodes.length > 0){
            messageDiv.removeChild(messageDiv.childNodes[0]);
        }
        messageDiv.appendChild(col);
        col.appendChild(msg);
        msg.appendChild(icon);
        msg.appendChild(text);

    }
</script>