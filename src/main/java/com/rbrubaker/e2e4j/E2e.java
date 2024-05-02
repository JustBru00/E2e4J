package com.rbrubaker.e2e4j;

import java.util.ArrayList;
import java.util.Optional;

import com.rbrubaker.e2e4j.beans.Alarm;
import com.rbrubaker.e2e4j.beans.AlarmList;
import com.rbrubaker.e2e4j.beans.ConfigValue;
import com.rbrubaker.e2e4j.beans.E2eControllerInformation;
import com.rbrubaker.e2e4j.beans.ExpandedStatus;
import com.rbrubaker.e2e4j.beans.MultiConfigValue;
import com.rbrubaker.e2e4j.beans.MultiExpandedStatus;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
/**
 * This class implements methods that were reverse engineered from e2json.js on the E2e webserver.
 * Full list of methods from e2json.js are listed below. Methods that have been implemented in this class are marked with an X.
 * E2.GetCellList
 * E2.Override
 * E2.MultiOverride
 * E2.GetPointInfo
 * E2.GetMultiPointInfo
 * E2.GetExpandedStatus
 * E2.GetMultiExpandedStatus  X
 * E2.GetExpandedInfo
 * E2.GetLogDataRaw
 * E2.GetAlarmList  X
 * E2.AlarmAction
 * E2.GetDeviceInfoForRoute
 * E2.GetConfigValues
 * E2.SetConfigValues
 * E2.GetAuxProps
 * E2.SetAuxProps
 * E2.GetMultiAuxProps
 * E2.SetMultiAuxProps
 * E2.GetAppConfig
 * E2.SetAppConfig
 * E2.GetBatteryStatus
 * E2.BatteryReplaced
 * E2.GetOutputPointers
 * E2.GetControllerList  X
 * 
 * E2e4J - A Java library for connecting with Emerson Einstein 2 Enhanced controllers.
 *   Copyright (C) 2024 Rufus Brubaker Refrigeration
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>
 *   
 *   You can contact us at rbr@rbrubaker.com.
 * 
 * @author Justin Brubaker
 *
 */
public class E2e {

	public static final String DEFAULT_E2E_FSD_CLIENT_PORT = "14106";
	
	private String ipAddress;
	private String portNumber;
	private String controllerName;
	private String model;
	private String firmwareRevision;	
	
	/**
	 * Creates a new instance for connecting to the given E2e controller.
	 * This method is blocking as it will contact the given E2e controller to get the controller name, model, and firmware revision.
	 * @param _ipAddress The IP address of the E2e this instance will contact.
	 * @param _portNumber A custom port number. This is the port number set with "FSD Client Port" on the E2e. The default is {@link E2e#DEFAULT_E2E_FSD_CLIENT_PORT}.
	 * @throws UnirestException
	 */
	public E2e(String _ipAddress, String _portNumber) throws UnirestException {
		ipAddress = _ipAddress;
		portNumber = _portNumber;
		getControllerInformation();
	}
	
	/**
	 * Creates a new instance for connecting to the given E2e controller.
	 * This method is blocking as it will contact the given E2e controller to get the controller name, model, and firmware revision.
	 * @param _ipAddress The IP address of the E2e this instance will contact.
	 * @throws UnirestException
	 */
	public E2e(String _ipAddress) throws UnirestException {
		ipAddress = _ipAddress;
		portNumber = DEFAULT_E2E_FSD_CLIENT_PORT;
		getControllerInformation();
	}	
	
