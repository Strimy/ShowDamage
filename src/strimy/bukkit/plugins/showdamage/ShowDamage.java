package strimy.bukkit.plugins.showdamage;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;


public class ShowDamage extends JavaPlugin 
{
	public static ShowDamage Instance;
	
	
	@Override
	public void onDisable() 
	{
		getServer().getLogger().info("[ShowDamage] Plugin disabled !");
		Instance = null;
	}

	@Override
	public void onEnable() 
	{
		Instance = this;
		getServer().getLogger().info("[ShowDamage] Plugin enabled !");
		
		SDEntityListener listener = new SDEntityListener(this);
		
		getServer().getLogger().info("["+getDescription().getName()+" "+ getDescription().getVersion()+"] Plugin loaded !");
		PluginCommand command = getCommand("showdamage");
		if(command != null)
		{
			command.setExecutor(listener);
		}		

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(listener, this);
	}
}
