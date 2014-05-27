package me.Craftiii4.PartyCraft.DropParties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import me.Craftiii4.PartyCraft.PartyCraft;
import me.Craftiii4.PartyCraft.Files.Config_DropParty;
import me.Craftiii4.PartyCraft.Files.Log_DropParty;
import me.Craftiii4.PartyCraft.Listeners.SignPlaceListener.DropType;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

public class DropParty {
	
	private PartyCraft plugin;
	
	private int id;
	
	private List<ItemStack> items;
	private ItemStack hiddenitem;
	
	private Location blocklocation;
	private int blockid;
	private int blocksubid;
	private int timeon;
	
	private int messageradius;
	
	private int amountofitems;
	private int maxitems;
	
	private int waves;
	private int wavedelay;
	private int waveon;
	
	private DropType dtype;
	
	private boolean hidden;
	private boolean stop;
	
	private boolean canputinitems;
	private boolean	spawningitems;
	
	private String filename;
	private List<String> addlog = new ArrayList<String>();
	
	public DropParty(PartyCraft plugin, int id, Location blocklocation, int blockid, int blocksubid,int maxitems, DropType dtype, boolean hidden, ItemStack hiddenitem, String filename) {
		
		this.plugin = plugin;
		
		this.id = id;
		this.blocklocation = blocklocation;
		this.blockid = blockid;
		this.blocksubid = blocksubid;
		this.maxitems = maxitems;
		this.messageradius = Config_DropParty.getItemAddMessageRadius();
		
		this.amountofitems = 0;
		this.items = new ArrayList<ItemStack>();
		
		this.dtype = dtype;
		this.hidden = hidden;
		this.hiddenitem = hiddenitem;
		this.timeon = Config_DropParty.getStartingTime();
		this.stop = false;
		this.canputinitems = true;
		this.spawningitems = false;
		
		this.waves = Config_DropParty.getWaves();
		this.wavedelay = Config_DropParty.getWaveDelay();
		this.waveon = 0;
		
		this.filename = filename;
		
	}
	
	public void Stop() {
		this.stop = true;
		this.canputinitems = false;
		this.spawningitems = false;
		if (Config_DropParty.getLogDropParties())
			saveLog();
		DP.removeDropParty(getID());
	}
	
	public void setSpawningItems(boolean spawning) {
		this.spawningitems = spawning;
	}
	
	public void ShuffleItems() {
		Collections.shuffle(items);
	}
	
	public void setCanPutInItems(boolean canputinitems) {
		this.canputinitems = canputinitems;
	}
	
	public void saveLog() {
		Log_DropParty.SaveLogToFile(plugin, filename, addlog);
		addlog.clear();
	}
	
	public void addtoLog(String logged) {
		this.addlog.add(PartyCraft.removeColour(logged));
	}
	
	public void setFileName(String filename) {
		this.filename = filename;
	}
	
	public void setTime(int time) {
		this.timeon = time;
	}
	
	public void setHiddenItem(ItemStack hiddenitem) {
		this.hiddenitem = hiddenitem;
	}
	
	public void setDropType(DropType dtype) {
		this.dtype = dtype;
	}
	
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	public void addItem(ItemStack item, Player player) {
		
		int amount = item.getAmount();
		
		if ((amountofitems + amount) > maxitems) {
			player.sendMessage("§cThat would go over the max allowed items");
			return;
		}
		
		if (Config_DropParty.hasTime(item))
			setTime(getTime() + (Config_DropParty.getTime(item) * item.getAmount()));
		
		this.amountofitems = this.amountofitems + amount;
		
		ItemStack oneitem = new ItemStack(item);
		oneitem.setAmount(1);
		
		for (int i=0; i<item.getAmount(); i++)
			items.add(oneitem);
		
	}
	
	public void sendAddMessage(ItemStack item, Player player) {
		
		String itemname = FindName.getItemName(item);
		
		double x = blocklocation.getBlockX();
		double y = blocklocation.getBlockY();
		double z = blocklocation.getBlockZ();
		
		if (Config_DropParty.getLogDropParties())
			addtoLog((player == null ? "Server" : player.getName()) + " added " + item.getAmount() + " " + itemname);
		
		for (Player p : blocklocation.getWorld().getPlayers()) {
			
			Location plocation = p.getLocation();
			double px = plocation.getBlockX();
			double py = plocation.getBlockY();
			double pz = plocation.getBlockZ();
			
			double distance = Math.sqrt(Math.pow((x - px),2) + Math.pow((y - py),2) + Math.pow((z - pz),2));
			
			if (distance <= this.messageradius) {
				if (player != null) {
					if (!p.getName().equals(player.getName()))
						p.sendMessage("§c" + player.getName() + " §aadded §d" + item.getAmount() + " §b" + itemname);
				} else {
					p.sendMessage("§cServer" + " §aadded §d" + item.getAmount() + " §b" + itemname);
				}
			}
			
		}
		
		if (player != null)
			player.sendMessage("§b You §aadded §d" + item.getAmount() + " §e" + itemname);
		
	}
	
