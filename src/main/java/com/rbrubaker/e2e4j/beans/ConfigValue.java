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
public class ConfigValue {

	private String pointer;
	private String value;
	private String valueBin;
	
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
	
	public String getValueBin() {
		return valueBin;
	}
	
	public void setValueBin(String valueBin) {
		this.valueBin = valueBin;
	}

	@Override
	public String toString() {
		return "ConfigValue [pointer=" + pointer + ", value=" + value + ", valueBin=" + valueBin + "]";
	}
}
