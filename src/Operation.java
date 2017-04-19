/**
 * Created by Gebruiker on 19/04/2017.
 */
public interface Operation {

    double apply(double left, double right);
    int getPrecedence();
}
