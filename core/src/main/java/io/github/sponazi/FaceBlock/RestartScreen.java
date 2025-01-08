package io.github.sponazi.FaceBlock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

import io.github.sponazi.FaceBlock.Levels.LevelFirst;

public class RestartScreen implements Screen, InputProcessor {
    private  Sprite background, restart, saveButton;
    private  float alphaBg = 0;
    private  Rectangle boundsRestart, boundsSave;
    private  String playerName = ""; // Для ввода имени игрока
    private  boolean showInputField = false; // Флаг для отображения ввода имени


    public  void handleInput() {
        if (showInputField) {
            // Запускаем диалог системной клавиатуры
            Gdx.input.getTextInput(new Input.TextInputListener() {
                @Override
                public void input(String text) {
                    playerName = text; // Сохраняем введённое имя
                    saveScore(playerName, Connector.Score ); // Сохраняем результат
                    showInputField = false; // Закрываем поле ввода
                }

                @Override
                public void canceled() {
                    showInputField = false; // Закрываем поле ввода, если пользователь отменил
                }
            }, "Enter your name", "", "Your name");

            // Отключаем поле, чтобы не запускать клавиатуру повторно
            showInputField = false;
        }
    }

    private  void createScoreFile() {
        FileHandle file = Gdx.files.local("scores.txt");
        if (!file.exists()) {
            file.writeString("", false); // Создаем файл, если он не существует
        }
    }

    private  void saveScore(String name, int score) {
        FileHandle file = Gdx.files.local("scores.txt");
        file.writeString(name + ":" + score + "\n", true); // Сохраняем результат
    }

    private  String[] getLeaderboard() {
        ArrayList<String> leaderboardList = new ArrayList<>();
        FileHandle file = Gdx.files.local("scores.txt");
        if (file.exists()) {
            String[] lines = file.readString().split("\n");
            for (String line : lines) {
                if (!line.isEmpty()) {
                    leaderboardList.add(line);
                }
            }
        }

        // Сортировка списка по очкам
        leaderboardList.sort((a, b) -> {
            int scoreA = Integer.parseInt(a.split(":")[1]);
            int scoreB = Integer.parseInt(b.split(":")[1]);
            return Integer.compare(scoreB, scoreA); // Сортируем по убыванию
        });

        // Возвращаем только первые 5 результатов
        while (leaderboardList.size() > 5) {
            leaderboardList.remove(leaderboardList.size() - 1);
        }

        // Заполняем оставшиеся слоты пустыми строками, если лидеров меньше 5
        while (leaderboardList.size() < 5) {
            leaderboardList.add(null);
        }

        return leaderboardList.toArray(new String[0]);
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // Преобразуем координаты экрана в координаты, используемые для проверки спрайтов
        float touchX = screenX;
        float touchY = Gdx.graphics.getHeight() - screenY; // Ось Y перевёрнута в LibGDX

        // Проверяем нажатие на кнопку рестарта
        if (restart.getBoundingRectangle().contains(touchX, touchY)) {
          Main.restart();
        }
        // Проверяем нажатие на кнопку сохранения
        else if (saveButton.getBoundingRectangle().contains(touchX, touchY)) {
            showInputField = true;
        }

        return false;
    }
    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
    SpriteBatch batch;
    @Override
    public void show() {
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
        background = new Sprite(new Texture("ty/black.png"));
        restart = new Sprite(new Texture("ty/restart.png"));
        saveButton = new Sprite(new Texture("ty/save.png")); // Кнопка "Сохранить"

        background.setAlpha(0);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        restart.setSize(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 4, (Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 4) / 4);
        saveButton.setSize(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 4, (Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 4) / 4);

        background.setPosition(-Gdx.graphics.getWidth(), 0);
        restart.setPosition(-Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 6);
        saveButton.setPosition(-Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 3);

        boundsRestart = new Rectangle(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 4, (Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 4) / 4, -Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 3);
        boundsSave = new Rectangle(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 4, (Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 4) / 4, -Gdx.graphics.getWidth(), Gdx.graphics.getHeight() / 6);

        // Создаем файл для хранения результатов
        createScoreFile();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(86 / 255f, 116 / 255f, 186 / 255f, 255 / 255f);

        batch.begin();
        if (true) {
            // Устанавливаем положение и размеры спрайтов
            background.setPosition(0, 0);
            restart.setPosition(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 6);
            saveButton.setPosition(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 3);

            alphaBg += Gdx.graphics.getDeltaTime() * 10;
            if (alphaBg > 1f) {
                alphaBg = 1f;
            }
            background.draw(batch);
            background.setAlpha(alphaBg);
            restart.draw(batch);
            saveButton.draw(batch);

            // Отображаем текст
            LevelFirst.font3.draw(batch, "You died", Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() * 5f / 6f);
            LevelFirst.font2.draw(batch, "Your score: " + Connector.Score, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() * 5f / 6f - 100);

            // Если флаг ввода имени активен, отображаем поле ввода
            if (showInputField) {
                LevelFirst.font2.draw(batch, "Enter your name: " + playerName, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 3);
                handleInput();
            }

            // Отображение списка лидеров
            LevelFirst.font2.draw(batch, "Leaderboard:", Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() * 5f / 6f - 200);
            String[] leaderboard = getLeaderboard();
            if (leaderboard.length == 0) {
                LevelFirst.font2.draw(batch, "No leaders yet.", Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() * 5f / 6f - 200 - 30);
            } else {
                for (int i = 0; i < leaderboard.length; i++) {
                    if (leaderboard[i] != null) {
                        LevelFirst.font2.draw(batch, leaderboard[i], Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() * 5f / 6f - 200 - (i + 1) * 50);
                    }
                }
            }

            // Обновляем прямоугольники для кнопок
            boundsRestart.set(restart.getX(), restart.getY(), restart.getWidth(), restart.getHeight());
            boundsSave.set(saveButton.getX(), saveButton.getY(), saveButton.getWidth(), saveButton.getHeight());
        }
        batch.end();
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

    }

    @Override
    public void dispose() {

    }
}
