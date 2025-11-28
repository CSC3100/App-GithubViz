import javiergs.tulip.GitHubHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * MetricsDelegate class to load Java files from a GitHub repository URL
 * and count inter-class references to estimate coupling/instability.
 *
 * @author javiergs
 * @version 1.2
 */
public class DelegateMetrics implements Runnable {
	
	private String url;
	
	public DelegateMetrics(String url) {
		this.url = url;
	}
	
	@Override
	public void run() {
		Blackboard.getInstance().clear();
		try {
			String token = loadTokenFromConfigFile();
			GitHubHandler gh = new GitHubHandler(token);
			
			// 1) Retrieve all files
			List<String> allFromUrl = gh.listFilesRecursive(url);
			
			// 2) Extract all class names
			List<String> allClassNames = new ArrayList<>();
			for (String path : allFromUrl) {
				if (path.endsWith(".java")) {
					allClassNames.add(extractSimpleClassName(path));
				}
			}
			
			// 3) For each file, count mentions to other classes
			for (String path : allFromUrl) {
				if (path.endsWith(".java")) {
					String content = gh.getFileContentFromUrl(convertToBlobUrl(url, path));
					int mentions = countMentions(content, extractSimpleClassName(path), allClassNames);
					
					Square square = new Square(path, mentions);
					Blackboard.getInstance().addSquare(square);
				}
			}
			
			Blackboard.getInstance().setReady();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Count how many times this file content mentions the names of the other classes.
	 *
	 * @param content       source code of the current file
	 * @param currentClass  simple name of the current class (to skip it)
	 * @param allClassNames list of all class names in the repo
	 * @return total number of mentions to other classes
	 */
	private int countMentions(String content, String currentClass, List<String> allClassNames) {
		int total = 0;
		for (String name : allClassNames) {
			if (name.equals(currentClass)) continue;
			int idx = 0;
			while ((idx = content.indexOf(name, idx)) != -1) {
				total++;
				idx += name.length();
			}
		}
		return total;
	}
	
	private String extractSimpleClassName(String path) {
		String fileName = path.substring(path.lastIndexOf('/') + 1);
		if (fileName.endsWith(".java")) {
			return fileName.substring(0, fileName.length() - ".java".length());
		}
		return fileName;
	}
	
	private String loadTokenFromConfigFile() throws IOException {
		Properties properties = new Properties();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				throw new IOException("config.properties not found in classpath.");
			}
			properties.load(input);
			return properties.getProperty("github.token");
		}
	}
	
	private String convertToBlobUrl(String url, String path) {
		if (url.contains("/tree/")) {
			String[] parts = url.split("/tree/");
			return parts[0] + "/blob/" + parts[1].split("/")[0] + "/" + path;
		} else {
			return url.replace("/tree/", "/blob/") + "/" + path;
		}
	}
}