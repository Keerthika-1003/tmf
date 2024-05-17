//package com.tmf.controller;
//
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
////import org.springframework.web.service.annotation.DeleteExchange;
////import org.springframework.web.service.annotation.PatchExchange;
//
//
//import com.tmf.entity.SettlementAccount;
//import com.tmf.repo.SettlementAccountRepo;
//import com.tmf.repo.SettlementAccountRepository;
//
//@RestController
//@RequestMapping("/tmf-api/accountManagement/v4/settlementAccount")
//public class SettlementController {
//
//    @Autowired
//    private SettlementAccountRepository settlementAccountRepo;
//    
//    @PostMapping("/createSettlementAccount")
//    public String createsettlementAccount (@RequestBody SettlementAccount settlementAccount) {
//    	settlementAccountRepo.save(settlementAccount);
//    	return "Created";
//    }
//    
//    
//    
//
//    @GetMapping("/listSettlementAccount")
//    public ResponseEntity<List<SettlementAccount>> listsettlementAccount() {
//        List<SettlementAccount> settlementAccount = settlementAccountRepo.findAll();
//        return new ResponseEntity<>(settlementAccount, HttpStatus.OK);
//    }
//
//    @GetMapping("/settlementAccount/{id}")
//    public SettlementAccount retrieveSettlementAccount(@PathVariable Long id) {
//    	return settlementAccountRepo.getById(id);
//    }
//    
//    @DeleteMapping("/settlementAccount/{id}")
//    public String deletesettlementAccount(@PathVariable Long id) {
//    	settlementAccountRepo.deleteById(id);
//    	return "Deleted";
//    }
//}
package com.tmf.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmf.entity.SettlementAccount;
import com.tmf.repo.SettlementAccountRepository;
@RequestMapping("/tmf-api/accountManagement/v4")
@RestController
public class SettlementController {

	@Autowired
	private SettlementAccountRepository settlementAccountRepository;

	@GetMapping("/getSettlementAccount")
	public ResponseEntity<List<SettlementAccount>> listSettlementAccount(
			@RequestParam(required = false) String fields, @RequestParam(defaultValue = "0") int offset,
			@RequestParam(defaultValue = "10") int limit) {
		Pageable pageable = PageRequest.of(offset, limit);
		Page<SettlementAccount> page = settlementAccountRepository.findAll(pageable);
		return ResponseEntity.ok().header("X-Result-Count", String.valueOf(page.getNumberOfElements()))
				.header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.getContent());
	}

	@PostMapping
	public String createSettlementAccount(@RequestBody SettlementAccount settlementAccount) {
		settlementAccountRepository.save(settlementAccount);
		return "created";
	}

	@GetMapping("/settlementAccount/{id}")
	public SettlementAccount retrieveSettlementAccount(@PathVariable Long id) {
		return settlementAccountRepository.findById(id).get();
	}

	@DeleteMapping("/deleteSettlementAccount/{id}")
	public String deleteSettlementAccount(@PathVariable Long id) {
		settlementAccountRepository.deleteById(id);
		return "Deleted";
	}

	@PutMapping("/{id}")
	public ResponseEntity<SettlementAccount> updateSettlementAccount(@PathVariable Long id,
			@RequestBody SettlementAccount updatedSettlementAccount) {
		return settlementAccountRepository.findById(id).map(settlementAccount -> {
			settlementAccount.setAccountNo(updatedSettlementAccount.getAccountNo());
			settlementAccount.setDescription(updatedSettlementAccount.getDescription());
//			settlementAccount.setLastModifiedDate(LocalDateTime.now());

			SettlementAccount savedSettlementAccount = settlementAccountRepository.save(settlementAccount);
//					.save(billingCycleSpecification);

			return ResponseEntity.ok(savedSettlementAccount);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
