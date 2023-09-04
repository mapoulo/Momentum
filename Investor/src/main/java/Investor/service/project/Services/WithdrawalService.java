package Investor.service.project.Services;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Investor.service.project.Entities.Investor;
import Investor.service.project.Entities.RetirementAccount;
import Investor.service.project.Entities.Withdrawal;
import Investor.service.project.Exceptions.AgeLessThan65Exception;
import Investor.service.project.Exceptions.ExcessiveWithdrawalException;
import Investor.service.project.Exceptions.InsufficientAmountException;
import Investor.service.project.Repositories.WithdrawalRepo;

@Service
public class WithdrawalService {
	
	
	@Autowired
	private WithdrawalRepo withdrawalRepo;
	@Autowired
	private InvestorService investorService;
	@Autowired
	private RetirementAccountService retirementAccountService;
	
	
	public Withdrawal initiateinitiateWithdrawal(Withdrawal withdrawal) {
		
		
		
		Investor investor = investorService.findByInvestorId(withdrawal.getInvestorId());
		RetirementAccount retirementAccount = retirementAccountService.findByInvestorId(withdrawal.getInvestorId());
		
		
		Withdrawal myWithdrawal = new Withdrawal();
		myWithdrawal.setBalanceBeforeWithdrawal(retirementAccount.getBalance());
		myWithdrawal.setInvestorId(investor.getInvestorId());
		myWithdrawal.setWithdrawalDate(getCurrentDateAndTime());
		myWithdrawal.setProductType(withdrawal.getProductType());
		
		
        String firstFourNumbers = investor.getInvestorId().substring(0, Math.min(investor.getInvestorId().length(), 4));
        int investorsYear = Integer.parseInt(firstFourNumbers);
        Year currentYear = Year.now();
        int currentYearValue = currentYear.getValue();
        
        int investorsAge = currentYearValue - investorsYear;
        
        if(canWithdraw(withdrawal.getWithdrawalAmount(), retirementAccount.getBalance())) {
        	if(withdrawal.getProductType().equalsIgnoreCase("RetirementAccount")) {
            	if(isOlderThan65(investorsAge)) {
            		
            		if (isOverdrawn(withdrawal.getWithdrawalAmount(), retirementAccount.getBalance())) {
            			
            			long currentBalance = retirementAccount.getBalance() - withdrawal.getWithdrawalAmount();
            			retirementAccount.setBalance(currentBalance);
            			retirementAccountService.saveAccount(retirementAccount); 
            			
            			return withdrawalRepo.save(myWithdrawal);
    					
    				}else {
    					throw new InsufficientAmountException("Insufficient Amount");
    				}
            		
            	}else {
            		throw new AgeLessThan65Exception("You must be older than 65 to make this widrawal");
            	}
            }
        }else {
        	throw new ExcessiveWithdrawalException("Excessive withdrawal amount. You cannot withdraw more than 90% of your current balance.");
        }
        
        return new Withdrawal();
        
	}
	
	
	private boolean isOlderThan65(int age) {
		return age > 65 ? true : false;
	}
	
	private boolean isOverdrawn(long withdrawalAmount, long balanceAmount) {
		return withdrawalAmount > balanceAmount ? true : false;
	}
	
	  public boolean canWithdraw(long withdrawalAmount, long balance) {
	        // Calculate 90% of the current balance
	        long maxWithdrawalAmount = (long) (balance * 0.90);

	        // Check if the withdrawal amount is less than or equal to 90% of the current balance
	        return withdrawalAmount <= maxWithdrawalAmount;
	    }
	  
	  
	  public String getCurrentDateAndTime() {
		    LocalDateTime currentDateTime = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateTime = currentDateTime.format(formatter);
	        return formattedDateTime;
	  
	  }

}
