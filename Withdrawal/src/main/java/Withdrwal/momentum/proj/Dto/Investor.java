package Withdrwal.momentum.proj.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Investor {
	
	    private long id;
		
		private String name;
		private String surname;
		private String dateOfBirth;
		private String address;
		private String emailAddress;
		private String mobileNumber;
		private String investorId;
		private long retirement;
		private long savings;

}
