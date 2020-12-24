 final static String salutaion="Hello  ";

    public static void main(String[] args) {
        //lambda表达式初步认识
//       ThreadTest();
        smok(new Smoking() {
            @Override
            public void smoking() {
                System.out.println("smoking!!!!!");
            }
        });

        smok(()->{
            System.out.println("AAAAAAAAAAA");
        });

    }

    private static void smok(Smoking smoking) {
        smoking.smoking();
    }

    private static void ThreadTest() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("@@@@@@@@");
            }
        }).start();


        new Thread(()->{
            System.out.println("lambda");
        }).start();
    }

    @Test
    public void testConSumer(){
        Add(1,(a)->{
            System.out.println("aa"+a);
        });

        HashMap<String, Object> map = new HashMap<>();
        map.put("a",2);
        map.put("b",4);
        AddMap(map,(map1)->{
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                int value =(int) entry.getValue();
                String key = entry.getKey();
                map.put(key,Integer.valueOf(value)*2);
            }
        });
        System.out.println(map);
    }

    /**
     * Consumer(T t ) :消费型接口
     *      void accept(T t);
     */
    private void Add(Integer a,Consumer<Integer> consumer) {
        consumer.accept(a);
    }

    /**
     * Consumer(T t ) :消费型接口
     *      void accept(T t);
     */
    private void AddMap(Map<String,Object> map, Consumer<Map> consumer) {
        consumer.accept(map);
    }


    /**
     * 生成一个执行大小的整数型list
     */
    @Test
    public void TestSupplier(){
        List<Integer> list = getList(4,()-> {
            return  (int)(Math.random()*100);}
        );
        System.out.println(list);
    }

    /**
     * Supplier<T> :供给型接口
     *      T get();
     */
    private List<Integer> getList(Integer a , Supplier<Integer> supplier) {
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer i = 0; i < a; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    //用于字符串处理
    @Test
    public void testApply(){
        String str =strHandler("\t\t\t 你好啊 ",(strig)-> strig.trim());
        System.out.println(str);
        String str2 =strHandler("你好啊 ",(strig)-> strig.substring(1,2));
        System.out.println(str2);
    }
    /**
     * Function<T,R> 函数接口
     *    R apply(T t)
     */
    public String strHandler(String str, Function<String,String> fun){
        return fun.apply(str);
    }


    @Test
    public void testStrFil(){
        List<String> list = Arrays.asList("hello","lamdba","test","wwww","a","b","c");
        List<String> list1 = filterStr(list, (s) -> s.length() > 3);
        System.out.println(list1);
    }

    /**
     * Predicate<T> 断言型函数
     *      boolean test(T  t);
     */
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List strList = new ArrayList();

        for (String str : list) {
            if (pre.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }
