package Withdrwal.momentum.proj.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import Withdrwal.momentum.proj.Dto.Investor;
import Withdrwal.momentum.proj.Dto.RetirementAccount;
import Withdrwal.momentum.proj.Dto.SavingsAccount;
import Withdrwal.momentum.proj.Entities.Withdrawal;
import Withdrwal.momentum.proj.Exception.AgeLessThan65Exception;
import Withdrwal.momentum.proj.Exception.ExcessiveWithdrawalException;
import Withdrwal.momentum.proj.Exception.InsufficientAmountException;
import Withdrwal.momentum.proj.Pojo.WithdrawalPojo;
import Withdrwal.momentum.proj.Repository.WithdrawalRepo;
import lombok.With;
import reactor.core.publisher.Mono;

@Service
public class WithdrawalService {
	
	
	@Autowired
	private WithdrawalRepo withdrawalRepo;

	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	
	public List<Withdrawal> getAllWithdrawalsByInvestorId(String investorId){
		return withdrawalRepo.findAll().stream().filter(value -> value.getInvestorId().equals(investorId)).toList();
	}
	
	
	
	
	public Withdrawal initiateinitiateWithdrawal(Withdrawal withdrawal) {
		
		RetirementAccount retirementAccount = getRetirementAccountByInvestorId(withdrawal.getInvestorId());
		SavingsAccount savingsAccount = getSavingsAccountByInvestorId(withdrawal.getInvestorId());

		
		Withdrawal withdrawaPojo = createWithdrawal(WithdrawalPojo.builder().retirementAccountBalance(retirementAccount.getBalance()).investorId(withdrawal.getInvestorId()).productType(withdrawal.getProductType()).withdrawalAmount(withdrawal.getWithdrawalAmount()).build() );
	 
		if(isRetirement(withdrawal.getProductType())) {
			return processRetirementWithdrawal(withdrawaPojo, retirementAccount);
		}else {
			return processSavingsWithdrawal(withdrawaPojo, savingsAccount);
		}
		
		
	}
	
	
	public Withdrawal processSavingsWithdrawal(Withdrawal withdrawal, SavingsAccount savingsAccount) {
         if(isWithdrawalAmountExceedingLimit(withdrawal.getWithdrawalAmount(),savingsAccount.getBalance() )) {
			if(isWithdrawalAmountGreaterThanBalance(withdrawal.getWithdrawalAmount(),savingsAccount.getBalance() )) {
				Withdrawal withdrawaPojo = createWithdrawal(WithdrawalPojo.builder().retirementAccountBalance(savingsAccount.getBalance()).investorId(withdrawal.getInvestorId()).productType(withdrawal.getProductType()).withdrawalAmount(withdrawal.getWithdrawalAmount()).build() );
                SavingsAccount mySavingsAccount = savingsAccount;
                mySavingsAccount.setBalance(mySavingsAccount.getBalance() - withdrawal.getWithdrawalAmount());
                updateSavingsAccount(mySavingsAccount.getInvestorId(),mySavingsAccount.getBalance());
				return withdrawalRepo.save(withdrawaPojo);
			}else {
				throw new InsufficientAmountException("Insufficient Amount");
			}
			
			
		}else {
			throw new ExcessiveWithdrawalException("Can't withdraw more than 90% of the current balance R"+savingsAccount.getBalance()+"====="+withdrawal.getWithdrawalAmount());
		}
	}
	
	
	
	
	
	
	
