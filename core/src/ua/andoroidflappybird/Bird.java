package ua.andoroidflappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ua.andoroidflappybird.neuralnetwork.NeuralNetwork;

import java.io.File;

public class Bird {
    private float y;
    private float velocity;
    private int score;
    private NeuralNetwork brain;
    private float size;

    private boolean died = false;

    public Bird() {
        y=1080/2f;
        velocity = 0;
        score = 0;
        size = 128;
        brain = new NeuralNetwork(3,new int[]{5,4,2} ,new File("bird.json"));
    }
    public Bird(File file) {
        y=1080/2f;
        velocity = 0;
        score = 0;
        size = 80;
        brain = new NeuralNetwork(3,new int[]{5,4,2} ,file);
    }

    public Bird(NeuralNetwork brain) {
        y=1080/2f;
        velocity = 0;
        score = 0;
        size = 80;
        this.brain = brain;
    }

    public void render(SpriteBatch batch, Texture tx) {
        if(!died)
            batch.draw(tx,1920/6f - size/2f,y - size/2f,size,size);
    }

    public void render(ShapeRenderer sr) {
        if(!died)
            sr.rect(1920/6f - size/2f,y - size/2f,size,size);
    }

    public void think(float pipe1, float pipe2, float pipeX, float pipeWidth) {
        float[] inputs = new float[] {y,pipe1,pipe2,pipeX, pipeWidth};
        float[] outputs = brain.predict(inputs);
        if(outputs[0] > outputs[1]) {
        //    if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocity = 600;
        }
    }

    public void update(float dt, float pipe1, float pipe2, float pipeX , float pipeWidth) {
        if(!died) {
            velocity -= 98.1*7.5 * dt;
            y += velocity * dt;
            score++;
            think(pipe1,pipe2,pipeX, pipeWidth);
        }

    }

    public void mutate(float val) {
        brain.mutate(val);
    }

    public int getScore() {
        return score;
    }

    public void setDied(boolean died) {
        this.died = died;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public NeuralNetwork getBrain() {
        return brain;
    }

    public boolean isDied() {
        return died;
    }
}
