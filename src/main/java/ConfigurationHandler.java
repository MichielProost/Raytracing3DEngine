import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Read property file. Provide methods for accessing configuration parameters.
 */
public class ConfigurationHandler {

    private Properties prop;

    /**
     * Create a new configuration handler.
     * @param fileLocation The location of the property file.
     */
    public ConfigurationHandler(String fileLocation){
        this.prop = new Properties();
        try (FileInputStream ip = new FileInputStream( fileLocation )) {
            // Load the property file.
            this.prop.load( ip );
        } catch (IOException e){
            System.out.println("Error: Configuration file does not exist.");
            // Exit Java program.
            System.exit(0);
        }
    }

    /**
     * Return the value of the matched key.
     * @param key The key of the property.
     * @return The value of the matched key if it exists. Returns null otherwise.
     */
    public String getProperty(String key){
        return prop.getProperty( key );
    }

    /**
     * Return the value of the matched key as an int.
     * @param key The key of the property.
     * @return The value of the matched key as an int. Returns 0 if the key doesn't exist.
     */
    public int getIntProperty(String key){
        try {
            return Integer.parseInt( prop.getProperty( key ) );
        } catch(NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        return 0;
    }

    /**
     * Return the value of the matched key as a double.
     * @param key The key of the property.
     * @return The value of the matched key as a double. Returns 0.0 if the key doesn't exist.
     */
    public double getDoubleProperty(String key) {
        try {
            return Double.parseDouble( prop.getProperty( key ) );
        } catch(NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        return 0.0;
    }

}
