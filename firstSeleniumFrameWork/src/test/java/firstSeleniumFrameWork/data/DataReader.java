package firstSeleniumFrameWork.data;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import java.util.List;
import java.util.HashMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class DataReader {

	public List<HashMap<String, String>> getDataFromJSON(String JSON_PATH) throws IOException {
		
		String jSONString = FileUtils.readFileToString(new File(JSON_PATH));
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jSONString, new TypeReference<List<HashMap<String,String>>>() {
		});
		
	}
}
