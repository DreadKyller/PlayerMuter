package org.styllex.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.util.config.*;

public class MuteConfig{
	public String mute_filename="Mutes.yml";
	public String dir="plugins/PlayerMuter/";
	public String settings_filename="Settings.yml";
	public File mute_file;
	public File settings_file;
	public Configuration mutes;
	public Configuration settings;
	public MuteConfig(){
		this.mute_file=new File(this.dir+this.mute_filename);
		this.settings_file=new File(this.dir+this.settings_filename);
	}
	public MuteConfig getMuteConfig(){
		this.mutes = new Configuration(this.mute_file);
		return this;
	}
	public MuteConfig getSettings(){
		this.settings = new Configuration(this.settings_file);
		return this;
	}
	public String[] getMutes(String playername){
		String[] ret={};
		if(!(this.mutes==null)){
			this.mutes.load();
			Object[] obl = (Object[]) this.mutes.getProperty(playername);
			for(int i=0;i<obl.length;i++){
				ret[i]=(String) obl[i];
			}
		}
		return ret;
	}
	public String getSetting(String key){
		if(this.settings==null){
			return "";
		}
		this.settings.load();
		return (String) this.settings.getProperty(key);
	}
	public void setMutes(String muter, String[] mutes){
		this.mutes.setProperty(muter, mutes);
	}
	public void changeSetting(String key, String value){
		this.settings.setProperty(key, value);
	}
	public void initalize(){
		getMuteConfig();
		getSettings();
		int isin = 0;
		if(!(new File(this.dir).exists())){
			new File(this.dir).mkdirs();
		}
		if(!(this.mute_file.exists())){
			try {
				this.mute_file.createNewFile();
				isin++;
			}catch(IOException e){
			}
		}
		if(!(this.settings_file.exists())){
			try {
				this.mute_file.createNewFile();
				isin++;
			}catch(IOException e){
			}
		}
		if(isin>1){
			/*
			 * Setting up Default Settings - These settings will only be set when the file is made
			 * Therefore these are only the dafaults and will not constantly be set to these, as
			 * that would ruin the point of having a cofig file.
			 * 
			 * If settings file is deleted however or moved settings will be set to default. As this script
			 * will not try to look for the old settings, it will be recreated.
			 */
			changeSetting("other-alert", "true");//allows others to see a message when muted, default
			changeSetting("personal-alert", "true");//allows you to see a message when you mute someone, default
			changeSetting("auto-save", "true");//enable automatic saving of players mutes, default
			changeSetting("allow-mute-ops", "false");//disables the ability to mute ops, default
		}
	}
}
