package Investor.service.project.Controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import Investor.service.project.Entities.Investor;
import Investor.service.project.Services.InvestorService;
import Investor.service.project.dto.AccountDto;
import Investor.service.project.dto.RetirementAccount;
import Investor.service.project.dto.SavingsAccount;

@RestController
@RequestMapping("/investor")
public class InvestorController {
	
	
	@Autowired
	private InvestorService investorService;

	
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	
	@PostMapping("/saveInvestor")
	public Investor saveInvestor(@RequestBody Investor investor) {
		return investorService.saveInvestor(investor);
	}
	
	
	@GetMapping("/")
	public List<Investor> getAllInvestors(){
		return investorService.getAllInvestors();
	}
	
	@GetMapping("/getAllMyAccounts")
	public List<AccountDto> getAllMyAccounts(@RequestParam("investorId") String investorId){
		
		 RetirementAccount retirementAccount = null;
		 SavingsAccount savingsAccount = null;
		try {
			
			  retirementAccount = webClientBuilder.build().get()
						.uri("http://RETIREMENT-SERVICE/retirementAccount/getRetirementAccountById",
								urlBuilder -> urlBuilder.queryParam("investorId", investorId).build())
						.retrieve().bodyToMono(RetirementAccount.class).block();
						
						
			   savingsAccount = webClientBuilder.build().get()
						.uri("http://SAVINGS-SERVICE/savings/getSavingsAccountById",
								urlBuilder -> urlBuilder.queryParam("investorId", investorId).build())
						.retrieve().bodyToMono(SavingsAccount.class).block();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	 
		
	  AccountDto dto1 = new AccountDto().builder()
			  .accountType(retirementAccount.getAccountType())
			  .balance(retirementAccount.getBalance())
			  .investorId(investorId)
			  .productId(retirementAccount.getId())
			  .build();
	  
	  AccountDto dto2 = new AccountDto().builder()
			  .accountType(savingsAccount.getAccountType())
			  .balance(savingsAccount.getBalance())
			  .investorId(investorId)
			  .productId(savingsAccount.getId())
			  .build();
	  
	  return Arrays.asList(dto1,dto2);
	}
	
	
	@GetMapping("/getInvestorByInvestorId")
	public Investor getInvestorByInvestorId(@RequestParam("investorId") String investorId) {
		return investorService.findByInvestorId(investorId);
	}
	


}
