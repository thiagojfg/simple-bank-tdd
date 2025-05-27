package com.techstack.algorithms;


import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class AccountServiceImpl implements AccountService {

    private final StatementPrinter statementPrinter;
    private final TransactionRepository transactionRepository;
    private int balance = 0;

    public AccountServiceImpl(StatementPrinter statementPrinter, TransactionRepository transactionRepository) {
        this.statementPrinter = statementPrinter;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void deposit(int amount) {
        balance+= amount;
        transactionRepository.add(new Transaction(transactionRepository.getCurrentDate(), amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        balance-= amount;
        transactionRepository.add(new Transaction(transactionRepository.getCurrentDate(), -amount, balance));
    }

    @Override
    public void printStatement() {
        statementPrinter.print("Date       || Amount || Balance");
        transactionRepository.getTransactions()
                .stream()
                .sorted(Comparator.comparing(Transaction::date).reversed())
                .forEach(transaction ->
                    statementPrinter.print(
                            String.format("%s || %d   || %d",
                                    transaction.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                    transaction.amount(),
                                    transaction.balance())
                    )
                );
    }
}
