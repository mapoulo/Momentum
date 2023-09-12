package Investor.service.project.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Investor.service.project.Entities.Investor;
import Investor.service.project.Exceptions.InvestorNotFoundException;
import Investor.service.project.Exceptions.UniqueFieldViolationException;
import Investor.service.project.Repositories.InvestoreRepo;

@Service
public class InvestorService {
	
	
	@Autowired
	private InvestoreRepo investorRepo;
	
	public Investor saveInvestor(Investor investor) {
		try {
			return investorRepo.save(investor);
		} catch (Exception e) {
		throw new UniqueFieldViolationException("User already exist");
		}
		
	}
	
	
	public List<Investor> getAllInvestors(){
		return investorRepo.findAll().stream().toList();
	}
	
	
	public Investor findByInvestorId(String investorId) {
		if(investorRepo.findByinvestorId(investorId).isPresent()) {
			return investorRepo.findByinvestorId(investorId).get();
		}else {
			throw new InvestorNotFoundException("Invalid Investor Id:"+investorId);
		}
		
	}
	


}
