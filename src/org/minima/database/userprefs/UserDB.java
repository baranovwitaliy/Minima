package org.minima.database.userprefs;

import java.util.ArrayList;

import org.minima.objects.Magic;
import org.minima.objects.base.MiniData;
import org.minima.objects.base.MiniNumber;
import org.minima.system.params.GlobalParams;
import org.minima.utils.JsonDB;
import org.minima.utils.MiniUtil;
import org.minima.utils.json.JSONArray;
import org.minima.utils.json.JSONObject;

public class UserDB extends JsonDB{

	public UserDB() {
		super();
	}
	
	/**
	 * Set your Welcome message
	 */
	public void setWelcome(String zWelcome) {
		setString("welcome", zWelcome);
	}
	
	public String getWelcome() {
		return getString("welcome", "Running Minima "+GlobalParams.MINIMA_VERSION);
	}
	
	/**
	 * Is RPC Enabled on this system..
	 */
	public boolean isRPCEnabled() {
		return getBoolean("rpcenable", false);
	}
	
	public void setRPCEnabled(boolean zEnabled) {
		setBoolean("rpcenable", zEnabled);
	}
	

	/**
	 * The Incentive Cash User
	 */
	public String getIncentiveCashUserID() {
		return getString("uid", "");
	}
	
	public void setIncentiveCashUserID(String zUID) {
		setString("uid", zUID);
	}
	
	/**
	 * SSH Tunnel settings
	 */
	public boolean isSSHTunnelEnabled() {
		return getBoolean("sshenabled", false);
	}
	
	public void setSSHTunnelEnabled(boolean zEnabled) {		
		setBoolean("sshenabled", zEnabled);
	}	
	
	public void setSSHTunnelSettings(JSONObject zSettings) {
		setJSON("sshtunnelsettings", zSettings);
	}
	
	public JSONObject getSSHTunnelSettings() {
		return getJSON("sshtunnelsettings", new JSONObject());
	}

	/**
	 * Web Hooks
	 */
	public void setWebHooks(ArrayList<String> zWebHooks) {
		
		//Create one string..
		JSONArray arr = new JSONArray();
		for(String hook : zWebHooks) {
			arr.add(hook);
		}
		
		//Add this..
		setJSONArray("webhooks", arr);
	}
	
	public ArrayList<String> getWebHooks() {
		ArrayList<String> ret = new ArrayList<>();
		
		JSONArray arr = getJSONArray("webhooks");
		for(Object hook : arr) {
			String hk = (String)hook;
			
			ret.add(hk);
		}
		
		return ret;
	}
	
	
	/**
	 * Load Custom Transactions
	 */
	public MiniData loadCustomTransactions() {
		return getData("custom_transactions", MiniData.ZERO_TXPOWID);
	}
	
	public void saveCustomTransactions(MiniData zCompleteDB) {
		setData("custom_transactions", zCompleteDB);
	}
	
	/**
	 * Get and Set the user hashrate
	 */
	public void setHashRate(MiniNumber zHashesPerSec) {
		setNumber("hashrate", zHashesPerSec);
	}
	
	public MiniNumber getHashRate() {
		return getNumber("hashrate", MiniNumber.MILLION);
	}
	
	/**
	 * Get set the User Maxima Details..
	 */
	public void setMaximaName(String zName) {
		setString("maximaname", zName);
	}
	
	public String getMaximaName() {
		return getString("maximaname", "noname");
	}
	
	/**
	 * Install MDS DAPPs the first time
	 */
	public boolean getMDSINIT() {
		return getBoolean("mdsinitdapps", false);
	}
	
	public void setMDSINIT(boolean zInit) {
		setBoolean("mdsinitdapps", zInit);
	}
	
	/**
	 * Is AUTO backup enabled..
	 */
	public boolean isAutoBackup() {
		return getBoolean("autobackup", false);
	}
	
	public void setAutoBackup(boolean zAuto) {
		setBoolean("autobackup", zAuto);
	}
	
	/**
	 * Desired Magic Numbers
	 */
	public MiniNumber getMagicDesiredKISSVM() {
		return getNumber("magic_kissvm", Magic.DEFAULT_KISSVM_OPERATIONS);
	}
	
	public void setMagicDesiredKISSVM(MiniNumber zKISSVM) {
		setNumber("magic_kissvm", zKISSVM);
	}
	
	public MiniNumber getMagicMaxTxPoWSize() {
		return getNumber("magic_txpowsize", Magic.DEFAULT_TXPOW_SIZE);
	}
	
	public void setMagicMaxTxPoWSize(MiniNumber zMaxSize) {
		setNumber("magic_txpowsize", zMaxSize);
	}
	
	public MiniNumber getMagicMaxTxns() {
		return getNumber("magic_txns", Magic.DEFAULT_TXPOW_TXNS);
	}
	
	public void setMagicMaxTxns(MiniNumber zMaxTxns) {
		setNumber("magic_txns", zMaxTxns);
	}
	
	/**
	 * Encrypted version of Seed phrase
	 */
	public void setEncryptedSeed(MiniData zEncryptedSeed) {
		setData("encrypted_seed", zEncryptedSeed);
	}
	
	public MiniData getEncryptedSeed() {
		return getData("encrypted_seed", MiniData.ZERO_TXPOWID);
	}
	
	/**
	 * MAXIMA - settings
	 */
	public boolean getMaximaAllowContacts() {
		return getBoolean("maxima_allowallcontacts", true);
	}
	
	public void setMaximaAllowContacts(boolean zAllowContacts) {
		setBoolean("maxima_allowallcontacts", zAllowContacts);
	}
	
	public ArrayList<String> getMaximaPermanent() {
		return MiniUtil.convertJSONArray(getJSONArray("maxima_permanent")) ;
	}
	
	public void setMaximaPermanent(ArrayList<String> zPermanentList) {
		setJSONArray("maxima_permanent",MiniUtil.convertArrayList(zPermanentList));
	}
}
