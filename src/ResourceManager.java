import java.io.File;


public class ResourceManager {
	
	static private ResourceManager singleton;
	private String bundlePath = "/User/Luavis/";
	
	synchronized
	static public ResourceManager getInstance() {
		if(singleton == null)
			singleton = new ResourceManager();
		
		return singleton;
	}
	
	public File getFile(String dirName, String fileName) {
		return new File(bundlePath + this.pathJoin(dirName, fileName)); 
	}
	
	public File getFile(String fileName) {
		return getFile("", fileName);
	}
	
	public static String pathJoin(final String ... pathElements)
    {
        final String path;

        if(pathElements == null || pathElements.length == 0)
        {
            path = File.separator;
        }
        else
        {
            final StringBuilder builder;

            builder = new StringBuilder();

            for(final String pathElement : pathElements)
            {
                final String sanitizedPathElement;

                // the "\\" is for Windows... you will need to come up with the 
                // appropriate regex for this to be portable
                sanitizedPathElement = pathElement.replaceAll("\\" + File.separator, "");

                if(sanitizedPathElement.length() > 0)
                {
                    builder.append(sanitizedPathElement);
                    builder.append(File.separator);
                }
            }

            path = builder.toString();
        }

        return (path);
    }
}
