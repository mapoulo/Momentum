package Investor.service.project.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Investor.service.project.Entities.RetirementAccount;
import Investor.service.project.Exceptions.RetirementAccountDuplicationExcetions;
import Investor.service.project.Exceptions.RetirementAccountNotFoundException;
import Investor.service.project.Repositories.RetirementAccountRepo;

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
	

}