	public Withdrawal processRetirementWithdrawal(Withdrawal withdrawal, RetirementAccount retirementAccount) {
		if(isWithdrawalAmountExceedingLimit(withdrawal.getWithdrawalAmount(),retirementAccount.getBalance() )) {
			if(isWithdrawalAmountGreaterThanBalance(withdrawal.getWithdrawalAmount(),retirementAccount.getBalance() )) {
				Investor investor = getInvestorByInvestorId(withdrawal.getInvestorId());
				if(calculateInvestorAge(investor.getDateOfBirth())>65) {
					Withdrawal withdrawaPojo = createWithdrawal(WithdrawalPojo.builder().retirementAccountBalance(retirementAccount.getBalance()).investorId(withdrawal.getInvestorId()).productType(withdrawal.getProductType()).withdrawalAmount(withdrawal.getWithdrawalAmount()).build() );
                   RetirementAccount myRetirementAccount = retirementAccount;
                   myRetirementAccount.setBalance(retirementAccount.getBalance() - withdrawal.getWithdrawalAmount());
                   updateRetirementAccount(myRetirementAccount.getInvestorId(),myRetirementAccount.getBalance());
					return withdrawalRepo.save(withdrawaPojo);
				}else {
					throw new AgeLessThan65Exception("You must be older than 65 to make a retirement withdrawal (Retirement)");
				}
				
			}else {
				throw new InsufficientAmountException("Insufficient Amount (Retirement)");
			}
			
			
		}else {
			throw new ExcessiveWithdrawalException(" (Retirement)  Can't withdraw more than 90% of the current balance R"+retirementAccount.getBalance()+"====="+withdrawal.getWithdrawalAmount());
		}
	}
	
	
	
