package com.rbrubaker.e2e4j.beans;

import java.util.ArrayList;

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
public class MultiExpandedStatus {

	private ArrayList<ExpandedStatus> expandedStatuses = new ArrayList<ExpandedStatus>();

	public ArrayList<ExpandedStatus> getExpandedStatuses() {
		return expandedStatuses;
	}

	public void setExpandedStatuses(ArrayList<ExpandedStatus> expandedStatuses) {
		this.expandedStatuses = expandedStatuses;
	}
	
	public void addExpandedStatus(ExpandedStatus expandedStatus) {
		expandedStatuses.add(expandedStatus);
	}
		
}
