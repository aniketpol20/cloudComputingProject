<!DOCTYPE html>
<html lang="en">
<head>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
body {
    margin: 0;
}

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
.image { 
   position: relative; 
   width: 100%; /* for IE 6 */
}
.imgContainer{
    float:left;
}

p  { 
	position: relative;
   color: black; 
   font: bold 24px/45px Helvetica, Sans-Serif; 
   letter-spacing: -1px;  
   //background: rgb(0, 0, 0); /* fallback color */
   align:center
    
}
</style>
</head>
<body id="VMsPage" onload="loadVMs()">
<ul>
  <li><a  href="showVMLists">Instances</a></li>
  <li><a href="logout">Logout</a></li>
  
</ul>


<script src='assets/js/jquery.min.js'></script>

        <script src="assets/js/VmsLoading.js"></script>
	<div style="margin-left:25%;padding:1px 16px;height:1000px;">
	<h1>List of Virtual Machines</h1>
	<div id="VMBox">
	
	
			
		</div>
	</div>
	
</body>
</html>