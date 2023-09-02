package kiraririria.serverscenes.core.transformers;

import kiraririria.serverscenes.Serverscenes;
import kiraririria.serverscenes.core.ClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import java.util.ListIterator;

public class AbstractMorphTransformer extends ClassTransformer
{
    @Override
    public void process(String name, ClassNode node)
    {
        FieldNode renderFor = new FieldNode(Opcodes.ASM4, Opcodes.ACC_PUBLIC, "renderFor", "Ljava/lang/String;", null, null);
        node.fields.add(renderFor);
        for (MethodNode method : node.methods)
        {
            if (method.name.equals("<init>") && method.desc.equals("()V"))
            {
                processInit(method);
            }
            if (method.name.equals("toNBT") && method.desc.equals("(Lnet/minecraft/nbt/NBTTagCompound;)V"))
            {
                processToNBT(method);
            }
            if (method.name.equals("fromNBT") && method.desc.equals("(Lnet/minecraft/nbt/NBTTagCompound;)V"))
            {
                processFromNBT(method);
            }
            if (method.name.equals("copy") && method.desc.equals("(Lmchorse/metamorph/api/morphs/AbstractMorph;)V"))
            {
                processCopy(method);
            }
            if (method.name.equals("equals") && method.desc.equals("(Ljava/lang/Object;)Z"))
            {
                processEquals(method);
            }
        }
    }

    public void processInit(MethodNode method)
    {
        ListIterator<AbstractInsnNode> iterator = method.instructions.iterator();
        int index = 0;
        while (iterator.hasNext())
        {
            ++index;
            AbstractInsnNode node = iterator.next();
            if (node.getOpcode() == 18)
            {
                if (((LdcInsnNode) node).cst.equals(""))
                {
                    index += 2;
                    break;
                }
            }
        }

        InsnList list = new InsnList();
        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
        LdcInsnNode ldcInsnNode = new LdcInsnNode(Opcodes.LDC);
        ldcInsnNode.cst = "";
        list.add(ldcInsnNode);
        list.add(new FieldInsnNode(Opcodes.PUTFIELD, "mchorse/metamorph/api/morphs/AbstractMorph", "renderFor", "Ljava/lang/String;"));
        method.instructions.insert(method.instructions.get(index + 2), list);
        Serverscenes.log("[>---<init> was changed successfully---<]");
    }

    public void processToNBT(MethodNode method)
    {
        InsnList list = new InsnList();
        list.add(new VarInsnNode(Opcodes.ALOAD, 1));
        list.add(new FieldInsnNode(Opcodes.GETSTATIC, "kiraririria/serverscenes/Serverscenes", "NBT_RENDER_FOR", "Ljava/lang/String;"));
        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
        list.add(new FieldInsnNode(Opcodes.GETFIELD, "mchorse/metamorph/api/morphs/AbstractMorph", "renderFor", "Ljava/lang/String;"));
        list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/nbt/NBTTagCompound", "setString", "(Ljava/lang/String;Ljava/lang/String;)V", false));
        method.instructions.insert(this.getFirstLabel(method), list);
        Serverscenes.log("[>---toNBT was changed successfully---<]");
    }

    public void processFromNBT(MethodNode method)
    {
        ListIterator<AbstractInsnNode> iterator = method.instructions.iterator();
        int index = 0;
        while (iterator.hasNext())
        {
            ++index;
            AbstractInsnNode node = iterator.next();
            if (node.getOpcode() == 18)
            {
                index += 2;
                break;
            }
        }

        InsnList list = new InsnList();
        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
        list.add(new VarInsnNode(Opcodes.ALOAD, 1));
        list.add(new FieldInsnNode(Opcodes.GETSTATIC, "kiraririria/serverscenes/Serverscenes", "NBT_RENDER_FOR", "Ljava/lang/String;"));
        list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "net/minecraft/nbt/NBTTagCompound", "getString", "(Ljava/lang/String;)Ljava/lang/String;", false));
        list.add(new FieldInsnNode(Opcodes.PUTFIELD, "mchorse/metamorph/api/morphs/AbstractMorph", "renderFor", "Ljava/lang/String;"));
        method.instructions.insert(method.instructions.get(index + 2), list);
        Serverscenes.log("[>---fromNBT was changed successfully---<]");
    }

    public void processCopy(MethodNode method)
    {
        InsnList list = new InsnList();
        list.add(new VarInsnNode(Opcodes.ALOAD, 0));
        list.add(new VarInsnNode(Opcodes.ALOAD, 1));
        list.add(new FieldInsnNode(Opcodes.GETFIELD, "mchorse/metamorph/api/morphs/AbstractMorph", "renderFor", "Ljava/lang/String;"));
        list.add(new FieldInsnNode(Opcodes.PUTFIELD, "mchorse/metamorph/api/morphs/AbstractMorph", "renderFor", "Ljava/lang/String;"));
        method.instructions.insert(getFirstLabel(method), list);
        Serverscenes.log("[>---copy was changed successfully---<]");
    }

    public void processEquals(MethodNode method)
    {
        InsnList instructions = method.instructions;
        LabelNode label = new LabelNode();

        for (AbstractInsnNode insnNode : instructions.toArray())
        {
            if (insnNode.getOpcode() == Opcodes.ICONST_0)
            {
                label = (LabelNode) insnNode.getPrevious().getPrevious();
            }
        }
        for (AbstractInsnNode insnNode : instructions.toArray())
        {
            if ((insnNode instanceof LabelNode) && (insnNode.getPrevious() instanceof VarInsnNode))
            {
                InsnList newInstructions = new InsnList();
                newInstructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
                newInstructions.add(new FieldInsnNode(Opcodes.GETFIELD, "mchorse/metamorph/api/morphs/AbstractMorph", "renderFor", "Ljava/lang/String;"));
                newInstructions.add(new VarInsnNode(Opcodes.ALOAD, 2));
                newInstructions.add(new FieldInsnNode(Opcodes.GETFIELD, "mchorse/metamorph/api/morphs/AbstractMorph", "renderFor", "Ljava/lang/String;"));
                newInstructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/util/Objects", "equals", "(Ljava/lang/Object;Ljava/lang/Object;)Z", false));
                newInstructions.add(new JumpInsnNode(Opcodes.IFEQ, label));
                instructions.insert(insnNode, newInstructions);
                break;
            }
        }
        Serverscenes.log("[>---equals was changed successfully---<]");
    }
}