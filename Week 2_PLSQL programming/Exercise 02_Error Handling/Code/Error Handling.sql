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

--scenario1
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_FromAccountID IN NUMBER,
    p_ToAccountID IN NUMBER,
    p_Amount IN NUMBER
) AS
    v_FromBalance NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Scenario 1');

    SELECT Balance INTO v_FromBalance
    FROM Accounts
    WHERE AccountID = p_FromAccountID;

    IF v_FromBalance < p_Amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds.');
    END IF;

    UPDATE Accounts SET Balance = Balance - p_Amount WHERE AccountID = p_FromAccountID;
    UPDATE Accounts SET Balance = Balance + p_Amount WHERE AccountID = p_ToAccountID;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer successful: ' || p_Amount || ' transferred from ' || p_FromAccountID || ' to ' || p_ToAccountID);

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END;

BEGIN
    DBMS_OUTPUT.PUT_LINE(' Scenario 1 ');

    DBMS_OUTPUT.PUT_LINE('Test Case 1: Valid transfer (100 from Account 1 to 2)');
    SafeTransferFunds(1, 2, 100);

    DBMS_OUTPUT.PUT_LINE('Test Case 2: Insufficient funds (999999 from Account 1 to 2)');
    SafeTransferFunds(1, 2, 999999);

    DBMS_OUTPUT.PUT_LINE('Test Case 3: Zero transfer (0 from Account 1 to 2)');
    SafeTransferFunds(1, 2, 0);

END;
/

--scenario 2

CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_EmpID IN NUMBER,
    p_Percent IN NUMBER
) AS
    v_CurrentSalary NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Scenario 2');

    SELECT Salary INTO v_CurrentSalary
    FROM Employees
    WHERE EmployeeID = p_EmpID;

    UPDATE Employees
    SET Salary = Salary + (Salary * p_Percent / 100)
    WHERE EmployeeID = p_EmpID;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for Employee ' || p_EmpID);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE(' Error: Employee ID ' || p_EmpID || ' not found.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(' Unexpected Error: ' || SQLERRM);
END;

BEGIN
    DBMS_OUTPUT.PUT_LINE('Scenario 2');

    DBMS_OUTPUT.PUT_LINE('Test Case 1: Valid salary update (+10% for Employee ID 1)');
    UpdateSalary(1, 10);

    DBMS_OUTPUT.PUT_LINE('Test Case 2: Invalid Employee ID (ID 999)');
    UpdateSalary(999, 10);

    DBMS_OUTPUT.PUT_LINE('Test Case 3: Zero percent increase for Employee ID 2');
    UpdateSalary(2, 0);
END;
/

--scenario 3
CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_CustomerID IN NUMBER,
    p_Name IN VARCHAR2,
    p_DOB IN DATE,
    p_Balance IN NUMBER
) AS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Scenario 3');

    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer added: ' || p_Name || ' (ID: ' || p_CustomerID || ')');

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: Duplicate Customer ID ' || p_CustomerID);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Unexpected Error: ' || SQLERRM);
END;

BEGIN
    DBMS_OUTPUT.PUT_LINE('Scenario 3: AddNewCustomer');

    DBMS_OUTPUT.PUT_LINE('Test Case 1: Add new customer with ID 3');
    AddNewCustomer(3, 'Mike Stark', TO_DATE('1992-03-12', 'YYYY-MM-DD'), 5000);

    DBMS_OUTPUT.PUT_LINE('Test Case 2: Attempt duplicate customer ID (ID 1)');
    AddNewCustomer(1, 'Duplicate User', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 9999);

    DBMS_OUTPUT.PUT_LINE('Test Case 3: Add customer with zero balance (ID 4)');
    AddNewCustomer(4, 'Zero Balance', TO_DATE('2000-01-01', 'YYYY-MM-DD'), 0);
END;
/