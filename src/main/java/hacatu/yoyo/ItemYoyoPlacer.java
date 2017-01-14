package hacatu.yoyo;

import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.SoundCategory;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;

import hacatu.yoyo.Yoyo;

public class ItemYoyoPlacer extends Item{
	public ItemYoyoPlacer(){
		super();
		setUnlocalizedName("yoyo_placer");
		setRegistryName("yoyo_placer");
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.TOOLS);
	}
	
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();
		if(!block.isReplaceable(worldIn, pos)){
			pos = pos.offset(facing);
		}
		ItemStack itemstack = player.getHeldItem(hand);
		if(player.canPlayerEdit(pos, facing, itemstack) && worldIn.mayPlace(Yoyo.yoyoBlock, pos, false, facing, (Entity)null)){
			SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, player);
			worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
			worldIn.setBlockState(pos, Yoyo.yoyoBlock.getDefaultState(), 11);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}