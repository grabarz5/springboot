//import java.io.File;
//import java.io.IOException;
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
//
//public class JsonWriter {
//
//    public static void main(String[] args) {
//
//        Country countryObj = new Country();
//        countryObj.name = "India";
//        countryObj.population = 1000000;
//
//        List<String> listOfStates = new ArrayList<String>();
//        listOfStates.add("Madhya Pradesh");
//        listOfStates.add("Maharastra");
//        listOfStates.add("Rajasthan");
//
//        countryObj.states = listOfStates ;
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//
//            // Writing to a file
//            mapper.writeValue(new File("c:\\country.json"), countryObj );
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
//
//public class json {
//    public class Country{
//        public String name;
//        public Integer population;
//        public List<String> states;
//    }
//}



//public class JsonSimpleWriteExample {
//
//    public static void main(String[] args) {
//
//        JSONObject json = new JSONObject();
//        json.put("name", "mkyong.com");
//        json.put("age", new Integer(100));
//
//        JSONArray list = new JSONArray();
//        list.add("msg 1");
//        list.add("msg 2");
//        list.add("msg 3");
//
//        json.put("messages", list);
//
//        try (FileWriter file = new FileWriter("f:\\test.json")) {
//
//            file.write(json.toJSONString());
//            file.flush();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.print(json);
//
//    }
//
//}