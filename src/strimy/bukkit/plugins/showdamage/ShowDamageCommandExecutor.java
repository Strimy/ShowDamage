package strimy.bukkit.plugins.showdamage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShowDamageCommandExecutor implements CommandExecutor 
{
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) 
	{
		if(!arg0.isOp())
			return false;
		
		if(arg3.length == 1)
		{
			if(arg3[0].equals("enable"))
				ShowDamageManager.Instance.Enable();
			else if(arg3[0].equals("disable"))
				ShowDamageManager.Instance.Disable();
			return true;
		}
		return false;
	}

}
