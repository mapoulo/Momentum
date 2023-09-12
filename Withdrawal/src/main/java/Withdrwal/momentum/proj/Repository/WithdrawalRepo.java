package Withdrwal.momentum.proj.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Withdrwal.momentum.proj.Entities.Withdrawal;

public interface WithdrawalRepo extends JpaRepository<Withdrawal, Long> {

}
