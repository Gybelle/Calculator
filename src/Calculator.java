import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Michelle on 19/04/2017.
 */

public class Calculator {
    double m_result;
    Stack<Double> m_numberStack;
    Stack<Operation> m_operationStack;
    HashMap<String, Operation> m_operationMap;


    public Calculator(){
        m_numberStack = new Stack<Double>();
        m_operationStack = new Stack<Operation>();
        createOperationMapping();
    }

    private void createOperationMapping(){
        m_operationMap = new HashMap<String, Operation>();

        OperationAdd addOperation = new OperationAdd();
        m_operationMap.put("+", addOperation);
        OperationSubstract subtractOperation = new OperationSubstract();
        m_operationMap.put("-", subtractOperation);

       // m_operationMap.put("*", multiplyOperation);


    }

     public double parseOperationString(String operation){
        m_numberStack.clear();
        m_operationStack.clear();
        String numberBuilder = "";

        for(char ch: operation.toCharArray()){
            if(Character.isDigit(ch)){
               numberBuilder += ch;
            } else {
                if (!numberBuilder.equals("")){
                    double number = Integer.parseInt(numberBuilder);
                    m_numberStack.push(number);
                    numberBuilder = ""; //reset number
                } else {
                    boolean precedenceOk = true;

                    Operation op = m_operationMap.get(Character.toString(ch));

                    while(m_operationStack.peek().getPrecedence() <= op.getPrecedence()){
                        double b = m_numberStack.pop();
                        double a = m_numberStack.pop();
                        Operation poppedOperation = m_operationStack.pop();

                        double result =  poppedOperation.apply(a, b);
                        m_numberStack.push(result);
                    }

                    m_operationStack.push(op);

                }
            }
        } //end for
        double lastNumber = Integer.parseInt(numberBuilder);
        m_numberStack.push(lastNumber);

        while(!m_operationStack.empty()){
            double b = m_numberStack.pop();
            double a = m_numberStack.pop();
            Operation poppedOperation = m_operationStack.pop();

            double result =  poppedOperation.apply(a, b);
            m_numberStack.push(result);
        }

        m_result = m_numberStack.pop();
         return m_result;

    }



    public static void main(String[] args){
        Calculator test = new Calculator();
        double result = test.parseOperationString("1+2");
        System.out.println(result);
    }


}
