import javiergs.tulip.GitHubHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Delegate class to load Java files from a GitHub repository URL.
 *
 * @author javiergs
 * @version 1.0
 */
public class Delegate implements Runnable {
	
	private String url;
	
	public Delegate(String url) {
		this.url = url;
	}
	
	@Override
	public void run() {
		Blackboard.getInstance().clear();
		try {
			String token = loadTokenFromConfigFile();
			GitHubHandler gh = new GitHubHandler(token);
			List<String> allFromUrl = gh.listFilesRecursive(url);
			for (String path : allFromUrl) {
				if (path.endsWith(".java")) {
					String content = gh.getFileContentFromUrl(convertToBlobUrl(url, path));
					System.out.println("Content of " + path + ":");
					int lines = countLines(content);
					Square square = new Square(path, lines);
					Blackboard.getInstance().addSquare(square);
				}
			}
			Blackboard.getInstance().setReady();
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String loadTokenFromConfigFile() throws IOException {
		Properties props = new Properties();
		try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			if (in != null) {
				props.load(in);
			}
		}
		System.out.println(props.getProperty("github.token"));
		return props.getProperty("github.token");
	}
	
	private int countLines(String content) {
		return (int) content.lines().count();
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