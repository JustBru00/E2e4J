package com.rbrubaker.e2e4j;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import com.rbrubaker.e2e4j.beans.ExpandedStatus;
import com.rbrubaker.e2e4j.beans.MultiExpandedStatus;

import kong.unirest.UnirestException;

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
public class CommandLineStatusCheck {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (scanner.hasNext()) {
			String next = scanner.nextLine();
			
			if (next.equalsIgnoreCase("exit")) {
				scanner.close();
				break;
			} else if (next.startsWith("status")) {
				// status COOLER 1:CONTROL TEMP|
				E2e e2e = new E2e("192.168.0.23");
				
				ArrayList<String> pointers = new ArrayList<String>();
				pointers.add("1A PLANT COOL:CONTROL TEMP");
				pointers.add("2A PLANT COOL:CONTROL TEMP");
				pointers.add("3A SHIP CLR:CONTROL TEMP");
				pointers.add("4A SHIP CLR:CONTROL TEMP");
				pointers.add("5A SHIP CLR:CONTROL TEMP");
				pointers.add("6A SHIP CLR:CONTROL TEMP");
				pointers.add("7A NEST CLR:CONTROL TEMP");
				pointers.add("8A NEST CLR:CONTROL TEMP");
				
				try {
					Optional<MultiExpandedStatus> multiExpanded = e2e.getMultiExpandedStatus(pointers);
					
					if (multiExpanded.isPresent()) {
						for (ExpandedStatus es : multiExpanded.get().getExpandedStatuses()) {
							System.out.println(es.toString());
						}
					} else {
						System.out.println("Optional was empty.");
					}				
				} catch (UnirestException e) {
					e.printStackTrace();
					System.out.println("Exception occured while attempting to get the multi expanded status.");
				}
			}
		}
		
	}

}
