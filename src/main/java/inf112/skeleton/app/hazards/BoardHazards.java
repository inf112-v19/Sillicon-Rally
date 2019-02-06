package inf112.skeleton.app.hazards;

public class BoardHazards {
    //TODO fix logic once robot interaction is made
    public void Mountain(){
    }

    public void Pit(){
        // "upon moving onto pit tile"
        // add 2x spam dmg type cards
        // end turn
        // move robot to reboot token/tile
    }

    public void Laser(){
        // "at turn end"
        // add 1x spam dmg pr laser (1-3)
        // cannot pass through player/robots or walls
        // priority 3

        int direction;
        int amount;
    }

    public void Gears(){
        // "at turn end"
        // rotate robot 90d left or right
        // priority 2

        int rotation;
    }

    public void Conveyors(){
        // "at turn end"
        // move robot in direction
        // priority 1

        int direction;
    }

    public void rebootTile(){
        //position
        int x;
        int y;
    }

    public void checkpoints(){
        // "at turn end"
        // grant residing player a "flag"
        // when picked up all flags declare winner.
    }

}
