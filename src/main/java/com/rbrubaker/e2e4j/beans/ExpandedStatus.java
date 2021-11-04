package com.rbrubaker.e2e4j.beans;

/**
* E2e4J - A Java library for connecting with Emerson Einstein 2 Enhanced controllers.
*   Copyright (C) 2021 Rufus Brubaker Refrigeration
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
public class ExpandedStatus {

	private String pointer;
	private String value;
	private boolean alarm;
	private boolean notice;
	private boolean fail;
	private boolean override;
	private String overrideTime;
	private int overrideType;
	private String engineeringUnits;
	private int dataType;
	private String bypassTime;
	
	public String getPointer() {
		return pointer;
	}
	
	public void setPointer(String pointer) {
		this.pointer = pointer;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
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
	
	public boolean isOverride() {
		return override;
	}
	
	public void setOverride(boolean override) {
		this.override = override;
	}
	
	public String getOverrideTime() {
		return overrideTime;
	}
	
	public void setOverrideTime(String overrideTime) {
		this.overrideTime = overrideTime;
	}
	
	public int getOverrideType() {
		return overrideType;
	}
	
	public void setOverrideType(int overrideType) {
		this.overrideType = overrideType;
	}
	
	public String getEngineeringUnits() {
		return engineeringUnits;
	}
	
	public void setEngineeringUnits(String engineeringUnits) {
		this.engineeringUnits = engineeringUnits;
	}
	
	public int getDataType() {
		return dataType;
	}
	
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	
	public String getBypassTime() {
		return bypassTime;
	}
	
	public void setBypassTime(String bypassTime) {
		this.bypassTime = bypassTime;
	}

	@Override
	public String toString() {
		return "ExpandedStatus [pointer=" + pointer + ", value=" + value + ", alarm=" + alarm + ", notice=" + notice
				+ ", fail=" + fail + ", override=" + override + ", overrideTime=" + overrideTime + ", overrideType="
				+ overrideType + ", engineeringUnits=" + engineeringUnits + ", dataType=" + dataType + ", bypassTime="
				+ bypassTime + "]";
	}		
}
