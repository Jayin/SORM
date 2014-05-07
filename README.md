SORM
====

simple orm for sqlite


## Useage

### Create a table 

the model  
```java  

	@Table  
	public class User {
		@PrimaryKey
		private long id

		@Column
		private String name;

		@Column
		private int age;
	
	}

```

to create a sql	:  
```java  

	new Creator().from(User.class).build()
```
