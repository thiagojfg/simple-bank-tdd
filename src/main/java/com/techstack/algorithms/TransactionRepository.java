package com.techstack.algorithms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
