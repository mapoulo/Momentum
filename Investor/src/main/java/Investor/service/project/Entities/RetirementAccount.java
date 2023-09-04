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
public class RetirementAccount {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String accountType = "RetirementAccount";
	@Column(unique = true)
	private String investorId;
	private long balance;

}
