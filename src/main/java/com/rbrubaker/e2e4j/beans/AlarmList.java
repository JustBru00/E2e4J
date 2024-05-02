package com.rbrubaker.e2e4j.beans;

import java.util.ArrayList;
import java.util.Optional;

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
