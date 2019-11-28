获取Class对象的方式：
	1.Class.forName("全类名")，将字节码文件加载进内存，返回Class对象
		*多用于配置文件，将类名定义在配置文件中，读取文件，加载类
	2.类名.Class:通过类名属性Class获取
		*多用于参数的传递
	3.对象.getClass():getClass()方法在Object类中定义
		*多用于对象的获取字节码方式
	同一个字节码文件（*.class）在一次程序运行过程只会被加载一次
	
获取类对象之后的功能:
1.获取成员变量们
	 Field	getField(String name) 
		          返回一个 Field 对象，它反映此 Class 对象所表示的类或接口的指定公共成员字段。
	 Field[]	getFields() 
		          返回一个包含某些 Field 对象的数组，这些对象反映此 Class 对象所表示的类或接口的所有可访问公共字段。
	Field	getDeclaredField(String name) 
		          返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段。
	 Field[]	getDeclaredFields() 
		          返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段。
2.获取构造方法们
	Constructor<T>	getConstructor(Class<?>... parameterTypes) 
		          返回一个 Constructor 对象，它反映此 Class 对象所表示的类的指定公共构造方法。
	 Constructor<?>[]	getConstructors() 
		          返回一个包含某些 Constructor 对象的数组，这些对象反映此 Class 对象所表示的类的所有公共构造方法。
	Constructor<T>	getDeclaredConstructor(Class<?>... parameterTypes) 
		          返回一个 Constructor 对象，该对象反映此 Class 对象所表示的类或接口的指定构造方法。
	 Constructor<?>[]	getDeclaredConstructors() 
		          返回 Constructor 对象的一个数组，这些对象反映此 Class 对象表示的类声明的所有构造方法。
	
3.获取成员方法们
	 Method	getMethod(String name, Class<?>... parameterTypes) 
		          返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法。
	 Method[]	getMethods() 
		          返回一个包含某些 Method 对象的数组，这些对象反映此 Class 对象所表示的类或接口（包括那些由该类或接口声明的以及从超类和超接口继承的那些的类或接口）的公共 member 方法。
	 Method	getDeclaredMethod(String name, Class<?>... parameterTypes) 
		          返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法。
	 Method[]	getDeclaredMethods() 
		          返回 Method 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
	
4.获取类名
	String getName()
	
Field:成员变量的操作
	1.获取对象的成员变量的值
	 Object	get(Object obj) 
		          返回指定对象上此 Field 表示的字段的值。
	2.设置值
	 void	set(Object obj, Object value) 
		          将指定对象变量上此 Field 对象表示的字段设置为指定的新值。
	3.忽略访问权限修饰符的安全检查
		setAccessible(true)
		
Constructor：构造方法
	创建对象:
	T newInstance(Object... initargs)
	如果使用空参数构造方法创建对象，操作可以简化：Class对象newInstance方法

Method：方法对象
	执行方法
	 Object	invoke(Object obj, Object... args) 
		          对带有指定参数的指定对象调用由此 Method 对象表示的底层方法。
	
