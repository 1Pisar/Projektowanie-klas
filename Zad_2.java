interface Game {
    void start();
    void pause();
    void end();
}

class RPGGame implements Game {
    private String title;

    public RPGGame(String title) {
        this.title = title;
    }

    @Override
    public void start() {
        System.out.println("Rozpoczynanie przygody w RPG: " + title);
    }

    @Override
    public void pause() {
        System.out.println("Pauzowanie gry RPG: " + title);
    }

    @Override
    public void end() {
        System.out.println("Zakończono grę RPG: " + title);
    }
}

class ShooterGame implements Game {
    private String name;

    public ShooterGame(String name) {
        this.name = name;
    }

    @Override
    public void start() {
        System.out.println("Start gry FPS: " + name);
    }

    @Override
    public void pause() {
        System.out.println("Gra FPS " + name + " została zapauzowana.");
    }

    @Override
    public void end() {
        System.out.println("Gra FPS " + name + " została zakończona.");
    }
}

class PuzzleGame implements Game {
    private String level;

    public PuzzleGame(String level) {
        this.level = level;
    }

    @Override
    public void start() {
        System.out.println("Uruchamianie łamigłówki: poziom " + level);
    }

    @Override
    public void pause() {
        System.out.println("Pauzowanie łamigłówki: poziom " + level);
    }

    @Override
    public void end() {
        System.out.println("Zakończono łamigłówkę: poziom " + level);
    }
}

public class Zad_2 {
    public static void main(String[] args) {
        Game rpg = new RPGGame("Wiedźmin 3");
        Game shooter = new ShooterGame("Counter-Strike");
        Game puzzle = new PuzzleGame("Expert");

        rpg.start();
        rpg.pause();
        rpg.end();

        shooter.start();
        shooter.pause();
        shooter.end();

        puzzle.start();
        puzzle.pause();
        puzzle.end();
    }
}