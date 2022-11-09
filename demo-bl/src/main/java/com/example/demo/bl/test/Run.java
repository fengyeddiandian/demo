package com.example.demo.bl.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Run {

    private static int radix = 4;

    public static void main(String[] args) throws Exception {
        Run run = new Run();
        int[] result = run.Power(radix);

        for (int i = result.length - 1; i >= 0; i--) {
            System.out.print(result[i]);
            if (i % 50 == 0) {
                System.out.print(" ");
            }
        }
    }

    public static int input() {
        String inline = null;
        try {
            BufferedReader inStream = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please enter a power number for the radix 2: ");
            inline = inStream.readLine();
            int number = Integer.parseInt(inline);
            return number;
        } catch (NumberFormatException ex) {
            System.out.println("Invalid!!");
            return 0;
        } catch (IOException e) {
            System.out.println("Error!! ");
            return 0;
        }
    }

    int[] Power(int radix) throws Exception {
        int[] result;
        int n = input();
        double v = Math.log10(radix);
        int size = (int) (n * Math.log10(radix)) + 1;
        result = new int[size];
        result[0] = 1;
        if (n < 0) {
            System.out.println("Sorry! Please enter a positive number!");
        }
        if (n == 0) {
            result = new int[1];
            result[0] = 1;
            return result;
        }
        // get the result
        for (int i = 0; i < n; i++) {
            GetResult(result, radix);
        }
        return result;
    }

    void GetResult(int[] result, int n) {
        int length = 0;
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] > 0) {
                length = i + 1; // get the highest bit
                break;
            }
        }
        for (int i = length - 1; i >= 0; i--) {
            // calculate from the highest bit to the lowest
            int temp = result[i] * n;
            // get a carry
            if (temp >= 10) {
                // put a carry to a higher bit
                result[i + 1] += temp / 10;
                // check this bit weather need carry or not
                CheckCarry(result, i + 1);
                temp %= 10;
            }
            result[i] = temp;
        }
    }

    void CheckCarry(int[] m, int index) {
        if (m[index] >= 10) {
            // put a carry to a higher bit
            m[index + 1] += m[index] / 10;
            // set the current bit
            m[index] %= 10;
            // check the next bit weather need carry or not
            CheckCarry(m, index + 1);
        }
    }
}
