package net.javaguides.bankingapp.service;

import java.util.List;

import net.javaguides.bankingapp.Dto.AccountDto;
import net.javaguides.bankingapp.entity.Account;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(long id);
	
	AccountDto depositAmount(long id, double amount);
	
	AccountDto withDraw(long id, double amount);
	
	List<AccountDto>getAllAccounts();
	
	void deleteAccount(long id);

}
