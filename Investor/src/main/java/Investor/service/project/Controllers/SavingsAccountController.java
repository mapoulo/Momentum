package Investor.service.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Investor.service.project.Entities.SavingsAccount;
import Investor.service.project.Services.SavingsAccountService;

@RestController
@RequestMapping("/savings")
public class SavingsAccountController {
	
	
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	
	@PostMapping("/addSavingsAccount")
	public SavingsAccount saveAccount(@RequestBody SavingsAccount retirementAccount) {
		return savingsAccountService.saveAccount(retirementAccount);
	}
	
	@GetMapping("/getSavingsAccountById")
	public SavingsAccount getRetirementAccountById(@RequestParam("id") String id) {
		return savingsAccountService.findByInvestorId(id);
	} 


}