	/**
	 * Method: E2.GetMultiExpandedStatus
	 * 
	 * @param pointers A list of pointer strings. Eg. 1 S FRZR EVAP:CONTROL TEMP
	 * @return An instance of {@link MultiExpandedStatus} with the current status of the given pointers.
	 * @throws UniresetException
	 */
	public Optional<MultiExpandedStatus> getMultiExpandedStatus(ArrayList<String> pointers) {
		MultiExpandedStatus multiExpandedStatus = new MultiExpandedStatus();
		StringBuilder jsonPointers = new StringBuilder();
		
		for (String pointer : pointers) {
			jsonPointers.append("\"" + controllerName + ":" + pointer + "\", ");
		}	
		
		String formattedPointer = jsonPointers.toString().substring(0, jsonPointers.toString().length() - 2);
		
		HttpResponse<JsonNode> response = Unirest.post(String.format("http://%s:%s/JSON-RPC?", ipAddress, portNumber))
				.header("Content-Type", "text/plain")
				.body(String.format("{\"id\":0,\"method\":\"E2.GetMultiExpandedStatus\",\"params\":[[%s]]}", formattedPointer))
				.asJson();
				
		if (response.getStatus() == 200) {
			JSONObject resultObj;
			JSONArray dataArray;
			try {
				resultObj = response.getBody().getObject().getJSONObject("result");
				dataArray = resultObj.getJSONArray("data");
			} catch (JSONException e) {
				System.out.println("ERROR: E2e#getMultiExpandedStatus() - Response had no result object.");
				System.out.println("ERROR: Response Body: " + response.getBody());
				return Optional.empty();
			}
			System.out.println("E2e Response:" + response.getBody().toString());
			try {
				for (int i = 0; i < dataArray.length(); i++) {
					ExpandedStatus expandedStatus = new ExpandedStatus();
					JSONObject current = dataArray.getJSONObject(i);
					expandedStatus.setPointer(current.getString("prop"));
					expandedStatus.setValue(current.getString("value"));
					expandedStatus.setAlarm(current.getBoolean("alarm"));
					expandedStatus.setNotice(current.getBoolean("notice"));
					expandedStatus.setFail(current.getBoolean("fail"));
					expandedStatus.setOverride(current.getBoolean("override"));
					expandedStatus.setOverrideTime(current.getString("ovtime"));
					expandedStatus.setEngineeringUnits(current.getString("engUnits"));
					expandedStatus.setDataType(current.getInt("dataType"));
					expandedStatus.setBypassTime(current.getString("bypasstime"));
					multiExpandedStatus.addExpandedStatus(expandedStatus);
				}
				
				return Optional.of(multiExpandedStatus);
			} catch (JSONException e) {
				System.out.println("ERROR: E2e#getMultiExpandedStatus() - Failed to parse response.");
				return Optional.empty();
			}			
		}
		
		return Optional.empty();
	}
	
	/**
	 * Method: E2.GetAlarmList
	 * 
	 * 
	 * @return An instance of {@link AlarmList} with the list of {@link Alarm}s.
	 * @throws UniresetException
	 */
	public Optional<AlarmList> getAlarmList() {
		HttpResponse<JsonNode> response = Unirest.post(String.format("http://%s:%s/JSON-RPC?", ipAddress, portNumber))
				.header("Content-Type", "text/plain")
				.body(String.format("{\"id\":0,\"method\":\"E2.GetAlarmList\",\"params\":[\"%s\", false]}", controllerName))
				.asJson();
				
		if (response.getStatus() == 200) {			
			JSONObject resultObj;
			JSONArray dataArray;
			
			try {
				resultObj = response.getBody().getObject().getJSONObject("result");
				dataArray = resultObj.getJSONArray("data");
			} catch (JSONException e) {
				System.out.println("ERROR: E2e#getAlarmList() - Response had no result object.");
				System.out.println("ERROR: Response Body: " + response.getBody());
				return Optional.empty();
			}
			
			try {
				AlarmList list = new AlarmList();
				for (int i = 0; i < dataArray.length(); i++) {
					Alarm alarm = new Alarm();
					JSONObject current = dataArray.getJSONObject(i);
					alarm.setAdvisoryId(current.getLong("advid"));
					alarm.setAdvisoryCode(current.getInt("advcode"));
					alarm.setTimestamp(current.getString("timestamp"));
					alarm.setState(current.getString("state"));
					alarm.setSource(current.getString("source"));
					alarm.setText(current.getString("text"));
					alarm.setAlarm(current.getBoolean("alarm"));
					alarm.setNotice(current.getBoolean("notice"));
					alarm.setFail(current.getBoolean("fail"));
					alarm.setUnacknowledged(current.getBoolean("unacked"));
					alarm.setAcknowledged(current.getBoolean("acked"));
					alarm.setReset(current.getBoolean("reset"));
					alarm.setReturnToNormal(current.getBoolean("rtn"));
					alarm.setAcknowledgedUser(current.getString("ackuser"));
					alarm.setAcknowledgedTimestamp(current.getString("acktimestamp"));
					alarm.setPriority(current.getInt("priority"));
					alarm.setReturnToNormalTimestamp(current.getString("rtntimestamp"));
					alarm.setReportValue(current.getString("reportvalue"));
					alarm.setLimitValue(current.getString("limit"));
					alarm.setEngineeringUnits(current.getString("engUnits"));
					
					list.addAlarmToList(alarm);
				}
				
				return Optional.of(list);
			} catch (JSONException e) {
				System.out.println("ERROR: E2e#getAlarmList() - Failed to parse response.");
				return Optional.empty();
			}
		}
		return Optional.empty();
	}
	
