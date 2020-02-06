package ua.kpi.tef.zu;

/**
 * Created by Anton Domin on 2020-02-06
 */

public class Main {

	public static void main(String[] args) {
		// Initialization
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(model, view);
		// Run
		controller.startNumberGame();
	}

}
