package io.github.team10.escapefromuni;

/**
 * NEW ENUM
 * Used for controlling the texture and effect of the PositiveEvent class.
 */
public enum PositiveEventType {

    // yeah i know some of the effects are pretty similar but at least they do something positive
    // IT IS NOT LAZY

    GREGGS("GreggsSausageRoll.png") {
        @Override
        void doThing(Player player, EscapeGame game, ScoreManager scoreManager) {
            // increases player speed by 2
            System.out.println("GREGGS EVENT");
            player.increaseSpeed(2f);
        }
    },
    MONSTER("Monster.png") {
        @Override
        void doThing(Player player, EscapeGame game, ScoreManager scoreManager) {
            // increases player speed by 50%
            System.out.println("MONSTER EVENT");
            player.setSpeed(player.getSpeed() * 1.5f);
        }
    },
    CUP_NOODLES("Cupnoodles.png") {
        @Override
        void doThing(Player player, EscapeGame game, ScoreManager scoreManager) {
            // gives the player +1000 score
            System.out.println("CUP_NOODLES EVENT");
            scoreManager.increaseScore(1000);
        }
    },
    NETWORKING("Networking.png") {
        @Override
        void doThing(Player player, EscapeGame game, ScoreManager scoreManager) {
            // increases player speed by 1 and +500 score
            System.out.println("NETWORKING EVENT");
            player.increaseSpeed(1f);
            scoreManager.increaseScore(500);
        }
    },
    PIZZA("Pizza.png") {
        @Override
        void doThing(Player player, EscapeGame game, ScoreManager scoreManager) {
            // gives the player +1000 score
            System.out.println("PIZZA EVENT");
            scoreManager.increaseScore(1000);
        }
    };

    private final String texture_path;

    PositiveEventType(String texture_path) {
        this.texture_path = texture_path;
    }

    /**
     * Abstract method to be overridden in each enum value.
     * @param player the current Player object.
     * @param game the current EscapeGame object.
     * @param scoreManager the current ScoreManager object.
     */
    abstract void doThing(Player player, EscapeGame game, ScoreManager scoreManager);

    // getters and setters
    public String getTexturePath() { return texture_path; }

}
