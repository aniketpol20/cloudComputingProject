package com.cloud.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.SimpleTenantUsage;
import org.openstack4j.openstack.OSFactory;
import com.cloud.model.CloudModel;

public class CloudService {

	private static final Logger logger = Logger.getLogger(CloudService.class);
    public static  final String adminprojectId="a7aa3bb3a5694e8c90ddc44007c6e4e4";
    public static  final String demoProjectId="4793beb7d69341d4a3f4f880334879d4";
    public static  final String keyStoneEndPoint="http://192.168.1.6/identity/v3";
    
	public CloudModel getAuthentication(CloudModel model) {
		  
	        try{
	        	
	        	OSClientV3 os = OSFactory.builderV3()
	        			.endpoint(keyStoneEndPoint)
	        			.credentials(model.getUser().getUserName(),model.getUser().getPassword(),Identifier.byName("default"))
	        			.scopeToProject(Identifier.byId(demoProjectId))
	        			.authenticate();
	        	model.setAuthenticated(true);
	        	return model;
	        }catch (Exception e) {
	        	model.setAuthenticated(false);
	        	if(logger.isDebugEnabled()){
	        		logger.debug("Error while authenticating", e);
	        	}
	        	return model;
			}
	             
	}
	private OSClientV3 generateAuthenticationToken(){
		try
		{
			OSClientV3 os = OSFactory.builderV3()
    			.endpoint(keyStoneEndPoint)
    			.credentials("admin", "123456",Identifier.byName("default"))
    			.scopeToProject(Identifier.byId(demoProjectId))
    			.authenticate();
			return os;
		}catch (Exception e) {
			// TODO: handle exception
			if(logger.isDebugEnabled()){
				logger.debug("Error occured while Authenticating user", e);
			}
			throw e;
		}
	}
	public CloudModel getListOfServers(){
			OSClientV3 os =generateAuthenticationToken();
			List<Server> server=(List<Server>) os.compute().servers().list();
			List<String> serverIds=new ArrayList<>();
	       
	       for(Server s:server){
	           serverIds.add(s.getId());
	        }
	       CloudModel model=new CloudModel();
	       model.setServerIds(serverIds);
		return model;
	}

	
	public CloudModel getServerDiagnostic(String serverid) {
		CloudModel model=new CloudModel();
		try
		{
			
			List<String> servers=new ArrayList<>();
			OSClientV3 os=generateAuthenticationToken();
			SimpleTenantUsage tenantUsageObj=os.compute().quotaSets().getTenantUsage(serverid);

			String ramUsage=tenantUsageObj.getTotalMemoryMbUsage().toString();
			String cpuUsage=tenantUsageObj.getTotalVcpusUsage().toString();
			String diskUsage=tenantUsageObj.getTotalLocalGbUsage().toString();
			//code to be written for calculating percentage of resource usage
			return model;
		}catch (Exception e) {
			// TODO: handle exception
			if(logger.isDebugEnabled()){
				logger.debug("Error occured while getting server diagnostic", e);
			}
			throw e;
		}
	
	}
	public boolean suspendUnUsedServer(String serverId) {
		try
		{
			OSClientV3 os=generateAuthenticationToken();
			os.compute().servers().action(serverId, Action.SUSPEND);
			return false;
		}catch (Exception e) {
			// TODO: handle exception
			if(logger.isDebugEnabled()){
				logger.debug("Error occured while suspending a server", e);
			}
			throw e;
		}
		
	}

}
