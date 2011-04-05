package strimy.bukkit.plugins.showdamage;

import java.io.File;

import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;


public class ShowDamage extends JavaPlugin 
{
	public static ShowDamage Instance;
	
	public ShowDamage(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc, File folder, File plugin, ClassLoader cLoader)
	{
		initialize(pluginLoader, instance, desc, folder, plugin, cLoader);
	}
	
	@Override
	public void onDisable() 
	{
		getServer().getLogger().info("[ShowDamage] Plugin disabled !");
	}

	@Override
	public void onEnable() 
	{
		Instance = this;
		getServer().getLogger().info("[ShowDamage] Plugin enabled !");
		
		SDEntityListener listener = new SDEntityListener(this);

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, listener , Priority.Monitor, this);
	}
}
