
1：代理模式
   三个核心组成部分，代理类、目标类、父接口或者父类
   调用方调用的时候，表面上看是调用代理类的功能，实际上代理类转了一道手，将对应的工作交给了目标类来实现

2：静态代理
   在源码层面比较清楚的定义出代理类、目标类、父接口或者父类，源码经过编译后直接产生对应的字节码文件

3：动态代理
   核心在于动态的理解上，这个动态是怎么体现出来的呢？主要是通过编译时动态的产生代理类，来体现的，
   对应的代理类在源码层面是看不到的，在编译时才动态生成，所以，这里的动态就在这里体现了
