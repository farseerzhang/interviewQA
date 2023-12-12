public class Singleton {
    //饿汉
    private static final Singleton instance = new Singleton();

    private Singleton () {}

    public static Singleton getInstance() {
        return instance;
    }
}

/*
public class Singleton {
    //懒汉
    private static final Singleton instance;

    private Singleton () {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
*/

/*
public class Singleton {
    //双重检测
    private static Singleton instance;

    private Singleton () {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) { // 注意这里是类级别的锁
                if (instance == null) {       // 这里的检测避免多线程并发时多次创建对象
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
*/

/*
public class Singleton {
    //静态内部类
    private Singleton () {}

    private static class SingletonInner {
        private static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInner.instance;
    }
}
*/

/*
public class Singleton {
    //将普通类用枚举改造成单例
    private Singleton() {}
    public static enum SingletonEnum {
        SINGLETON;
        private Singleton instance = null;
        private SingletonEnum(){
            instance = new Singleton();
        }
        public Singleton getInstance(){
            return instance;
        }
    }
}
*/