	public void sendNearMessage(String message) {
		
		double x = blocklocation.getBlockX();
		double y = blocklocation.getBlockY();
		double z = blocklocation.getBlockZ();
		
		for (Player p : blocklocation.getWorld().getPlayers()) {
			
			Location plocation = p.getLocation();
			double px = plocation.getBlockX();
			double py = plocation.getBlockY();
			double pz = plocation.getBlockZ();
			
			double distance = Math.sqrt(Math.pow((x - px),2) + Math.pow((y - py),2) + Math.pow((z - pz),2));
			
			if (distance <= this.messageradius)
				p.sendMessage(message);
			
		}
		
	}
	
	public void sendNearMessage(List<String> message) {
		
		double x = blocklocation.getBlockX();
		double y = blocklocation.getBlockY();
		double z = blocklocation.getBlockZ();
		
		for (Player p : blocklocation.getWorld().getPlayers()) {
			
			Location plocation = p.getLocation();
			double px = plocation.getBlockX();
			double py = plocation.getBlockY();
			double pz = plocation.getBlockZ();
			
			double distance = Math.sqrt(Math.pow((x - px),2) + Math.pow((y - py),2) + Math.pow((z - pz),2));
			
			if (distance <= this.messageradius)
				for (String m : message)
					p.sendMessage(m);
			
		}
		
	}
	
	public void setMaxItems(int maxitems) {
		this.maxitems = maxitems;
	}
	
	public void setBlockLocation(Location location) {
		this.blocklocation = location;
	}
		
	public void setWaves(Integer waveon) {
		this.waves = waveon;
	}
	
	public void setWaveDelay(Integer waveon) {
		this.wavedelay = waveon;
	}
	
	public void setWaveOn(Integer waveon) {
		this.waveon = waveon;
	}
	
