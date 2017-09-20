1）用final修饰的类不能被继承，没有子类；用final修饰的方法不能被子类的方法覆盖；用final修饰的变量表示常量，只能被赋一次值。 
final不能用来修饰构造方法，父类中用private修饰的方法不能被子类的方法覆盖。final方法是不允许子类方法中写一个同样签名的方法的，但private的情况下，你可以定义一个同样签名的方法。（final方法不能重载，private方法可以重载） 
class FinalExtends { 
private void goDown() { 
System.out.println("hello A"); 
} 
} 

class FinalTest extends FinalExtends { 
public void goDown() { 
System.out.println("hello B"); 
} 

public static void main(String[] args) { 
FinalTest xx = new FinalTest(); 
xx.goDown(); 
} 
} 
结果为： 
hello B 
2）final类 
final类不能被继承，因此final类的成员方法没有机会被覆盖，默认都是final 的。在设计类时候，如果这个类不需要有子类，类的实现细节不允许改变，并且确信这个类不会载被扩展，那么就设计为final类。 
3) final方法 
如果一个类不允许其子类覆盖某个方法，则可以把这个方法声明为final方法。 使用final方法的原因有二： 
第一、把方法锁定，防止任何继承类修改它的意义和实现。 
第二、高效。编译器在遇到调用final方法时候会转入内嵌机制，大大提高执行效率。 
class Test2 { 
public void f1() { 
System.out.println("f1"); 
} 

// 无法被子类覆盖的方法 
public final void f2() { 
System.out.println("f2"); 
} 

public void f3() { 
System.out.println("f3"); 
} 

private void f4() { 
System.out.println("f4"); 
} 
} 

public class Test1 extends Test2 { 

public void f1() { 
System.out.println("Test2父类方法f1被覆盖!"); 
} 

public static void main(String[] args) { 
Test1 t = new Test1(); 
t.f1(); 
t.f2(); // 调用从父类继承过来的final方法 
t.f3(); // 调用从父类继承过来的方法 
//t.f4(); //调用失败，无法从父类继承获得 
} 
} 
结果为： 
Test2父类方法f1被覆盖! 
f2 
f3 
4）final变量（常量） 
用final修饰的成员变量表示常量，值一旦给定就无法改变！ 
final修饰的变量有三种：静态变量、实例变量和局部变量，分别表示三种类型的常量。 
从下面的例子中可以看出，一旦给final变量初值后，值就不能再改变了。 
另外，final变量定义的时候，可以先声明，而不给初值，这中变量也称为final空白，无论什么情况，编译器都确保空白final在使用之前必须被初始化。但是，final空白在final关键字final的使用上提供了更大的灵活性，为此，一个类中的f 

inal数据成员就可以实现依对象而有所不同，却有保持其恒定不变的特征。 
对于final类型的实例变量，可以在定义变量时，或者在构造方法中进行初始化；对于final类型的静态变量，可以在定义变量时进行初始化，或者在静态代码块中初始化。 
public class Sample { 
static final int a = 10; 
static final int b; 
static {//静态块中初始化final类型静态成员变量 
b = 15; 
} 

void methodPrintln() { 
System.out.println("调用方法输出："); 
System.out.println(a); 
System.out.println(b); 
} 

public static void main(String[] args) { 
Sample sa = new Sample(); 
System.out.println("实例化对象输入"); 
System.out.println(sa.a); 
System.out.println(sa.b); 

System.out.println("静态成员变量，不用实例化也可以输出："); 
System.out.println(Sample.a); 
System.out.println(Sample.b); 

sa.methodPrintln(); 
} 
} 
结果为： 
实例化对象输入 
10 
15 
静态成员变量，不用实例化也可以输出： 
10 
15 
调用方法输出： 
10 
15 

如果将引用类型的变量用final修饰，那么该变量只能始终引用一个对象，但可以改变对象的内容。 
public class FinalSample { 
public int var; 

public FinalSample(int var) { 
this.var = var; 
} 

public static void main(String[] args) { 
final FinalSample s = new FinalSample(1); 
s.var = 2; 
System.out.println(s.var); 

// 出错，不能改变引用变量s所引用的FinalSample类的对象 
// s=new FinalSample(2); 
} 
} 
5）final参数 
当函数参数为final类型时，你可以读取使用该参数，但是无法改变该参数的值。 
class Test4 { 
public static void main(String[] args) { 
new Test4().f1(2); 
} 

public void f1(final int i) { 
// i++; //i是final类型的,值不允许改变的. 
System.out.print(i); 
} 
} 