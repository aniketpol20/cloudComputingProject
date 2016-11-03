# RESTFul Approach to monitor and manage Cloud resources

Project aims to implement a user friendly web-portal which will be used to monitor and manage cloud resources using RESTful API’s. It requires setting up a private cloud environment on a virtual machine, understanding core services provided by the OpenStack to monitor and manage instances such as keystone for authentication, nova for computational etc. OpenStack Java SDK (openstack4j) will be used to communicate with the cloud platform. Resourceusage data provided by the OpenStack will be displayed in the graphical form on web portal.


Technologgies Used:                                                                                                                       

[1] Cloud Environment is setup using openstack cloud platform<br>                                                             

[2] OpenStack4j SDk is used to communicate with the cloud plaform to get the resource information and for authentication purpose<br>           

[3] RestFul API's are provided using Java Spring MVC framework <br>                                                           

[4] Dashboard is implemented using JavaScript and bootstrap<br>

[5] Google chart used to display resource usage in form of pie charts<br>



Software Requirements:



[1] Dev stack Cloud Environment<br>

[2] Apache Tomcat 7 or above<br>

[3] JRE 7 or above<br>

[4] Browser (Chrome or Mozilla)<br>



<b>Hardware Requirements:<b/>



[1] Minimum 4GB of RAM for a machine where dev stack is installed





<b>Installation Guide:<b/>



[1] Install dev stack cloud environment on one of the VM<br>

[2] create a private network using router and an interface to connect to the public network.<br>

[3] Launch VMS with different configuration on a newly created private network.<br>

[4] Deploy war on apache tomcat server<br>

[5] Access cloud portal through http://localhost:8080/cloudPortal <br>
