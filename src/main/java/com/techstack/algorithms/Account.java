package com.techstack.algorithms;

public class Account {

    private final TransactionRepository transactionRepository;
    private final StatementPrinter statementPrinter;

    public Account(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.statementPrinter = statementPrinter;
        this.transactionRepository = transactionRepository;
    }

    public void deposit(int amount) {
        transactionRepository.addDeposit(amount);
    }

    public void withdraw(int amount) {
        this.transactionRepository.addWithdraw(amount);
    }

    public void printStatement() {
        statementPrinter.print(transactionRepository.getAllTransactions());
    }
}
