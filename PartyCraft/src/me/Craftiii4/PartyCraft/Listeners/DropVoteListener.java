package me.Craftiii4.PartyCraft.Listeners;

import java.util.ArrayList;
import java.util.List;

import me.Craftiii4.PartyCraft.PartyCraft;
import me.Craftiii4.PartyCraft.DropParties.DP;
import me.Craftiii4.PartyCraft.Files.Config_DropParty;
import me.Craftiii4.PartyCraft.Files.File_Voting;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.vexsoftware.votifier.model.VotifierEvent;

public class DropVoteListener implements Listener  {
	
	public static PartyCraft plugin;

	public DropVoteListener(PartyCraft instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onVotifierEvent(VotifierEvent event) {
    	
    	if (Config_DropParty.getAllowVote()) {
    		
    		if (Config_DropParty.getMustBeOnline()) {
    			
    			if (event.getVote().getUsername() == null)
    				return;
    			
    			if (event.getVote().getUsername().length() < 1)
    				return;

    			Player player = Bukkit.getPlayer(event.getVote().getUsername());
    			
    			if (player == null)
    				return;
    			
    		}
    		
    		File_Voting.addtoCurrentVotes();
    		
    		Integer currentvotes = File_Voting.getCurrentVotes();
    		Integer votesneeded = Config_DropParty.getVotesRequired();
    		Integer amountleft = votesneeded - currentvotes;
    		
    		List<String> alertat = Config_DropParty.getVoteNotifyAt();
    		
    		if (alertat.contains("" + amountleft)) {
    			
    			List<String> messages = Config_DropParty.getVoteNotifyMessage();
    			List<String> newmessages = new ArrayList<String>();
    			
    			for (String message : messages) {
    				message = message.replace("%votes%", "" + currentvotes)
    						.replace("%votesneeded%", "" + votesneeded)
    						.replace("%votesleft%", "" + amountleft);
    				newmessages.add(message);
    			}
    			
    			DP.BroadCastMessage(newmessages);
    			
    		}
    		
    		if (amountleft > 0)
    			return;
    		
    		File_Voting.setCurrentVotes(0);
	    	setBlock();
    		
    	}
    	
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    	
    	if (Config_DropParty.getCommandOverRide()) {
    		
    		String command = event.getMessage();
    		
    		if (command.equals("/votes") || command.equals("/vote")) {
    			
    			event.setCancelled(true);
    			
    			List<String> messages = Config_DropParty.getCommandResult();
    			List<String> newmessages = new ArrayList<String>();
    			
        		Integer currentvotes = File_Voting.getCurrentVotes();
        		Integer votesneeded = Config_DropParty.getVotesRequired();
        		Integer amountleft = votesneeded - currentvotes;
    			
    			for (String message : messages) {
    				message = message.replace("%votes%", "" + currentvotes)
    						.replace("%votesneeded%", "" + votesneeded)
    						.replace("%votesleft%", "" + amountleft);
    				newmessages.add(message);
    			}
    			
    			Player player = event.getPlayer();
    			
    			for (String newmessage : newmessages)
    				player.sendMessage(newmessage);
    			
    		}
    		
    	}
    	
    }

	private static void setBlock() {
		
		final Location location = File_Voting.getVoteLocation();
		
		if (location == null) {
			Bukkit.broadcastMessage(PartyCraft.getPrefix() + "§cDrop party failed to start, location has not been set");
			return;
		}
		
		Block block = location.getBlock();
		
		block.setType(Material.SIGN_POST);
		
		Sign sign2 = (Sign) block.getState();
		
		sign2.setLine(0, "§a[Drop Party]");
		
		if (File_Voting.getHiddenItem().equals("0:0")) {
			sign2.setLine(1, "§7[O][I]");
			sign2.setLine(2, "");
		} else {
			sign2.setLine(1, "§7[O][H]");
			sign2.setLine(2, File_Voting.getHiddenItem());
		}
				
		sign2.update();
		
		DP.BroadCastMessage(Config_DropParty.getVoteReachMessage());
		
		SignPlaceListener.StartNewDropParty(sign2, null, true);
		
	}

}
