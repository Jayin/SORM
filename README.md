SORM
====

simple orm for sqlite


## Useage

### Create a table 

the model  
```java  

@Table  
public class User extends Model {  

	@Column  
	private long userid; 

	@Column   
	private int age;  

	@Column  
	private String name;  

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

```


### update & automantily insert if not exist  
```java  

User u1 = new User();  
u1.setAge(12);   
u1.setName("Jack");  
u1.setUserid(3112002720L);  
u1.save(getContext());//automanti   
```

### Five Operation
* build entity  
```java  

	new Creater().from(User.class).build()
```

* insert  
```java  
  
    User u = new User();    
    u.setAge(11);     
    u.setName("aaa");     
    String sql = new Inserter().insert(u ).build();    
```  

* delete  
```java  

	String sql = new Deletor()
				.from(User.class)
				.where("id", "=", "1")
				.and().where("age", ">", "18")
				.build();

```

* selecte  
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

* update  
```java

	User u = new User();
	u.setAge(11);
	u.setName("bbb");
	u.setSaveTime(12354546);
	u.setId(1);
	String sql = new Updater()
					.update(u)
					.where("id", "=", "1")
					.build();
```

