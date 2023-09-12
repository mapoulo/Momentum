package Saving.momentum.service.Entities;

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
public class SavingsAccount {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String accountType = "SavingsAccount";
	@Column(unique = true)
	private String investorId;
	private double balance;

}
