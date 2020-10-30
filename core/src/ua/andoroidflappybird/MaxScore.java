package ua.andoroidflappybird;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.andoroidflappybird.neuralnetwork.NeuralNetwork;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MaxScore {
    private int gen;
    private int score;
    private int highScore;

    public MaxScore() {
        score = 0;
        gen = 1;
        highScore = 0;
    }

    public MaxScore(int score, int gen, int highScore) {
        this.score = score;
        this.gen = gen;
        this.highScore = highScore;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore(int score) {
        if(score > this.score) this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void updateHighScore(int highScore) {
        if(highScore > this.highScore) this.highScore = highScore;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

    public int getGen() {
        return gen;
    }

    public void save(File file) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter(file);
            gson.toJson(this, writer);
            writer.flush();
            writer.close();
        }catch (IOException ignored) {}
    }
    public void load(File file) {
        Gson gson = new Gson();
        try{
            MaxScore dd = gson.fromJson(new FileReader(file), this.getClass());
            this.gen = dd.gen;
            this.score = dd.score;
            this.highScore = dd.highScore;
        } catch (Exception ex) {
            this.score = 0;
            this.highScore = 0;
        }
    }
}
