package Investor.service.project.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Investor.service.project.Entities.RetirementAccount;

public interface RetirementAccountRepo extends JpaRepository<RetirementAccount, Long> {

	 Optional<RetirementAccount>  findByInvestorId(String investorId);

}
