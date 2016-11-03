
  function chartValues(data){
	   var ram=data["ramUsage"];
	   var cpu=data["cpuUsage"];
	   var disk=data["diskUsage"];
	   
	   Memory = [
	             ['Ram Usage', 'Memory in GBs '],
	             ['used',     ram["used"]],
	             ['Remaininig', ram["remain"]]
	           ];
	          CPU = [
	                       ['CPU Usage', 'No of Virtual CPUs '],
	                       ['used',     cpu["used"]],
	      	             ['Remaininig', cpu["remain"]]
	                     ];
	          Resources = [
	                       ['Disk Usage', 'Memory in GBs '],
	                       ['used',     disk["used"]],
	      	             ['Remaininig', disk["remain"]]
	                     ];

	            options = {
	             title: 'CPU Usage',
	             is3D: true,
	           };
	            options1 = {
	                   title: 'Memory Usage',
	                   is3D: true,
	                 };
	            options2 = {
	                   title: 'Resource Utilization',
	                   is3D: true,
	                 };
   }
function getUrlParameter(sParam) {
            var sPageURL = decodeURIComponent(window.location.search.substring(1)),
                sURLVariables = sPageURL.split('&'),
                sParameterName,
                i;

            for (i = 0; i < sURLVariables.length; i++) {
                sParameterName = sURLVariables[i].split('=');

                if (sParameterName[0] === sParam) {
                    return sParameterName[1] === undefined ? true : sParameterName[1];
                }
            }
        };     
        
  //collect data from ajax call 
        
        function getParameterByName(name, url) {
    	    if (!url) {
    	      url = window.location.href;
    	    }
    	    name = name.replace(/[\[\]]/g, "\\$&");
    	    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
    	        results = regex.exec(url);
    	    if (!results) return null;
    	    if (!results[2]) return '';
    	    return decodeURIComponent(results[2].replace(/\+/g, " "));
    	}
      
        function loadVMData()
        {
        	
        	var tenantId = getParameterByName('id');  
          
        	$('heading').text(heading);
        	$.ajax ({
        			type:"POST",
        	       url: "getResourceDetails/"+tenantId,
        	       success: function(data) {
        	    	   
        	    	   chartValues(data["diagnostic"]);
        	    	   google.charts.load("current", {packages:["corechart"]});
        	    	   google.charts.setOnLoadCallback(drawChart);
        	    	  
        	}
        	  });
//        	
        }
        
        
      function drawChart() {
        var data = google.visualization.arrayToDataTable(Memory);
        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
        
        data = google.visualization.arrayToDataTable(CPU);
        var chart1 = new google.visualization.PieChart(document.getElementById('piechart_ram'));
        chart1.draw(data, options1);
        
        data = google.visualization.arrayToDataTable(Resources);
        var chart2 = new google.visualization.PieChart(document.getElementById('piechart_cpu'));
        chart2.draw(data, options2);
      }
      
      function stopVM()
      {
      	
      	var tenantId = getParameterByName('id');  
        
      	$.ajax ({
      			type:"POST",
      	       url: "suspendServer/"+tenantId,
      	       success: function(data) {
      	    	   
      	    	   alert("VM stopped successfully");
      	    	 window.location.href = "showVMLists";
      	    	  
      	}
      	  });
//      	
      }
    
