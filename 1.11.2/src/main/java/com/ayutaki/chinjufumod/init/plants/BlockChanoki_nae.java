package com.ayutaki.chinjufumod.init.plants;

import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.init.TTimePlants;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockChanoki_nae extends Block {

	/*苗木*/
	public BlockChanoki_nae() {
		super(Material.WOOD);

		this.setSoundType(SoundType.WOOD);
		this.setHardness(1.0F);
		this.setResistance(5.0F);

	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {

        ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();
        int k;
        k = itemstack.getMetadata();

		if (item != Items.DYE) { return true; }

    	else if (item == Items.DYE) {

    		if(k == 15) {

    		/*特定のアイテムを1個消費*/
            itemstack.shrink(1);
            return  worldIn.setBlockState(pos,TTimePlants.CHANOKI_2.getDefaultState()); }

    		if(k != 15) { return true; }

        }
    	return true;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {

		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();

		world.scheduleUpdate(new BlockPos(i, j, k), this, this.tickRate(world));

	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {

		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();

		if (world.getBlockState(new BlockPos(i, j - 1, k)).getBlock() == Blocks.DIRT) {

    		if (world.getLightFromNeighbors(pos.up()) >= 9) {

    			world.setBlockState(new BlockPos(i, j, k), TTimePlants.CHANOKI_1.getDefaultState());
    		}
    		world.scheduleUpdate(new BlockPos(i, j, k), this, this.tickRate(world));
    	}

	}

	@Override
	public int tickRate(World world) {
		return 6000;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos) {

		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();

		if (world.getBlockState(new BlockPos(i, j - 1, k)).getBlock() != Blocks.DIRT) {

			TTimePlants.CHANOKI_nae.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.75D, 0.8125D);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		int meta = stack.getMetadata();
		tooltip.add(I18n.format("tips.tile.block_wood_chanoki_nae.name", meta));
	}
}