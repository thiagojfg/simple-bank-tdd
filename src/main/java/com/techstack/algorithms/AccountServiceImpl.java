package com.techstack.algorithms;

public class AccountServiceImpl implements AccountService {

    private final TransactionRepository transactionRepository;
    private final StatementPrinter statementPrinter;

    public AccountServiceImpl(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.statementPrinter = statementPrinter;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void deposit(int amount) {
        transactionRepository.addDeposit(amount);
    }

    @Override
    public void withdraw(int amount) {
        this.transactionRepository.addWithdraw(amount);
    }

    @Override
    public void printStatement() {
        statementPrinter.print(transactionRepository.getAllTransactions());
    }
}
