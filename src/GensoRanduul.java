import javax.security.auth.login.LoginException;

import Commands.Command;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class GensoRanduul {
	public static void main(String[] args) throws LoginException, InterruptedException {

			JDA api = new JDABuilder("NTQzMjkwMDE5MDM3NTc3MjI2.Dz6rRg.PF4ieCwenWr9zo7iEYpFFUcaO_g").build();
			Command listener = new Command(api);
			api.addEventListener(listener);
	}
}