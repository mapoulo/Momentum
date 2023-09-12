package Saving.momentum.service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Saving.momentum.service.Entities.SavingsAccount;
import Saving.momentum.service.Exceptions.SavingsAccountDuplicationExcetion;
import Saving.momentum.service.Exceptions.SavingsAccountNotFoundException;
import Saving.momentum.service.Repository.SavingsAccountRepo;

@Service
public class SavingsAccountService {
	
	
	@Autowired
	private SavingsAccountRepo savingsAccountRepo;
	
	
	public SavingsAccount saveAccount(SavingsAccount savingsAccount) {
		try {
			return savingsAccountRepo.save(savingsAccount);
		} catch (Exception e) {
			throw new SavingsAccountDuplicationExcetion("The Savings Account of the same investor id: "+savingsAccount.getInvestorId()+" already exist");
		}
	}
	
	
	
	public SavingsAccount findByInvestorId(String investorId) {
        if(savingsAccountRepo.findByInvestorId(investorId).isPresent()) {
        	return savingsAccountRepo.findByInvestorId(investorId).get();
        }else {
			throw new SavingsAccountNotFoundException("Saving account with investor id:"+investorId+" not found");
		}
		
	}
	
	 public SavingsAccount update(String investorId, double balance) {
		 SavingsAccount account = findByInvestorId(investorId);
		   account.setBalance(balance);
		   System.out.println(account);
		   return savingsAccountRepo.save(account);
	   }

}
