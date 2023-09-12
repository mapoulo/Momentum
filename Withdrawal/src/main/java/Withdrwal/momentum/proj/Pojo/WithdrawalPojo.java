package Withdrwal.momentum.proj.Pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawalPojo {
	
	private double retirementAccountBalance;
	private String investorId;
	private String productType;
	private double withdrawalAmount;
	private double balanceBeforeWithdrawal;
	private String withdrawalDate;
	private double balanceAfterWithDrawal;

}
