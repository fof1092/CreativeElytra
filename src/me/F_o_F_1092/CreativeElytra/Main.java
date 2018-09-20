package me.F_o_F_1092.CreativeElytra;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.F_o_F_1092.CreativeElytra.PluginManager.Command;
import me.F_o_F_1092.CreativeElytra.PluginManager.CommandListener;
import me.F_o_F_1092.CreativeElytra.PluginManager.ServerLog;
import me.F_o_F_1092.CreativeElytra.PluginManager.VersionManager;
import me.F_o_F_1092.CreativeElytra.PluginManager.VersionManager.ServerType;
import me.F_o_F_1092.CreativeElytra.PluginManager.Spigot.HelpPageListener;
import me.F_o_F_1092.CreativeElytra.PluginManager.Spigot.UpdateListener;

public class Main extends JavaPlugin {

	boolean updateAvailable = false;

	
	static Main plugin;
	
	public static Main getPlugin() {
		return plugin;
	}
	
	@Override
	public void onEnable() {
		System.out.println("[CreativeElytra] a Plugin by F_o_F_1092");

		plugin = this;
		
		ServerLog.setPluginTag("§f[§6CreativeElytra§f]§6");
		UpdateListener.initializeUpdateListener(1.05, "1.0.5", 37226);
		UpdateListener.checkForUpdate();
		

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EventListener(), this);

		this.getCommand("CreativeElytra").setExecutor(new CommandCreativeElytra());
		this.getCommand("CreativeElytra").setTabCompleter(new CommandCreativeElytraTabCompleter());

		
		VersionManager.setVersionManager(Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3], ServerType.BUKKIT, false);
		
		
		
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
		} else {
			double version = ymlFileConfig.getDouble("Version");
			if (version < UpdateListener.getUpdateDoubleVersion()) {
				try {
					
					if (version <= 1.02) {
						ymlFileConfig.set("ShowBlockMessage", true);
					}
					
					ymlFileConfig.set("Version", UpdateListener.getUpdateDoubleVersion());
					ymlFileConfig.save(fileConfig);
				} catch (IOException e) {
					ServerLog.err("Can't update the Config.yml. [" + e.getMessage() +"]");
				}
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
		} else {
			double version = ymlFilePlayers.getDouble("Version");
			if (version < UpdateListener.getUpdateDoubleVersion()) {
				try {
					ymlFilePlayers.set("Version", UpdateListener.getUpdateDoubleVersion());
					ymlFilePlayers.save(filePlayers);
				} catch (IOException e) {
					ServerLog.err("Can't update the Players.yml. [" + e.getMessage() +"]");
				}
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
		} else {
			double version = ymlFileMessage.getDouble("Version");
			if (version < UpdateListener.getUpdateDoubleVersion()) {
				try {
					ymlFileMessage.set("Version", UpdateListener.getUpdateDoubleVersion());
					ymlFileMessage.save(fileMessages);
				} catch (IOException e) {
					ServerLog.err("Can't update the Messages.yml [" + e.getMessage() +"]");
				}
			}
		}

		Options.msg.put("[CreativeElytra]", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("[CreativeElytra]")));
		Options.msg.put("color.1", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("Color.1")));
		Options.msg.put("color.2", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("Color.2")));
		Options.msg.put("msg.1", ChatColor.translateAlternateColorCodes('&', Options.msg.get("color.1") + ymlFileMessage.getString("Message.1")));
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
		
		CommandListener.addCommand(new Command("/ce help (Page)", null, ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpText.1"))));
		CommandListener.addCommand(new Command("/ce info", null, ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpText.2"))));
		CommandListener.addCommand(new Command("/ce reload", "CreativeElytra.Reload", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpText.3"))));
		CommandListener.addCommand(new Command("/ce toggle", "CreativeElytra.Toggle", ChatColor.translateAlternateColorCodes('&', ymlFileMessage.getString("HelpText.4"))));
		
	}
	
	@Override
	public void onDisable() {
		System.out.println("[CreativeElytra] a Plugin by F_o_F_1092");
	}

}
