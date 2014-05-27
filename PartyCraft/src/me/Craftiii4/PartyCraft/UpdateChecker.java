package me.Craftiii4.PartyCraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.regex.Pattern;

import me.Craftiii4.PartyCraft.Files.Config_PartyCraft;

import org.bukkit.entity.Player;

public class UpdateChecker implements Runnable {
	
	public static boolean updateout = false;
	public static String versions = "";
	
	public static void StartUpdateChecker(PartyCraft plugin, Player player) {
		
		UpdateChecker UpdateCheckerRunnable = new UpdateChecker(plugin, player);
		Thread UpdateCheckerThread = new Thread(UpdateCheckerRunnable);
		UpdateCheckerThread.start();
		
	}
	
	PartyCraft plugin;
	Player player;
	UpdateChecker(PartyCraft plug, Player p) { plugin = plug; player = p; }

	@Override
	public void run() {
		
		plugin.LogMessage(Level.INFO, "Checking for update");
		
		URL updateurl = null;
		
		try {
			updateurl = new URL("https://api.curseforge.com/servermods/files?projectIds=33310");
		} catch (MalformedURLException e) {
			plugin.LogMessage(Level.WARNING, "Failed to contact curseforge API to check for update");
			return;
		}
		
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(updateurl.openStream()));
		} catch (IOException e) {
			plugin.LogMessage(Level.WARNING, "Failed to contact curseforge API to check for update");
			return;
		}

		String linefromurl;
		
		try {
			
			while ((linefromurl = in.readLine()) != null) {
				
				String splitme = "},{";
				
				String splittest[] = linefromurl.split(Pattern.quote(splitme));
				
				if (splittest[0] != null) {
					
					int size = splittest.length;
					int onetoget = size - 1;
					
					String versioncheck[] = splittest[onetoget].split(Pattern.quote(","));
					
					for (String s : versioncheck) {
						if (s.contains("name")) {
							
							String splitupagain[] = s.split(" ");
							String version = splitupagain[1].replace("\"", "").replace("v", "");
							
							if (version.equals(plugin.getDescription().getVersion())) {
								if (player != null) {
									if (Config_PartyCraft.getAlertAdminsOfUpdate())
										player.sendMessage(PartyCraft.getPrefix() + "§eYou are running the latest version");
								} else {
									plugin.LogMessage(Level.INFO, "You are running the latest version");
								}
							} else {
								
								String splitnumbers[] = version.split(Pattern.quote("."));
								
								int one = Integer.parseInt(splitnumbers[0]);
								int two = Integer.parseInt(splitnumbers[1]);
								int three = Integer.parseInt(splitnumbers[2]);
								
								String currentversion = plugin.getDescription().getVersion();
								
								String splitnumbers1[] = currentversion.split(Pattern.quote("."));
								
								int one1 = Integer.parseInt(splitnumbers1[0]);
								int two1 = Integer.parseInt(splitnumbers1[1]);
								int three1 = Integer.parseInt(splitnumbers1[2]);
								
								if (one < one1) {
									if (player != null) {
										if (Config_PartyCraft.getAlertAdminsOfUpdate())
											player.sendMessage(PartyCraft.getPrefix() + "§eYou are running a future build! wooo");
									} else {
										plugin.LogMessage(Level.INFO, "You are running a future build! wooo");
									}
									return;
								}
								
								if (one > one1) {
									if (player != null) {
										if (Config_PartyCraft.getAlertAdminsOfUpdate())
											player.sendMessage(PartyCraft.getPrefix() + "§eA new version §7v" + version + " §eis out");
									} else {
										plugin.LogMessage(Level.INFO, "A new version v" + version + " is out");
									}
									updateout = true;
									versions = version;
									return;
								}
								
								if (two < two1) {
									if (player != null) {
										if (Config_PartyCraft.getAlertAdminsOfUpdate())
											player.sendMessage(PartyCraft.getPrefix() + "§eYou are running a future build! wooo");
									} else {
										plugin.LogMessage(Level.INFO, "You are running a future build! wooo");
									}
									return;
								}
								
								if (two > two1) {
									if (player != null) {
										if (Config_PartyCraft.getAlertAdminsOfUpdate())
											player.sendMessage(PartyCraft.getPrefix() + "§eA new version §7v" + version + " §eis out");
									} else {
										plugin.LogMessage(Level.INFO, "A new version v" + version + " is out");
									}
									updateout = true;
									versions = version;
									return;
								}
								
								if (three < three1) {
									if (player != null) {
										if (Config_PartyCraft.getAlertAdminsOfUpdate())
											player.sendMessage(PartyCraft.getPrefix() + "§eYou are running a future build! wooo");
									} else {
										plugin.LogMessage(Level.INFO, "You are running a future build! wooo");
									}
									return;
								}
								
								if (three > three1) {
									if (player != null) {
										if (Config_PartyCraft.getAlertAdminsOfUpdate())
											player.sendMessage(PartyCraft.getPrefix() + "§eA new version §7v" + version + " §eis out");
									} else {
										plugin.LogMessage(Level.INFO, "A new version v" + version + " is out");
									}
									updateout = true;
									versions = version;
									return;
								}
								
								if (player != null) {
									if (Config_PartyCraft.getAlertAdminsOfUpdate())
										player.sendMessage(PartyCraft.getPrefix() + "§eYou are running the latest version");
								} else {
									plugin.LogMessage(Level.INFO, "You are running the latest version");
								}

							}
							
						}
					}
					
				}
				
			}
			
		} catch (IOException e) {
			plugin.LogMessage(Level.WARNING, "Error occured while checking for update");
			return;
		}
		
	}
	
	public static Boolean getUpdateOut() {
		return updateout;
	}
	
	public static String getUpdateVersion() {
		return versions;
	}

}
