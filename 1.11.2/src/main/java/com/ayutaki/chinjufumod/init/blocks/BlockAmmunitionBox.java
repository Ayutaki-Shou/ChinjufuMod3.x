package com.ayutaki.chinjufumod.init.blocks;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.init.ChinjufuModBlocks;
import com.ayutaki.chinjufumod.init.New_ChinjufuModWeapons;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockAmmunitionBox extends BlockFacingSapo {

	public BlockAmmunitionBox() {
		super(Material.WOOD);

		this.setSoundType(SoundType.WOOD);
		this.setHardness(1.0F);
		this.setResistance(5.0F);

	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	/*ドロップ指定、ピックアップ指定*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

        stack.add(new ItemStack(ChinjufuModBlocks.EMPTY_BOX, 1, this.damageDropped(state)));
        stack.add(new ItemStack(New_ChinjufuModWeapons.AMMUNITION_KC, 8, this.damageDropped(state)));
        return stack;

	}
}
