package pers.jiangyinzuo.rollcall.ui.javafx.router;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Jiang Yinzuo
 */
public class StageManager {

	private static Stage currentStage;

	public static Stage getCurrentStage() {
		return currentStage;
	}

	// ���ҳ�������
	private static Map<String, Scene> sceneMap = new HashMap<>();

	// �����̨������
	private static Map<String, Stage> stageMap = new HashMap<>();

	private static Scene getScene(String sceneName) {
		if (sceneMap.get(sceneName) == null) {
			try {
				createScene(sceneName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sceneMap.get(sceneName);
	}

	private static void createScene(String sceneName) throws IOException {
		for (int i = 0; i < sceneName.length(); ++i) {
			if (sceneName.charAt(i) == '.') {
				Parent root = FXMLLoader.load(new URL("file:" + System.getProperty("user.dir") + "\\resources1\\" + sceneName));
				sceneMap.put(sceneName, new Scene(root));
			}
		}
	}

	public static void showStage(String stageName, String sceneName) {
		Stage stage = getStage(stageName);
		stage.setScene(getScene(sceneName));
		stage.setTitle(stageName);
		stage.setResizable(false);
		stage.show();
	}
	
	public static void showTempStage(String stageTitle, String fxmlFileName) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(new URL("file:" + System.getProperty("user.dir") + "\\resources1\\" + fxmlFileName));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle(stageTitle);
			stage.setResizable(false);
			stage.show();
			currentStage = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Stage getStage(String stageName) {
		Stage stage = stageMap.get(stageName);
		if (stage == null) {
			stage = new Stage();
			addStage(stage, stageName);
		}
		return stage;
	}

	public static void addStage(Stage stage, String stageName) {
		stageMap.put(stageName, stage);
	}

	public static void closeStage(String stageName) {
		if (stageMap.get(stageName) != null) {
			stageMap.get(stageName).close();
		}
	}
}
