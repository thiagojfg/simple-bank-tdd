package com.techstack.algorithms;

import java.util.LinkedList;
import java.util.List;

public class TransactionRepository {

    private final LinkedList<Transaction> transactions = new LinkedList<>();
    private final Clock clock;

    public TransactionRepository(Clock clock) {
        this.clock = clock;
    }

    public void addDeposit(int amount) {
        Transaction head = transactions.isEmpty() ? null : transactions.getFirst();
        transactions.addFirst(new Transaction(clock.today(), amount, head == null ? amount : head.balance() + amount));
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public void addWithdraw(int amount) {
        Transaction head = transactions.isEmpty() ? null : transactions.getFirst();
        transactions.addFirst(new Transaction(clock.today(), -amount, head == null ? -amount : head.balance() - amount));
    }
}
