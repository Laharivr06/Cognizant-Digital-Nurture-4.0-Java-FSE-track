-- Customers table
CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);

-- Accounts table
CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- Transactions table
CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

-- Loans table
CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- Employees table
CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
);




-- Insert Customers
INSERT INTO Customers VALUES (1, 'John Doe', TO_DATE('1950-01-01','YYYY-MM-DD'), 1000, SYSDATE);
INSERT INTO Customers VALUES (2, 'Jane Smith', TO_DATE('1990-07-20','YYYY-MM-DD'), 15000, SYSDATE);

-- Insert Accounts
INSERT INTO Accounts VALUES (1, 1, 'Savings', 1000, SYSDATE);
INSERT INTO Accounts VALUES (2, 2, 'Checking', 1500, SYSDATE);

-- Insert Transactions
INSERT INTO Transactions VALUES (1, 1, SYSDATE, 200, 'Deposit');
INSERT INTO Transactions VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

-- Insert Loans
INSERT INTO Loans VALUES (1, 1, 5000, 7, SYSDATE, ADD_MONTHS(SYSDATE, 20));
INSERT INTO Loans VALUES (2, 2, 7000, 6, SYSDATE, ADD_MONTHS(SYSDATE, 25));

-- Insert Employees
INSERT INTO Employees VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));
INSERT INTO Employees VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

COMMIT;

ALTER TABLE Customers ADD IsVIP VARCHAR2(5);

--scenario1--

SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Scenario 1');

    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Interest applied to all Savings accounts.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(' Error applying interest: ' || SQLERRM);
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('Test Case 1: Apply interest to savings accounts');
    ProcessMonthlyInterest;

    DBMS_OUTPUT.PUT_LINE('Test Case 2: Apply interest again (check compounding)');
    ProcessMonthlyInterest;

    DBMS_OUTPUT.PUT_LINE('Test Case 3: No savings accounts exist (simulate by deleting savings and run again)');
    DELETE FROM Accounts WHERE AccountType = 'Savings';
    COMMIT;
    ProcessMonthlyInterest;
END;
/

--scenaro 2
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_Department IN VARCHAR2,
    p_BonusPercent IN NUMBER
) AS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Scenario 2: Update Employee Bonus ');

    UPDATE Employees
    SET Salary = Salary + (Salary * p_BonusPercent / 100)
    WHERE Department = p_Department;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE(' Bonus applied to employees in ' || p_Department || ' department.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(' Error updating bonus: ' || SQLERRM);
END;
/


BEGIN
    DBMS_OUTPUT.PUT_LINE('Test Case 2.1: Apply 5% bonus to IT department');
    UpdateEmployeeBonus('IT', 5);

    DBMS_OUTPUT.PUT_LINE('Test Case 2.2: Apply 10% bonus to HR department');
    UpdateEmployeeBonus('HR', 10);

    DBMS_OUTPUT.PUT_LINE('Test Case 2.3: Apply bonus to non-existent department (should silently affect 0 rows)');
    UpdateEmployeeBonus('Marketing', 15);
END;
/

--scenario 3

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_FromAccountID IN NUMBER,
    p_ToAccountID IN NUMBER,
    p_Amount IN NUMBER
) AS
    v_FromBalance NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Scenario 3: TransferFunds Between Customer Accounts');

    SELECT Balance INTO v_FromBalance
    FROM Accounts
    WHERE AccountID = p_FromAccountID;

    IF v_FromBalance < p_Amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in source account.');
    END IF;

    UPDATE Accounts SET Balance = Balance - p_Amount WHERE AccountID = p_FromAccountID;
    UPDATE Accounts SET Balance = Balance + p_Amount WHERE AccountID = p_ToAccountID;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(' Transfer successful: ' || p_Amount || ' transferred from ' || p_FromAccountID || ' to ' || p_ToAccountID);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('Test Case 1: Valid transfer from Account 2 to 1');
    TransferFunds(2, 1, 200);

    DBMS_OUTPUT.PUT_LINE('Test Case 2: Invalid transfer (insufficient funds)');
    TransferFunds(2, 1, 999999);

    DBMS_OUTPUT.PUT_LINE('Test Case 3: Transfer zero amount');
    TransferFunds(2, 1, 0);
END;
/