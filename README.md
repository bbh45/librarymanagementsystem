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
    
Data Modelling [represent as  ER Diagram]
------------------------------
1. STUDENT -------(1-M)------ BOOK
2. STUDENT -------(1-M)------ TRANSACTION
3. STUDENT -------(1-1)------ STUDENT ACCOUNT
4. BOOK ----(M-1)---- AUTHOR (Assuming that a book does not have multiple authors)
5. BOOK ----(1-M)---- TRANSACTION (Assuming each transaction invloves only a single book)


--> STUDENT ----(1-M)---- BOOK
- A Student can take multiple books
- But one book can not be taken by multiple students

--> Therefore, while creating table in the databases, 
- Book table can have forgien key - studentId as column (taken by which student)
- Student table can not have a column storing the list of books taken by him.

--> In case of M-M mapping, we create a seperate table with forgien keys of each table and the primary key will be the combination of both.

-------------------------
we can model them as an attribute on exactly one of the associated entities or both. Defining the direction of the relationship between entities has no impact on the database mapping.

JPA MAPPINGS 
----------------
1. Uni directional Mapping 
    - only one class will store the forgien key of the other 
     - eg: Book class has the studentId as attribute.
    - weaker relationship
2. Bi directional Mapping
    - Both classes should have forgien keys of another - mapping both ways
     - eg: Book class will have studentId as attribute and student class will have the list of BookId's[List<Book> books] as attribute(but are not stored in the database)
     - hibernate internally makes a query to book table and fetches the list of bookId's taken by a student
    - Stronger Relationship because its both ways.
   



