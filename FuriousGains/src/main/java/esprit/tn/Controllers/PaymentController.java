package esprit.tn.Controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import esprit.tn.Utils.MyConnexion;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PaymentController {

    @FXML
    private TextField amountField;

    @FXML
    private TextField currencyField;

    @FXML
    private TextField cardNumberField;

    private MyConnexion myConnexion; // Use correct casing for variable name

    // Method to set the payment amount in the amountField TextField
    public void setAmountToPay(float amount) {
        Platform.runLater(() -> amountField.setText(String.valueOf(amount)));
    }

    // Method to process payment
    @FXML
    public void processPayment() {
        String amountText = amountField.getText();
        String currency = currencyField.getText();
        String card = cardNumberField.getText();

        // Move the payment processing logic here
        try {
            Stripe.apiKey = "sk_test_51OqhUBC9zXfLVdKjGOM5GfNgvfK5DVHHabe2AjdSnOih1Pe2L1zqSzzSCAxsrkruUYPP1AqvMLLvyPY673LL8dnN00IwkW2cqE"; // Your Secret Key

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) (Float.valueOf(amountText) * 100)) // Amount in cents
                    .setCurrency(currency)
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());

            // Clear the text fields after successful payment
            amountField.clear();
            currencyField.clear();
            cardNumberField.clear();

            // Close the payment window
            Stage stage = (Stage) amountField.getScene().getWindow();
            stage.close();

            // Refresh the panier table

        } catch (StripeException e) {
            System.out.println("Payment failed. Error: " + e.getMessage());
        }
    }
}
