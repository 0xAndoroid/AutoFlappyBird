package ua.andoroidflappybird.neuralnetwork;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class NeuralNetwork {
    public float learningRate = 0.25f;

    public int layersAmount;
    public int[] nodesAmount;

    public Matrix[] weights;
    public Matrix[] biases;

    private File file;

    public NeuralNetwork(int layersAmount, int[] nodesAmount , File file) {
        this.layersAmount = layersAmount;
        this.nodesAmount = nodesAmount;
        this.file = file;

        weights = new Matrix[layersAmount-1];
        biases = new Matrix[layersAmount-1];

        for(int i=0;i<layersAmount-1;i++)
            weights[i] = new Matrix(this.nodesAmount[i+1],this.nodesAmount[i]);
        for(int i=0;i<layersAmount-1;i++)
            biases[i] = new Matrix(this.nodesAmount[i+1],1);
        load();
    }

    public float[] predict(float[] input_array) {
        Matrix inputs = Matrix.fromArray(input_array);
        for(int i=0;i<layersAmount-1;i++) {
            inputs = Matrix.multiply(this.weights[i],inputs);
            inputs.add(this.biases[i]);
            inputs.takeSigmoid();
        }
        return inputs.toArray();
    }

    public void train(float[] input_array, float[] target_array) {
        Matrix targets = Matrix.fromArray(target_array);
        Matrix[] outputs = new Matrix[layersAmount];
        outputs[0] = Matrix.fromArray(input_array);
        for(int i=0;i<layersAmount-1;i++) {
            outputs[i+1] = Matrix.multiply(this.weights[i],outputs[i]);
            outputs[i+1].add(this.biases[i]);
            outputs[i+1].takeSigmoid();
        }
        Matrix output_errors = Matrix.subtract(targets, outputs[layersAmount-1]);
        for(int i=layersAmount-2;i>=0;i--) {
            Matrix gradients = outputs[i+1].takeDSigmoid();
            gradients.multiply(output_errors);
            gradients.multiply(this.learningRate);
            Matrix transposed = Matrix.transpose(outputs[i]);
            Matrix weightDelta = Matrix.multiply(gradients,transposed);
            weights[i].add(weightDelta);
            biases[i].add(gradients);
            Matrix whoT = Matrix.transpose(this.weights[i]);
            output_errors = Matrix.multiply(whoT, output_errors);
        }
    }

    public void mutate(float val) {
            for (int i = 0; i < weights.length; i++)
                for (int j = 0; j < weights[i].rows; j++)
                    for (int k = 0; k < weights[i].cols; k++)
                        if(Math.random() < val)
                            weights[i].data[j][k] += Math.random() * 0.2f - 0.1f;
            for (int i = 0; i < biases.length; i++)
                for (int j = 0; j < biases[i].rows; j++)
                    for (int k = 0; k < biases[i].cols; k++)
                        if(Math.random() < val)
                            biases[i].data[j][k] += Math.random() * 0.2f - 0.1f;
    }

    public NeuralNetwork copy() {
        NeuralNetwork nn = new NeuralNetwork(layersAmount,nodesAmount,file);
        for(int i=0;i<nn.weights.length;i++)
            for(int j=0;j<nn.weights[i].rows;j++)
                for(int k=0;k<nn.weights[i].cols;k++)
                    nn.weights[i].data[j][k] = weights[i].data[j][k];
        for(int i=0;i<nn.biases.length;i++)
            for(int j=0;j<nn.biases[i].rows;j++)
                for(int k=0;k<nn.biases[i].cols;k++)
                    nn.biases[i].data[j][k] = biases[i].data[j][k];
        nn.learningRate = learningRate;
        return nn;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void save()  throws IOException{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(file);
        gson.toJson(this, writer);
        writer.flush();
        writer.close();
    }

    public void load() {
        Gson gson = new Gson();
        try{
            NeuralNetwork dd = gson.fromJson(new FileReader(file), this.getClass());
            this.weights = dd.weights;
            this.biases = dd.biases;
            this.learningRate = dd.learningRate;
            this.layersAmount = dd.layersAmount;
            this.nodesAmount = dd.nodesAmount;
        } catch (Exception ex) {
            for(int i=0;i<layersAmount-1;i++) {
                weights[i].randomize();
                biases[i].randomize();
            }
        }
    }

    public void setLearningRate(float value) {
        this.learningRate = value;
    }
}
