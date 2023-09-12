package Saving.momentum.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Saving.momentum.service.Entities.SavingsAccount;
import Saving.momentum.service.Service.SavingsAccountService;

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
	public SavingsAccount getRetirementAccountById(@RequestParam("investorId") String investorId) {
		return savingsAccountService.findByInvestorId(investorId);
	} 
	
	@PostMapping("/update")
	public SavingsAccount update(@RequestParam("investorId") String investorId, @RequestParam("balance") double balance) {
		return savingsAccountService.update(investorId, balance);
	}


}
