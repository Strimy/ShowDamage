package strimy.bukkit.plugins.showdamage;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ShowDamageManager extends JavaPlugin {

	public static ShowDamageManager Instance;
	
	private boolean isEnabledByDefault = true;
	private ShowDamage plugin;
	private PluginManager pm;
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		pm.disablePlugin(plugin);

	}

	@Override
	public void onEnable() {

		Instance = this;
		getServer().getLogger().info("["+getDescription().getName()+" "+ getDescription().getVersion()+"] Plugin loaded !");
		PluginCommand command = getCommand("showdamage");
		if(command != null)
		{
			command.setExecutor(new ShowDamageCommandExecutor());
		}
		plugin = new ShowDamage(getPluginLoader(), getServer(), getDescription(), getFile(), getFile(), getClassLoader());
		if(plugin.getPluginLoader() == null)
		{
			getServer().getLogger().info("Loader null");
		}
		pm = getServer().getPluginManager();
		
		if(isEnabledByDefault)
		{
			Enable();
		}
	}
	
	public void Enable()
	{
		pm.enablePlugin(plugin);
	}
	
	public void Disable()
	{
		pm.disablePlugin(plugin);
	}
	
	public boolean isPluginEnabled()
	{
		return plugin.isEnabled();
	}
}
