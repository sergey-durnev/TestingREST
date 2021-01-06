package files;

public class payLoad {
	
	public static String getPostData() {
		
		String res="\"formatted_address\": \"пр. Ленина, 75, Томск, Томская обл., Россия, 634050\",\r\n" + 
				"            \"geometry\": {\r\n" + 
				"                \"location\": {\r\n" + 
				"                    \"lat\": 56.47637599999999,\r\n" + 
				"                    \"lng\": 84.95029\r\n" + 
				"                },\r\n" + 
				"                \"viewport\": {\r\n" + 
				"                    \"northeast\": {\r\n" + 
				"                        \"lat\": 56.47772382989272,\r\n" + 
				"                        \"lng\": 84.95154147989273\r\n" + 
				"                    },\r\n" + 
				"                    \"southwest\": {\r\n" + 
				"                        \"lat\": 56.47502417010727,\r\n" + 
				"                        \"lng\": 84.94884182010728\r\n" + 
				"                    }\r\n" + 
				"                }\r\n" + 
				"            }";
		return res;
	}

}
