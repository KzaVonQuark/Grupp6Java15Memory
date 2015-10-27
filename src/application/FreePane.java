package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FreePane is a pane that can smoothly transition from 1 pane to another with
 * color or black/white fade transition or without.
 * This pane will be used as root Pane in the main project.
 * Initialization to the projects first pane is done with setPane().
 * Transitions are made with setPane() or fadeChange() with Color.color as fade color.
 * @author Masih
 *
 */
public class FreePane extends GridPane {
	protected Pane innerContent;
	protected Canvas transit;
	private float gradient = 0.0f;

	// inner Transition Method.
	public void fadeChange(Pane newPane, Color fadeColor) {
		transit = new Canvas(getWidth(), getHeight());
		add(transit, 0, 0);
		GraphicsContext gc = transit.getGraphicsContext2D();

		Timeline loadAnimation = new Timeline();
		loadAnimation.setCycleCount(Timeline.INDEFINITE);
		final double r = fadeColor.getRed();
		final double g = fadeColor.getGreen();
		final double b = fadeColor.getBlue();
		KeyFrame fadeAnimation = new KeyFrame(Duration.seconds(0.03), e -> {

			gradient = gradient + 0.04f;
			gc.clearRect(0, 0, getWidth(), getHeight());
			if (gradient < 1) {
				gc.setFill(Color.color(r, g, b, gradient));
				gc.fillRect(0, 0, getWidth(), getHeight());
			} else {
				changePane(newPane, fadeColor);
				loadAnimation.stop();

			}
		});

		loadAnimation.getKeyFrames().add(fadeAnimation);
		loadAnimation.play();

	}
	//Instant transition to new Pane.
	public void setPane(Pane newPane) {
		getChildren().remove(innerContent);
		innerContent = newPane;
		innerContent.setMinWidth(getWidth());
		innerContent.setMinHeight(getHeight());
		add(innerContent, 0, 0);
	}
	//Inner method that handles pane change.
	private void changePane(Pane newPane, Color fadeColor) {
		getChildren().remove(innerContent);
		innerContent = newPane;
		innerContent.setMinWidth(getWidth());
		innerContent.setMinHeight(getHeight());
		innerContent.setVisible(false);
		add(innerContent, 0, 0);
		fadeBack(innerContent, fadeColor);
	}

	private void fadeBack(Pane newPane, Color fadeColor) {
		// TODO Auto-generated method stub
		transit = new Canvas(getWidth(), getHeight());
		add(transit, 0, 0);
		final double r = fadeColor.getRed();
		final double g = fadeColor.getGreen();
		final double b = fadeColor.getBlue();
		GraphicsContext gc = transit.getGraphicsContext2D();
		gc.setFill(Color.color(r, g, b, 1));
		gc.fillRect(0, 0, getWidth(), getHeight());
		newPane.setVisible(true);
		Timeline backAnimation = new Timeline();
		backAnimation.setCycleCount(Timeline.INDEFINITE);

		KeyFrame fadeAnimation = new KeyFrame(Duration.seconds(0.03), e -> {
			gradient = gradient - 0.04f;
			gc.clearRect(0, 0, getWidth(), getHeight());
			if (gradient > 0) {
				gc.setFill(Color.color(r, g, b, gradient));
				gc.fillRect(0, 0, getWidth(), getHeight());
			} else {
				backAnimation.stop();
				getChildren().remove(transit);
			}
		});

		backAnimation.getKeyFrames().add(fadeAnimation);
		backAnimation.play();
	}

}
