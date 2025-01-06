package io.github.sponazi.FaceBlock.Levels;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

import io.github.sponazi.FaceBlock.Connector;
import io.github.sponazi.FaceBlock.ParalaxBG;
import io.github.sponazi.FaceBlock.Player;
import io.github.sponazi.FaceBlock.RestartScreen;
import io.github.sponazi.FaceBlock.platform.FinishBlock;
import io.github.sponazi.FaceBlock.platform.Wall;

public class LevelSecond implements Screen, InputProcessor {
    private final Game game; // Reference to the main game class
    private Player player;
    private float screenWidth;
    private float screenHeight;
    private SpriteBatch batch2;
    private Level1 level1;
    public static BitmapFont font,font2,font3;



    public LevelSecond(Game game) {
        this.game = game; // Pass the game instance



        RestartScreen.create();
        ParalaxBG.create();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("joystix monospace.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        font = generator.generateFont(parameter);
        parameter.size = 48;
        font2 = generator.generateFont(parameter);
        parameter.size = 64;
        font3 = generator.generateFont(parameter);
        generator.dispose();

    }

    @Override
    public void show() {
        Texture block = new Texture("ty/Block.png");
        block.setWrap(Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);
        TextureRegion reg1 = new TextureRegion(block);

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        player = new Player(screenWidth / 2, 0);
        batch2 = new SpriteBatch();
        level1 = new Level1();
//        level1.AddBlock(new Wall(new Sprite(new Texture("ty/black.png")),new Vector2(100,500),new Vector2(200,-100)));
////        level1.AddBlock(new Wall(new Sprite(new Texture("ty/black.png")),new Vector2(100,500),new Vector2(900,-100)));
//        level1.AddBlock(new Wall(new Sprite(new Texture("ty/black.png")),new Vector2(800,100),new Vector2(200,300)));
        reg1.setRegion(0,0,1000,100);
        level1.AddBlock(new Wall(new Sprite(reg1),new Vector2(1000,100),new Vector2(0,-100)));
        reg1.setRegion(0,0,700,100);
        level1.AddBlock(new Wall(new Sprite(reg1),new Vector2(700,100),new Vector2(0,600)));
        reg1.setRegion(0,0,100,2400);
        level1.AddBlock(new Wall(new Sprite(reg1),new Vector2(100,2400),new Vector2(0,0)));
        reg1.setRegion(0,0,100,2400);
        level1.AddBlock(new Wall(new Sprite(reg1),new Vector2(100,2400),new Vector2(1000,0)));
        reg1.setRegion(0,0,700,100);
        level1.AddBlock(new Wall(new Sprite(reg1),new Vector2(700,100),new Vector2(400,1000)));
        reg1.setRegion(0,0,100,400);
        level1.AddBlock(new Wall(new Sprite(reg1),new Vector2(100,400),new Vector2(400,1000)));
        reg1.setRegion(0,0,600,100);
        level1.AddBlock(new Wall(new Sprite(reg1),new Vector2(600,100),new Vector2(0,1800)));
        reg1.setRegion(0,0,1000,100);
        level1.AddBlock(new Wall(new Sprite(reg1),new Vector2(650,100),new Vector2(450,2300)));
        level1.AddBlock(new FinishBlock(new Sprite(new Texture("ty/black.png")),new Vector2(350,100),new Vector2(100,2300)));

        Sprite back = new Sprite(new Texture("ty/back.png"));
        back.setSize(Gdx.graphics.getHeight(), Gdx.graphics.getHeight());
        back.setPosition(-(Gdx.graphics.getHeight()-Gdx.graphics.getWidth())/2,0);
        level1.setBackground(back);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        // Clear the screen (optional)
        Gdx.gl.glClearColor(1, 1, 1, 1); // Set background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen

        // Begin batch
        batch2.begin();
        level1.Render(batch2);
        player.render(batch2);
        player.update(delta, level1.getWalls());


        font.setColor(0, 1, 1, 1); // RGBA: cian

        font.draw(batch2, "SCORE:"+((int) Connector.Score), 50, 50);
        RestartScreen.render(batch2);
        batch2.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        // Dispose resources if needed
        batch2.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        // Handle key down events
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // Handle key up events
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // Handle key typed events
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Начало ввода (нажатие мыши или пальца)
        player.startDrag(screenX, screenHeight - screenY); // Инвертируем Y для координат экрана
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // Конец ввода (отпускание мыши или пальца)
//        RestartScreen.onTouchUp(screenX, (int) (screenHeight - screenY));
        player.endDrag(screenX, screenHeight - screenY); // Инвертируем Y для координат экрана
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        player.Dragged(screenX,screenY);
        return false;
    }



    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void dispose() {
        // Clean up resources when the screen is no longer needed
        batch2.dispose();
//        level1.dispose(); // Assuming Level1 has a dispose method
    }
}
