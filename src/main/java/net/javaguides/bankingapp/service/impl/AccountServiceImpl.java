package net.javaguides.bankingapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javaguides.bankingapp.Dto.AccountDto;
import net.javaguides.bankingapp.entity.Account;
import net.javaguides.bankingapp.mapper.AccountMapper;
import net.javaguides.bankingapp.repository.AccountRepository;
import net.javaguides.bankingapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	// whenever spring find single constructor in bean , it will automatically
	// inject dependency .so no need to declare
	// autowired

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {

		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(long id) {

		Account accont = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));
		return AccountMapper.mapToAccountDto(accont);
	}

	@Override
	public AccountDto depositAmount(long id, double amount) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));

		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withDraw(long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));

		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient amount");
		}
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		List<AccountDto> accountDto = accounts.stream().map(acc -> AccountMapper.mapToAccountDto(acc))
				.collect(Collectors.toList());
		return accountDto;
	}

	@Override
	public void deleteAccount(long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("account does not exist"));
		accountRepository.deleteById(id);

	}

}
