package assignment;

import java.io.IOException;

public class Appointment implements Comparable<Appointment>{
	String patientName;
    String DocName;
    String currentApp;
    String nextApp;
    
    Appointment (String line) {
		try{
			DocName = line.split("\\s*,\\s*")[1];
		} catch(Exception e) {
			DocName = line.split("\\s*,")[1];
		}
		try{
			patientName = line.split("\\s*,\\s*")[0];
		} catch(Exception e) {
			patientName = line.split("\\s*,")[0];
		}
		try{
			currentApp = line.split("\\s*,\\s*")[2];
		} catch(Exception e) {
			currentApp = line.split("\\s*,")[2];
		}
		try{
			nextApp = line.split("\\s*,\\s*")[3];
		} catch(Exception e) {
			nextApp = line.split("\\s*,")[3];
		}
   }
    
    @Override
    public int compareTo(Appointment app1) {
    	char c1 = patientName.charAt(0);
    	char c2 = app1.patientName.charAt(0);
    	
    	if(c1 < c2) {
    		return 1;
    	}
    	else if (c1 > c2) {
    		return -1;
    	}
    	else {
    		c1 = patientName.charAt(1);
        	c2 = app1.patientName.charAt(1);
        	if(c1 < c2) {
        		return 1;
        	}
        	else if (c1 > c2) {
        		return -1;
        	}
        	else {
        		c1 = patientName.charAt(2);
            	c2 = app1.patientName.charAt(2);
            	if(c1 < c2) {
            		return 1;
            	}
            	else if (c1 > c2) {
            		return -1;
            	}
            	else {
            		return 0;
            	}
        	}	
    	}
    }
    
    @Override
	public String toString() {
		return currentApp + ", " + patientName + ", " + DocName + ", " + nextApp;
	}
}

