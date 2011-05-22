package org.styllex;

import java.util.HashMap;

import org.styllex.FN.*;

import org.styllex.config.MuteConfig;
import org.styllex.mute.*;

import org.styllex.mute.MuteAdder;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.*;

public class Mute extends JavaPlugin{
	public MyMap mm = new MyMap();
	public HashMap<Player, HashMap<Player, Integer>> mutes=new HashMap<Player, HashMap<Player, Integer>>();
	public Mute(){
	}
	public MuteListener ml = new MuteListener(this);
	public LoginListener ll = new LoginListener(this);
	public void onDisable(){
		System.out.println("PlayerMute Disabled!");
	}
	public void onEnable(){
		new MuteConfig().initalize();
		System.out.println("PlayerMute Enabled!");
		PluginManager pm = getServer().getPluginManager();
		getCommand("mute").setExecutor(new MuteAdder(this));
		pm.registerEvent(Event.Type.PLAYER_CHAT, ml, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, ll, Event.Priority.Normal, this);
	}
}