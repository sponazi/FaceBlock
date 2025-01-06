package io.github.sponazi.FaceBlock.Levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import io.github.sponazi.FaceBlock.platform.BaseBlock;

public class Level1 {
    private Sprite background;
    public Level1 (){
        walls = new ArrayList<>();
        background = new Sprite(new Texture("ty/white.png"));
    }

    public Sprite getBackground() {
        return background;
    }

    public void setBackground(Sprite background) {
        this.background = background;
    }

    private ArrayList <BaseBlock> walls;
    public  ArrayList <BaseBlock> getWalls(){
        return walls;
    };
    public void AddBlock (BaseBlock block){
        walls.add(block);
    }
    public void Render(SpriteBatch batch){
        background.draw(batch);
        for (BaseBlock i:walls){
            i.Render(batch);
        }

    };
}
