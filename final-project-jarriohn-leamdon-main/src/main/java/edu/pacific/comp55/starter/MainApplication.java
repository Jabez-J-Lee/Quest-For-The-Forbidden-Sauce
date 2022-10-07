package edu.pacific.comp55.starter;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 637;
	public static final String MUSIC_FOLDER = "sounds";
	public static final String[] SOUND_FILES = { "Boss Fight.wav" };

	// Rooms 
	public Room start;
	private OptionPane options;
	private MenuPane menu;
	public BossRoom bossRoom;
	private TurnBasedBattle tBB;
	private sauceRoom sauceRoom;
	private winScreen winScreen;
	private gameOver gameOver;
	
	// Menu Buttons
	public GButton startButton;
	public GButton optionsButton;
	public GButton exitButton;
	
	// Global Game Variables
	public GParagraph rewardEffect;
	
	// Player Singleton
	private Player player;
 
	public void init() { 
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		playBGSound();
		player = new Player();
		start = new Room(this);
		options = new OptionPane(this);
		menu = new MenuPane(this);
		winScreen = new winScreen(this);
		gameOver = new gameOver(this);
		setupInteractions();
		switchToMenu();
	}
	
	public void reset() {
		start = null;
		options = null;
		menu = null;
		sauceRoom = null;
		winScreen = null;
		gameOver = null;
		bossRoom = null;
		tBB = null;
		
		Player.INSTANCE = new Player();
		playBGSound();
		
		start = new Room(this);
		options = new OptionPane(this);
		menu = new MenuPane(this);
		winScreen = new winScreen(this);
		gameOver = new gameOver(this);
		switchToMenu();
	}

	public void switchToMenu() {
		switchToScreen(menu);
	}
	
	public void continuetoStart() {
		switchToScreen(start);
	}

	public void switchToStart() {
		switchToScreen(start);
	}
	
	public void switchToOptions() {
		switchToScreen(options);
	}
	
	public void switchToBoss(Bullet bullet) {
		bossRoom = new BossRoom(this, bullet);
		switchToScreen(bossRoom);
	}
	public void continueToBossRoom() {
		switchToScreen(bossRoom);
	}
	
	public void switchToTBB(int enemyType, Bullet bullet, Boss boss) {
		tBB = new TurnBasedBattle(this, enemyType, bullet, boss);
		switchToScreen(tBB);
	}
	
	public void switchToSauceRoom() {
		sauceRoom = new sauceRoom(this);
		switchToScreen(sauceRoom);
	}
	
	public void switchToWinScreen() {
		switchToScreen(winScreen);
	}
	
	public void switchToGameOver() {
		switchToScreen(gameOver);
	}

	private void playBGSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[0], true);
	}
	
	public static void main(String[] args) {
		new MainApplication().start();
	}
}
