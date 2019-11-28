

1、字符常量：凡是用单引号引起来的单个字符，就叫做字符常量。例如：'A'、'b'、'中'
2、byte、short、char这三种类型都可以发生数据运算，例如加法“+”，在运算的时候，都会被首先提升成int类型，然后再进行计算，计算的结果为int类型。

ASCII码：American Standard Code Information Interchange 美国信息交换标准码表
```xml
	48 --“0”
	65 -- “A"
	97 -- “a”
 ```


break 关键字的用法常见的有两种：
```
	1.可以再switch语句当中,一旦执行，整个switch语句立刻结束
	2.在循环中，一单执行，整个循环语句立刻结束，打断循环
```
continue关键字：
```
	一旦执行，立刻跳出当前次循环剩下的内容，马上开始下一次循环
```
	
switch语句使用的注意事项：
	```
	1.多个case后面的值不可以重复
	2.switch后面的小括号当中只能是下列数据类型
		基本类型：byte、int、short、char
		引用数据类型：String字符串、enum枚举
	3.switch语句格式可以很灵活：前后顺序可以颠倒，而且break语句可以省略。
	当当前case语句后边没有break时，会直接执行下一个case，并且不管case的执行条件，直接穿透，直至碰到break语句跳出。
	```
函数方法的注意事项：
	```
	对于有返回值的方法，可以使用单独的调用、打印调用或者赋值调用
	但对于没有返回值的方法，只能使用单独调用，不能打印或者赋值调用动态初始化数组的时候，其中元素将自动拥有一个默认值：
	整数类型：默认值是0；
	浮点类型：默认值是0.0；
	字符类型：默认值是'\u0000'；
	布尔类型：默认值是false;
	引用类型： 默认值是null;
	```

定义一个类，用来模拟“学生”，其中包括两个组成部分：
	```
	属性:
		姓名
		年龄
		String name；
		int age；
	行为：
		吃饭
		学习睡觉
		`
		public void eat（）{}
		public void sleep（）{}public void study（）{}
		`
	注意事项：
	1、成员变量直接定义在本类中，在方法外边（在堆内存中）
	2、成员方法不能写static关键字
	```
局部变量和成员变量的区别：
```
	1、定义位置不一样
		局部变量：在方法内部定义
		成员变量：在方法外部定义，在类当中
	2、作用范围不一样
		局部变量：只有在方法当中才能使用
		成员变量：整个类中都能使用
	3、默认值不一样
		局部变量：没有默认值
		成员变量：如果没有复制，还有默认初始值，和数组默认值一样
	4、内存位置不一样：
		局部变量：位于栈内存中
		成员变量：位于堆内存
	5、声明周期不一样：
		局部变量：随方法进栈而诞生，随方法出栈而消失
		成员变量：随对象创建而诞生，随对象被垃圾回收而消失
```
类构造方法注意事项：
```
	1、没有写构造方法时，会有一个默认无参构造
	2、一旦编写了至少一个构造方法，那么将没有默认无参构造。
```
		
对于基本类型当中的boolean值，Getter方法一定要写成isXxx的形式，setXxx不变
this关键字：
	当方法的局部变量和类的成员变量重名的时候，根据“就近原则”，优先使用局部变量，如果需要访问本类当中的成员变量，需要使用格式：this.成员变量名

	通过谁调用的方法，谁就是this
	
ArrayList
	ArrayList只能存储引用类，如果要使用基本数据类型，必须使用基本类型的“包装类”
String字符串的特点：
	
	1、字符串内容永不可变
	2、正因为不可变，所以字符串是可以共享的
	3、字符串效果上相当于char[]字符数组，但底层原理是byte[]字符数组
	创建字符串的三种方式：
	public String(),创建一个空白的字符串，不含任何内容
	public String(char[] array)，根据字符数组的内容，创建对应的字符串
	public String(byte[] array),根据字符数组的内容，来创建对应的字符串
字符串常量池，程序当中直接写上的双引号，就在字符串常量池中
	对于基本类型来说，==是进行数值的比较，
	对于引用类型来说，==是进行【地址值】的比较
	public int length(),获取字符串当中含有的字符个数，拿到字符串长度
	public String concat()，拼接字符串
	public int indexOf()，查找字符串中首次出现字符的索引位置
	public char charAt(int index),获取指定索引位置的字符
