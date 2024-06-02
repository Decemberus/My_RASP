# My_RASP

## 项目简介

本项目一共有两个目录，一个是RASP目录，就是代表RASP的主实现功能

另一个webuse目录就是简单写了一个页面用来测试命令执行是否能够被执行



## 使用方法

首先生成agent.jar的包

```
mvn clean install
```

配置好本机的tomcat，我使用的是tomcat9，即可启动web



访问测试页面，执行whoami的操作

```
http://localhost:8080/cmd.jsp?cmd=whoami
```

![image-20240602131931987](https://enjoyy-1322917755.cos.ap-nanjing.myqcloud.com/image-20240602131931987.png)

在tomcat路径下可以拿到供给制执行的命令





在TestClassVisitor中使用这些被注释是的代码即可打印出攻击者执行的命令的调用栈信息

```
    //拿到攻击者执行的命令的调用栈信息
//    @Override
//    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
//
////        System.out.println(name + "方法的描述符是：" + desc);
//        System.out.println("the describtion of "+ name +" is " + desc);
//        return mv;
//    }
```

