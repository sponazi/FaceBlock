package io.github.sponazi.FaceBlock.platform;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseBlock {
    private Rectangle box;
    private BlockType blockType;
    public static enum BlockType{
        wall,finish
    }

    public BlockType getBlockType() {
        return blockType;

    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;

    }

    public abstract void Render(SpriteBatch batch);
    public abstract void Render(SpriteBatch batch,float delta);
    public void SetPosition(Vector2 position){
        box.setPosition(position);
    };
    public void SetSize(Vector2 size){
        box.setSize(size.x,size.y);
    };
    public Vector2 GetPosition(){
        return box.getPosition(new Vector2());
    };
    public Vector2 GetSize(){
        return box.getSize(new Vector2());
    };
    public  boolean collision(Rectangle react){
        return box.overlaps(react);
    };
    public BaseBlock(Vector2 size,Vector2 position,BlockType type){
        box = new Rectangle(position.x, position.y, size.x, size.y);
        blockType = type;
    };
}
