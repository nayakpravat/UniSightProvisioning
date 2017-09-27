package com.unisight.provision.controllers;

import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author c58783.
 *
 */
@Controller
public class RootController  {

	@RequestMapping(method = RequestMethod.POST, value = "/provision", headers = "accept=application/json")
	public ResponseEntity<Map> createProvision(@RequestBody Map<String,String> paramMap ,@RequestParam("provSrcName") String provSrcName) {
		return new ProvisionController().executeProvisioning(paramMap,provSrcName);
	}

	

}
