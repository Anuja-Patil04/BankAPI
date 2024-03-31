package net.javaguides.bankingapp.Dto;

import java.util.Objects;

import lombok.Data;

//data anotation automatically generate constructors and getter setters

public class AccountDto {

	private long id;
	private String accountHolderName;
	private double balance;
	public AccountDto(long id, String accountHolderName, double balance) {
		super();
		this.id = id;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountHolderName, balance, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountDto other = (AccountDto) obj;
		return Objects.equals(accountHolderName, other.accountHolderName)
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance) && id == other.id;
	}
	@Override
	public String toString() {
		return "AccountDto [id=" + id + ", accountHolderName=" + accountHolderName + ", balance=" + balance + "]";
	}
	
	
	

}
