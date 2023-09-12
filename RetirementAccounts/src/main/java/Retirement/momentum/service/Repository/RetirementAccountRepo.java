package Retirement.momentum.service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Retirement.momentum.service.Entities.RetirementAccount;

public interface RetirementAccountRepo extends JpaRepository<RetirementAccount, Long> {
    
	 Optional<RetirementAccount>  findByInvestorId(String investorId);

}
