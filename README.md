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

	new Creater().from(User.class).build()
```

### insert
```java  

    User u = new User();  
    u.setAge(11);  
    u.setName("aaa");  
    String sql = new Inserter().insert(u ).build();  
```  

### selcte

```java  

	String sql = new Selector("id","title","content") //the result columns. select all(*) when nothing here
					.from(Database.class)  //table
					.distinct() // all() or distinct()
					.where("showFlag","=", 1 + "")   //`where` expression
					.and().where("version", "!=", "0")
					.groupBy("id","title")  //more than one
					.orderBy("id") //ablt to more than one
					.limit(10)
					.offset(10)
					.build();

```

### delete

```java  

	String sql = new Deletor()
				.from(User.class)
				.where("id", "=", "1")
				.and().where("age", ">", "18")
				.build();

```