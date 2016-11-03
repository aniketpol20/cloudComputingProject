# RESTFul Approach to monitor and manage Cloud resources

Project aims to implement a user friendly web-portal which will be used to monitor and manage cloud resources using RESTful API’s. It requires setting up a private cloud environment on a virtual machine, understanding core services provided by the OpenStack to monitor and manage instances such as keystone for authentication, nova for computational etc. OpenStack Java SDK (openstack4j) will be used to communicate with the cloud platform. Resourceusage data provided by the OpenStack will be displayed in the graphical form on web portal.

<br>
<b>Technologgies Used: </b>                                                                                                                      

[1] Cloud Environment is setup using openstack cloud platform<br>                                                             

[2] OpenStack4j SDk is used to communicate with the cloud plaform to get the resource information and for authentication purpose<br>           

[3] RestFul API's are provided using Java Spring MVC framework <br>                                                           

[4] Dashboard is implemented using JavaScript and bootstrap<br>

[5] Google chart used to display resource usage in form of pie charts<br>


<br>
<b>Software Requirements:</b>



[1] Dev Stack Cloud Environment<br>

[2] Apache Tomcat 7 or above<br>

[3] JRE 7 or above<br>

[4] Browser (Chrome or Mozilla)<br>


<br>
<b>Hardware Requirements:</b>



[1] Minimum 4GB of RAM for a machine where dev stack is installed



<br>
<b>Installation Guide:</b>



[1] Install dev stack cloud environment on one of the VM<br>

[2] Create a private network using router and an interface to connect to the public network.<br>

[3] Launch VMs with same or different configurations on a newly created private network.<br>

[4] Deploy war on apache tomcat server<br>

[5] Access cloud portal through http://localhost:8080/cloudPortal <br>


<br>
<b>File Description:</b>

All files are new files<br>

<br>
<b>Server Files:</b>

[1]CloudController.java : This file contains all REST APIS that are exposed to the clent such as authentication, fetching resource usage information

[2] CloudService.java : This file contains all APIs of Openstack4j that are used to comminicate with keystone and compute component of openstack.

[3] CloudMode.java : This is a POJO class which represents model containing all necessary information which client needs such as UserObject.java containing username and password ,authenticationToken etc
<br>
<b>Client Files:</b><br>
[1] index.jsp : This file contains the login page for dasgboard.

[2] ListVMs.jsp : This file contains code to fetch list of the VMs present in the deployed dev stack environment.

[3] Pie.jsp : This file shows resource usages such as RAM usage,VCPUS used and disk usages of the selected VM in form of pie charts. 








