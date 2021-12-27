# E2e4J 
### Author: Justin Brubaker
Java library for connecting to Emerson Einstein 2 Enhanced controllers. (Abbreviation: E2e)
The goal of this library is to provide access to controller data by using the E2e's "FSD Client Port".
This library is not based around any official Emerson API documentation.

### Maven Details
Repository:
```
	<repositories>
		<repository>
			<id>jitpack.io</id>
			<url>https://jitpack.io</url>
		</repository>
	</repositories>
```

Dependency:
```
	<dependency>
		<groupId>com.github.JustBru00</groupId>
		<artifactId>E2e4J</artifactId>
		<version>3924936</version> <!-- There is no published version yet. Using commit 3924936 instead. -->
	</dependency>
```

### Library usage examples:
The main class you will be dealing with is the `E2e` class. 
```Java
E2e e2e = new E2e("192.168.0.26");
```

This will create a new instance of the `E2e` class that is ready to connect to a E2e controller at 192.168.0.26:14106.
The constructor of the `E2e` class is blocking because we need to contact the E2e to get the controller name.
This controller name is important when constructing data pointers for the E2e.
If the E2e you are connecting to uses a different "FSD Client Port", you can set a custom port using the following code:
```Java
E2e e2e = new E2e("192.168.0.26", "CUSTOM_PORT_NUMBER_HERE");
```

Next, lets get the current status of the `CONTROL TEMP` value on two different standard circuit applications.
In this example the two standard circuit applications are named `1 S FRZR EVAP` and `2 N FRZR EVAP`.
```Java
ArrayList<String> pointers = new ArrayList<String>();
pointers.add("1 S FRZR EVAP:CONTROL TEMP");
pointers.add("2 N FRZR EVAP:CONTROL TEMP");
		
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
```

The `ExpandedStatus` class stores the information returned by the E2e. I have an example output of the data returnedd below:
```
ExpandedStatus [pointer=EINSTEIN:1 S FRZR EVAP:CONTROL TEMP, value=66.30, alarm=false, notice=false, fail=false, override=true, overrideTime=78:16:51, overrideType=0, engineeringUnits=DF, dataType=1, bypassTime=]
ExpandedStatus [pointer=EINSTEIN:2 N FRZR EVAP:CONTROL TEMP, value=67.40, alarm=false, notice=false, fail=false, override=true, overrideTime=0:00:00, overrideType=0, engineeringUnits=DF, dataType=1, bypassTime=]
```

Notice that the `pointer` variable now has the controller name as a prefix. This is why the E2e class blocks to retrieve the controller name.

This shows the most basic application of this library, retrieving the current temperature from a standard circuit application. 

### License
```
E2e4J - A Java library for connecting with Emerson Einstein 2 Enhanced controllers.
    Copyright (C) 2021 Rufus Brubaker Refrigeration

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>
    
    You can contact us at rbr@rbrubaker.com.
```    



