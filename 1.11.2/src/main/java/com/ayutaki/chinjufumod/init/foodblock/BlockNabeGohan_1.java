package com.ayutaki.chinjufumod.init.foodblock;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.init.ChinjufuModFoodBlocks;
import com.ayutaki.chinjufumod.init.TTimeItems;
import com.ayutaki.chinjufumod.init.blocks.BlockFacingSapo;
import com.ayutaki.chinjufumod.util.CollisionHelper;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockNabeGohan_1 extends BlockFacingSapo {

	private static final AxisAlignedBB BOUNDING_BOX_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);
	private static final AxisAlignedBB BOUNDING_BOX_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);
	private static final AxisAlignedBB BOUNDING_BOX_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);
	private static final AxisAlignedBB BOUNDING_BOX_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.21875, 0.0, 0.34375, 0.78125, 0.28125, 0.90625);
	private static final AxisAlignedBB[] BOUNDING_BOX = { BOUNDING_BOX_SOUTH, BOUNDING_BOX_WEST, BOUNDING_BOX_NORTH, BOUNDING_BOX_EAST };

	public BlockNabeGohan_1()  {
		super(Material.WOOD);

		/*鍋・皿*/
		this.setSoundType(SoundType.STONE);
		this.setHardness(1.0F);
		this.setResistance(5.0F);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {

        ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();

    	if (item == TTimeItems.Item_CHAWAN) {

            itemstack.shrink(1);

			if (itemstack.isEmpty()) {
				playerIn.setHeldItem(hand, new ItemStack(Item.getItemFromBlock(ChinjufuModFoodBlocks.GOHAN_1)));
			}
			else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Item.getItemFromBlock(ChinjufuModFoodBlocks.GOHAN_1)))) {
				playerIn.dropItem(new ItemStack(Item.getItemFromBlock(ChinjufuModFoodBlocks.GOHAN_1)), false);
			}

	    	return  worldIn.setBlockState(pos, ChinjufuModFoodBlocks.NABEGOHAN_2
						.getDefaultState().withProperty(FACING, state.getValue(FACING)));
        }

       	else if (item == TTimeItems.Item_SARA) {

            itemstack.shrink(1);

			if (itemstack.isEmpty()) {
				playerIn.setHeldItem(hand, new ItemStack(Item.getItemFromBlock(ChinjufuModFoodBlocks.RICE_1)));
			}
			else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Item.getItemFromBlock(ChinjufuModFoodBlocks.RICE_1)))) {
				playerIn.dropItem(new ItemStack(Item.getItemFromBlock(ChinjufuModFoodBlocks.RICE_1)), false);
			}

	    	return  worldIn.setBlockState(pos, ChinjufuModFoodBlocks.NABEGOHAN_2
						.getDefaultState().withProperty(FACING, state.getValue(FACING)));
        }

       	else if (item == Items.BOWL) {

            itemstack.shrink(1);


			if (itemstack.isEmpty()) {
				playerIn.setHeldItem(hand, new ItemStack(TTimeItems.MUSHIGOME));
			}
			else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(TTimeItems.MUSHIGOME))) {
				playerIn.dropItem(new ItemStack(TTimeItems.MUSHIGOME), false);
			}
	    	return  worldIn.setBlockState(pos, ChinjufuModFoodBlocks.NABE_kara
						.getDefaultState().withProperty(FACING, state.getValue(FACING)));
        }
    	return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {

		EnumFacing facing = state.getValue(FACING);
		return BOUNDING_BOX[facing.getHorizontalIndex()];
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes,
			@Nullable Entity entityIn, boolean p_185477_7_) {

		EnumFacing facing = state.getValue(FACING);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX[facing.getHorizontalIndex()]);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
	    return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
	    return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		int meta = stack.getMetadata();
		tooltip.add(I18n.format("tips.tile.block_food_nabegohan_1.name", meta));
	}
}
