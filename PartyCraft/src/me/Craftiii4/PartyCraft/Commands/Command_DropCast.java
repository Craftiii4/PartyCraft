package me.Craftiii4.PartyCraft.Commands;

import me.Craftiii4.PartyCraft.PartyCraft;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command_DropCast implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[]) {
		
		if (!sender.hasPermission("partycraft.dropparty.cast")) {
			sender.sendMessage("§cYou lack permission do to this");
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage("§9Usage§f: §7/dropcast §c<§emessage§c>");				
			return true;
		}
		
		String message = "";
		
		for (String s : args)
			message = message + s + " ";
		
		message = PartyCraft.convertColourMessage(message);
		
		Bukkit.broadcastMessage("§e[§aDropCast§e] §f" + message);
		
		return false;
	}

}
