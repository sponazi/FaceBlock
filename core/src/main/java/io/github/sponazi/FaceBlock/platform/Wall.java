package io.github.sponazi.FaceBlock.platform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Wall extends BaseBlock {
    public Wall(Sprite sprite, Vector2 size, Vector2 position) {
        super(size, position,BlockType.wall);
        this.sprite = sprite;
        sprite.setSize(size.x,size.y);
        sprite.setPosition(position.x,position.y);
    }

    @Override
    public void Render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    private Sprite sprite;

    @Override
    public void Render(SpriteBatch batch, float delta) {
        sprite.draw(batch);
    }

}
