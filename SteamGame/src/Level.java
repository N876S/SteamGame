import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Level {

	Board b = new Board(10, 5);

	Random r = new Random();

	ArrayList<Food> activeFoods = new ArrayList<Food>();
	ArrayList<Creature> activeCreatures = new ArrayList<Creature>();

	private int creatureTimer = 0;
	private int creatureInterval = 60;
	private int spawnTimeDecrease = 0;
	private int spawnTimeInterval = 1;

	private int score = 0;
	private int highScore;

	public Level() {
		loadGame();
	}

	public void drawLevel(Graphics g) {
		try {
			b.drawBoard(g);
		} catch (Exception e) {
			System.out.println("Failed to draw board");
		}
		try {
			b.drawBoard(g);
		} catch (Exception e) {
			System.out.println("Failed to draw board");
		}
		try {
			for (int i = 0; i < activeFoods.size(); i++) {
				activeFoods.get(i).drawFood(g);
				activeFoods.get(i).drawHealthBar(g);
			}
		} catch (Exception e) {
			System.out.println("Failed to draw foods");
			System.out.println(e);
		}
		try {
			for (int i = 0; i < activeCreatures.size(); i++) {
				activeCreatures.get(i).drawCreature(g);
				activeCreatures.get(i).drawHealthBar(g);
			}
		} catch (Exception e) {
			System.out.println("Failed to draw creatures");
			System.out.println(e);
		}
		g.setColor(Color.BLACK);
		g.drawString("Score: " + Integer.toString(score), 25, 50);
		g.drawString("Highscore: " + Integer.toString(highScore), 25, 70);
	}

	public void war() {
		activeFoods.add(new Food((short) 0, b.startGridX + (r.nextInt(0, b.tileWidthCount / 2) * Board.tileSize),
				b.startGridY + (r.nextInt(0, b.tileHeightCount) * Board.tileSize)));
		activeCreatures.add(new Creature((short) 0,
				b.startGridX + (r.nextInt(b.tileWidthCount / 2, b.tileWidthCount) * Board.tileSize),
				b.startGridY + (r.nextInt(0, b.tileHeightCount) * Board.tileSize)));
	}

	public void updateLevel() {

		// ADD CREATURES

		creatureTimer++;
		if (creatureTimer >= creatureInterval) {
			activeCreatures.add(new Creature((short) 0,
					b.startGridX + (r.nextInt(b.tileWidthCount / 2, b.tileWidthCount) * Board.tileSize),
					b.startGridY + (r.nextInt(0, b.tileHeightCount) * Board.tileSize)));
			creatureTimer = 0;
			spawnTimeDecrease++;
			if (spawnTimeDecrease >= spawnTimeInterval) {
				creatureInterval--;
				spawnTimeDecrease = 0;
			}
		}

		// UPDATE FOOD

		for (int i = activeFoods.size() - 1; i >= 0; i--) {
			// if any of the foods are not at the end of the board
			if (activeFoods.get(i).x < b.startGridX + (b.tileWidthCount * Board.tileSize)) {
				// checking for collision
				activeFoods.get(i).attacking = false;
				for (int j = 0; j < activeCreatures.size(); j++) {
					// if the foods collision box is inside of a creatures
					if (activeFoods.get(i).x <= activeCreatures.get(j).x
							&& activeFoods.get(i).x + 80 >= activeCreatures.get(j).x
							&& activeCreatures.get(j).y == activeFoods.get(i).y) {
						// fight the creature
						activeFoods.get(i).attacking = true;
						// start counting down the attack intervals
						activeFoods.get(i).attlen--;
						// if attack interval is over
						if (activeFoods.get(i).attlen <= 0) {
							// deal damage to enemy
							activeCreatures.get(j).hp -= activeFoods.get(i).dmg;
							activeCreatures.get(j).updateHealthBar();

							// reset attack length
							activeFoods.get(i).attlen = FoodInfo.defaultStats[3][activeFoods.get(i).indexType];
						}
					}
				}
				// if food is not attacking
				if (activeFoods.get(i).attacking == false) {
					// move them forward
					activeFoods.get(i).x += activeFoods.get(i).movespd;
				}
			} else {
				// otherwise break the tower
				activeFoods.remove(i);
				score += 100;
				if (score > highScore) {
					highScore = score;
				}
			}
		}

		// UPDATE CREATURES

		for (int i = activeCreatures.size() - 1; i >= 0; i--) {
			// if any of the creatures are not at the end of the board
			if (activeCreatures.get(i).x > b.startGridX) {
				// checking for collision
				activeCreatures.get(i).attacking = false;
				for (int j = 0; j < activeFoods.size(); j++) {
					// if the creatures collision box is inside of a foods
					if (activeFoods.get(j).x + 80 >= activeCreatures.get(i).x
							&& activeFoods.get(j).x <= activeCreatures.get(i).x + 80
							&& activeCreatures.get(i).y == activeFoods.get(j).y) {
						// fight the food
						activeCreatures.get(i).attacking = true;
						// start counting down the attack intervals
						activeCreatures.get(i).attlen--;
						// if attack interval is over
						if (activeCreatures.get(i).attlen <= 0) {
							// deal damage to enemy
							activeFoods.get(j).hp -= activeCreatures.get(i).dmg;
							activeFoods.get(j).updateHealthBar();

							// reset attack length
							activeCreatures.get(i).attlen = FoodInfo.defaultStats[3][activeCreatures.get(i).indexType];
						}
					}
				}
				// if creature is not attacking
				if (activeCreatures.get(i).attacking == false) {
					// move them forward
					activeCreatures.get(i).x -= activeCreatures.get(i).movespd;
				}
			} else {
				// otherwise break the tower
				activeCreatures.remove(i);
				score /= 2;
				score -= 1;
			}
		}

		// DEATH SYSTEM

		for (int i = activeFoods.size() - 1; i >= 0; i--) {
			if (activeFoods.get(i).hp <= 0) {
				activeFoods.remove(i);
			}
		}
		for (int i = activeCreatures.size() - 1; i >= 0; i--) {
			if (activeCreatures.get(i).hp <= 0) {
				activeCreatures.remove(i);
			}
		}

		// GAME OVER SYSTEM

		if (score < 0) {
			activeCreatures.clear();
			activeFoods.clear();
			
			saveGame();
			
			score = 0;
			creatureInterval = 60;
		}
	}
	
	public void loadGame() {
		//load game
		
		try {

			FileReader reader = new FileReader("data/highscore.txt");
			StringBuilder code = new StringBuilder();
			StringBuilder key = new StringBuilder();
			
			int c;
			boolean keySwitch = false;

			try {
				while ((c = reader.read()) != -1) {
					if(keySwitch == false) {
						code.append((char) c);
						if((char) c == '\n'){
							keySwitch = true;
						}
					} else if (keySwitch == true) {
						key.append((char) c);
					}
				}
			} catch (IOException e) {
				System.out.println("Failed to input highscore!");
				e.printStackTrace();
			}
			
			Decrypter dcr = new Decrypter();

			if (code.toString() != "") {
				String decrypted = dcr.decrypt(code.toString(), Integer.parseInt(key.toString()));
				try {
					highScore = Integer.parseInt(decrypted);
				} catch (Exception e) {
					System.out.println("Invalid Save Data!");
				}
			}

			try {
				reader.close();
			} catch (IOException e) {
				System.out.println("Failed to close highscore data!");
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			System.out.println("Failed to find highscore!");
			e.printStackTrace();
		}
	}

	public void saveGame() {
		// save highscore

		try {
			PrintWriter pw = new PrintWriter("data/highscore.txt");
			Encrypter ecr = new Encrypter();
			
			Random r = new Random();
	
			int key = r.nextInt(99999);
			
			pw.print(ecr.encrypt(Integer.toString(highScore), key));
			pw.println();
			pw.print(key);
			

			pw.close();

		} catch (Exception e) {
			System.out.println("Failed to save score");
		}
	}
}
