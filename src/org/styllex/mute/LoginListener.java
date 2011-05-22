package org.styllex.mute;

import org.bukkit.entity.Player;
import org.bukkit.event.player.*;
import org.styllex.Mute;
import org.styllex.config.MuteConfig;

public class LoginListener extends PlayerListener{
	public Mute plugin;
	public LoginListener(Mute plugin){
		this.plugin=plugin;
	}
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		String name = player.getDisplayName();
		if(!(this.plugin.mutes.containsKey(player))){
			String[] stl = new MuteConfig().getSettings().getMuteConfig().getMutes(name);
			for(int i=0;i<stl.length;i++){
				this.plugin.mutes.get(player).put(player.getServer().getPlayer(stl[i]), 1);
			}
		}
	}
}
