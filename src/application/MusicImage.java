package application;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class MusicImage extends ImageView {
	String imagePath, audioPath, offimagePath;
	AudioClip audioFile;
	boolean playing;

	public MusicImage(String imgres, String imgres2, String audiores, boolean plays) {
		super(new Image(imgres));
		imagePath=imgres;
		offimagePath=imgres2;
		playing = plays;
		audioFile = new AudioClip(new File(audiores).toURI().toString());
		
		if (playing)
			audioFile.play();
		else
			setImage(new Image(imgres2));
		
		setOnMouseReleased(e->{
			togglePlay();
		});
	}

	public void togglePlay() {
		playing = playing ? false : true;
		if (playing) {
			setImage(new Image(imagePath));
			audioFile.play();
		} else {
			audioFile.stop();
			setImage(new Image(offimagePath));
		}
	}
}
