package com.techstack.algorithms;

import org.junit.jupiter.api.BeforeEach;
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

    @Mock
    private Clock clock;

    private Account account;

    @BeforeEach
    public void setup() {
        account = new Account(new TransactionRepository(clock), new StatementPrinter(consolePrinter));
    }

    @Test
    public void should_print_statement_after_transactions() {

        when(clock.today())
                .thenReturn(LocalDate.of(2012, 1, 10))
                .thenReturn(LocalDate.of(2012, 1, 13))
                .thenReturn(LocalDate.of(2012, 1, 14));

        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);

        account.printStatement();

        InOrder inOrder = inOrder(consolePrinter);
        inOrder.verify(consolePrinter).print("Date       || Amount || Balance");
        inOrder.verify(consolePrinter).print("14/01/2012 || -500   || 2500");
        inOrder.verify(consolePrinter).print("13/01/2012 || 2000   || 3000");
        inOrder.verify(consolePrinter).print("10/01/2012 || 1000   || 1000");
    }
}
