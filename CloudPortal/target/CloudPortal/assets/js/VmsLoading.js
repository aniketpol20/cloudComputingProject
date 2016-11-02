/**
 * 
 */
function loadVMs()
{
	$.ajax ({
		
	       url: "getListOfServers",
	       type:"POST",
	       success: function(data) {
	    	   console.log('I am in loadVMs success');
	    	   vms=getVMs(data);
	    	   addDivs(vms);
	    	
	}
	  });
	
	
	
	
	
}
function vmDetails(elem)
{
    console.log('Inside vmDetails ');
    	var url='Pie?id='+ elem.id+'&name='+elem.id ;
    	console.log(url);
        window.location.href = url;
    
}

function getVMs(data)
{
	console.log('I am in getVMs');
//	get data json and convert into list of names like
	var servers=data["listOfServer"];
	return servers;
}

function addDivs(vms)
{
	var n=vms.length;
	var divElem;//=document.createElement("div");
	 s=2;//0+Math.sqrt(n);
	for(i=0;i<n;i++)
	{
		if(i%s==0)
			{
			console.log("i="+i+"s="+s);
			divElem = document.createElement("div");
			//divElem.setAttribute("class","imgContainer");
			document.getElementById("VMBox").appendChild(divElem);
			
		}
		var elem=getDiv(vms[i]);
		divElem.appendChild(elem);
		console.log('loadVMs method');
		

	}
}
function getDiv(vm,w,h)
{
	var divElem = document.createElement("div");
	divElem.setAttribute("class","imgContainer");
	var elem = document.createElement("img");
	elem.src="assets/images/vm_icon.png";
	elem.setAttribute("height", "200");
	elem.setAttribute("width", "200");
	elem.setAttribute("id", vm['tenantId']);
	elem.setAttribute("dataId", vm['serverId']);
	elem.setAttribute("class","thumbnail");
	elem.setAttribute("onClick","vmDetails(this)");
	//elem.setAttribute("id", "1234");
	divElem.appendChild(elem);
	var label = document.createElement("p");
	label.innerHTML=vm['serverName'];
	divElem.appendChild(label);
	return divElem;
	

}