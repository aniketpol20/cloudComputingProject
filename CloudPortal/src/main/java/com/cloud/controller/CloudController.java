package com.cloud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.model.CloudModel;
import com.cloud.service.CloudService;

/**
 * Cloud Controller is used to expose RESTAPIS to the client to fetch resource information from 
 *  dev stack cloud environment.
 * @author aniket
 *
 */
@Controller
public class CloudController {

	CloudService service=new CloudService();
	

	/**
	 * API is used to return the new view to the UI 
	 * @return
	 */
	@RequestMapping(value="/authorize")
	public String authorize(){
		return "ListVMs";
		
	}	
	
	
	/**
	 * API is used to get resource usage information from the cloud for a given tentantUd
	 * @param tenantId
	 * @return
	 */
	@RequestMapping(value="/getResourceDetails/{tenantId}",method=RequestMethod.POST)
	@ResponseBody
	public CloudModel getResourceDetails(@PathVariable String tenantId){
		CloudModel model=new CloudModel();
		return service.getResourceDetails(tenantId,model);
	}
	
	
	/**
	 * API is used to stop VM which is passed in a parameter
	 * @param serverId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/suspendServer/{tenantId}",method=RequestMethod.POST)
	@ResponseBody
	public boolean suspendVM(@PathVariable String tenantId){
		CloudModel model=new CloudModel();
		return service.stopVM(tenantId,model);
	}
	
	
	@RequestMapping(value="/startServer/{tenantId}",method=RequestMethod.POST)
	@ResponseBody
	public boolean startVM(@PathVariable String tenantId){
		CloudModel model=new CloudModel();
		return service.startVM(tenantId,model);
	}
	
	
	/**
	 * API is used to fetch list of VMs and their configuration details
	 * @return
	 */
	@RequestMapping(value ="/getListOfServers", method = RequestMethod.POST)
	@ResponseBody
	public CloudModel getListOfServers() {
		 CloudModel model =new CloudModel();
		return service.getListOfServers(model);
	}

	@RequestMapping(value={"/","/logout"})
	public String welcome(){
		return "index";
	}
	
	
	@RequestMapping(value="/ListVMs")
	public String vmlists(HttpServletRequest request ){
		String userName=request.getParameter("username").toString();
		String password=request.getParameter("password").toString();
		boolean flag= service.authorizeUser(userName,password);
		 if(flag)
			return "ListVMs";
		 return "index";
	}
	@RequestMapping(value="/showVMLists")
	public String vmlists( ){
			return "ListVMs";
	}
	
	@RequestMapping(value="/Pie")
	public ModelAndView pieCharts(HttpServletRequest request){
		String tenantId=request.getParameter("id").toString();
		ModelAndView mv=new ModelAndView("Pie");
		mv.addObject("tenantId",tenantId);
		return mv;
	}
}
