package com.epam.training.webshop;

import com.epam.training.webshop.interpreter.CommandLineInterpreterFactory;

public class Application {

    public static void main(String[] args) {
        CommandLineInterpreterFactory.create().handleUserInputs();
    }
}
