package Retirement.momentum.service.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Retirement.momentum.service.Entities.RetirementAccount;
import Retirement.momentum.service.Exceptions.RetirementAccountDuplicationExcetions;
import Retirement.momentum.service.Exceptions.RetirementAccountNotFoundException;
import Retirement.momentum.service.Repository.RetirementAccountRepo;

@Service
public class RetirementAccountService {
	
	

	@Autowired
	private RetirementAccountRepo retirementAccountRepo;
	
	
	public RetirementAccount saveAccount(RetirementAccount retirementAccount) {
		try {
			return retirementAccountRepo.save(retirementAccount);
		} catch (Exception e) {
			throw new RetirementAccountDuplicationExcetions("The Retirement Account of the same investor id: "+retirementAccount.getInvestorId()+" already exist");
		}
	}
	
	public RetirementAccount findByInvestorId(String investorId) {
        if(retirementAccountRepo.findByInvestorId(investorId).isPresent()) {
        	return retirementAccountRepo.findByInvestorId(investorId).get();
        }else {
			throw new RetirementAccountNotFoundException("Invalid Investor Id:"+investorId);
		}
		
	}
	
   public RetirementAccount update(String investorId, double balance) {
	   RetirementAccount account = findByInvestorId(investorId);
	   account.setBalance(balance);
	   System.out.println(account);
	   return retirementAccountRepo.save(account);
   }
	

}
