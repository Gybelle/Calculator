/**
 * Created by Michelle on 19/04/2017.
 */
public class OperationSubstract implements Operation {
        int m_precedence; //based on table: https://www.wikiwand.com/en/Order_of_operations

        public OperationSubstract(){
            m_precedence = 4;
        }

        public double apply(double left, double right){
            return left - right;
        }

        public int getPrecedence(){
            return m_precedence;
        }

    }
