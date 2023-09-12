package Withdrwal.momentum.proj.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingsAccount {
	
    private long id;
	private String accountType = "SavingsAccount";
	private String investorId;
	private double balance;

}
