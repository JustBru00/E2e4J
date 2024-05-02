package com.rbrubaker.e2e4j.beans;

import java.util.ArrayList;
import java.util.Optional;

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
public class AlarmList {

	private ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
	
	public void addAlarmToList(Alarm alarm) {
		alarmList.add(alarm);
	}

	public ArrayList<Alarm> getAlarmList() {
		return alarmList;
	}

	public void setAlarmList(ArrayList<Alarm> alarmList) {
		this.alarmList = alarmList;
	}	
	
	public Optional<Alarm> getAlarmByAdvisoryId(long advisoryId) {
		for (Alarm a : alarmList) {
			if (a.getAdvisoryId() == advisoryId) {
				return Optional.of(a);
			}
		}
		return Optional.empty();
	}
	
	/**
	 * Searches the alarm list for the given keywords in source and text.
	 * @param ignoreAcknowledged
	 * @param ignoreReset
	 * @param keywords
	 * @return
	 */
	public ArrayList<Alarm> search(boolean ignoreAcknowledged, boolean ignoreReset, boolean ignoreReturnToNormal, String... keywords) {
		ArrayList<Alarm> filtered = new ArrayList<Alarm>(); 
		
		for (Alarm a : alarmList) {
			if (ignoreAcknowledged && a.isAcknowledged()) {
				continue;
			}
			
			if (ignoreReset && a.isReset()) {
				continue;
			}
			
			if (ignoreReturnToNormal && a.isReturnToNormal()) {
				continue;
			}
			
			for (String s : keywords) {
				if (a.getSource().toLowerCase().contains(s.toLowerCase())) {
					filtered.add(a);
					break;
				}
				
				if (a.getText().toLowerCase().contains(s.toLowerCase())) {
					filtered.add(a);
					break;
				}
			}
		}
		
		return filtered;
	}
}
