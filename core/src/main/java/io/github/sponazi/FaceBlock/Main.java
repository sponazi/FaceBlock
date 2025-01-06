package io.github.sponazi.FaceBlock;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Screen;

import java.util.ArrayList;

import io.github.sponazi.FaceBlock.Levels.LevelFirst;
import io.github.sponazi.FaceBlock.Levels.LevelSecond;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game implements InputProcessor,Screen {
    private SpriteBatch batch;
    Sprite ground;
    private float screenWidth;
    private float screenHeight;

    static ArrayList<Screen> levels;
    static int  currentLevel = 0;
    private static Game app;
    @Override
    public void create() {
        batch = new SpriteBatch();
        ground = new Sprite(new Texture("ty/black.png"));
        ground.setSize(Gdx.graphics.getWidth(), (float) Gdx.graphics.getWidth() /8);
        Gdx.input.setInputProcessor(this);
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        app = this;
        levels = new ArrayList<>();
        levels.add(new LevelFirst(this));
        levels.add(new LevelSecond(this));
        levels.add(new LevelFirst(this));
        levels.add(new LevelFirst(this));
        levels.add(new LevelFirst(this));

        setScreen(levels.get(0));

    }
    public static void nextScreen(){
        if(currentLevel + 1 < levels.size() ){
            Connector.Score +=100;
            app.setScreen(levels.get(currentLevel +=1));
        }
    };

    @Override
    public void render() {
        ScreenUtils.clear(86/255f, 116/255f, 186/255f, 255/255f);
        super.render();
//        player.update(Gdx.graphics.getDeltaTime());

//        batch.begin();
//        ParalaxBG.render(batch);
//        player.render(batch);
//        font.draw(batch, "SCORE:"+((int)Connector.Score/100), 50, 50);
//        RestartScreen.render(batch);
//        batch.end();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(86/255f, 116/255f, 186/255f, 255/255f);

//        player.update(delta);
//        batch.begin();
//        ParalaxBG.render(batch);
//        player.render(batch);
//        font.draw(batch, ""+((int)Connector.Score/100), 0, 0);
//        batch.end();

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
//        image.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Начало ввода (нажатие мыши или пальца)
//        player.startDrag(screenX, screenHeight - screenY); // Инвертируем Y для координат экрана
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // Конец ввода (отпускание мыши или пальца)
//        RestartScreen.onTouchUp(screenX, (int) (screenHeight - screenY));
//        player.endDrag(screenX, screenHeight - screenY); // Инвертируем Y для координат экрана
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        player.Dragged(screenX,screenY);
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
}
