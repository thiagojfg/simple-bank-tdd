# Simple Bank Application

## Project Objective
This project implements a simple banking application that allows users to:
- Make deposits to their account
- Make withdrawals from their account
- Print their account statement showing transaction history with dates, amounts, and running balance

The application demonstrates clean code principles, separation of concerns, and test-driven development.

## Implementation Approach
The project follows a modular design with a clear separation of responsibilities:

- **Account**: Main entry point that coordinates transactions and statement printing
- **Transaction**: Immutable record representing a single transaction with date and amount
- **TransactionRepository**: Stores and manages all transactions
- **StatementPrinter**: Formats and prints account statements
- **Clock**: Provides the current date for transactions
- **ConsolePrinter**: Handles output to the console

The implementation uses dependency injection to improve testability and follows SOLID principles.

## Potential Improvements
- Add input validation for deposit and withdrawal amounts
- Implement persistent storage for transactions
- Add user authentication and multiple account support
- Improve error handling for edge cases
- Add transaction categories or descriptions
- Implement a proper money type instead of using integers
- Create a user interface (web or desktop)

## Desired Behaviour

### Here's the specification for an acceptance test that expresses the desired behaviour for this

 Given a client makes a deposit of 1000 on 10-01-2012

 And a deposit of 2000 on 13-01-2012

 And a withdrawal of 500 on 14-01-2012

 When they print their bank statement

 Then they would see:

 Date       | Amount | Balance

 14/01/2012 | -500   | 2500

 13/01/2012 | 2000   | 3000

 10/01/2012 | 1000   | 1000
