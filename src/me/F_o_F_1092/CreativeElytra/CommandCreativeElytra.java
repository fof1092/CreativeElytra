package me.F_o_F_1092.CreativeElytra;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.F_o_F_1092.CreativeElytra.PluginManager.CommandListener;
import me.F_o_F_1092.CreativeElytra.PluginManager.JSONMessage;
import me.F_o_F_1092.CreativeElytra.PluginManager.Spigot.HelpPageListener;
import me.F_o_F_1092.CreativeElytra.PluginManager.Spigot.JSONMessageListener;
import me.F_o_F_1092.CreativeElytra.PluginManager.Spigot.UpdateListener;
import me.F_o_F_1092.CreativeElytra.PluginManager.Math;
import me.F_o_F_1092.CreativeElytra.PluginManager.ServerLog;

import org.bukkit.ChatColor;

public class CommandCreativeElytra implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			String replaceCommand = Options.msg.get("msg.9");
			replaceCommand = replaceCommand.replace("[COMMAND]", CommandListener.getCommand("/ce help (Page)").getColoredCommand());
			cs.sendMessage(Options.msg.get("[CreativeElytra]") + replaceCommand); 
		} else {
			if (args[0].equalsIgnoreCase("help")) {
				if (!(args.length >= 1 && args.length <= 2)) {
					String replaceCommand = Options.msg.get("msg.9");
					replaceCommand = replaceCommand.replace("[COMMAND]", CommandListener.getCommand("/ce help (Page)").getColoredCommand());
					cs.sendMessage(Options.msg.get("[CreativeElytra]") + replaceCommand); 
				} else {
					if (!(cs instanceof Player)) {
						if (args.length != 1) {
							String replaceCommand = Options.msg.get("msg.9");
							replaceCommand = replaceCommand.replace("[COMMAND]", CommandListener.getCommand("/ce help (Page)").getColoredCommand());
							cs.sendMessage(Options.msg.get("[CreativeElytra]") + replaceCommand); 
						} else {
							HelpPageListener.sendNormalMessage(cs);
						}
					} else {
						Player p = (Player)cs;
							if (args.length == 1) {
							HelpPageListener.sendMessage(p, 0);
						} else {
							if (!Math.isInt(args[1])) {
								String replaceCommand = Options.msg.get("msg.9");
								replaceCommand = replaceCommand.replace("[COMMAND]", CommandListener.getCommand("/ce help (Page)").getColoredCommand());
								cs.sendMessage(Options.msg.get("[CreativeElytra]") + replaceCommand); 
							} else {
								if (Integer.parseInt(args[1]) <= 0 || Integer.parseInt(args[1]) > HelpPageListener.getMaxPlayerPages(p)) {
									String replaceCommand = Options.msg.get("msg.9");
									replaceCommand = replaceCommand.replace("[COMMAND]", CommandListener.getCommand("/ce help (Page)").getColoredCommand());
									cs.sendMessage(Options.msg.get("[CreativeElytra]") + replaceCommand); 
								} else {
									HelpPageListener.sendMessage(p, Integer.parseInt(args[1]) - 1);
								}
							}
						}
					}
				}
			} else if (args[0].equalsIgnoreCase("info")) {
				if (args.length != 1) {
					String replaceCommand = Options.msg.get("msg.9");
					replaceCommand = replaceCommand.replace("[COMMAND]", CommandListener.getCommand("/ce info").getColoredCommand());
					cs.sendMessage(Options.msg.get("[CreativeElytra]") + replaceCommand); 
				} else {
					cs.sendMessage("§6§m-----------§f [§6CreativeElytra§f] §6§m-----------");
					cs.sendMessage("");
					
					if (cs instanceof Player) {
						Player p = (Player) cs;
						
						List<JSONMessage> jsonFoFMessages = new ArrayList<JSONMessage>();
						
						JSONMessage FoFText = new JSONMessage("§6By: ");
						JSONMessage FoFLink = new JSONMessage("§eF_o_F_1092");
						FoFLink.setHoverText("§6[§eOpen my Website§6]");
						FoFLink.setOpenURL("https://fof1092.de");
						
						jsonFoFMessages.add(FoFText);
						jsonFoFMessages.add(FoFLink);
						
						JSONMessageListener.send(p, JSONMessageListener.putJSONMessagesTogether(jsonFoFMessages));
						
						cs.sendMessage("");
						
						List<JSONMessage> jsonTwitterMessages = new ArrayList<JSONMessage>();
						
						JSONMessage twitterText = new JSONMessage("§6Twitter: ");
						JSONMessage twitterLink = new JSONMessage("§e@F_o_F_1092");
						twitterLink.setHoverText("§6[§eOpen Twitter§6]");
						twitterLink.setOpenURL("https://twitter.com/F_o_F_1092");
						
						jsonTwitterMessages.add(twitterText);
						jsonTwitterMessages.add(twitterLink);
						
						JSONMessageListener.send(p, JSONMessageListener.putJSONMessagesTogether(jsonTwitterMessages));
					
						cs.sendMessage("");
						cs.sendMessage("§6Version: §e" + UpdateListener.getUpdateStringVersion());
						
						List<JSONMessage> jsonPluginPageMessages = new ArrayList<JSONMessage>();
						
						JSONMessage pluginWebsiteText = new JSONMessage("§6TimeVote: ");
						JSONMessage pluginWebsiteLink = new JSONMessage("§ehttps://fof1092.de/Plugins/CE");
						pluginWebsiteLink.setHoverText("§6[§eOpen the Plugin Page§6]");
						pluginWebsiteLink.setOpenURL("https://fof1092.de/Plugins/CE");
						
						jsonPluginPageMessages.add(pluginWebsiteText);
						jsonPluginPageMessages.add(pluginWebsiteLink);
						
						JSONMessageListener.send(p, JSONMessageListener.putJSONMessagesTogether(jsonPluginPageMessages));
					
					} else {
						cs.sendMessage("§6By: §eF_o_F_1092");
						cs.sendMessage("");
						cs.sendMessage("§6Twitter: §e@F_o_F_1092");
						cs.sendMessage("");
						cs.sendMessage("§6Version: §e" + UpdateListener.getUpdateStringVersion());
						cs.sendMessage("§6TimeVote: §ehttps://fof1092.de/Plugins/CE");
					}
					
					cs.sendMessage("");
					cs.sendMessage("§6§m-----------§f [§6CreativeElytra§f] §6§m-----------");
				}
			} else if (args[0].equalsIgnoreCase("toggle")) {
				if (!(cs instanceof Player)) {
					cs.sendMessage(Options.msg.get("[CreativeElytra]") + Options.msg.get("msg.1"));
				} else {
					Player p = (Player) cs;
					if (args.length != 1) {
						String replaceCommand = Options.msg.get("msg.9");
						replaceCommand = replaceCommand.replace("[COMMAND]", CommandListener.getCommand("/ce toggle").getColoredCommand());
						cs.sendMessage(Options.msg.get("[CreativeElytra]") + replaceCommand); 
					} else {
						if (!cs.hasPermission("CreativeElytra.Toggle")) {
							cs.sendMessage(Options.msg.get("[CreativeElytra]") + Options.msg.get("msg.2"));
						} else {
							if (Options.creativeElytraPlayers.contains(p.getUniqueId().toString())) {
								Options.creativeElytraPlayers.remove(p.getUniqueId().toString());
								
								cs.sendMessage(Options.msg.get("[CreativeElytra]") + Options.msg.get("msg.4"));
							} else {
								Options.creativeElytraPlayers.add(p.getUniqueId().toString());
								
								cs.sendMessage(Options.msg.get("[CreativeElytra]") + Options.msg.get("msg.3"));
							}
							
							File filePlayers = new File("plugins/CreativeElytra/Players.yml");
							FileConfiguration ymlFilePlayers = YamlConfiguration.loadConfiguration(filePlayers);

							if(filePlayers.exists()) {
								try {
									ymlFilePlayers.set("Version", UpdateListener.getUpdateDoubleVersion());
									ymlFilePlayers.set("Players", Options.creativeElytraPlayers);
									ymlFilePlayers.save(filePlayers);
								} catch (IOException e) {
									ServerLog.err("Can't save the Players.yml. [" + e.getMessage() +"]");
								}
							} else {
								ServerLog.err("Can't save the Players.yml. [File not found!]");
							}
						}
					}
				}
			} else if (args[0].equalsIgnoreCase("reload")) {
				if (args.length != 1) {
					String replaceCommand = Options.msg.get("msg.9");
					replaceCommand = replaceCommand.replace("[COMMAND]", CommandListener.getCommand("/ce reload").getColoredCommand());
					cs.sendMessage(Options.msg.get("[CreativeElytra]") + replaceCommand); 
				} else {
					if (!cs.hasPermission("CreativeElytra.Reload")) {
						cs.sendMessage(Options.msg.get("[CreativeElytra]") + Options.msg.get("msg.2"));
					} else {
						cs.sendMessage(Options.msg.get("[CreativeElytra]") + Options.msg.get("msg.7"));

						
						Options.creativeElytraPlayers.clear();
						
						
						File fileConfig = new File("plugins/CreativeElytra/Config.yml");
						FileConfiguration ymlFileConfig = YamlConfiguration.loadConfiguration(fileConfig);
						
						if (!fileConfig.exists()) {
							try {
								ymlFileConfig.set("Version", UpdateListener.getUpdateDoubleVersion());
								ymlFileConfig.set("ColoredConsoleText", true);
								ymlFileConfig.set("ShowBlockMessage", true);
								ymlFileConfig.save(fileConfig);
							} catch (IOException e) {		
								ServerLog.err("Can't create the Config.yml. [" + e.getMessage() +"]");
							}
						}
						
						ServerLog.setUseColoredColores(ymlFileConfig.getBoolean("ColoredConsoleText"));
						Options.showBlockMessage = ymlFileConfig.getBoolean("ShowBlockMessage");

						
						File filePlayers = new File("plugins/CreativeElytra/Players.yml");
						FileConfiguration ymlFilePlayers = YamlConfiguration.loadConfiguration(filePlayers);

						if(!filePlayers.exists()) {
							try {
								ymlFilePlayers.set("Version", UpdateListener.getUpdateDoubleVersion());
								ymlFilePlayers.set("Players", Options.creativeElytraPlayers);
								ymlFilePlayers.save(filePlayers);
							} catch (IOException e) {
								ServerLog.err("Can't create the Players.yml. [" + e.getMessage() +"]");
							}
						}
						
						Options.creativeElytraPlayers.addAll(ymlFilePlayers.getStringList("Players"));
						
						
						File fileMessages = new File("plugins/CreativeElytra/Messages.yml");
						FileConfiguration ymlFileMessage = YamlConfiguration.loadConfiguration(fileMessages);

						if(!fileMessages.exists()) {
							try {
								ymlFileMessage.save(fileMessages);
								ymlFileMessage.set("Version", UpdateListener.getUpdateDoubleVersion());
								ymlFileMessage.set("[CreativeElytra]", "&f[&6CreativeElytra&f] ");
								ymlFileMessage.set("Color.1", "&6");
								ymlFileMessage.set("Color.2", "&e");
								ymlFileMessage.set("Message.1", "You have to be a player to use this command.");
								ymlFileMessage.set("Message.2", "You do not have the permission for this command.");
								ymlFileMessage.set("Message.3", "Toggle-Mode &eON&6.");
								ymlFileMessage.set("Message.4", "Toggle-Mode &4OFF&6.");
								ymlFileMessage.set("Message.5", "Your Elytra hasn't been removed because your Elytra-Toggle-Mode is on.");
								ymlFileMessage.set("Message.6", "There is a new update available for this plugin. &e( https://fof1092.de/Plugins/CE )&6");
								ymlFileMessage.set("Message.7", "The plugin is reloading...");
								ymlFileMessage.set("Message.8", "Reloading completed.");
								ymlFileMessage.set("Message.9", "Try [COMMAND]");
							    ymlFileMessage.set("HelpTextGui.1", "&e[&6Click to use this command&e]");
							    ymlFileMessage.set("HelpTextGui.2", "&e[&6Next page&e]");
							    ymlFileMessage.set("HelpTextGui.3", "&e[&6Last page&e]");
							    ymlFileMessage.set("HelpTextGui.4", "&7&oPage [PAGE]. &7Click on the arrows for the next page.");
								ymlFileMessage.set("HelpText.1", "This command shows you the help page.");
								ymlFileMessage.set("HelpText.2", "This command shows you the info page.");
								ymlFileMessage.set("HelpText.3", "This command is reloading the Messages.yml file.");
								ymlFileMessage.set("HelpText.4", "This command is toggeling the Elytra-Mode.");
								ymlFileMessage.save(fileMessages);
							} catch (IOException e) {
								ServerLog.err("Can't create the Messages.yml. [" + e.getMessage() +"]");
							}
						}

						Options.msg.put("[CreativeElytra]", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("[CreativeElytra]")));
						Options.msg.put("color.1", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("Color.1")));
						Options.msg.put("color.2", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("Color.2")));
						Options.msg.put("msg.2", ChatColor.translateAlternateColorCodes('&', Options.msg.get("color.1") + ymlFileMessage.getString("Message.1")));
						Options.msg.put("msg.2", ChatColor.translateAlternateColorCodes('&', Options.msg.get("color.1") + ymlFileMessage.getString("Message.2")));
						Options.msg.put("msg.3", ChatColor.translateAlternateColorCodes('&', Options.msg.get("color.1") + ymlFileMessage.getString("Message.3")));
						Options.msg.put("msg.4", ChatColor.translateAlternateColorCodes('&', Options.msg.get("color.1") + ymlFileMessage.getString("Message.4")));
						Options.msg.put("msg.5", ChatColor.translateAlternateColorCodes('&', Options.msg.get("color.1") + ymlFileMessage.getString("Message.5")));
						Options.msg.put("msg.6", ChatColor.translateAlternateColorCodes('&', Options.msg.get("color.1") + ymlFileMessage.getString("Message.6")));
						Options.msg.put("msg.7", ChatColor.translateAlternateColorCodes('&', Options.msg.get("color.1") + ymlFileMessage.getString("Message.7")));
						Options.msg.put("msg.8", ChatColor.translateAlternateColorCodes('&', Options.msg.get("color.1") + ymlFileMessage.getString("Message.8")));
						Options.msg.put("msg.9", ChatColor.translateAlternateColorCodes('&', Options.msg.get("color.1") + ymlFileMessage.getString("Message.9")));
						Options.msg.put("helpTextGui.1", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpTextGui.1")));
						Options.msg.put("helpTextGui.2", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpTextGui.2")));
						Options.msg.put("helpTextGui.3", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpTextGui.3")));
						Options.msg.put("helpTextGui.4", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpTextGui.4")));
						
						
						HelpPageListener.initializeHelpPageListener("/CreativeElytra help", Options.msg.get("[CreativeElytra]"));
						
						CommandListener.addCommand(new me.F_o_F_1092.CreativeElytra.PluginManager.Command("/ce help (Page)", null, ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpText.1"))));
						CommandListener.addCommand(new me.F_o_F_1092.CreativeElytra.PluginManager.Command("/ce info", null, ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpText.2"))));
						CommandListener.addCommand(new me.F_o_F_1092.CreativeElytra.PluginManager.Command("/ce reload", "CreativeElytra.Reload", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpText.3"))));
						CommandListener.addCommand(new me.F_o_F_1092.CreativeElytra.PluginManager.Command("/ce toggle", "CreativeElytra.Toggle", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpText.4"))));


						cs.sendMessage(Options.msg.get("[CreativeElytra]") + Options.msg.get("msg.8"));
					}
				}
			} else {
				String replaceCommand = Options.msg.get("msg.9");
				replaceCommand = replaceCommand.replace("[COMMAND]", CommandListener.getCommand("/ce help (Page)").getColoredCommand());
				cs.sendMessage(Options.msg.get("[CreativeElytra]") + replaceCommand); 
			}
		}
		return true;
	}

}
