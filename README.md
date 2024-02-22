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
3. AUTHOR
4. TRANSACTION

    
Data Modelling [represent as  ER Diagram]
------------------------------
1. STUDENT -------(1-M)------ BOOK
2. STUDENT -------(1-M)------ TRANSACTION
3. BOOK ----(M-1)---- AUTHOR (Assuming that a book does not have multiple authors)
4. BOOK,STUDENT ----(1-M)---- TRANSACTION (Assuming each transaction invloves only a single book)
