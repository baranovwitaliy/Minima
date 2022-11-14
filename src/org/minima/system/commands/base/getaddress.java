package org.minima.system.commands.base;

import java.util.ArrayList;
import java.util.Arrays;

import org.minima.database.MinimaDB;
import org.minima.database.wallet.ScriptRow;
import org.minima.database.wallet.Wallet;
import org.minima.system.commands.Command;
import org.minima.utils.MinimaLogger;
import org.minima.utils.json.JSONObject;

public class getaddress extends Command {

	public getaddress() {
		super("getaddress","(createall:true) Get one of your default Minima addresses");
	}
	
	@Override
	public String getFullHelp() {
		return "\ngetaddress\n"
				+ "\n"
				+ "Returns an existing default Minima address to receive funds, use as a change address etc.\n"
				+ "\n"
				+ "Each address can be used securely 262144 (64^3) times.\n"
				+ "\n"
				+ "To get a brand new address for one time use, use 'newaddress' instead.\n"
				+ "\n"
				+ "createall: (optional)\n"
				+ "    true only. To create all 64 default keys.\n"
				+ "\n"
				+ "Examples:\n"
				+ "\n"
				+ "getaddress\n"
				+ "\n"
				+ "getaddress createall:true\n";
	}
	
	@Override
	public ArrayList<String> getValidParams(){
		return new ArrayList<>(Arrays.asList(new String[]{"createall"}));
	}
	
	@Override
	public JSONObject runCommand() throws Exception{
		JSONObject ret = getJSONReply();
		
		//Get the wallet..
		Wallet wallet = MinimaDB.getDB().getWallet();
		
		//Are we creating them all..
		if(existsParam("createall")) {
			
			MinimaLogger.log("Creating all remaining keys..");
			
			//Create all remaining addresses..
			wallet.initDefaultKeys(Wallet.NUMBER_GETADDRESS_KEYS, true);
			
			ret.put("response", "All keys created..");
			
		}else {
		
			String type = getParam("type","single");
			
			//Get an existing address
			ScriptRow scrow = wallet.getDefaultAddress();
				
			//Put the details in the response..
			ret.put("response", scrow.toJSON());
		}
		
		return ret;
	}

	@Override
	public Command getFunction() {
		return new getaddress();
	}

}
