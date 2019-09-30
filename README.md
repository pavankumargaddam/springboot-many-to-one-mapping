#Spring Boot ManyToOne Unidirectional Mapping(Student --> University) Using Annotations
---------------------------------------------------------------------------------------

1) In Many-To-One Unidirectional mapping, one table has a foreign key column that references the primary key of associated table.
By Unidirectional relationship means only one side navigation is possible (STUDENT to UNIVERSITY in this example).

2) Many students can enroll at one University. And one University can have many students.


Student.java
------------
@ManyToOne(optional = false,cascade = CascadeType.ALL)
@JoinColumn(name = "UNIVERSITY_STU_ID")
private University university;

Explanation:
------------

@ManyToOne indicates that Many student tuples can refer to one University tuple.
Also note that we have provided optional=false means this relationship becomes mandatory , no student row can be saved without a university tuple reference.
@JoinColumn says that there is a column UNIVERSITY_STU_ID in Student table which will refer(foreign key) to primary key of the University table.
In this example only Student to University entity navigation is possible. Not viceversa.
In practice, however, you are free to use query language to find all the student for a given university.
cascade = CascadeType.ALL indicates that, once we start any operation(CURD) on student table , it will reflects to university table.