	public Investor getInvestorByInvestorId(String investorId) {
		Investor investor = null;
		try {
			 investor = webClientBuilder.build().get()
						.uri("http://INVESTOR-SERVICE/investor/getInvestorByInvestorId",
								urlBuilder -> urlBuilder.queryParam("investorId", investorId).build())
						.retrieve().bodyToMono(Investor.class).block();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return investor;
	}
	
	
	
	
	
	public RetirementAccount getRetirementAccountByInvestorId(String investorId) {
		RetirementAccount retirementAccount = null;
		try {
			 retirementAccount = webClientBuilder.build().get()
						.uri("http://RETIREMENT-SERVICE/retirementAccount/getRetirementAccountById",
								urlBuilder -> urlBuilder.queryParam("investorId", investorId).build())
						.retrieve().bodyToMono(RetirementAccount.class).block();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retirementAccount;
	}
	
	
	public SavingsAccount getSavingsAccountByInvestorId(String investorId) {
		SavingsAccount savingsAccount = null;
		try {
			savingsAccount = webClientBuilder.build().get()
						.uri("http://SAVINGS-SERVICE/savings/getSavingsAccountById",
								urlBuilder -> urlBuilder.queryParam("investorId", investorId).build())
						.retrieve().bodyToMono(SavingsAccount.class).block();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savingsAccount;
	}
	
	
	
	
	public void updateRetirementAccount(String investorId, double balance) {
		try {
			        webClientBuilder.build().post()
						.uri("http://RETIREMENT-SERVICE/retirementAccount/update",
								urlBuilder -> urlBuilder.queryParam("investorId", investorId).queryParam("balance", balance).build())
						.retrieve().bodyToMono(RetirementAccount.class).block();
	            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void updateSavingsAccount(String investorId, double balance) {
		try {
			        webClientBuilder.build().post()
						.uri("http://SAVINGS-SERVICE/savings/update",
								urlBuilder -> urlBuilder.queryParam("investorId", investorId).queryParam("balance", balance).build())
						.retrieve().bodyToMono(SavingsAccount.class).block();
	            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
 public Withdrawal createWithdrawal(WithdrawalPojo w) {
	 Withdrawal myWithdrawal = new Withdrawal();
		myWithdrawal.setBalanceBeforeWithdrawal(w.getRetirementAccountBalance());
		myWithdrawal.setInvestorId(w.getInvestorId());
		myWithdrawal.setWithdrawalDate(getCurrentDateAndTime());
		myWithdrawal.setProductType(w.getProductType());
        myWithdrawal.setWithdrawalAmount(w.getWithdrawalAmount());		
		
		return myWithdrawal;
 }
	
	
	
 public int calculateInvestorAge(String investorDateOfBirth) {
	 
	 String firstFourNumbers = investorDateOfBirth.substring(0, Math.min(investorDateOfBirth.length(), 4));
     int investorsYear = Integer.parseInt(firstFourNumbers);
     Year currentYear = Year.now();
     int currentYearValue = currentYear.getValue();
     
     int investorsAge = currentYearValue - investorsYear;
     
     return investorsAge;
 }	
	
	
	
 public boolean isRetirement(String productType) {
	 return productType.equals("RetirementAccount") ? true : false;
 }
 
 
 
 
 
 public boolean ageIsGreaterThan65(int investorsAge) {
	 return investorsAge > 65 ? true : false;
 }
 
 
 public boolean isWithdrawalAmountGreaterThanBalance(double withdrawal, double balance) {
	 return withdrawal < balance ? true : false;
 }
 
 
 
 public boolean isWithdrawalAmountExceedingLimit(double withdrawalAmount, double currentBalance) {
	    double withdrawalLimit = 0.9 * currentBalance; // 90% of the current balance
	    return withdrawalAmount < withdrawalLimit;
	}
	
	
	
public double calculateBalanceAfterWithdrawal(double currentBalance, double withdrawalAmount) {
	return (currentBalance - withdrawalAmount);
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Withdrawal initiateinitiateWithdrawal1(Withdrawal withdrawal) {
		
		Investor investor = null;
		RetirementAccount retirementAccount = null;
		try {
			
			  investor = webClientBuilder.build().get()
					.uri("http://INVESTOR-SERVICE/investor/getInvestorByInvestorId",
							urlBuilder -> urlBuilder.queryParam("investorId", withdrawal.getInvestorId()).build())
					.retrieve().bodyToMono(Investor.class).block();
					
					
			   retirementAccount = webClientBuilder.build().get()
					.uri("http://RETIREMENT-SERVICE/retirementAccount/getRetirementAccountById",
							urlBuilder -> urlBuilder.queryParam("investorId", withdrawal.getInvestorId()).build())
					.retrieve().bodyToMono(RetirementAccount.class).block();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		Withdrawal myWithdrawal = new Withdrawal();
		myWithdrawal.setBalanceBeforeWithdrawal(retirementAccount.getBalance());
		myWithdrawal.setInvestorId(investor.getInvestorId());
		myWithdrawal.setWithdrawalDate(getCurrentDateAndTime());
		myWithdrawal.setProductType(withdrawal.getProductType());

		
        String firstFourNumbers = investor.getDateOfBirth().substring(0, Math.min(investor.getInvestorId().length(), 4));
        int investorsYear = Integer.parseInt(firstFourNumbers);
        Year currentYear = Year.now();
        int currentYearValue = currentYear.getValue();
        
        int investorsAge = currentYearValue - investorsYear;
       
        
        if(canWithdraw(withdrawal.getWithdrawalAmount(), retirementAccount.getBalance())) {
        	if(withdrawal.getProductType().equalsIgnoreCase("RetirementAccount")) {
            	if(isOlderThan65(investorsAge)) {
            		
            		if (isOverdrawn(withdrawal.getWithdrawalAmount(), retirementAccount.getBalance())) {
            			
            			double currentBalance = retirementAccount.getBalance() - withdrawal.getWithdrawalAmount();
            			retirementAccount.setBalance(currentBalance);
//            			retirementAccountService.saveAccount(retirementAccount);
            			webClientBuilder.build().patch()
        				.uri("http://RETIREMENT-SERVICE/retirementAccount/addRetirementAccout")
        				.body(Mono.just(retirementAccount), RetirementAccount.class)
                        .retrieve()
                        .bodyToMono(RetirementAccount.class);
            			
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
	
	private boolean isOverdrawn(double withdrawalAmount, double balanceAmount) {
		return withdrawalAmount > balanceAmount ? true : false;
	}
	
	  public boolean canWithdraw(double withdrawalAmount, double balance) {
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
