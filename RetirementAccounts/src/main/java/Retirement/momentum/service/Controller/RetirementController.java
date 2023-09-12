package Retirement.momentum.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Retirement.momentum.service.Entities.RetirementAccount;
import Retirement.momentum.service.Services.RetirementAccountService;

@RestController
@RequestMapping("/retirementAccount")
public class RetirementController {
	

	@Autowired
	private RetirementAccountService retirementAccountService;
	
	
	@PostMapping("/addRetirementAccout")
	public RetirementAccount saveAccount(@RequestBody RetirementAccount retirementAccount) {
		return retirementAccountService.saveAccount(retirementAccount);
	}
	
	@GetMapping("/getRetirementAccountById")
	public RetirementAccount getRetirementAccountById(@RequestParam("investorId") String investorId) {
		return retirementAccountService.findByInvestorId(investorId);
	} 
	
	
	@PostMapping("/update")
	public RetirementAccount update(@RequestParam("investorId") String investorId, @RequestParam("balance") double balance) {
		return retirementAccountService.update(investorId, balance);
	} 


}
