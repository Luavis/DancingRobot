
public class Main {
	public static void main(String[] args) {
		Resources.initResources();
		Application application = (new Application(Constants.MAIN_WINDOW_WIDTH, Constants.MAIN_WINDOW_HEIGHT,Constants.MAIN_WINDOW_TITLE));
		application.getApplicationWindow().setVisible(true);
		application.startAnimation();
  }
}
