package hacatu.yoyo;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.Nonnull;

public class BlockYoyoBlock extends Block{
	public BlockYoyoBlock(){
		super(Material.GOURD, MapColor.AIR);
		this.setUnlocalizedName("yoyo_block");
		this.setRegistryName("yoyo_block");
		this.setHardness(0f);
		this.setResistance(17f);
	}
	
	public int tickRate(World worldIn){
		return 100;
	}
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state){
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
	
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand){}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand){
		if(!worldIn.isRemote){
			worldIn.setBlockToAir(pos);
		}
	}
	
	public int quantityDropped(Random random){
		return 0;
	}
	
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity){
		return !(entity instanceof net.minecraft.entity.boss.EntityDragon || entity instanceof net.minecraft.entity.boss.EntityWither || entity instanceof net.minecraft.entity.projectile.EntityWitherSkull);
	}
}

