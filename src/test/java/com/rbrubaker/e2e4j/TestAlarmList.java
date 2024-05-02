package com.rbrubaker.e2e4j;

import java.util.Optional;

import com.rbrubaker.e2e4j.beans.Alarm;
import com.rbrubaker.e2e4j.beans.AlarmList;

public class TestAlarmList {

	public static void main(String[] args) {
		
		E2e e2e = new E2e("10.10.100.100");
		
		Optional<AlarmList> possibleAlarmList = e2e.getAlarmList();

		if (possibleAlarmList.isPresent()) {
			var list = possibleAlarmList.get().getAlarmList();			
			for (Alarm a : list) {
				System.out.println(a.toString());
			}
			System.out.println("Size: " + list.size());
		} else {
			System.out.println("Optional Empty.");
		}
		
	}

}
