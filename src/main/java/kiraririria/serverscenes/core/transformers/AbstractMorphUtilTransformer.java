package kiraririria.serverscenes.core.transformers;

import kiraririria.serverscenes.Serverscenes;
import mchorse.blockbuster.utils.mclib.coremod.ClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import java.util.Iterator;

public class AbstractMorphUtilTransformer extends ClassTransformer
{
    @Override
    public void process(String s, ClassNode node)
    {
        for (MethodNode method : node.methods)
        {

            if (method.name.equals("getRenderFor") && method.desc.equals("(Lmchorse/metamorph/api/morphs/AbstractMorph;)Ljava/lang/String;"))
            {
                this.processGetRenderFor(method);
            }
            if (method.name.equals("setRenderFor") && method.desc.equals("(Ljava/lang/String;Lmchorse/metamorph/api/morphs/AbstractMorph;)V"))
            {
                this.processSetRenderFor(method);
            }
        }
    }

    public void processGetRenderFor(MethodNode method)
    {
        Iterator<AbstractInsnNode> it = method.instructions.iterator();
        AbstractInsnNode target = null;
        int index = -3;

        while (it.hasNext())
        {
            index++;
            AbstractInsnNode node = it.next();
            if (node.getOpcode() == Opcodes.LDC)
            {
                target = node;
                break;
            }
        }

        method.instructions.remove(target);
        target = method.instructions.get(index + 1);
        InsnList list = new InsnList();
        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
        list.add(new FieldInsnNode(Opcodes.GETFIELD, "mchorse/metamorph/api/morphs/AbstractMorph", "renderFor", "Ljava/lang/String;"));
        method.instructions.insert(target, list);
        Serverscenes.log("[>---getRenderFor was changed successfully---<]");
    }

    public void processSetRenderFor(MethodNode method)
    {
        Iterator<AbstractInsnNode> it = method.instructions.iterator();
        AbstractInsnNode target = null;
        int index = 0;

        while (it.hasNext())
        {
            index++;
            AbstractInsnNode node = it.next();
            if (node.getOpcode() == Opcodes.RETURN)
            {
                break;
            }
        }
        target = method.instructions.get(index - 2);
        InsnList list = new InsnList();
        list.add(new VarInsnNode(Opcodes.ALOAD, 1));
        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
        list.add(new FieldInsnNode(Opcodes.PUTFIELD, "mchorse/metamorph/api/morphs/AbstractMorph", "renderFor", "Ljava/lang/String;"));
        method.instructions.insert(target, list);
        Serverscenes.log("[>---setRenderFor was changed successfully---<]");
    }
}
