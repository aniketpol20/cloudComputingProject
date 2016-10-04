package com.cloud.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.model.CloudModel;
import com.cloud.service.CloudService;


@Controller
public class CloudController {

	CloudService service=new CloudService();
	private static final Logger logger = Logger.getLogger(CloudController.class);
	
	
	 @RequestMapping(value = "/login",method = RequestMethod.GET)
	 public String showLoginPage() {
			return "index";
	}
	
	@RequestMapping(value ="/authenticate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public CloudModel getAuthentication(@RequestBody CloudModel model) {
		return service.getAuthentication(model);
	}
	
	
	@RequestMapping(value ="/getListOfServers", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public CloudModel getListOfServers(@RequestBody CloudModel model) {
		return service.getListOfServers();
	}
	
	@RequestMapping(value ="/getListOfServers/{serverId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public CloudModel getServerDiagnostic(@PathVariable String serverId) {
		return service.getServerDiagnostic(serverId);
	}
	
	@RequestMapping(value ="/suspendServer/{serverId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public boolean suspendUnUsedServer(@RequestBody String serverId) {
		return service.suspendUnUsedServer(serverId);
	}
	
}
