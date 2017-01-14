package hacatu.yoyo;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import java.util.Map;

import hacatu.yoyo.proxy.CommonProxy;
import hacatu.yoyo.proxy.ClientProxy;

/**
 * Yo-yo adds an automatically vanishing scaffolding block to game.
 * 
 * @author hacatu
 */

@Mod(
	modid = Yoyo.modID,
	name = Yoyo.modName,
	version = Yoyo.modVersion,
	acceptedMinecraftVersions = "1.11")
public class Yoyo{
	public static final String modID = "yoyo";//TODO: refactor names like this into a separate file
	public static final String modVersion = "${version}";
	public static final String modName = "Yo-yo";
	
	public static Block yoyoBlock;
	public static Item yoyoPlacer;
	
	@Mod.Instance(modID)
	public static Yoyo instance;
	
	@SidedProxy(serverSide = "hacatu.yoyo.proxy.CommonProxy", clientSide = "hacatu.yoyo.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	public Yoyo(){
		//nothing to do
	}
	
	@NetworkCheckHandler()
	public boolean matchModVersions(Map<String, String> remoteVersions, Side side){
		if(side == Side.CLIENT){//servers with the mod should not accept clients without the mod
			return remoteVersions.containsKey(modID);
		}//but clients with the mod should connect to servers without it (it will just not run)
		return !remoteVersions.containsKey(modID) || modVersion.equals(remoteVersions.get(modID));
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		yoyoBlock = new BlockYoyoBlock();
		GameRegistry.register(yoyoBlock);
		yoyoPlacer = new ItemYoyoPlacer();
		ModelLoader.setCustomModelResourceLocation(yoyoPlacer, 0, new ModelResourceLocation(yoyoPlacer.getRegistryName(), "inventory"));
		GameRegistry.register(yoyoPlacer);
		GameRegistry.addShapedRecipe(new ItemStack(yoyoPlacer, 1, 0), "CCC", "CFC", "CCC", 'C', new ItemStack(Items.CHORUS_FRUIT_POPPED, 1, 0), 'F', new ItemStack(Items.FISHING_ROD, 1, 0));
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		//nothing to do
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		//nothing to do
	}
	
	@Mod.EventHandler
	public void onMissingMapping(FMLMissingMappingsEvent event){
		//nothing to do
	}
}

