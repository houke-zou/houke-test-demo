# Stream

### [class1]. FourTypeTraversalUnitTest1
### [class2]. FourTypeTraversalUnitTest2
### [class3]. FourTypeTraversalUnitTest3

# 普通遍历
### 参考测试: [class1].transversal,[class2].transversal
1. 对象的创建(全参构造函数)的消耗, 远高于循环遍历.
2. 初期,时间消耗大的是stream并行(parallelStream) 模式, 后期(time > 200ms)parallelStream模式的优势显现
3. 增强for循环, 普通for循环, stream流三种方式难以辨别.
   [class1].transversal 中 普通for循环性能明显好于其他两者,但[class2].transversal中却相反.
### 参考测试: [class3].transversal
1. 循环中读类中静态值 和 读栈中值 耗时无差别.
2. 是否是包装类对性能有影响, 栈中对象转换成包装类对象需要花费时间. 当输入类型与所需类型一致时, 出并行stream外的其他循环都的出现了时间缩短现象.
3. 其他类(非常量)转换成所需的类需要耗费大量的时间.    
4. 对象类型的转变对 并行stream 无影响?


