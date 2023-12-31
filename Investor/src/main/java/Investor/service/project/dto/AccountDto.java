package Investor.service.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
	
	private long productId;
	private String accountType;
	private String investorId;
	private long balance;

}
