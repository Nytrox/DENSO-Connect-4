import org.apache.commons.net.telnet.TelnetClient;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class TelNet {
    private static TelnetClient telnet = new TelnetClient();
    private static InputStream in;
    private static PrintWriter output;
    private static String prompt = "";

    public TelNet(String server, String user, String password) throws Exception {
    	while(true){
	    	try {
	    		telnet.connect(server, 50000);		    						//Connect to the specified server at port 50000
	
	    		// Get input and output stream references
	    		in = telnet.getInputStream();
	    		output = new PrintWriter(new OutputStreamWriter(telnet.getOutputStream()), true);
	    	} catch (Exception e) {
	    	}
	    	TimeUnit.SECONDS.sleep(1);
	    	if(TelNet.getInputStream() == null){
				TimeUnit.SECONDS.sleep(1);
			}
	    	else{
	    		break;
	    	}
		}
    }
    
    public static InputStream getInputStream(){
    	return in;
    }

    public void su(String password) {
    	try {
    		write("su");
    		readUntil("Password: ");
    		write(password);
    		prompt = "#";
    		readUntil(prompt + " ");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static String readUntil(String pattern) {   
        try {   
            char lastChar = pattern.charAt(pattern.length() - 1);   
            StringBuffer sb = new StringBuffer();   
            char ch = (char) in.read();   
            while (true) {   
            	sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                    	String stringy = sb.toString();
                        return String.valueOf(stringy.charAt(stringy.length() - 2));
                    }
                }
                int value = in.read();
                if (value<1) break;
                ch = (char) value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public static String readString() throws IOException {

        ByteArrayOutputStream into = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        for (int n; 0 < (n = in.read(buf));) {
            into.write(buf, 0, n);
        }
        into.close();
        return new String(into.toByteArray(), "UTF-8"); // Or whatever encoding
    }

    public static void write(String value) {
    	try {
    		output.print(value+"\n");
    		output.flush();
    		System.out.println(value);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public static String sendCommand(String command) {
    	try {
    		output.print(command+"\n");
    		output.flush();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    public void disconnect() {
    	try {
    		telnet.disconnect();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}