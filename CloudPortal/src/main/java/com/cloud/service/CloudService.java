package com.cloud.service;

import java.util.ArrayList;
import java.util.List;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.SimpleTenantUsage;
import org.openstack4j.openstack.OSFactory;

import com.cloud.model.CloudModel;



/**
 * CloudService is a service class which is used to call openstack4j apis to communicate with the dev stack cloud environment
 * 
 * @author aniket
 *
 */
public class CloudService {

	
	  public static  final String adminprojectId="d23db022b04a4683966fa42fd9923672";
	  public static  final String keyStoneEndPoint="http://192.168.1.6/identity/v3";
	
	  
	  public boolean checkpowerState(String tenantid){
		
		  
		CloudModel model=new CloudModel();
        OSClientV3 os=generateAuthenticationToken(model);
		
		List<Server> server=(List<Server>) os.compute().servers().list();
		for(Server s:server){
			if(s.getVmState().equals("stopped")){
				return false;
			}else{
				return true;
			}
		
		}
		return false;
	}
	/**
	 * Service fetches list of VMs using compute API of Openstack4J
	 * @param model
	 * @return
	 */
	public CloudModel getListOfServers(CloudModel model) {
		
		OSClientV3 os=generateAuthenticationToken(model);
		
		List<Server> server=(List<Server>) os.compute().servers().list();
		List<com.cloud.model.Server> listOfServer=new ArrayList<>();
		  for(Server s:server){
    	   com.cloud.model.Server serverObject=new com.cloud.model.Server();
           serverObject.setServerId(s.getId());
           serverObject.setServerName(s.getName());
           serverObject.setTenantId(s.getTenantId());
           listOfServer.add(serverObject);
       }
       model.setListOfServer(listOfServer);
       return model;
	}

	/**
	 * This API is used to generate authentication token given username and password
	 * @param model
	 * @return
	 */
	private OSClientV3 generateAuthenticationToken(CloudModel model) {
		
			OSClientV3 os = OSFactory.builderV3()
    			.endpoint("http://192.168.1.6/identity/v3")
    			.credentials(model.getUser().getUserName(),model.getUser().getPassword(),Identifier.byName("default"))
    			.scopeToProject(Identifier.byId("6e43817c72bf48c28d2d8bef67382e10"))
    			.authenticate();
			return os;
		
	}

	/**
	 * Service is used to fetch resource usage details for given tenantId
	 * it will fill total memory usage MB
	 * total no of VCPS used
	 * total local disk being used by VM of the allocated one
	 * @param tenantId
	 * @param model
	 * @return
	 */
	public CloudModel getResourceDetails(String tenantId, CloudModel model) {
		OSClientV3 os=generateAuthenticationToken(model);
		
		List<Server> serverList=(List<Server>) os.compute().servers().list();

		for(Server serverObject:serverList){
			if(serverObject.getTenantId().equals(tenantId)){
				SimpleTenantUsage usage=os.compute().quotaSets().getTenantUsage(serverObject.getTenantId());
				model.setTotalMemoryUsage(usage.getTotalMemoryMbUsage());
				model.setTotalVcpuUsage(usage.getTotalVcpusUsage());
				model.setTotalLocalGbUsage(usage.getTotalLocalGbUsage());
				break;
			}
		}
		return model;
	}

	
	/**
	 * Service is used to stop VM
	 * @param serverId
	 * @param model
	 * @return
	 */
	public boolean stopVM(String tenantId, CloudModel model) {
		OSClientV3 os=generateAuthenticationToken(model);
		
		List<Server> serverList=(List<Server>) os.compute().servers().list();

		for(Server serverObject:serverList){
			if(serverObject.getTenantId().equals(tenantId)){
				os.compute().servers().action(serverObject.getId(), Action.STOP);
				break;
			
			}
		}
		return true;
	}

	/**
	 * Service is used to authorize user on login if username password is correct it returns treu else false 
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean authorizeUser(String userName, String password) {
		// TODO Auto-generated method stub
		try{
		OSClientV3 os = OSFactory.builderV3()
		.endpoint("http://192.168.1.6/identity/v3")
		.credentials(userName,password,Identifier.byName("default"))
		.scopeToProject(Identifier.byId("6e43817c72bf48c28d2d8bef67382e10"))
		.authenticate();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}

	}
	public boolean startVM(String tenantId, CloudModel model) {
       
		
		OSClientV3 os=generateAuthenticationToken(model);
		List<Server> serverList=(List<Server>) os.compute().servers().list();

		for(Server serverObject:serverList){
			if(serverObject.getTenantId().equals(tenantId)){
				os.compute().servers().action(serverObject.getId(), Action.START);
				break;
			
			}
		}
		return true;
	}


}
