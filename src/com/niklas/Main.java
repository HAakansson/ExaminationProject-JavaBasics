package com.niklas;

public class Main {

    public static void main(String[] args) {

        SkurupAIFProgram program = new SkurupAIFProgram();

        View.getInstance().showMessage("\nHi! Welcome to the club of ancient lineage: FC Skurup. We are fortunate to have you with us today.\n" +
                "At this moment, we already have a few players and coaches under contract.\n" +
                "What would you like to do today?\n");

        program.start();



    }
}
