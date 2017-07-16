package game.players;

import game.FrameCounter;
import game.GameWindow;
import game.Utils;
import game.bases.Contraints;
import game.bases.ImageRenderer;
import game.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by cuonghx2709 on 7/11/2017.
 */
public class Player {
    //properties:thuoc tinh
    public Vector2D position;
    public ImageRenderer imageRenderer;
    Contraints contraints ;
    FrameCounter cooldownCounter;
    boolean spellDisable;

    public void castSpell(ArrayList<PlayerSpell> playerSpells){
        //cast spell
        if(!spellDisable){
            PlayerSpell playerSpell = new PlayerSpell();
            playerSpell.position.set(this.position.add(0,-20));
            playerSpells.add(playerSpell);
            spellDisable = true;
        }
    }

    public Player(){
        this.position = new Vector2D();
        this.cooldownCounter = new FrameCounter(17); // 17 fram = 300 milisecon to cool down
        this.imageRenderer  = new ImageRenderer(Utils.Loadimage("assets/images/players/straight/0.png"));
    }


//methods:phuong thuc
    public void move(float dx, float dy){
        this.position.addUp(dx, dy);
        contraints.make(this.position);
    }
    // setter
    public  void setContraints(Contraints contraints){
        this.contraints = contraints;
    }

    public void  render(Graphics2D g2d){
        imageRenderer.render(g2d, this.position);
    }
    public void cooldown(){
        if(spellDisable){
                boolean status = cooldownCounter.run();
                if(status ){
                    spellDisable = false;
                    cooldownCounter.reset();
                }
        }
    }
}
