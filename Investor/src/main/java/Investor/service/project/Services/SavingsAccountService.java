package Investor.service.project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Investor.service.project.Entities.RetirementAccount;
import Investor.service.project.Entities.SavingsAccount;
import Investor.service.project.Exceptions.RetirementAccountDuplicationExcetions;
import Investor.service.project.Exceptions.RetirementAccountNotFoundException;
import Investor.service.project.Exceptions.SavingsAccountDuplicationExcetion;
import Investor.service.project.Exceptions.SavingsAccountNotFoundException;
import Investor.service.project.Repositories.SavingsAccountRepo;

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

}
