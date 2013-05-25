package strimy.bukkit.plugins.showdamage;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class SDEntityListener implements Listener, CommandExecutor
{
	HashMap<Player, LastDamage> lastDamages = new HashMap<Player, LastDamage>();
	ShowDamage plugin;
	ArrayList<Player> m_lstDisabled = new ArrayList<Player>();
	
	public SDEntityListener(ShowDamage plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onEntityDamage(EntityDamageEvent event) 
	{
		if(event.isCancelled())
			return;
		
		if(event instanceof EntityDamageByEntityEvent)
		{
			EntityDamageByEntityEvent entityEvent = (EntityDamageByEntityEvent)event;
			Entity damager = entityEvent.getDamager();
			
			if(damager instanceof Player && entityEvent.getEntity() instanceof LivingEntity)
			{
				Player player = (Player)damager;
				
				if(m_lstDisabled.contains(player))
					return;
				
				LivingEntity entity = (LivingEntity)entityEvent.getEntity(); 
				LastDamage d;
				if(lastDamages.containsKey(player))
				{
					d = lastDamages.get(player);
					if(d.getDamaged() == entity && d.getHealtLeft() == entity.getHealth())
					{
						return;
					}
					lastDamages.remove(player);
				}
					
				d = new LastDamage();
				
				
				d.setDamaged(entity);
				d.setHealtLeft(entity.getHealth());
				lastDamages.put(player, d);
				
				int newHealth = entity.getHealth() - entityEvent.getDamage();
				player.sendMessage(ChatColor.BLUE + entity.getType().getName() + " : - " + 
						entityEvent.getDamage() + " ("+ 
						((newHealth < 0) ? 0 : newHealth) +")");
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) 
	{
		if(arg3.length == 1 && arg0 instanceof Player)
		{
			if(arg3[0].equals("enable"))
			{
				m_lstDisabled.remove(arg0);
			}
			else if(arg3[0].equals("disable"))
			{
				m_lstDisabled.add((Player)arg0);
			}
			return true;
		}
		return false;
	}

}
