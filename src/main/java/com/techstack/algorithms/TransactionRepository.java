package com.techstack.algorithms;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class TransactionRepository {

    private final LinkedList<Transaction> transactions = new LinkedList<>();

    public void addDeposit(int amount) {
        Transaction head = transactions.isEmpty() ? null : transactions.getFirst();
        transactions.addFirst(new Transaction(getCurrentDate(), amount, head == null ? amount : head.balance() + amount));
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public void addWithdraw(int amount) {
        Transaction head = transactions.isEmpty() ? null : transactions.getFirst();
        transactions.addFirst(new Transaction(getCurrentDate(), -amount, head == null ? -amount : head.balance() - amount));
    }
}
