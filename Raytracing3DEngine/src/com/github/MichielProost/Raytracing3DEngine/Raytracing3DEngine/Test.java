package com.github.MichielProost.Raytracing3DEngine.Raytracing3DEngine;

import javax.swing.*;

class Test{

    // Test the visualization of a point.
    Test drawPoint(){

        System.out.println("TEST: DRAW POINT\n");

        //Create a window.
        JFrame window = new JFrame();

        //Settings.
        window.setTitle("Point Example");
        window.setSize(200, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        window.setVisible(true);

        DrawingComponent component = new DrawingComponent(50, 50);
        window.add(component);

        System.out.println("\n");

        return this;

    }

    // Test the multiplication of matrices.
    Test multiplyMatrices(){

        System.out.println("TEST: MULTIPLY MATRICES\n");

        // Create and initialize matrix A.
        Matrix matrixA = new Matrix()
                .setValue(0, 0, 5).setValue(0, 1, 7).setValue(0, 2, 9).setValue(0, 3, 10)
                .setValue(1, 0, 2).setValue(1, 1, 3).setValue(1, 2, 3).setValue(1, 3, 8)
                .setValue(2, 0, 8).setValue(2, 1, 10).setValue(2, 2, 2).setValue(2, 3, 3)
                .setValue(3, 0, 3).setValue(3, 1, 3).setValue(3, 2, 4).setValue(3, 3, 8);

        // Print matrix A.
        matrixA.print();

        // Create and initialize matrix B.
        Matrix matrixB = new Matrix()
                .setValue(0, 0, 3).setValue(0, 1, 10).setValue(0, 2, 12).setValue(0, 3, 18)
                .setValue(1, 0, 12).setValue(1, 1, 1).setValue(1, 2, 4).setValue(1, 3, 9)
                .setValue(2, 0, 9).setValue(2, 1, 10).setValue(2, 2, 12).setValue(2, 3, 2)
                .setValue(3, 0, 3).setValue(3, 1, 12).setValue(3, 2, 4).setValue(3, 3, 10);

        // Print matrix B.
        matrixB.print();

        // Multiply matrix A with matrix B.
        Matrix matrixC = matrixA.multiply(matrixB);

        // Print matrix C.
        matrixC.print();

        System.out.println("\n");

        return this;

    }

    Test addMatrices(){

        System.out.println("TEST: ADD MATRICES\n");

        // Create and initialize matrix A.
        Matrix matrixA = new Matrix()
                .setValue(0, 0, 5).setValue(0, 1, 7).setValue(0, 2, 9).setValue(0, 3, 10)
                .setValue(1, 0, 2).setValue(1, 1, 3).setValue(1, 2, 3).setValue(1, 3, 8)
                .setValue(2, 0, 8).setValue(2, 1, 10).setValue(2, 2, 2).setValue(2, 3, 3)
                .setValue(3, 0, 3).setValue(3, 1, 3).setValue(3, 2, 4).setValue(3, 3, 8);

        // Print matrix A.
        matrixA.print();

        // Create and initialize matrix B.
        Matrix matrixB = new Matrix()
                .setValue(0, 0, 1).setValue(0, 1, 1).setValue(0, 2, 1).setValue(0, 3, 1)
                .setValue(1, 0, 1).setValue(1, 1, 1).setValue(1, 2, 1).setValue(1, 3, 1)
                .setValue(2, 0, 1).setValue(2, 1, 1).setValue(2, 2, 1).setValue(2, 3, 1)
                .setValue(3, 0, 1).setValue(3, 1, 1).setValue(3, 2, 1).setValue(3, 3, 1);

        // Print matrix B.
        matrixB.print();

        // Add matrix A with matrix B.
        Matrix matrixC = matrixA.add(matrixB);

        // Print matrix C.
        matrixC.print();

        System.out.println("\n");

        return this;

    }
}
