package com.techstack.algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

    @Mock
    private ConsolePrinter consolePrinter;

    @Spy
    private TransactionRepository transactionRepository;

    @Test
    public void should_print_statement_after_transactions() {

        doReturn(LocalDate.of(2012, 1, 10))
                .doReturn(LocalDate.of(2012, 1, 13))
                .doReturn(LocalDate.of(2012, 1, 14))
                .when(transactionRepository)
                .getCurrentDate();

        AccountService accountService = new AccountServiceImpl(transactionRepository, new StatementPrinter(consolePrinter));

        accountService.deposit(1000);
        accountService.deposit(2000);
        accountService.withdraw(500);

        accountService.printStatement();

        InOrder inOrder = inOrder(consolePrinter);
        inOrder.verify(consolePrinter).print("Date       || Amount || Balance");
        inOrder.verify(consolePrinter).print("14/01/2012 || -500   || 2500");
        inOrder.verify(consolePrinter).print("13/01/2012 || 2000   || 3000");
        inOrder.verify(consolePrinter).print("10/01/2012 || 1000   || 1000");
    }
}
