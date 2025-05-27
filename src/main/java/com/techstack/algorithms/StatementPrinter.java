package com.techstack.algorithms;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class StatementPrinter {

    private final ConsolePrinter consolePrinter;

    public StatementPrinter(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void print(List<Transaction> transactions) {
        consolePrinter.print("Date       || Amount || Balance");
        transactions.forEach(transaction ->
                consolePrinter.print(
                        String.format("%s || %d   || %d",
                                transaction.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                transaction.amount(),
                                transaction.balance()
                        )
                )
        );
    }
}
