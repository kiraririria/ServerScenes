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
            if (method.name.equals("renderOnScreen") && method.desc.equals("(Lnet/minecraft/entity/player/EntityPlayer;IIFF)V"))
            {
                this.processRenderOnScreen(method);
            }

        }
    }

    public void processRender(MethodNode method)
    {
        LabelNode label = this.getFirstLabel(method);
        final String descs = CoreClassTransformer.get("Lmchorse/metamorph/api/morphs/AbstractMorph;Lvp;DDDFF", "Lmchorse/metamorph/api/morphs/AbstractMorph;Lnet/minecraft/entity/EntityLivingBase;DDDFF");
        if (label != null)
        {
            InsnList list = new InsnList();
            list.add(new VarInsnNode(Opcodes.ALOAD, 0));
            list.add(new VarInsnNode(Opcodes.ALOAD, 1));
            list.add(new VarInsnNode(Opcodes.DLOAD, 2));
            list.add(new VarInsnNode(Opcodes.DLOAD, 4));
            list.add(new VarInsnNode(Opcodes.DLOAD, 6));
            list.add(new VarInsnNode(Opcodes.FLOAD, 8));
            list.add(new VarInsnNode(Opcodes.FLOAD, 9));
            list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "kiraririria/serverscenes/client/MorphRenderPatcher", "isPatchable", "(" + descs + ")Z", false));
            list.add(new JumpInsnNode(Opcodes.IFEQ, label));
            list.add(new VarInsnNode(Opcodes.ALOAD, 0));
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

    public void processRenderOnScreen(MethodNode method)
    {
        LabelNode label = this.getFirstLabel(method);
        final String descs = CoreClassTransformer.get("Lmchorse/metamorph/api/morphs/AbstractMorph;Laed;IIFF", "Lmchorse/metamorph/api/morphs/AbstractMorph;Lnet/minecraft/entity/player/EntityPlayer;IIFF");
        if (label != null)
        {
            InsnList list = new InsnList();
            list.add(new VarInsnNode(Opcodes.ALOAD, 0));
            list.add(new VarInsnNode(Opcodes.ALOAD, 1));
            list.add(new VarInsnNode(Opcodes.ILOAD, 2));
            list.add(new VarInsnNode(Opcodes.ILOAD, 3));
            list.add(new VarInsnNode(Opcodes.FLOAD, 4));
            list.add(new VarInsnNode(Opcodes.FLOAD, 5));
            list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "kiraririria/serverscenes/client/MorphRenderPatcher", "isPatchable", "(" + descs + ")Z", false));
            list.add(new JumpInsnNode(Opcodes.IFEQ, label));
            list.add(new VarInsnNode(Opcodes.ALOAD, 0));
            list.add(new VarInsnNode(Opcodes.ALOAD, 1));
            list.add(new VarInsnNode(Opcodes.ILOAD, 2));
            list.add(new VarInsnNode(Opcodes.ILOAD, 3));
            list.add(new VarInsnNode(Opcodes.FLOAD, 4));
            list.add(new VarInsnNode(Opcodes.FLOAD, 5));
            list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "kiraririria/serverscenes/client/MorphRenderPatcher", "patchMorph", "(" + descs + ")V", false));
            list.add(new InsnNode(Opcodes.RETURN));
            method.instructions.insert(list);
            Serverscenes.log("[>---renderOnScreen was changed successfully---<]");
        }
    }
}