package org.enjoy;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

public class TestClassVisitor extends ClassVisitor implements Opcodes {

    public TestClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

        if ("start".equals(name) && "()Ljava/lang/Process;".equals(desc)) {
            System.out.println("the describtion of "+ name +" is " + desc);
            //插入ProcessBuilderHook.start(this.command)
            return new AdviceAdapter(Opcodes.ASM5, mv, access, name, desc) {
                @Override
                public void visitCode() {

                    mv.visitVarInsn(ALOAD, 0);//获取this，也就是ProcessBuilder对象里面的那个this
                    mv.visitFieldInsn(GETFIELD, "java/lang/ProcessBuilder", "command", "Ljava/util/List;");//获取this中的command
                    //调用我们上面新建的ProcessBuilderHook类中的start方法,将上面拿到的this.command压入我们方法
                    mv.visitMethodInsn(INVOKESTATIC, "org/enjoy/ProcessBuilderHook", "start", "(Ljava/util/List;)V", false);
                    super.visitCode();
                }
            };
        }
        return mv;
    }

    //拿到攻击者执行的命令的调用栈信息
//    @Override
//    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
//
////        System.out.println(name + "方法的描述符是：" + desc);
//        System.out.println("the describtion of "+ name +" is " + desc);
//        return mv;
//    }
}
