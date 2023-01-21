package commands;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public class RollDice extends Command{
	private int diceValue;
	private int diceAmount;
	private int[] rolls;
	private String output;
	private String args;
	
	public RollDice(MessageChannelUnion channel, String content) {
		super(jda, twitter);
		output = " ";
		
		args = content.substring(content.indexOf(" ")+1);
		
		//Checks to see if the player were rolling dice, stats, tables, etc.
		switch(args) {
			case "stats":
				rolls = new int[6];
				rollStats(rolls);
				channel.sendMessage("Time to create a new destiny.").queue();
				break;
			default:
				try{
					diceAmount = Integer.parseInt(content.substring(content.indexOf(" ")+1, content.indexOf("d")));
					diceValue = Integer.parseInt(content.substring(content.indexOf("d")+1));
				}catch(Exception e){
					channel.sendMessage("My creation, you do not understand what a dice is do you?").queue();
					return;
				}
				
				rolls = new int[diceAmount];
				
				channel.sendMessage("Your fate is being unraveled as we speak.").queue();
				rollDice(rolls, diceValue);
		}
		
		sendRolls(channel);
	}
	
	//Displays the value of each roll.
	public void sendRolls(MessageChannelUnion channel) {
		int sum = 0;
		for(int i = 0; i < rolls.length; i++) {
			output = output + " [" + rolls[i] + "]";
			sum += rolls[i];
		}
		
		try{channel.sendMessage(output + "\n\n= **" + sum + "**").queue();}catch(Exception e) {channel.sendMessage("You thought I'd let you get that powerful?").queue(); }
	}
	
	//Randomizes integers based on the value of the dice and adds them to the the list of the number of the dice.
	public static int[] rollDice(int[] list, int value) {
		int[] rolls = list;
		int random;
		for(int i = 0; i < list.length; i++) {
			random = (int)(Math.random()*value)+1;
			rolls[i] = random;
		}
		
		return rolls;
	}
	
	//Rolls stats for a character. Stats are roll 4d6 and drop the lowest number.
	public static int[] rollStats(int[] list){
		int lowest = 1000;
		int single = 0;
		int[] indvStat = new int[4];
		int [] stats = list;
		
		for(int j = 0; j < stats.length; j++) {
			rollDice(indvStat, 6);
			
			for(int i = 0; i < indvStat.length; i++) {
				if(lowest > indvStat[i]) {
					lowest = indvStat[i];
				}
				
				single += indvStat[i];
			}
				stats[j] = single-lowest;
				lowest = 1000;
				single = 0;
		}
		
		
		return stats;
	}
}
