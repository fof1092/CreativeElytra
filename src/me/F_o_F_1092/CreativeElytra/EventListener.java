package me.F_o_F_1092.CreativeElytra;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.F_o_F_1092.CreativeElytra.PluginManager.UpdateListener;

public class EventListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();

		if (UpdateListener.isAnewUpdateAvailable()) {
			if (p.hasPermission("CreativeElytra.UpdateMessage")) {
				p.sendMessage(Options.msg.get("[CreativeElytra]") + Options.msg.get("msg.2"));
			}
		}
	}

	@EventHandler
	public void InventoryShiftElytraDeleteEvent(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if (e.getSlot() == 38) {
			if (e.getCurrentItem().getType() == Material.ELYTRA) {
				if (Options.creativeElytraPlayers.contains(p.getUniqueId().toString())) {
					e.setCancelled(true);
					p.updateInventory();
					
					if(Options.showBlockMessage) {
						p.sendMessage(Options.msg.get("[CreativeElytra]") + Options.msg.get("msg.5"));
					}
				}
			}
		}
	}
}