# librarymanagementsystem
SpringBoot with JPA and Hibernate

Managing a library of mutlitple books along with student transactions.

Assumptions:
------------
There are mutliple copies of each book.
Each transaction invloves only a single book.
A book does not have multiple authors.

Entities (Models)
-------------
1. BOOK
2. STUDENT
3. STUDENT ACCOUNT
4. AUTHOR
5. TRANSACTION

Functionalities (Controllers)
----------------
1. CRUD for BOOK
    1. Create a new book - book details + author details
    2. Read the details about a perticular book or books of a perticular author etc
    3. Update status of book - assigned or unassigned
    4. Delete a perticular book 
3. CRUD for STUDENT
4. CRU for TRANSACTION
    1. Creating a transaction
    2. Read the details of a transaction
    3. Updating status of a transaction etc
    
Data Modelling [use ER Diagram]
------------------------------
1. STUDENT -------(1-M)------ BOOK
2. STUDENT -------(1-M)------ TRANSACTION
3. STUDENT -------(1-1)------ STUDENT ACCOUNT
4. BOOK ----(M-1)---- AUTHOR (Assuming that a book does not have multiple authors)
5. BOOK ----(1-M)---- TRANSACTION (Assuming each transaction invloves only a single book)