stattic 
	static修饰成员方法，就成为静态方法，属于类，
	静态不能直接访问非静态内容，因为在内存当中先有静态内容，后有非静态内容
	static修饰的静态代码块内容：
		当第一次使用本类时,静态代码块执行唯一一次
		静态内容优先非静态，所以静态代码块比构造方法先执行
	静态代码块可以用来一次性对静态成员变量进行赋值
Arrays:
	public static String toString(数组),将参数数组变成字符串
	public static void sort（数组）,对数组元素进行排序
	
Math:java.util.Math
	public static double abs(double num),取绝对值，有多种重载
	public static double ceil(double num),向上取整
	public static double floor(double num),向下取整
	public static double round(double num),向上取整
	
在继承关系中，创建子类对象，访问成员方法的规则：
	创建的对象是谁，就优先用谁，如果没有则向上找，
	局部变量：直接写变量名
	本类的成员变量：this.成员变量名
	父类的成员变量:super.成员变量
继承关系：
	1、子类构造方法当中有一个默认的super()调用，所以先调用父类构造，后执行子类构造
	2、子类构造可以通过super关键字来调用父类重载构造
	子类必须调用父类构造方法，不写则赠送，写了则按指定的super调用，super只能有一个，而且必须第一个。
抽象方法：
	抽象方法：就是加上abstract关键字，然后去掉大括号，直接分号结束
	抽象类：抽象方法所在的类必须是抽象类，在class之前写上abstarct

接口：
	java7，接口中可以可以包含的内容：
		1、常量
		2、抽象方法
	java8，还可以额外包含：
		1、默认方法
		2、静态方法
	java9 还可以额外包含：
		1、私有方法
在任何版本的java中，接口都能定义抽象方法
格式：public abstarct 返回值类型 方法名称（参数列表）；
注意事项：1、接口当中的抽象方法，修饰符必须是两个固定的关键字：public abstarct 
		2、两个关键字修饰符可以省略
java 8开始,接口里允许定义默认方法，
	格式：public default 返回值类型 方法名称（参数列表）{      方法体}
	主要为了解决接口升级的问题，使用默认方法，继承类可以不用实现
java 8开始,接口里允许定义静态方法，
	格式：public static 返回值类型 方法名称（参数列表）{      方法体}
java9开始，接口当中允许定义私有方法
	1、普通私有可以解决多个默认方法之间重复代码问题
	格式：private 返回值类型 方法名称（参数列表）{方法体}
	2、静态私有方法，解决多个静态之间重复代码问题
	格式：private static 返回值类型 （参数列表）{方法体}
接口中可以定义成员变量，但是必须使用public static final三个关键字修饰
格式：public static final 数据类型 常量名称 = 数据值
注意：必须赋值，public static final可以省略，成员变量不可变
	


Static
	特点：当第一次使用到本类时，静态代码执行唯一的一次。静态内容总是优于非静态，所有静态代码比构造方法先执行，
	抽象方法：就是在abstarct关键字，然后去掉大括号，以分号结尾，
	public abstarct class Animal{
	        //这是一个抽象方法，
	        public abstarct void eat（）；
	        //这是一个普通的成员方法
	        public void normalMethod(){
	        } 
	}
	
Math类中常用的方法：
	 * public static double abs(double num ): 获取绝对值
	 * public static double ceil（double num） 向上取整
	 * public static double floor（double num） ：向下取整
	 * public static long round(double num) :四舍五入
泛型：
	

	不写泛型默认是object类型
	

