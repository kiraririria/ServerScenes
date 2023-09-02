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

public class RenderCustomNpcTransformer extends ClassTransformer
{
    @Override
    public void process(String name, ClassNode node)
    {
        for (MethodNode method : node.methods)
        {
            if (method.name.equals("doRender") && method.desc.equals("(Lnoppes/npcs/entity/EntityNPCInterface;DDDFF)V"))
            {
                this.processDoRender(method);
            }
            if (method.name.equals("func_76979_b") && method.desc.equals("(Lnet/minecraft/entity/Entity;DDDFF)V"))
            {
                this.processShadowRender(method);
            }
        }
    }

    public void processDoRender(MethodNode method)
    {
        LabelNode label = this.getFirstLabel(method);
        final String entity = CoreClassTransformer.get("Lvx;DDDFF", "Lnet/minecraft/entity/EntityCreature;DDDFF");
        if (label != null)
        {
            InsnList list = new InsnList();
            list.add(new VarInsnNode(Opcodes.ALOAD, 1));
            list.add(new VarInsnNode(Opcodes.DLOAD, 2));
            list.add(new VarInsnNode(Opcodes.DLOAD, 4));
            list.add(new VarInsnNode(Opcodes.DLOAD, 6));
            list.add(new VarInsnNode(Opcodes.FLOAD, 8));
            list.add(new VarInsnNode(Opcodes.FLOAD, 9));
            list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "kiraririria/serverscenes/client/RenderingHandler", "isCanceledNPC", "(" + entity + ")Z", false));
            list.add(new JumpInsnNode(Opcodes.IFEQ, label));
            list.add(new VarInsnNode(Opcodes.ALOAD, 1));
            list.add(new VarInsnNode(Opcodes.DLOAD, 2));
            list.add(new VarInsnNode(Opcodes.DLOAD, 4));
            list.add(new VarInsnNode(Opcodes.DLOAD, 6));
            list.add(new VarInsnNode(Opcodes.FLOAD, 8));
            list.add(new VarInsnNode(Opcodes.FLOAD, 9));
            list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "kiraririria/serverscenes/client/RenderingHandler", "renderNPC", "(" + entity + ")V", false));
            list.add(new InsnNode(Opcodes.RETURN));
            method.instructions.insert(list);
            Serverscenes.log("[>---doRender was changed successfully---<]");
        }

    }

    public void processShadowRender(MethodNode method)
    {
        LabelNode label = this.getFirstLabel(method);
        final String entity = CoreClassTransformer.get("Lvg;DDDFF", "Lnet/minecraft/entity/Entity;DDDFF");
        if (label != null)
        {
            InsnList list = new InsnList();
            list.add(new VarInsnNode(Opcodes.ALOAD, 1));
            list.add(new VarInsnNode(Opcodes.DLOAD, 2));
            list.add(new VarInsnNode(Opcodes.DLOAD, 4));
            list.add(new VarInsnNode(Opcodes.DLOAD, 6));
            list.add(new VarInsnNode(Opcodes.FLOAD, 8));
            list.add(new VarInsnNode(Opcodes.FLOAD, 9));
            list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "kiraririria/serverscenes/client/RenderingHandler", "isCanceledShadows", "(" + entity + ")Z", false));
            list.add(new JumpInsnNode(Opcodes.IFEQ, label));
            list.add(new VarInsnNode(Opcodes.ALOAD, 1));
            list.add(new VarInsnNode(Opcodes.DLOAD, 2));
            list.add(new VarInsnNode(Opcodes.DLOAD, 4));
            list.add(new VarInsnNode(Opcodes.DLOAD, 6));
            list.add(new VarInsnNode(Opcodes.FLOAD, 8));
            list.add(new VarInsnNode(Opcodes.FLOAD, 9));
            list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "kiraririria/serverscenes/client/RenderingHandler", "renderShadows", "(" + entity + ")V", false));
            list.add(new InsnNode(Opcodes.RETURN));
            method.instructions.insert(list);
            Serverscenes.log("[>---renderShadow was changed successfully---<]");
        }
    }
}