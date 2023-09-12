package Investor.service.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetirementAccount {
	
	
    private long id;
	private String accountType;
	private String investorId;
	private long balance;

}
