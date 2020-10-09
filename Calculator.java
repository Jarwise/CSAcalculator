import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Calculator {
    public static void main(String[] args){
        while(true){
            JTextField field1 = new JTextField();
            JTextField field2 = new JTextField();
            JTextField field3 = new JTextField();
            Object[] Fields = { // object to be used for a GUI with three fields
                "a: ", field1,
                "b: ", field2,
                "c: ", field3
            };

            Object[] options = {"Calculator", "Pascal triangle", "Temperature converter", "Quardatic equation", "Prime number factorization"}; // add another options here
            String input;
            int n = JOptionPane.showOptionDialog(null, "What would you like to calculate?", 
            "JarWise", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

                // switch for each option of button
            switch (n) { 
                case 0: 
                    input = JOptionPane.showInputDialog(null, "Equation: ", "Calculator.exe", JOptionPane.QUESTION_MESSAGE);
                    calculate(input);
                    break;
                case 1:
                    int x = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of layers ", "Pascal.exe", JOptionPane.QUESTION_MESSAGE));
                    pascal(x);
                    break;
                case 2: 
                    //JOptionPane.showMessageDialog(null, "A Cookie", "CookieGiver.exe", JOptionPane.INFORMATION_MESSAGE);
                    temperature();
                    break;
                case 3: 
                    JOptionPane.showConfirmDialog(null, Fields, "QuadraticEquationCalculator.exe", JOptionPane.DEFAULT_OPTION);
                    quadratic(Integer.parseInt(field1.getText()), Integer.parseInt(field2.getText()), Integer.parseInt(field3.getText()));
                    break;
                case 4:
                    int y = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert a number", "PrimeNumberFactorization.exe", JOptionPane.INFORMATION_MESSAGE));
                    factor(y);
                    break;
                default: break;
            }
        }
    }
    public static void calculate(String text){  // regular calculator for two integer numbers
        long x = 0, y = 0, v=1;
        char b = '+';
        for(int i=0; i<text.length(); i++){
            char c = text.charAt(i);
            if(c<='9' && c>='0'){
                x *= 10;
                x += (c-'0');
            }
            if(c=='+' || c=='-' || c=='*' || c=='/' || c=='^' || c=='%'){ b = c; y = x; x = 0;}
            else {if(c>'9' && c<'0'){System.out.println("Probably some error, anyway I have this: "+x);}}
        }
        switch(b){
            case '+': v = y + x; break;  // for now only for 5 operator - can be expanded
            case '-': v = y - x; break;
            case '*': v = y * x; break;
            case '/':
                try {v = y / x;} catch (ArithmeticException e) {System.out.println("An exception for division by zero occured " + e);} break;
                // catching an exception for division by zero
            case '%': v = y % x; break;
            case '^': for(int i=0; i<x; i++){ v *= y;} break;
        }
        System.out.println(y+""+b+""+x+" = "+v+"\n");
        JOptionPane.showMessageDialog(null, y+""+b+""+x+" = "+v, "Calculator.exe", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void pascal(int layers){  // pascal's trinagle with an option for choosing a number of layers
        ArrayList<Integer> list = new ArrayList<>();
        String out = "";
        list.add(1);
        System.out.println(list);
        for(int i=1; i<=layers; i++){
            int prev = 1;
            for(int j=1; j<list.size(); j++){
                list.set(j, (prev + list.get(j)));
                prev = list.get(j) - prev;
            }
            list.add(1);
            out += list + "\n";
        }
        JOptionPane.showMessageDialog(null, out, "Pascal.exe", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void temperature() {  // convertion between Celcius and Fahrengheit
        double fahrenheit, celsius;
        String tempInput, conversionOptions, output = " ";
        conversionOptions = JOptionPane.showInputDialog(null, "Type in 'C' for Celsius to Fahrenheit. Type in 'F' for Fahrenheit to Celsius.");
        if (conversionOptions.equals("C")) {
            tempInput = JOptionPane.showInputDialog(null, "Please enter a temperature in Celsius", "CelsiusToFahrenheit.exe", JOptionPane.PLAIN_MESSAGE);
            celsius = Double.parseDouble(tempInput);
            fahrenheit = celsius * 9 / 5 + 32;
            output = celsius + " degrees Celsius is " + Math.round(fahrenheit) + " degrees Fahrenheit.";
        } else if (conversionOptions.equals("F")) {
            tempInput = JOptionPane.showInputDialog(null, "Please enter a temperature in Fahrenheit", "FahrenheitToCelsius.exe", JOptionPane.PLAIN_MESSAGE);
            fahrenheit = Double.parseDouble(tempInput);
            celsius = (fahrenheit - 32) * 5 / 9;
            output = fahrenheit + " degrees Fahrenheit is " + Math.round(celsius) + " degrees Celsius.";
        } else {
            output = "Sorry, this is not a valid option :( ";
        }
        JOptionPane.showMessageDialog(null, output);
    }

    public static void quadratic(double a, double b, double c){  // quadratic function calculator in a form of ax^2+bx+c=0
        double result1 = ((-b) + Math.sqrt(b*b - 4*a*c)) / (2*a);
        double result2 = ((-b) - Math.sqrt(b*b - 4*a*c)) / (2*a);
        String response = " For "+a+"x^2 + "+b+"x + "+c+" = 0\n The roots for x are ";
        JOptionPane.showMessageDialog(null, response + result1+" and "+result2, "QuadraticEquationCalculatorBecauseWhyNot?ItsUsefulIsntIt?.exe", JOptionPane.PLAIN_MESSAGE);
    }

    public static void factor(int x){   // Prime number factorization of an integer
        double d = x;
        int storeNumber = x;
        d = java.lang.Math.sqrt(d);
        ArrayList<Integer> array = new ArrayList<Integer>(0);
        for(int i = 2; i < d; i++){
            if(x % i == 0){
                array.add(i);
                x /= i; i = 1;
            }
        }
        if(x != 1){array.add(x);}
        JOptionPane.showMessageDialog(null, "Prime number factorization of number "+ storeNumber +" is\n "+array, "PrimeNumberFactorization.exe", JOptionPane.DEFAULT_OPTION);
    }
}