	/**
	 * Method: E2.GetConfigValues
	 * 
	 * @param pointers A list of pointer strings without the controller name. Eg. CONDENSER:PRES CTRL STPT
	 * @return An instance of {@link MultiConfigValue} with the current status of the given pointers.
	 * @throws UniresetException
	 */
	public Optional<MultiConfigValue> getConfigValues(ArrayList<String> pointersWithoutControllerName) {
		MultiConfigValue multiConfigValues = new MultiConfigValue();
		StringBuilder jsonPointers = new StringBuilder();
		
		for (String pointer : pointersWithoutControllerName) {
			jsonPointers.append("\"" + controllerName + ":" + pointer + "\", ");
		}	
		
		String formattedPointer = jsonPointers.toString().substring(0, jsonPointers.toString().length() - 2);
		
		HttpResponse<JsonNode> response = Unirest.post(String.format("http://%s:%s/JSON-RPC?", ipAddress, portNumber))
				.header("Content-Type", "text/plain")
				.body(String.format("{\"id\":0,\"method\":\"E2.GetConfigValues\",\"params\":[[%s]]}", formattedPointer))
				.asJson();
				
		if (response.getStatus() == 200) {
			JSONObject resultObj;
			JSONArray dataArray;
			try {
				resultObj = response.getBody().getObject().getJSONObject("result");
				dataArray = resultObj.getJSONArray("data");
			} catch (JSONException e) {
				System.out.println("ERROR: E2e#getConfigValues() - Response had no result object.");
				System.out.println("ERROR: Response Body: " + response.getBody());
				return Optional.empty();
			}
			System.out.println("E2e Response:" + response.getBody().toString());
			try {
				for (int i = 0; i < dataArray.length(); i++) {
					ConfigValue configValue = new ConfigValue();
					JSONObject current = dataArray.getJSONObject(i);
					configValue.setPointer(current.getString("prop"));
					configValue.setValue(current.getString("value"));
					configValue.setValueBin(current.getString("valueBin"));
					multiConfigValues.addConfigValue(configValue);
				}
				
				return Optional.of(multiConfigValues);
			} catch (JSONException e) {
				System.out.println("ERROR: E2e#getConfigValues() - Failed to parse response.");
				return Optional.empty();
			}			
		}
		
		return Optional.empty();
	}
	
	/**
	 * Method: E2.GetControllerList
	 * 
	 * @return An instance of {@link E2eControllerInformation} with the information received from the E2e controller.
	 * @throws UnirestException
	 */
	public Optional<E2eControllerInformation> getControllerInformation() throws UnirestException {
		E2eControllerInformation info = new E2eControllerInformation();
				
		HttpResponse<JsonNode> response = Unirest.post(String.format("http://%s:%s/JSON-RPC?", ipAddress, portNumber))
				.header("Content-Type", "text/plain")
				.body("{\"id\":0,\"method\":\"E2.GetControllerList\",\"params\":[1]}")
				.asJson();
		
		if (response.getStatus() == 200) {
			JSONArray resultObj;
			try {
				resultObj = response.getBody().getObject().getJSONArray("result");
			} catch (JSONException e) {
				System.out.println("ERROR: E2e#getControllerInformation() - Response had no result array.");
				return Optional.empty();
			}			
			
			try {
				JSONObject controller1 = resultObj.getJSONObject(0);
				info.setName(controller1.getString("name"));
				info.setType(controller1.getInt("type"));
				info.setModel(controller1.getString("model"));
				info.setFirmwareRevision(controller1.getString("revision"));
				info.setSubnet(controller1.getInt("subnet"));
				info.setNode(controller1.getInt("node"));
			} catch (JSONException e) {
				System.out.println("ERROR: E2e#getControllerInformation() - Trouble parsing controller 1.");
				return Optional.empty();
			}
			controllerName = info.getName();
			model = info.getModel();
			firmwareRevision = info.getFirmwareRevision();
			return Optional.of(info);
		}
		return Optional.empty();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public String getControllerName() {		
		return controllerName;
	}

	public String getModel() {
		return model;
	}

	public String getFirmwareRevision() {
		return firmwareRevision;
	}
}
