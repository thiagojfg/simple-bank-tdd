package com.techstack.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

    private StatementPrinter statementPrinter;
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        statementPrinter = Mockito.mock(StatementPrinter.class);
        transactionRepository = spy(new TransactionRepository());
    }

    @Test
    public void should_print_statement_after_transactions() {

        doReturn(LocalDate.of(2012, 1, 10))
                .doReturn(LocalDate.of(2012, 1, 13))
                .doReturn(LocalDate.of(2012, 1, 14))
                .when(transactionRepository)
                .getCurrentDate();

        AccountService accountService = new AccountServiceImpl(statementPrinter, transactionRepository);

        accountService.deposit(1000);
        accountService.deposit(2000);
        accountService.withdraw(500);
        accountService.printStatement();

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(statementPrinter, times(4)).print(captor.capture());
        List<String> lines = captor.getAllValues();
        assertThat(lines.get(0), is("Date       || Amount || Balance"));
        assertThat(lines.get(1), is("14/01/2012 || -500   || 2500"));
        assertThat(lines.get(2), is("13/01/2012 || 2000   || 3000"));
        assertThat(lines.get(3), is("10/01/2012 || 1000   || 1000"));
    }
}
