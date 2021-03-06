package com.github.atomicblom.shearmadness.api;

import com.google.common.collect.Iterators;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.Iterator;
import java.util.Objects;

@SuppressWarnings("UtilityClass")
public final class ItemStackHelper
{
    private ItemStackHelper() {}

    public static boolean isStackForBlock(ItemStack itemStack, Block block)
    {
        if (itemStack == null) return false;
        final Item item = itemStack.getItem();
        if (item instanceof ItemBlock) {
            if (Objects.equals(((ItemBlock) item).block, block)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isStackForBlockSubclassOf(ItemStack itemStack, Class<? extends Block> blockClass)
    {
        if (itemStack == null) return false;
        final Item item = itemStack.getItem();
        if (item instanceof ItemBlock) {
            if (blockClass.isAssignableFrom(((ItemBlock) item).block.getClass())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isStackForBlock(ItemStack itemStack, Block block, int meta) {
        return isStackForBlock(itemStack, block) && itemStack.getItemDamage() == meta;
    }

    public static boolean isStackForBlock(ItemStack itemStack, Block... blocks) {
        return isStackForBlock(itemStack, Iterators.forArray(blocks));
    }

    private static boolean isStackForBlock(ItemStack itemStack, Iterator<Block> blocks) {
        if (itemStack == null) return false;
        final Item item = itemStack.getItem();
        if (item instanceof ItemBlock) {
            final Block block = ((ItemBlock) item).block;
            while (blocks.hasNext()) {
                if (Objects.equals(blocks.next(), block)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isStackForBlock(ItemStack itemStack, Iterable<Block> blocks) {
        return isStackForBlock(itemStack, blocks.iterator());
    }
}