package Investor.service.project.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Investor.service.project.Entities.SavingsAccount;

public interface SavingsAccountRepo extends JpaRepository<SavingsAccount, Long>{

	Optional<SavingsAccount> findByInvestorId(String investorId);

}
