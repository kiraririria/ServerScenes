package kiraririria.serverscenes.core.transformers;

import java.util.Iterator;

import kiraririria.serverscenes.core.ClassMethodTransformer;
import kiraririria.serverscenes.core.CoreClassTransformer;
import kiraririria.serverscenes.core.SSCoreClassTransformer;
import kiraririria.serverscenes.core.SSCoreMod;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class RenderCustomNpcTransformer extends ClassMethodTransformer
{
    public RenderCustomNpcTransformer()
    {
        super();
        this.setMcp("doRender", "(Lnoppes/npcs/entity/EntityCustomNpc;DDDFF)V");

//        this.setMcp("setBlockState", "(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;I)Z");
//        this.setNotch("a", "(Let;Lawt;I)Z");
    }

    @Override
    public void processMethod(String name, MethodNode method)
    {
        InsnList list = method.instructions;
        SSCoreClassTransformer.debugInstructions(list);
//        while (it.hasNext())
//        {
//            AbstractInsnNode node = it.next();
//            if (node.getOpcode() == Opcodes.IRETURN)
//            {
//                i++;
//                continue;
//            }
//
//            if (i == 2)
//            {
//                InsnList newList = new InsnList();
//                String desc = CoreClassTransformer.get("(Lamu;Let;Lawt;I)V", "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;I)V");
//                newList.add(new VarInsnNode(Opcodes.ALOAD, 0));
//                newList.add(new VarInsnNode(Opcodes.ALOAD, 1));
//                newList.add(new VarInsnNode(Opcodes.ALOAD, 2));
//                newList.add(new VarInsnNode(Opcodes.ILOAD, 3));
//                newList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "mchorse/blockbuster/recording/capturing/WorldEventListener", "setBlockState", desc, false));
//                list.insert(node, newList);
//                System.out.println("BBCoreMod: successfully patched setBlockState!");
//                break;
//            }
//        }
    }
}