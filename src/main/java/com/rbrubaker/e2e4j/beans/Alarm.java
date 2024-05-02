package com.rbrubaker.e2e4j.beans;

/**
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
public class Alarm {

	/**
	 * Appears to be a unique number that counts up +1 for every alarm logged.
	 * Does not appear to be time or date based at all.
	 */
	private long advisoryId;
	private int advisoryCode;
	private String timestamp;
	private String state;
	private String source;
	private String text;
	boolean alarm;
	boolean notice;
	boolean fail;
	boolean unacknowledged;
	boolean acknowledged;
	boolean reset;
	boolean returnToNormal;
	private String acknowledgedUser;
	private String acknowledgedTimestamp;
	private int priority;
	private String returnToNormalTimestamp;
	/**
	 * This string value appears to be both the alarm value and the return to normal value.
	 * Ex: "32.94                39.92                "
	 * To use this value properly the values probably need to be split and the extra space removed.
	 */
	private String reportValue;
	/**
	 * This string value appears to be formated similarly to reportValue. I do not think that there can be 2 different values.
	 * However maybe if the alarm limit is changed during an active alarm, then it may have two values.
	 * Ex: "39.92                "
	 */
	private String limitValue;
	private String engineeringUnits;
	
	public long getAdvisoryId() {
		return advisoryId;
	}
	
	public void setAdvisoryId(long advisoryId) {
		this.advisoryId = advisoryId;
	}
	
	public int getAdvisoryCode() {
		return advisoryCode;
	}
	
	public void setAdvisoryCode(int advisoryCode) {
		this.advisoryCode = advisoryCode;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public boolean isAlarm() {
		return alarm;
	}
	
	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}
	
	public boolean isNotice() {
		return notice;
	}
	
	public void setNotice(boolean notice) {
		this.notice = notice;
	}
	
	public boolean isFail() {
		return fail;
	}
	
	public void setFail(boolean fail) {
		this.fail = fail;
	}
	
	public boolean isUnacknowledged() {
		return unacknowledged;
	}
	
	public void setUnacknowledged(boolean unacknowledged) {
		this.unacknowledged = unacknowledged;
	}
	
	public boolean isAcknowledged() {
		return acknowledged;
	}
	
	public void setAcknowledged(boolean acknowledged) {
		this.acknowledged = acknowledged;
	}
	
	public boolean isReset() {
		return reset;
	}
	
	public void setReset(boolean reset) {
		this.reset = reset;
	}
	
	public boolean isReturnToNormal() {
		return returnToNormal;
	}
	
	public void setReturnToNormal(boolean returnToNormal) {
		this.returnToNormal = returnToNormal;
	}
	
	public String getAcknowledgedUser() {
		return acknowledgedUser;
	}
	
	public void setAcknowledgedUser(String acknowledgedUser) {
		this.acknowledgedUser = acknowledgedUser;
	}
	
	public String getAcknowledgedTimestamp() {
		return acknowledgedTimestamp;
	}

	public void setAcknowledgedTimestamp(String acknowledgedTimestamp) {
		this.acknowledgedTimestamp = acknowledgedTimestamp;
	}

	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String getReturnToNormalTimestamp() {
		return returnToNormalTimestamp;
	}
	
	public void setReturnToNormalTimestamp(String returnToNormalTimestamp) {
		this.returnToNormalTimestamp = returnToNormalTimestamp;
	}
	
	public String getReportValue() {
		return reportValue;
	}
	
	public void setReportValue(String reportValue) {
		this.reportValue = reportValue;
	}
	
	public String getLimitValue() {
		return limitValue;
	}
	
	public void setLimitValue(String limitValue) {
		this.limitValue = limitValue;
	}
	
	public String getEngineeringUnits() {
		return engineeringUnits;
	}
	
	public void setEngineeringUnits(String engineeringUnits) {
		this.engineeringUnits = engineeringUnits;
	}

	@Override
	public String toString() {
		return "Alarm [advisoryId=" + advisoryId + ", advisoryCode=" + advisoryCode + ", timestamp=" + timestamp
				+ ", state=" + state + ", source=" + source + ", text=" + text + ", alarm=" + alarm + ", notice="
				+ notice + ", fail=" + fail + ", unacknowledged=" + unacknowledged + ", acknowledged=" + acknowledged
				+ ", reset=" + reset + ", returnToNormal=" + returnToNormal + ", acknowledgedUser=" + acknowledgedUser
				+ ", priority=" + priority + ", returnToNormalTimestamp=" + returnToNormalTimestamp + ", reportValue="
				+ reportValue + ", limitValue=" + limitValue + ", engineeringUnits=" + engineeringUnits + "]";
	}
	
}
