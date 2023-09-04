package Investor.service.project.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Investor.service.project.Entities.Withdrawal;
import Investor.service.project.Services.WithdrawalService;

@RestController
@RequestMapping("/withdrawals")
public class WithdrawalController {
	
	@Autowired
	private WithdrawalService withdrawalService;
	
	@PostMapping("/initiateWithdrawal")
	public Withdrawal initiateWithdrawal(@RequestBody Withdrawal withdrawal) {
		return withdrawalService.initiateinitiateWithdrawal(withdrawal);
	}

}
