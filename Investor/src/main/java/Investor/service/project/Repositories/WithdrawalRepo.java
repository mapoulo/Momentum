package Investor.service.project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Investor.service.project.Entities.Withdrawal;

public interface WithdrawalRepo extends JpaRepository<Withdrawal, Long> {

}
