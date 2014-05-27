package me.Craftiii4.PartyCraft.DropParties;

import me.Craftiii4.PartyCraft.PartyCraft;
import me.Craftiii4.PartyCraft.Files.Config_DropParty;

import org.bukkit.Bukkit;

public class CountDown {
	
	public static void DropCountDown(final PartyCraft plugin, final int droppartyid) {
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run(){
				
				DropParty dropparty = DP.DropParties.get(droppartyid);
				
				if (dropparty == null)
					return;
				
				if (dropparty.isStoped())
					return;
				
				int time = dropparty.getTime();
				
				if (time > Config_DropParty.getMaxTime())
					time = Config_DropParty.getMaxTime();
				
				if (time > 60) {
					
					int min = time;
					int minon = 0;
					
					while (min > 0) {
						min = min - 60;
						minon++;
					}
					
					if (min == 0)
						dropparty.sendNearMessage("§d" + minon + " §5minutes left on the §cdrop party§5!");
					
				}
				
				if (time == 60) dropparty.sendNearMessage("§d1 §5minutes left on the §cdrop party§5!");
				if (time == 45) dropparty.sendNearMessage("§d45 §5seconds left on the §cdrop party§5!");
				if (time == 30) dropparty.sendNearMessage("§d30 §5seconds left on the §cdrop party§5!");
				if (time == 15) dropparty.sendNearMessage("§d15 §5seconds left on the §cdrop party§5!");
				if (time == 10) dropparty.sendNearMessage("§d10 §5seconds left on the §cdrop party§5!");
				if (time == 5) dropparty.sendNearMessage("§d5 §5seconds left on the §cdrop party§5!");
				if (time == 4) dropparty.sendNearMessage("§d4 §5seconds left on the §cdrop party§5!");
				if (time == 3) dropparty.sendNearMessage("§d3 §5seconds left on the §cdrop party§5!");
				if (time == 2) dropparty.sendNearMessage("§d2 §5seconds left on the §cdrop party§5!");
				if (time == 1) dropparty.sendNearMessage("§d1 §5seconds left on the §cdrop party§5!");
				
				if (time == 0) {
					
					Integer radius = Config_DropParty.getLaunchMessageRadius();
					
					if (radius <= -2)
						DP.BroadCastMessage(Config_DropParty.getLaunchMessage());
					if (radius == -1)
						DP.BroadCastMessage(Config_DropParty.getLaunchMessage(), dropparty.getBlockLocation().getWorld());
					if (radius >= 0)
						DP.BroadCastMessage(Config_DropParty.getLaunchMessage(), dropparty.getBlockLocation(), radius);
					
					dropparty.setCanPutInItems(false);
					dropparty.ShuffleItems();
					dropparty.SpawnItems();
					
					return;
				}
				
				if (time < 0)
					return;
				
				dropparty.setTime(time -1);
				DropCountDown(plugin, droppartyid);
				
			}
		}, 20);
	}

}
