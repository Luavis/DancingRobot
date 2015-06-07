
public class Main {
	public static void main(String[] args) {
		Resources.initResources();
		Application.getInstance().initApplication(Constants.MAIN_WINDOW_WIDTH,
										Constants.MAIN_WINDOW_HEIGHT, 
										Constants.MAIN_WINDOW_TITLE);
		Application.getInstance().getApplicationWindow().setVisible(true);
		Application.getInstance().startAnimation();
  }
}
