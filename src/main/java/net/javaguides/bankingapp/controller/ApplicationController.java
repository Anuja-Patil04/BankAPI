package net.javaguides.bankingapp.controller;

import java.util.Map;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.bankingapp.Dto.AccountDto;
import net.javaguides.bankingapp.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class ApplicationController {
	
	private AccountService accountService;

	//contructor based dependency injection
	public ApplicationController(AccountService accountService) {
		this.accountService = accountService;
	}
	//@RequestBody map json to java object.
	
	//Add account
	@PostMapping
	public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);		
	}
	
	//Get account
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto>getAccountById(@PathVariable long id){
		 AccountDto account= accountService.getAccountById(id);	
		return ResponseEntity.ok(account);		
	}
	
	//Deposit amount
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto>depositAmount(@PathVariable long id ,@RequestBody Map<String ,Double>request){
		 AccountDto account= accountService.depositAmount(id, request.get("amount"));		
		return ResponseEntity.ok(account);		
	}
	
	//Withdraw amount
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto>withdrawAmount(@PathVariable long id ,@RequestBody Map<String ,Double>request){
		AccountDto account= accountService.withDraw(id, request.get("amount"));
		return ResponseEntity.ok(account);
	}
	
	@GetMapping()
	public ResponseEntity<List<AccountDto>>getAllAccounts(){
		List<AccountDto>accounts =accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account deleted successfully");
	}
	
	
	
	
	

}
