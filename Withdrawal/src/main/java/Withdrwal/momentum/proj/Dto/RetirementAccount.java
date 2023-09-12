package Withdrwal.momentum.proj.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetirementAccount {
	
	
    private long id;
	private String accountType = "RetirementAccount";
	private String investorId;
	private double balance;

}
