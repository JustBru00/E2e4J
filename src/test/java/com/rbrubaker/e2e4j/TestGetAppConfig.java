package com.rbrubaker.e2e4j;

import java.util.ArrayList;

import com.rbrubaker.e2e4j.beans.ConfigValue;

public class TestGetAppConfig {

	
	public static void main(String[] args) {
		E2e e2e = new E2e("10.10.100.100");
		// EINSTEIN:
		ArrayList<String> pointers = new ArrayList<String>();
		pointers.add("CONDENSER:PRES CTRL STPT");
		
		var possible = e2e.getConfigValues(pointers);
		
		if (possible.isPresent()) {
			for (ConfigValue cv : possible.get().getConfigValues()) {
				System.out.println(cv.toString());
			}
		} else {
			System.out.println("Empty optional");
		}
		
	}
}
