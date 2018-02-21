package lance5057.tDefense;

import lance5057.tDefense.core.materials.TDMaterials;
import lance5057.tDefense.core.parts.TDParts;
import lance5057.tDefense.core.tools.TDTools;
import lance5057.tDefense.holiday.HolidayBase;
import lance5057.tDefense.proxy.CommonProxy;
import lance5057.tDefense.util.RegEvents;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import scala.reflect.internal.Trees.Modifiers;
import slimeknights.mantle.client.CreativeTab;


@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.MOD_NAME, dependencies = "required-after:tconstruct")
public class TinkersDefense {

	private static int modGuiIndex = 0;
	//public static final int GUI_CREST_INV = modGuiIndex++;
	//public static final int GUI_ANVIL_INV = modGuiIndex++;
	//public static final int GUI_GUIDEBOOK = modGuiIndex++;
	public static final int GUI_STRAPS_INV = modGuiIndex++;

	@Mod.Instance(Reference.MOD_ID)
	public static TinkersDefense instance = new TinkersDefense();
	
	PacketHandler phandler = new PacketHandler();

	public static CreativeTab tab = new CreativeTab("TinkersDefense", new ItemStack(Items.SHIELD));
	
	public static HolidayBase holiday;
	//public static ModuleBase core;

	public static TD_Config config;

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE
			.newSimpleChannel(Reference.MOD_ID);

	public static RegEvents reg;
	
	public static Modifiers mods;

	public static TDParts parts;
	public static TDTools tools;
	public static TDMaterials mats;
	
	@SidedProxy(clientSide = "lance5057.tDefense.proxy.ClientProxy", serverSide = "lance5057.tDefense.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		
		NetworkRegistry.INSTANCE.registerGuiHandler(TinkersDefense.instance, new CommonProxy());
		
		holiday = new HolidayBase();
		
		mats = new TDMaterials();
		parts = new TDParts();
		tools = new TDTools();
		config = new TD_Config();
		
		//core.preInit(e);
		holiday.preInit(e);
		mats.preInit(e);
		parts.preInit(e);
		tools.preInit(e);
		proxy.preInit();
	}
	


	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		//core.init(e);
		holiday.init(e);
		mats.init(e);
		parts.init(e);
		tools.init(e);
		proxy.init();
		
		phandler.init();
		
		mats.integrate(mats.materials, mats.materialIntegrations, mats.deferredMaterials);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		mats.postInit(e);
		parts.postInit(e);
		tools.postInit(e);
		proxy.postInit();
	}
	
	
}
