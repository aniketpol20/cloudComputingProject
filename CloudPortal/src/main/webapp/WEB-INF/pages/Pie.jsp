<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
  <!-- Source : https://developers.google.com/chart/interactive/docs/gallery/piechart#rotating-a-pie-chart-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    
    <script type="text/javascript" src="assets/js/Pie.js"></script>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    
     /*  google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart); */
      
      
    </script>
    <style type="text/css">
    ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 15%;
    background-color: #f1f1f1;
    position: fixed;
    height: 100%;
    overflow: auto;
}

li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}

li a.active {
    background-color: #4CAF50;
    color: white;
}

li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
    .imgContainer{
    float:left;
}
</style>
  </head>
  <body id="VMDetails" onload="loadVMData()">
  <ul>
  <li><a  href="showVMLists">Instances</a></li>
  <li><a href="logout">Logout</a></li>
  
</ul>

<div style="margin-left:25%;padding:1px 16px;height:1000px;">
	<h1 id='heading'>Utilization of Virtual Machine </h1>
  <div class="container-fluid">
  <div class="row">
  	<div class="col-sm-6" >
    <span  id="piechart_3d" style="width: 900px; height: 500px;"></span>
    </div>
    <div class="col-sm-6" >
    <span  id="piechart_ram" style="width: 900px; height: 500px;"></span>
    </div>
    <div class="col-sm-6" >
    <span  id="piechart_cpu" style="width: 900px; height: 500px;"></span>
    </div>
    </div>
    <div>
    <button onClick="stopVM()">Stop VM</button>
    </div>
    </div>
    </div>
  </body>
</html>