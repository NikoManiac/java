## 编辑器

- 快捷键
- Vim

## 习惯

- 查官方文档
- java language specification

## Java基础

### Integer

- 各种基础数据类型的位数，bit与byte的关系
- MAX_VALUE与MIN_VALUE，以及overflow时的计算问题。
- 补码、原码
- 移位操作，包括<<< 与<< 的不同
- int overflow问题处理，底层可以使用((x ^ r) & (y ^ r)) < 0判断，Math封装了相关函数
- 不同数据类型之间转换，包括显示转换与隐式转换，基本原则是高精度向低精度转换需要显示转换，反之相反。
- Data default value
- Java underline for  improve the readability of your code

### Floating

- 任意两个NAN都不相等
- 声明double与float可以使用D与F（大小写都可以，大写可读性比较好）

### char

- unicode历史

### boolean

- boolean与包装类的区别
- && 与 ||是逻辑操作
- &与|以及~是位操作，证明了三者的优先级 ~ > & > |

### Array

- 声明final数组，本质上数组的指向是不可改变，但是数组内的数据是可以改变。

### String

- codepoint相关操作
- String的比较不能使用==，而是需要使用equals
- 可以使用trim()去除收尾的空格
- String对其操作都是生产新的String，包括使用+操作符，会消耗大量内存，所以当需要大量操作String时，可以考虑使用StringBuilder
- StringBuilder与StringBuffer使用方法相似，只不过线程不安全，后者线程安全

### Object

- Object在内存中的存在形式
- class关键字与.class的区别
- 方法签名

### Inheritance

- 构造函数调用：
  - 当初始化一个对象的时候，必须要选择使用一个构造函数，分为两步，先调用父类的，在调用子类的
  - 在这个过程中，可以使用this调用其他的构造函数
- Object的equals比较需要注意的要求
  - 自反性
  - 对称性
  - 传递性
  - 一致性 
- Object的hashcode

### Reflection

- class关键字与.class的区别
- 类使用类.class，而实例需要使用实例.getClass()
- Class.forName的使用
- for (Method method : methods) 这种for循环的使用方式
- Arrays.stream的使用
- Modifier的方法以及可访问性
- 通过注解获取方法
- 反射的黑科技与问题

### Interface

- 使用.super调用interface的default方法
- Java 8 自带的元注解以及如何写一个注解
- ::符号的使用，Method reference 
- 运行时错误与编译时错误

### Lambda

- 内部类
- Java 编译器编译 Lambda 表达式并将他们转化为类里面的私有函数
- Function Interface
- Java本身提供的一些Function Interface
- Class与interface的区别
  - multiple implement
  - field to be final and static
  - no instance
  - class implements an interface, the class must override all interface methods
  - Method to be public
  - default can be extend
  - implement and extend different 
  - Variables should be public only
  - to be abstract
  - have one special interface is function interface 
  - to be package-private
  - can be type
- 什么是闭包





