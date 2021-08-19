package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InputController {

    private final BufferedReader bReader;

    public InputController() {
        bReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public List<String> getUserInput() {
        try {
            String input = bReader.readLine();
            if (input.contains("find"))
                return Arrays.stream(input.split(" ")).toList();
            return Arrays.stream(input.split("(?!^)")).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
}
