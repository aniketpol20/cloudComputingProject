package com.cloud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.SimpleTenantUsage;
import org.openstack4j.openstack.OSFactory;

import com.cloud.model.CloudModel;
import com.cloud.model.Diagnostic;



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
    			.endpoint(keyStoneEndPoint)
    			.credentials(model.getUser().getUserName(),model.getUser().getPassword(),Identifier.byName("default"))
    			.scopeToProject(Identifier.byId(adminprojectId))
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
		int totalAvailable=512;
		int totapVCPS=8;
		int totalDisk=40;
		List<Server> serverList=(List<Server>) os.compute().servers().list();

		for(Server serverObject:serverList){
			if(serverObject.getTenantId().equals(tenantId)){
				SimpleTenantUsage usage=os.compute().quotaSets().getTenantUsage(serverObject.getTenantId());
				
				Diagnostic diag=new Diagnostic();
				HashMap<String, Integer> ramusage=new HashMap<>();
				HashMap<String, Integer> vcpusused=new HashMap<>();
				HashMap<String, Integer>  diskusage=new HashMap<>();
				ramusage.put("used",Integer.parseInt(usage.getTotalMemoryMbUsage().toString()));
				ramusage.put("remain",totalAvailable-Integer.parseInt(usage.getTotalMemoryMbUsage().toString()));
				vcpusused.put("used",Integer.parseInt(usage.getTotalVcpusUsage().toString()));
				vcpusused.put("remain",totapVCPS-Integer.parseInt(usage.getTotalMemoryMbUsage().toString()));
				diskusage.put("used",Integer.parseInt(usage.getTotalLocalGbUsage().toString()));
				diskusage.put("remain",totalDisk-Integer.parseInt(usage.getTotalMemoryMbUsage().toString()));
				diag.setRamUsage(ramusage);
				diag.setCpuUsage(vcpusused);
				diag.setDiskUsage(diskusage);
				
				model.setDiagnostic(diag);
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
		.endpoint(keyStoneEndPoint)
		.credentials(userName,password,Identifier.byName("default"))
		.scopeToProject(Identifier.byId(adminprojectId))
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
