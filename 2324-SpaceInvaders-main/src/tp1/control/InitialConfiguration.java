package tp1.control;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tp1.view.Messages;


public class InitialConfiguration {

    private List<String> descriptions;
	
	public static final InitialConfiguration NONE = new InitialConfiguration();
	
	public static InitialConfiguration readFromFile(String filename) throws FileNotFoundException{
		InitialConfiguration conf = new InitialConfiguration();
		conf.descriptions = new ArrayList<String>();
		 try {
	            List<String> lineas = Files.readAllLines(Paths.get(filename + ".txt"));
	            
	            for (String linea : lineas) {
	            	conf.descriptions.add(linea);
	            } 
            
        } catch (Exception e) {
            throw new FileNotFoundException(String.format(Messages.FILE_NOT_FOUND, filename));
        }
		return conf;
		
	}

    private InitialConfiguration() {
        this.descriptions = Collections.emptyList();
    }

    private InitialConfiguration(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public List<String> getShipDescription() {
        return Collections.unmodifiableList(descriptions);
    }

}

/*
 * public static InitialConfiguration readFromFile(String filename) throws FileNotFoundException {
    InitialConfiguration conf = new InitialConfiguration();
    conf.descriptions = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename + ".txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            conf.descriptions.add(line);
        }
    } catch (IOException e) {
        throw new FileNotFoundException(String.format(Messages.FILE_NOT_FOUND, filename));
    }
    return conf;
}
*/

