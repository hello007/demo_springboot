# spring-boot

基于Gradle5.6.4的一个基础的SpringBoot工程，其他工程复制一份当前工程即可



# spring-boot-swagger

测试swagger功能的SpringBoot工程



# spring-boot-test

测试部分SpringBoot工程的简单demo

## Bean加载顺序

### test.bean.init

测试结果：

```
bean constructor
调用postProcessBeforeInitialization...
调用Bean的函数(postConstruct)
bean initializing
调用Bean的函数(initMethod)
调用postProcessAfterInitialization...
// shutdown
调用Bean的函数(preDestroy)
bean disposable
调用Bean的函数(destroyMethod)
```



### Bean生命周期

<center>
<img style="border-radius: 0.3125em;
box-shadow: 0 2px 4px 0 rgba(34,36,38,.12),0 2px 10px 0 rgba(34,36,38,.08);" 
src="https://gitee.com/liu1204/images/raw/master/img/7qDql3.jpg">
<br>
<div style="color:orange; border-bottom: 1px solid #d9d9d9;
display: inline-block;
color: #999;
padding: 2px;">Bean的生命周期</div>
</center>

