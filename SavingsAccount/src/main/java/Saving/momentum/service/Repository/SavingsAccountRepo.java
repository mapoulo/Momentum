package Saving.momentum.service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Saving.momentum.service.Entities.SavingsAccount;

public interface SavingsAccountRepo extends JpaRepository<SavingsAccount, Long>{

	Optional<SavingsAccount> findByInvestorId(String investorId);

}
