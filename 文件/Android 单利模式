package com.note.mytest.tool;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/11/2 9:04
 * @desc : 单利模式 双重机制
 * volatile 实现一个双重检查锁的单例模式
 */
public class Singleton {
    /**
     * 双重加锁检查DCL（Double Check Lock）
     */
    private static volatile Singleton singleton;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (singleton == null) { //先检查实例是否存在，如果不存在才进入下面的同步块
            synchronized (Singleton.class) {  //同步块，线程安全的创建实例
                if (singleton == null) { //再次检查实例是否存在，如果不存在才真的创建实例
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    /**
     * 懒汉式单利模式
     * 优点：懒加载启动快，资源占用小，使用时才实例化，无锁。
     * 缺点：非线程安全。
     */
    private static Singleton singleton2;

    public static Singleton getInstance2() {
        if (singleton2 == null) {
            singleton2 = new Singleton();
        }
        return singleton2;
    }

    /**
     * 饿汉模式
     * 优点：饿汉模式天生是线程安全的，使用时没有延迟。
     * 缺点：启动时即创建实例，启动慢，有可能造成资源浪费。
     */
    private static Singleton singleton3 = new Singleton();

    //    private Singleton(){ }
    public static Singleton getInstance3() {
        return singleton3;
    }

    /**
     * Holder模式
     * 将懒加载和线程安全完美结合的一种方式（无锁）
     */
    //类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例,没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
    private static class SingletonHolder {
        private static Singleton singleton4 = new Singleton();
    }
//    private Singleton(){
    public static  Singleton getInstance4(){
             return SingletonHolder.singleton4;
    }


}
