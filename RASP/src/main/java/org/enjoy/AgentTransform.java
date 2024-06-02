package org.enjoy;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class AgentTransform implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        className.replace("/",".");//用.来表示className更符合阅读习惯


        try {
            if (className.contains("ProcessBuilder")){
                System.out.println("Load class" +className);
                ClassReader  classReader  = new ClassReader(classfileBuffer);
                ClassWriter classWriter  = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
                ClassVisitor classVisitor = new TestClassVisitor(classWriter);//通过TestClassVisitor来修改字节码

                classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);

                classfileBuffer = classWriter.toByteArray();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return classfileBuffer;
    }
}
