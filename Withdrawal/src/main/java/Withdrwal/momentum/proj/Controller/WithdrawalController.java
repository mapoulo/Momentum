package Withdrwal.momentum.proj.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Withdrwal.momentum.proj.Entities.Withdrawal;
import Withdrwal.momentum.proj.Service.WithdrawalService;

@RestController
@RequestMapping("/withdrawals")
public class WithdrawalController {
	
	@Autowired
	private WithdrawalService withdrawalService;
	
	@PostMapping("/initiateWithdrawal")
	public Withdrawal initiateWithdrawal(@RequestBody Withdrawal withdrawal) {
		return withdrawalService.initiateinitiateWithdrawal(withdrawal);
	}
	
	
	@GetMapping("/getAllWithdrawalsByInvestorId")
	public List<Withdrawal> getAllWithdrawalsByInvestorId(@RequestParam("investorId") String investorId){
		return withdrawalService.getAllWithdrawalsByInvestorId(investorId);
	}

}
