package jp.cafebabe.pochi.birthmarks.uc;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.signature.SignatureVisitor;

class SignatureWriter extends SignatureVisitor {
    private Helper helper;

    public SignatureWriter(Helper helper){
        super(Opcodes.ASM9);
        this.helper = helper;
    }

    @Override
    public void visitClassType(String classType){
        helper.add(classType);
    }
}
