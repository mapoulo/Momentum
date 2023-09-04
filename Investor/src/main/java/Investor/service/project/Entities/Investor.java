package Investor.service.project.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Investor {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	private String name;
	private String surname;
	private String dateOfBirth;
	private String address;
	private String emailAddress;
	private String mobileNumber;
	@Column(unique = true)
	private String investorId;
	private long retirement;
	private long savings;
	
	

}
