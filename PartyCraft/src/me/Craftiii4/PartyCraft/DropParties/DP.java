package me.Craftiii4.PartyCraft.DropParties;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import me.Craftiii4.PartyCraft.Listeners.SignPlaceListener.DropType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class DP {
	
	public static HashMap<Integer, DropParty> DropParties = new HashMap<Integer, DropParty>();
	
	public static void addDropParty(DropParty dropparty) {
		DropParties.put(dropparty.getID(), dropparty);
	}
	
	public static int getFutureID() {
		
		int id = 0;
		
		while (DropParties.containsKey(id)) {
			id++;
		}
		
		return id;
	}
	
	public static DropParty getDropParty(int id) {
		if (DropParties.containsKey(id))
			return DropParties.get(id);
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static void removeDropParty(int id) {
		if (DropParties.containsKey(id)) {
			
			DropParty dropparty = getDropParty(id);
			
			if (dropparty.getDropType() != DropType.AUTO) {
				dropparty.getBlockLocation().getBlock().setType(Material.AIR);
				dropparty.getBlockLocation().add(0, -1, 0).getBlock().setTypeId(dropparty.getBlockID());
				dropparty.getBlockLocation().add(0, -1, 0).getBlock().setData((byte) dropparty.getBlockSUBID());
			} else {
				
				Block block = dropparty.getBlockLocation().getBlock();
					
				if (block.getState() instanceof Sign) {
						
					Sign sign = (Sign) block.getState();
						
					sign.setLine(2, "§d§lClick me to");
					sign.setLine(3, "§d§lStart");
						
					sign.update();
						
				}
				
				dropparty.getBlockLocation().add(0, -1, 0).getBlock().setTypeId(dropparty.getBlockID());
				dropparty.getBlockLocation().add(0, -1, 0).getBlock().setData((byte) dropparty.getBlockSUBID());
				
			}
			
			DropParties.remove(id);
		}
	}
	
	public static void BroadCastMessage(List<String> messages) {
		
		for (String message : messages)
			Bukkit.broadcastMessage(message);
		
	}
	
	public static void BroadCastMessage(List<String> messages, World world) {
		
		for (Player p : world.getPlayers())
			for (String message : messages)
				p.sendMessage(message);
		
	}
	
	public static void BroadCastMessage(List<String> messages, Location location, Integer radius) {
		
		double x = location.getBlockX();
		double y = location.getBlockY();
		double z = location.getBlockZ();
		
		for (Player p : location.getWorld().getPlayers()) {
			
			Location plocation = p.getLocation();
			
			double px = plocation.getBlockX();
			double py = plocation.getBlockY();
			double pz = plocation.getBlockZ();
			
			double distance = Math.sqrt(Math.pow((x - px), 2) + Math.pow((y - py), 2) + Math.pow((z - pz), 2));
			
			if (distance <= radius)
				for (String message : messages)
					p.sendMessage(message);
			
		}
		
	}
	
	public static Integer getRandom(int START, int END) {

		Random random = new Random();
		long range = (long) END - (long) START + 1;
		long fraction = (long) (range * random.nextDouble());
		int randomNumber = (int) (fraction + START);
		
		return randomNumber;
		
	}

}
