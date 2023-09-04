package Investor.service.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Investor.service.project.Entities.RetirementAccount;
import Investor.service.project.Services.RetirementAccountService;

@RestController
@RequestMapping("/retirementAccount")
public class RetirementAccountController {
	
	
	@Autowired
	private RetirementAccountService retirementAccountService;
	
	
	@PostMapping("/addRetirementAccout")
	public RetirementAccount saveAccount(@RequestBody RetirementAccount retirementAccount) {
		return retirementAccountService.saveAccount(retirementAccount);
	}
	
	@GetMapping("/getRetirementAccountById")
	public RetirementAccount getRetirementAccountById(@RequestParam("id") String id) {
		return retirementAccountService.findByInvestorId(id);
	} 

}
