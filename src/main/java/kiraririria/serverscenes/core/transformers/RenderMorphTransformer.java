package kiraririria.serverscenes.core.transformers;

import kiraririria.serverscenes.Serverscenes;
import kiraririria.serverscenes.core.ClassTransformer;
import kiraririria.serverscenes.core.CoreClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class RenderMorphTransformer extends ClassTransformer
{
    @Override
    public void process(String name, ClassNode node)
    {

        for (MethodNode method : node.methods)
        {
            if (method.name.equals("render") && method.desc.equals("(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V"))
            {
                this.processRender(method);
            }
        }
    }

    public void processRender(MethodNode method)
    {
        LabelNode label = this.getFirstLabel(method);
        final String descs = CoreClassTransformer.get("Lvp;DDDFF", "Lnet/minecraft/entity/EntityLivingBase;DDDFF");
        if (label != null)
        {
            InsnList list = new InsnList();
            list.add(new VarInsnNode(Opcodes.ALOAD, 1));
            list.add(new VarInsnNode(Opcodes.DLOAD, 2));
            list.add(new VarInsnNode(Opcodes.DLOAD, 4));
            list.add(new VarInsnNode(Opcodes.DLOAD, 6));
            list.add(new VarInsnNode(Opcodes.FLOAD, 8));
            list.add(new VarInsnNode(Opcodes.FLOAD, 9));
            list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "kiraririria/serverscenes/client/MorphRenderPatcher", "isPatchable", "(" + descs + ")Z", false));
            list.add(new JumpInsnNode(Opcodes.IFEQ, label));
            list.add(new VarInsnNode(Opcodes.ALOAD, 1));
            list.add(new VarInsnNode(Opcodes.DLOAD, 2));
            list.add(new VarInsnNode(Opcodes.DLOAD, 4));
            list.add(new VarInsnNode(Opcodes.DLOAD, 6));
            list.add(new VarInsnNode(Opcodes.FLOAD, 8));
            list.add(new VarInsnNode(Opcodes.FLOAD, 9));
            list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "kiraririria/serverscenes/client/MorphRenderPatcher", "patchMorph", "(" + descs + ")V", false));
            list.add(new InsnNode(Opcodes.RETURN));
            method.instructions.insert(list);
            Serverscenes.log("[>---render was changed successfully---<]");
        }
    }
}