	public void SpawnItems() {
				
		if (getWaveOn() >= getWaves()) {
			Stop();
			return;
		}
		
		setWaveOn(getWaveOn() + 1);
		
		if (getWaves() == getWaveOn()) { // Last wave
			
			if (getAllItems() != null && getAllItems().size() > 0)
				SpawnItems(getAllItems());
			
			Stop();
			return;
			
		}
		
		if (getCurrentAmount() > 0) {
					
			Double total = (double) getCurrentAmount();
			Double waves = (double) getWaves();
			
			Double amount = total / waves;
			int amounttospawn = (int) Math.ceil(amount);
			
			List<ItemStack> WhatToSpawn = new ArrayList<ItemStack>();
			List<ItemStack> WhatsLeft = new ArrayList<ItemStack>();
			
			if (items != null && items.size() > 0) {
			
				Integer oneon = 0;
				
				while (oneon < amounttospawn) {
					
					WhatToSpawn.add(items.get(oneon));					
					oneon++;
					
				}
				
				while (oneon < items.size()) {
					WhatsLeft.add(items.get(oneon));
					oneon++;
				}
				
				items = WhatsLeft;
				
				SpawnItems(WhatToSpawn);
			
			}
		
		}
		
		Integer delay = getWaveDelay() * 20;
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run(){
				
				if (this == null || isStoped() == true)
					return;
				
				SpawnItems();
				
			}
		}, delay);
	}
	
	@SuppressWarnings("deprecation")
	public void SpawnItems(List<ItemStack> items) {
		
		Location newlocation = new Location(getBlockLocation().getWorld(), getBlockLocation().getX(), getBlockLocation().getY(), getBlockLocation().getZ()).add(0, Config_DropParty.getHeight(), 0);
		Location fireworklocation = new Location(getBlockLocation().getWorld(), getBlockLocation().getX(), getBlockLocation().getY() + 10, getBlockLocation().getZ());
		
		Builder builder = FireworkEffect.builder();
		FireworkEffect effect1 = builder.flicker(false).trail(false).with(FireworkEffect.Type.BALL_LARGE).withColor(Color.RED).withFade(Color.BLUE).withFlicker().withTrail().build();
		Builder builder2 = FireworkEffect.builder();
		FireworkEffect effect2 = builder2.flicker(false).trail(false).with(FireworkEffect.Type.STAR).withColor(Color.YELLOW).withFade(Color.GREEN).withFlicker().withTrail().build();
			
		Firework firework = (Firework) newlocation.getWorld().spawn(fireworklocation, Firework.class);
		FireworkMeta fireworkmeta = (FireworkMeta) firework.getFireworkMeta();
		fireworkmeta.clearEffects();
		fireworkmeta.addEffect(effect1);
		fireworkmeta.addEffect(effect2);
		fireworkmeta.setPower(0);		
		firework.setFireworkMeta(fireworkmeta);

				
		Random rand = new Random();
		
		Integer ItemSpread = Config_DropParty.getItemSpread();
		Integer ReleasedOver = Config_DropParty.getReleasedOver();
		
		for (ItemStack item : items) {
			
			double r1, r2, r3;

			int r01, r02;

			r01 = ItemSpread;
			r01 = (r01 * 2);

			r02 = ItemSpread;

			r1 = rand.nextDouble();
			r2 = rand.nextDouble();
			r3 = rand.nextDouble();

			r1 = (r1 * r01) - r02;
			r2 = (r2 * r01) - r02;

			r1 = (r1 / 100) * 5;
			r2 = (r2 / 100) * 5;

			r3 = (r3 * (ReleasedOver / 1.4));
			
			double rand1 = Math.random();
			
			ItemStack item2 = new ItemStack(item);
			
			if (item2.hasItemMeta()) {
				if (item2.getItemMeta().hasLore()) {
					ItemMeta meta1 = item2.getItemMeta();
					List<String> fakelore = meta1.getLore();
					fakelore.add("PARTYCRAFT:REMOVE " + rand1);
					meta1.setLore(fakelore);
					item2.setItemMeta(meta1);
				} else {
					ItemMeta meta1 = item2.getItemMeta();
					List<String> fakelore = new ArrayList<String>();
					fakelore.add("PARTYCRAFT:REMOVE " + rand1);
					meta1.setLore(fakelore);
					item2.setItemMeta(meta1);
				}
			} else {
				ItemMeta meta1 = item2.getItemMeta();
				List<String> fakelore = new ArrayList<String>();
				fakelore.add("PARTYCRAFT:REMOVE " + rand1);
				meta1.setLore(fakelore);
				item2.setItemMeta(meta1);
			}
			
			if (getHidden()) {
				
				Integer TypeId = item2.getTypeId();
				
				if (TypeId == 373 || TypeId == 386 || TypeId == 387 || TypeId == 401 || TypeId == 402) {
					
					// Leave as unhidden
					
				} else {
										
					ItemStack tohideas = getHiddenItem();
					ItemMeta meta = item2.getItemMeta();
					ItemMeta newmeta = tohideas.getItemMeta();
					
					List<String> fakelore;
					
					if (meta.hasLore()) {
						fakelore = item2.getItemMeta().getLore();
					} else {
						fakelore = new ArrayList<String>();
					}
					
					fakelore.add("PARTYCRAFT:HIDE");
										
					if (item2.getItemMeta().hasDisplayName()) {
						fakelore.add("PARTYCRAFT:ITEM:" + item2.getTypeId() + ":" + item2.getDurability() + ":" + item2.getItemMeta().getDisplayName());
					} else {
						fakelore.add("PARTYCRAFT:ITEM:" + item2.getTypeId() + ":" + item2.getDurability());
					}
					
	        		if (item2.getEnchantments() != null && item2.getEnchantments().size() > 0) {
	        			String enchants = "";
	        			for (Enchantment e : item2.getEnchantments().keySet()) {
	        				if (enchants.equals("")) {
	        					enchants = e.getId() + ":" + item2.getEnchantmentLevel(e);
	        				} else {
	        					enchants = enchants + "@" + e.getId() + ":" + item2.getEnchantmentLevel(e);
	        				}
	        			}
						fakelore.add("PARTYCRAFT:ENCHANTS:" + enchants);
	        		}
	        			        		
	        		Material type = item2.getType();
	        		if (type.equals(Material.LEATHER_HELMET) || type.equals(Material.LEATHER_CHESTPLATE) || type.equals(Material.LEATHER_LEGGINGS) || type.equals(Material.LEATHER_BOOTS)) {
	        			LeatherArmorMeta lmeta = (LeatherArmorMeta) item2.getItemMeta();
						fakelore.add("PARTYCRAFT:LEATHERCOLOUR:" + lmeta.getColor().asRGB());
	        		}
	        		
	        		if (item2.getTypeId() == 397 && item2.getDurability() == 3) {
	        			
	        			if (item2.hasItemMeta()) {
	        				SkullMeta smeta = (SkullMeta) item2.getItemMeta();
	        				if (smeta.hasOwner()) {
	    						fakelore.add("PARTYCRAFT:SKULLOWNER:" + smeta.getOwner());
	        				}
	        			}
	        			
	        		}
	        		
	        		if (item2.getTypeId() == 403) {
	        			if (item2.hasItemMeta()) {
		        			EnchantmentStorageMeta ebmeta = (EnchantmentStorageMeta) item2.getItemMeta();
		        			String enchants = "";
		        			for (Enchantment e : ebmeta.getStoredEnchants().keySet()) {
		        				if (enchants.equals("")) {
		        					enchants = e.getId() + ":" + ebmeta.getStoredEnchantLevel(e);
		        				} else {
		        					enchants = enchants + "@" + e.getId() + ":" + ebmeta.getStoredEnchantLevel(e);
		        				}
		        			}
		        			if (!enchants.equals("")) {
								fakelore.add("PARTYCRAFT:ENCHANTEDBOOK:" + enchants);
		        			}
	        			}
	        		}
					
	        		newmeta.setLore(fakelore);
	        		
	        		item2.setItemMeta(newmeta);
	        		
	        		Integer typeid = tohideas.getTypeId();
	        		Short dura = tohideas.getDurability();
	        		
	        		if (typeid == 331 && dura >= 100) {
	        			
	        			if (dura == 100) {
		        			Integer random = DP.getRandom(0, 15);
			        		item2.setTypeId(35);
			        		item2.setDurability(random.shortValue());
	        			}
	        			if (dura == 101) {
		        			Integer random = DP.getRandom(0, 15);
			        		item2.setTypeId(95);
			        		item2.setDurability(random.shortValue());
	        			}
	        			if (dura == 102) {
		        			Integer random = DP.getRandom(0, 15);
			        		item2.setTypeId(351);
			        		item2.setDurability(random.shortValue());
	        			}
	        			if (dura == 103) {
		        			Integer random = DP.getRandom(0, 3);
			        		item2.setTypeId(349);
			        		item2.setDurability(random.shortValue());
	        			}
	        			if (dura == 104) {
		        			Integer random = DP.getRandom(0, 9);
		        			if (random != 9) {
				        		item2.setTypeId(38);
				        		item2.setDurability(random.shortValue());
		        			} else {
				        		item2.setTypeId(37);
				        		item2.setDurability((short) 0);
		        			}
		        			
	        			}
	        			if (dura == 105) {
		        			Integer random = DP.getRandom(0, 11);
				        	item2.setTypeId(2256 + random);		        			
	        			}
	        			if (dura == 106) {
		        			Integer random = DP.getRandom(0, 3);
		        			if (random != 3) {
					        	item2.setTypeId(325 + random);		
		        			} else {
					        	item2.setTypeId(335);		
		        			}
        			
	        			}
	        			
	        		} else {
		        		item2.setTypeId(tohideas.getTypeId());
		        		item2.setDurability(tohideas.getDurability());
	        		}
					
				}
				
			}
			
			final Item customdropfire = newlocation.getWorld().dropItemNaturally(newlocation, item2);
			
			customdropfire.setVelocity(new Vector(r1, r3, r2));
			
		}
		
	}
	
	//
	
	public int getMaxItems() {
		return this.maxitems;
	}
	
	public int getID() {
		return this.id;
	}
	
	public int getTime() {
		return this.timeon;
	}
	
	public int getCurrentAmount() {
		return this.amountofitems;
	}
	
	public Location getBlockLocation() {
		return this.blocklocation;
	}
	
	public int getBlockID() {
		return this.blockid;
	}
	
	public int getBlockSUBID() {
		return this.blocksubid;
	}
	
	public int getMessageRadius() {
		return this.messageradius;
	}
	
	public int getWaves() {
		return this.waves;
	}
	
	public int getWaveDelay() {
		return this.wavedelay;
	}
	
	public int getWaveOn() {
		return this.waveon;
	}
		
	public ItemStack getHiddenItem() {
		return this.hiddenitem;
	}
	
	public List<ItemStack> getAllItems() {
		return this.items;
	}
	
	public DropType getDropType() {
		return this.dtype;
	}
	
	public Boolean getHidden() {
		return this.hidden;
	}
	
	public Boolean isStoped() {
		return this.stop;
	}
	
	public Boolean getSpawningItems() {
		return this.spawningitems;
	}
	
	public Boolean getCanPutInItems() {
		return this.canputinitems;
	}

}