field字段例子：
	person.java
	package reflect;
	
	public class Person {
	    private String name;
	    private int age;
	    public String a;
	    public String b;
	
	    public Person() {
	    }
	
	    public Person(String name, int age) {
	        this.name = name;
	        this.age = age;
	    }
	
	    @Override
	    public String toString() {
	        return "Person{" +
	                "name='" + name + '\'' +
	                ", age=" + age +
	                ", a='" + a + '\'' +
	                ", b='" + b + '\'' +
	                '}';
	    }
	
	    public String getName() {
	        return name;
	    }
	
	    public void setName(String name) {
	        this.name = name;
	    }
	
	    public int getAge() {
	        return age;
	    }
	
	    public void setAge(int age) {
	        this.age = age;
	    }
	
	    public static void eat(){
	        System.out.println("eat----");
	    }
	    public static void eat(String food){
	        System.out.println("eat----"+food);
	    }
	
	}

	package reflect;
	
	import java.lang.reflect.Constructor;
	import java.lang.reflect.Field;
	
	public class ReflectFieldDemo {
	    public static void main(String[] args) throws Exception {
	        //加载类对象
	        Class personClass = Person.class;
	
	        //1.获取所有public修饰的成员变量
	        Field[] fields = personClass.getFields();
	        for (Field field : fields) {
	            System.out.println(field);
	        }
	
	        //获取成员变量 然后操作成员变量
	        Field a = personClass.getField("a");
	        a.setAccessible(true);
	        //获取成员变量name的值
	        Person person = new Person();
	        Object value = a.get(person);
	        System.out.println(value);
	        //设置a的值
	        a.set(person,"你好");
	        System.out.println(person);
	
	        System.out.println("设置私有的成员变量----------------");
	        //获取私有的成员变量 然后操作成员变量
	        Field name = personClass.getDeclaredField("name");
	        name.setAccessible(true);
	        Person person1 = new Person();
	        Object o = name.get(person1);
	        System.out.println(o);
	        name.set(person1,"private name");
	        System.out.println(person1);
	
	        System.out.println("------------------");
	        //获取所有成员变量
	        Field[] declaredFields = personClass.getDeclaredFields();
	        for (Field declaredField : declaredFields) {
	            System.out.println(declaredField);
	        }
	
	        System.out.println("\n\r-----------获取Constructor-------------");
	        System.out.println("-----------无参构造-------------");
	        //空参构造
	        Constructor constructor = personClass.getConstructor();
	        System.out.println(constructor);
	        //创建对象
	        Object o1 = constructor.newInstance();
	        System.out.println(o1);
	
	        System.out.println("\n\r-----------有参构造-------------");
	        //获取有参构造
	        Constructor constructor1 = personClass.getConstructor(String.class, int.class);
	        System.out.println(constructor1);
	        //创建对象
	        Object o2 = constructor1.newInstance("有参构造", 2);
	        System.out.println(o2);
	
	        System.out.println("\n\r-----------直接通过类对象创建实例-------------");
	        //或者直接通过类对象创建实例
	        Object o3 = personClass.newInstance();
	        System.out.println(o3);
	    }
	}
method例子：
	package reflect;
	
	import java.lang.reflect.Method;
	
	public class ReflectMethodDemo {
	    public static void main(String[] args) throws Exception {
	        Class personClass = Person.class;
	
	        //获取指定名称的方法名
	        Method eat = personClass.getMethod("eat");
	        Object o = personClass.newInstance();
	        //执行方法
	        eat.invoke(o);
	
	        Method eat1 = personClass.getMethod("eat", String.class);
	        eat1.invoke(o,"饭");
	
	        //获取所有的public修饰的方法
	        Method[] methods = personClass.getMethods();
	        for (Method method : methods) {
	            System.out.println(method);
	        }
	
	    }
	}

修改配置文件：
	pro.properties
	className=reflect.Person
	methodName=eat
	
	package reflect;
	
	import java.io.IOException;
	import java.io.InputStream;
	import java.lang.reflect.Method;
	import java.util.Properties;
	
	/**
	 * 不改变原Java文件，只改变配置文件，改变输出结果
	 */
	public class ReflectDemo {
	    public static void main(String[] args) throws Exception {
	        //1.加载配置文件
	        Properties properties = new Properties();
	        
	        //1.1记载配置文件，转化为一个集合
	        //1.2获取Class目录下的配置文件
	        ClassLoader classLoader = ReflectDemo.class.getClassLoader();
	        InputStream is = classLoader.getResourceAsStream("pro.properties");
	
	        properties.load(is);
	
	        //2获取配置文件中定义的数据
	        String className = properties.getProperty("className");
	        String methodName = properties.getProperty("methodName");
	
	        //3.加载该类进内存
	        Class aClass = Class.forName(className);
	        //4.创建对象
	        Object o = aClass.newInstance();
	        //5.获取方法对象
	        Method method = aClass.getMethod(methodName);
	        //6.执行方法
	        method.invoke(o);
	
	    }
	}



