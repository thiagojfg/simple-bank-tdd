package com.techstack.algorithms;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StatementPrinter {

    public static final String HEADER = "Date       || Amount || Balance";
    public static final String LINE_FORMAT = "%s || %d   || %d";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    private final ConsolePrinter consolePrinter;

    public StatementPrinter(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void print(List<Transaction> transactions) {
        consolePrinter.print(HEADER);
        AtomicInteger cumulativeBalance = new AtomicInteger(0);
        List<String> statementLines = transactions.stream()
                .map(transaction -> format(transaction, cumulativeBalance))
                .toList();
        statementLines.reversed().forEach(consolePrinter::print);
    }

    private static String format(Transaction transaction, AtomicInteger balance) {
        return String.format(LINE_FORMAT,
                transaction.date().format(DateTimeFormatter.ofPattern(DD_MM_YYYY)),
                transaction.amount(),
                balance.addAndGet(transaction.amount())
        );
    }
}
