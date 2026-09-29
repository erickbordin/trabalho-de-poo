package usecases.verifiers;

public class NumberVerifiers {

    public boolean isNumero(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDecimal(String valor) {
        try {
            Double.parseDouble(valor.replace(',', '.'));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
