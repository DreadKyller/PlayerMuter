package org.styllex.mute;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.styllex.Mute;
import org.styllex.config.MuteConfig;

public class MuteAdder implements CommandExecutor{
	private Mute plugin;
	public MuteConfig conf;
	public MuteAdder(Mute plugin){
		this.plugin=plugin;
		MuteConfig mc = new MuteConfig();
		this.conf=mc.getMuteConfig().getSettings();
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!(sender instanceof Player)){
			System.out.println("Console Can Not Mute Players");
			return true;
		}
		Player player = (Player) sender;
		if(!(args.length==1)){
			player.sendMessage("mute : /mute <PlayerName>");
			return true;
		}
		try{
			Player other = player.getServer().getPlayer(args[0]);
			if(this.conf.getSetting("allow-mute-ops")=="false"){
				if(other.isOp()){
					player.sendMessage("You can not mute OP's");
					return true;
				}
			}
			if(this.plugin.mutes.containsKey(player)){
				if(this.plugin.mutes.get(player).containsKey(other)){
					this.plugin.mutes.get(player).remove(other);
					if(this.conf.getSetting("personal-alert")=="true"){
						player.sendMessage("You Un-Muted : "+args[0]);
					}
					if(this.conf.getSetting("other-alert")=="true"){
						other.sendMessage("Player "+player.getDisplayName()+" Un-Muted You");
					}
					if(this.conf.getSetting("auto-save")=="true"){
						Object[] obl = this.plugin.mutes.get(player).keySet().toArray();
						String[] stl = {};
						for(int i=0;i<obl.length;i++){
							Player play = (Player) obl[i];
							stl[i]=play.getDisplayName();
						}
						this.conf.setMutes(player.getDisplayName(), stl);
					}
				}else{
					this.plugin.mutes.get(player).put(other, 1);
					this.plugin.mutes.put(player, this.plugin.mutes.get(player));
					if(this.conf.getSetting("personal-alert")=="true"){
						player.sendMessage("You Muted : "+args[0]);
					}
					if(this.conf.getSetting("other-alert")=="true"){
						other.sendMessage("Player "+player.getDisplayName()+" Muted You");
					}
					if(this.conf.getSetting("auto-save")=="true"){
						Object[] obl = this.plugin.mutes.get(player).keySet().toArray();
						String[] stl = {};
						for(int i=0;i<obl.length;i++){
							Player play = (Player) obl[i];
							stl[i]=play.getDisplayName();
						}
						this.conf.setMutes(player.getDisplayName(), stl);
					}
				}
			}
			else{
				HashMap<Player, Integer> pl = new HashMap<Player, Integer>();
				pl.put(other, 1);
				this.plugin.mutes.put(player, pl);
				player.sendMessage("You Muted : "+args[0]);
				other.sendMessage("Player "+player.getDisplayName()+" Muted You");
			}
		}catch (Exception e){
			player.sendMessage("Player Named "+args[0]+" does not exist or is not online");
		}
		return true;
	}
}
