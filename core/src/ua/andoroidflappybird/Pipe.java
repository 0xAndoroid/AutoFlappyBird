package ua.andoroidflappybird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Pipe {
    private float pipe1;
    private float pipe2;
    private float pipeX;
    private float velocity;
    private float width;
    private float height;

    public boolean isOff = false;

    public Pipe() {
        height = 400;
        Random random = new Random();
        pipeX = 1920;
        pipe1 = random.nextInt(1080-(int)height+1)+height;
        pipe2 = pipe1 - height;
        velocity = 200;
        width = 200;
    }

    public void render(ShapeRenderer sr) {
        sr.rect(pipeX,pipe1,width,1080);
        sr.rect(pipeX,pipe2 - 1080,width,1080);
    }

    public void update(float dt) {
        pipeX -= velocity*dt;
        if(pipeX + width < 0) isOff = true;
    }

    public float getPipeX() {
        return pipeX;
    }

    public float getPipe1() {
        return pipe1;
    }

    public float getPipe2() {
        return pipe2;
    }

    public float getWidth() {
        return width;
    }
}