Thread
	创建方式一：
	此方法是实现Runnable，避免只能继承Thread一个类，可以变相的继承另一个类
	 Runnable runnable = new Runnable() {
	            @Override
	            public void run() {
	                for (int i = 0; i < 20; i++) {
	                    System.out.println(Thread.currentThread().getName());
	                }
	            }
	        };
	 new Thread(runnable).start();
	创建方式二：
	new Thread(){
	            @Override
	            public void run() {
	                super.run();
	            }
	  }.start();
	枷锁方式：
		//创建一个锁对象
		    Object obj =new Object();
		//加锁
		 synchronized (obj){
		       if (ticket>0){
		           try {
		               Thread.sleep(100);
		                    } catch (InterruptedException e) {
		                        e.printStackTrace();
		                    }
		                }
		        System.out.println(Thread.currentThread().getName()+"第"+ticket+"张票出售中。。。。。");
		            ticket --;
		 }
	Lock锁：
		提供了比synchronied代码块和synchronized方法更广泛的操作
		同步代码块/同步方法具有的功能Lock都有，除此之外，更面体现面向对象
		Lock锁也称为同步锁，加锁和释放锁的方法：
			public void  lock（）：加同步锁
			public void  unlock（）：释放同步锁
			
		lock对象的创建方式：
			Lock lock = new ReentrantLock(); 
			        lock.lock();    //加锁
			        lock.unlock();  //释放锁
		通常情况下，unlock（）方法一般与try..catch..finally代块连用
	
	
进入到timeWaiting（计时等待）有两种方式
1、使用sleep(long time)方法，在毫秒值结束之后，线程进入Runnable/Blocked状态
2、使用wait(long time)方法，如果毫秒值结束之后，还没有被notify唤醒,就会自动醒来，线程进入Runnable/Blocked状态
3、使用wait()方法，等待被notify唤醒
唤醒方法：
	notify() 唤醒此对象监视器上等待的某个线程
	notifyAll() 唤醒此对象监视器上等待的所有线程
	
Executors：线程的工厂类，用来生成线程池
Executor类中的静态方法：
	static ExecutorService newFixedThreadPool(int nThread)创建一个可重复使用固定线程数的线程池，
	用来从线程池中获取线程，调用start方法，执行线程任务
	submit(Runnable task)提交一个Runnable任务用于执行
	关闭/销毁线程的方法
		void shutdown()

File：
	static String pathSeparator 与系统有关的路径分隔符，
	 static String separator 与系统有关的默认名称分隔符，
	//与系统有关的路径分隔符 ,
	String pathSeparator=File.pathSeparator;
	System.out.println(pathSeparator);
	
	//与系统有关的默认名称分隔符 \
	String separator = File.separator;
	System.out.println(separator);

File（String parent,String child）根据parent路径名称字符串和child路径名称拼接成一个新的File实例
	File file = new File("D:\\Git");
	 File file1 = new File("D:\\Git", "CGB-JT-SYS-V1.01");

	获取功能的方法：
		getAbsolutePath():返回File的绝对路径名字符串
		getPath(): 将File转换为路径名字符串
		getName()：返回File表示的文件或目录的名称
		length():返回File表示的文件的长度
	判断动能的方法：
		exists()：File表示的文件或目录是否实际存在。
		isDirectory()：判断是否是目录
		isFile()：判断是否为文件
	File类创建删除功能
		createNewFile() ：当文件不存时，创建一个空的文件
		delete() 删除此File表示的文件或目录
		mkdir() 创建由File表示的目录
		mkdirs() 创建由此File表示的目录
	File类遍历（文件夹）目录功能
		public String[] list() ：返回一个String数组，表示File目录中的所有子文件或目录。
		public File[] listFiles() ：返回一个File数组，表示File目录中的所有子文件或目录
		list返回的文件名
		listFile返回的File对象，是文件的绝对路径
	fileList（FileFilter filter）
		FileFilter接口，用于抽象路径（File 对象的过滤器）的过滤
		抽象方法：
		boolean accept(File pathName)测试指定抽象路径名是否包含在某个路径列表下
		private static void getFilesFilter(File dir) {
		        //添加文件过滤方法
		        File[] files = dir.listFiles(new FileFilter() {
		            @Override
		            public boolean accept(File pathname) {
		             //表示只输出文件夹或者以txt结尾的文件
		                return pathname.isDirectory()||pathname.getName().endsWith(".txt");
		            }
		        });
		        for (File file : files) {
		            if (file.isDirectory()){
		                getAllFiles(file);
		            }else {
		                //添加一个过滤文件的过滤方法
		                    System.out.println(file);
		            }
		        }
		
		    }

	
	
