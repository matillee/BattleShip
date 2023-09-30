package highscore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import main.Player;
import board.GameBoard;


/**
 * To save and print HighScore of the game
 */
public class HighScore {

	private static final String highScoreFile = "HighScoreTextfile.txt"; // TODO Fix so that file is in highscore folder

	private ArrayList<Score> highScores;
	private ArrayList<String> highScoreNames;

	public HighScore() {
		highScores = new ArrayList<Score>();
		highScoreNames = new ArrayList<String>();
	}

	/**
	 * Method is used after game is over to see which player had the highest
	 * damage/hit result
	 * 
	 * @param players
	 */
	public void gameResult(ArrayList<Player> players) {

		float highestDamageResult = 0;
		String highestDamagePerson = "";
		float bestHitResult = 0;
		String bestHitPerson = "";

		for (int n = 0; n < players.size(); n++) {

			Player currentPlayer = players.get(n);
			GameBoard currentBoard = currentPlayer.getPlayerBoard();

			if (currentBoard.getDamagePercent() > highestDamageResult) {
				highestDamageResult = currentBoard.getDamagePercent();
				highestDamagePerson = currentPlayer.getPlayerName();
			}
			if (currentPlayer.getHitPercent() > bestHitResult) {
				bestHitResult = currentPlayer.getHitPercent();
				bestHitPerson = currentPlayer.getPlayerName();
			}
		}

		System.out.println(highestDamagePerson + " had the highest damage percent with " + highestDamageResult + "%");
		System.out.println(bestHitPerson + " had the best hit percent with " + bestHitResult + "%");

	}


	/**
	 * Method is used to save least amount of shots fired used to win in a list to
	 * compare to other players result
	 * 
	 * @param winner
	 */
	public void setHighScore(Player winner) {
		getScore();

		String winnerName = winner.getPlayerName();
		int winnerScore = winner.getShotsFired();

		System.out.println(winnerName + " won with " + winnerScore + " shots fired.");

		if (highScoreNames.contains(winnerName)) {
			for (Score sc : highScores) {
				if (sc.getName().equals(winnerName)) {
					if (sc.getScore() > winnerScore) {
						sc.setScore(winnerScore);
					}
				}
			}

		} else {
			Score score = new Score(winnerName, winnerScore);
			highScores.add(score);
		}

		Collections.sort(highScores, new Comparator<Score>() {
			@Override
			public int compare(Score h1, Score h2) {
				return h1.getScore() - h2.getScore();
			}
		});

		writeHighScore();
	}

	private void getScore() {

		highScores.clear();

		final Path path = FileSystems.getDefault().getPath(highScoreFile);
		try (final BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line = null;

			while ((line = br.readLine()) != null) {

				String[] output = line.split(",");
				Score score = new Score(output[0], Integer.parseInt(output[1]));

				highScores.add(score);
				highScoreNames.add(output[0]);

			}
		} catch (FileNotFoundException FNFe) {
			FNFe.printStackTrace();
		} catch (IOException IOe) {
			IOe.printStackTrace();
		} catch (NumberFormatException NFe) {
			NFe.printStackTrace();
		}

	}

	/**
	 * Writes score from highScores to file for saving
	 */
	private void writeHighScore() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(highScoreFile));

			for (Score hs : highScores) {

				writer.write(hs.getName() + "," + Integer.toString(hs.getScore()));
				writer.newLine();

			}

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Prints score from to file
	 */
	public void printHighScore() {
		getScore();
		
		int counter = 0;
		for (Score sc : highScores) {
			counter++;
			System.out.println(counter + ". " + sc.getName() + ": " + sc.getScore());
		}
	}

}
