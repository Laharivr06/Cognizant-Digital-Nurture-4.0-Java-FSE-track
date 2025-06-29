
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

--Scenario 1
SET SERVEROUTPUT ON;

BEGIN
    DBMS_OUTPUT.PUT_LINE('Scenario 1');

    FOR cust_rec IN (SELECT CustomerID, Name, DOB FROM Customers) LOOP
        IF TRUNC(MONTHS_BETWEEN(SYSDATE, cust_rec.DOB) / 12) > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = cust_rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Discount applied to: ' || cust_rec.Name ||
                                 ' (CustomerID: ' || cust_rec.CustomerID || ')');
        ELSE
            DBMS_OUTPUT.PUT_LINE('No discount for: ' || cust_rec.Name ||
                                 ' (Age: ' || TRUNC(MONTHS_BETWEEN(SYSDATE, cust_rec.DOB) / 12) || ')');
        END IF;
    END LOOP;
    COMMIT;
END;

--Scenario 2--
SET SERVEROUTPUT ON;

BEGIN
    DBMS_OUTPUT.PUT_LINE('Scenario 2');

    FOR cust IN (SELECT CustomerID, Name, Balance FROM Customers) LOOP
        IF cust.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE('' || cust.Name || ' promoted to VIP (Balance: ' || cust.Balance || ')');
        ELSE
            UPDATE Customers
            SET IsVIP = 'FALSE'
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE('' || cust.Name || ' is not a VIP (Balance: ' || cust.Balance || ')');
        END IF;
    END LOOP;
    COMMIT;
END;

--Scenario 3--
SET SERVEROUTPUT ON;

DECLARE
    CURSOR loan_cursor IS
        SELECT L.LoanID, L.CustomerID, C.Name, L.EndDate
        FROM Loans L
        JOIN Customers C ON L.CustomerID = C.CustomerID
        WHERE L.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
    reminder_found BOOLEAN := FALSE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Scenario 3');

    FOR loan_rec IN loan_cursor LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || loan_rec.Name ||
                             ', your loan (ID: ' || loan_rec.LoanID ||
                             ') is due on ' || TO_CHAR(loan_rec.EndDate, 'YYYY-MM-DD'));
        reminder_found := TRUE;
    END LOOP;

    IF NOT reminder_found THEN
        DBMS_OUTPUT.PUT_LINE('No loans are due in the next 30 days.');
    END IF;
END;