字节流
FileInputStream
构造方法摘要 
	FileInputStream(File file) 
	          通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的 File 对象 file 指定。 
	FileInputStream(String name) 
	          通过打开一个到实际文件的连接来创建一个 FileInputStream，该文件通过文件系统中的路径名 name 指定。
方法摘要 
	 int read() 
	          从此输入流中读取一个数据字节。 
	 int read(byte[] b) 
	          从此输入流中将最多 b.length 个字节的数据读入一个 byte 数组中。 
	 int read(byte[] b, int off, int len) 
	          从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。 
	
FileOutputStream
	FileOutputStream(String name,boolean append),
	FileOutputStream(File file',boolean append),
	参数：
		String name，file写入文件的位置
		boolean append 追加写开关
			true：创建对象不会覆盖问件，继续在文件末尾追加写数据
			false：创建一个新文件，覆盖原文件
		换行符：
		window： \r\n
		linux: /n
		moc: /r
	/**
	* 默认追加方式，直接在文件后边追加内容，不覆盖原先内容
	*/
	 FileOutputStream fos = new FileOutputStream("b.txt",true);
	
	
	
	文件复制：
		 //文件输入输出字节流
		 FileInputStream fis = new FileInputStream("E:\\图片\\1.jpg");
		FileOutputStream fos = new FileOutputStream("F:\\1.jpg");
		
		//使用数组缓读取多个字节，写入多个字节
		 byte[] bytes = new byte[1024];
		 int len =0;
		while ((len =fis.read(bytes)) != -1){
		      //书写有效字值，
		      fos.write(bytes,0,len);
		}
		  //释放资源 
		fos.close();
		fis.close();

字符流
	单使用字节流读取文件时，可能会有一个小问题，就是遇到中文时，可能不会显示完整的字符，那是因为一个中文字占用多个字节存储，所以java提供一些字符流类，以字符为单位专门提供文件文本。
	gbk 一个中文字占用两个字节
	utf8 一个中文字占用三个字节
	
FileReader
	构造方法摘要 
		FileReader(File file) 
		          在给定从中读取数据的 File 的情况下创建一个新 FileReader。
		FileReader(String fileName) 
		          在给定从中读取数据的文件名的情况下创建一个新 FileReader。
	int read() 读取单个字符并返回
	int read(char[] cbuf) 一次性读取多个字符，并将字符读入到数组中
FileWriter
构造方法摘要 
	FileWriter(File file) 
	          根据给定的 File 对象构造一个 FileWriter 对象。 
	FileWriter(File file, boolean append) 
	          根据给定的 File 对象构造一个 FileWriter 对象。 
	FileWriter(String fileName) 
	          根据给定的文件名构造一个 FileWriter 对象。 
	FileWriter(String fileName, boolean append) 
	          根据给定的文件名以及指示是否附加写入数据的 boolean 值来构造 FileWriter 对象。 
	
BufferedWriter
	java.lang.Object
	  继承者 java.io.Writer
	      继承者 java.io.BufferedWriter
	将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入。
	构造方法摘要 
		BufferedWriter(Writer out) 
		          创建一个使用默认大小输出缓冲区的缓冲字符输出流。 
		BufferedWriter(Writer out, int sz) 
		          创建一个使用给定大小输出缓冲区的新缓冲字符输出流。 
	方法摘要 
		 void close() 关闭此流，但要先刷新它。 
		 void flush()    刷新该流的缓冲。 
		 void newLine()   写入一个行分隔符。 
		 void write(char[] cbuf, int off, int len) 写入字符数组的某一部分。 
		 void write(int c)  写入单个字符。 
		 void write(String s, int off, int len)   写入字符串的某一部分。 
	
BufferedReader：从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。 
	java.lang.Object
	  继承者 java.io.Reader
	      继承者 java.io.BufferedReader
	
	通常，Reader 所作的每个读取请求都会导致对底层字符或字节流进行相应的读取请求。因此，建议用 BufferedReader 包装所有其 read() 操作可能开销很高的 Reader（如 FileReader 和 InputStreamReader）。例如， 
	 BufferedReader in
   = new BufferedReader(new FileReader("foo.in"));
	将缓冲指定文件的输入。如果没有缓冲，则每次调用 read() 或 readLine() 都会导致从文件中读取字节，并将其转换为字符后返回，而这是极其低效的。 
	通过用合适的 BufferedReader 替代每个 DataInputStream，可以对将 DataInputStream 用于文字输入的程序进行本地化。 
构造方法摘要 
	BufferedReader(Reader in) 
	          创建一个使用默认大小输入缓冲区的缓冲字符输入流。 
	BufferedReader(Reader in, int sz) 
	          创建一个使用指定大小输入缓冲区的缓冲字符输入流。 
方法摘要 ：
	String readLine() 
	          读取一个文本行。 
字符流通向字节流
OutputStreamWriter：是字符流通向字节流的桥梁：可使用指定的 charset 将要写入流中的字符编码成字节。它使用的字符集可以由名称指定或显式给定，否则将接受平台默认的字符集。
	构造方法摘要 
		OutputStreamWriter(OutputStream out) 
		          创建使用默认字符编码的 OutputStreamWriter。 
		OutputStreamWriter(OutputStream out, String charsetName) 
		          创建使用指定字符集的 OutputStreamWriter。
	方法摘要 
		 void close() 
		          关闭此流，但要先刷新它。 
		 void flush() 
		          刷新该流的缓冲。 
		 void write(char[] cbuf, int off, int len) 
		          写入字符数组的某一部分。 
		 void write(int c) 
		          写入单个字符。 
		 void write(String str, int off, int len) 
		          写入字符串的某一部分。 
字节流通向字符流
InputStreamReader:是字节流通向字符流的桥梁：它使用指定的 charset 读取字节并将其解码为字符。它使用的字符集可以由名称指定或显式给定，或者可以接受平台默认的字符集
	构造方法：
		InputStreamReader(InputStream in) 
		          创建一个使用默认字符集的 InputStreamReader。
		InputStreamReader(InputStream in, String charsetName) 
		          创建使用指定字符集的 InputStreamReader。
	方法摘要：
		 int read() 
		          读取单个字符。 
		 int read(char[] cbuf, int offset, int length) 
		          将字符读入数组中的某一部分。 
		 boolean ready() 
		          判断此流是否已经准备好用于读取。 
		//使用utf-8格式读取文件
		InputStreamReaderisr=newInputStreamReader(
		newFileInputStream("uft-8.txt"),"utf-8");
		intlen=0;
		while((len=isr.read())!=-1){
		System.out.println((char)len);
		}
		isr.close();
	
对象的序列化和反序列化
ObjectOutputStream:对象序列化
	构造方法：
	ObjectOutputStream（OutputStream out）创建写入指定的stream
	特有的成员方法:
		void writeObject（Object ojb）将指定的对象写入ObjectOutputStream
ObjectInputStream：对象反序列化
	ObjectInputStream(InputStream in )创建指定的InputStream读取文件
	特有方法：
		void readObject（Object ojb）从ObjectInputStream读取

	    //static transient 防止序列化
	//    static int age;
	//    transient int age;
	
	//序列化
	ObjectOutputStreamoos=newObjectOutputStream(newFileOutputStream("Objectos.txt"));
	oos.writeObject(newPerson("小小",18));
	oos.close();
	
	//反序列化
	ObjectInputStreamois=new ObjectInputStream(newFileInputStream("Objectos.txt"));
	Objecto=ois.readObject();
	System.out.println(o);
	System.out.println(((Person)o));
	
PrintStream
	为其他输出流添加了功能
	特点：
		1、只负责数据的输出，不负责数据的读取
		2、与其他流不同，不会抛出IOException
		3、特有方法
			void print（任意类型的值）
			void println（任意类型的值）
		构造方法：
		PrintStream(File file) :输出目的是一个文件
		PrintStream(OutPutStream out) :输出目的是一个字节输出流
		PrintStream(String fileName) :输出目的是一个文件路径
	更改System.out.println()的输出位置
	PrintStream ps = new PrintStream("print.txt");
	System.setOut(ps);
	
	System.out.println("PrintStram ......");
	System.out.println("你好 ......");
		
	
	
	









