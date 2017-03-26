package me.F_o_F_1092.CreativeElytra.PluginManager;

import java.util.List;

import me.F_o_F_1092.CreativeElytra.PluginManager.JSONMessage;

public class JSONMessageListener {

	public static String putJSONMessagesTogether(List<JSONMessage> jsonMessages) {
		
		String jsonString = "[\"\"";
		
		for (JSONMessage jsonMessage : jsonMessages) {
			jsonString += "," + jsonMessage.getJsonText();
		}
		
		jsonString += "]";
		
		return jsonString;
	}
}
