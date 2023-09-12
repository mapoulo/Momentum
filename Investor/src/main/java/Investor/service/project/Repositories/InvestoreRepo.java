package Investor.service.project.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Investor.service.project.Entities.Investor;

public interface InvestoreRepo extends JpaRepository<Investor, Long>{

	Optional<Investor>  findByinvestorId(String investorId);

}
