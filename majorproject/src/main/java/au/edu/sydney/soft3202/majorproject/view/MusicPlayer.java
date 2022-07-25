package au.edu.sydney.soft3202.majorproject.view;

import javafx.scene.control.ToggleButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.net.URL;

public class MusicPlayer {

  private MediaPlayer mediaPlayer;

  /** Theme Music plays at the start of the application */
  public void playThemeMusic() {
    URL resource = getClass().getClassLoader().getResource("themeMusic.mp3");
    Media media = new Media(resource.toString());
    mediaPlayer = new MediaPlayer(media);
    mediaPlayer.setVolume(0.3);
    mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

    mediaPlayer.play();
  }

  /**
   * Allows user to play and pause music when toggle is clicked
   *
   * @param playMusicButton The Toggle Button for users to click
   */
  public void playMusicButtonAction(ToggleButton playMusicButton) {
    playMusicButton.setOnAction(
        event -> {
          if (playMusicButton.isSelected()) {
            mediaPlayer.pause();
          } else {
            mediaPlayer.play();
          }
        });
  }

  /**
   * user can play pronunciation of entry or lemma searched
   *
   * @param pronunciation URL of pronunciation to play
   */
  public void playPronunciation(String pronunciation) {
    Media media = new Media(pronunciation);
    MediaPlayer player = new MediaPlayer(media);
    player.play();
  }
}
