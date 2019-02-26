package bot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import commands.Command;
import data.Campaign;
import data.Player;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;



public class GensoRanduul{
	private static ArrayList<Campaign> campaigns = new ArrayList<Campaign>();
	private static ArrayList<Player> players = new ArrayList<Player>();
	
	public static void main(String[] args) throws LoginException, InterruptedException, ClassNotFoundException, IOException {
			// Building the JDA, logging the bot in, adding a Command class as a listener, and reading stored member/player data.
		
		JDA api = new JDABuilder(AccountType.BOT).setToken("NTQ5NzU4OTczMzY5MjUzOTE5.D1Ymxw.BHAB-z_MXx3fSV7IlkXDZsZBwvA").build();
		Command listener = new Command();
		api.addEventListener(listener);
		getStoredData();
	}
	
	//Reads players.txt and retrieves all player and campaign objects stored there.
	public static void getStoredData() {
		try {
			FileInputStream fi = new FileInputStream(new File("players.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			addPlayer((Player)oi.readObject());
			
			oi.close();
			fi.close();
		}catch(Exception e) {
			System.out.println("Error!");
		}
	}
	
	//Adds a player to the player list
	public static void addPlayer(Player player) {
		players.add(player);
	}
	
	//Adds a campaign to the campaign list
	public static void addCampaign(Campaign campaign) {
		campaigns.add(campaign);
	}
	
	//Retrieves the entire campaign list object
	public static ArrayList<Campaign> getCampaigns() {
		return campaigns;
	}
	
	//Retrieves the entire player list object
	public static ArrayList<Player> getPlayers(){
		return players;
	}
	
	//Retrieves a specific campaign from the campaign list
	public static Campaign getCampaign(String name) {
		for(int i = 0; i < campaigns.size(); i++) {
			//if(campaigns.get(i).getName().equals(name)) return campaigns.get(i);
		}
		
		return null;
	}
	
	//Retrieves a specific player from the player list
	public static Player getPlayer(String mem) {
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getName().equals(mem)) return players.get(i);
		}
		
		return null;
	}
}
