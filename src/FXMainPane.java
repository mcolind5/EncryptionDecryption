
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The class creates an object of FXMainPane used in FXDriver
 * 
 * @author Farnaz Eivazi
 * @version 7/12/2022
 */

public class FXMainPane extends BorderPane {

	private Button decryption, exitButton, encryption, test, clearButton;
	private TextField plainTextTextField, inputForEncryptionTextField, encryptedStringTextField3, decryptedTextField4;
	private Label plainTextLabel, descriptionForInputLabel, encryptedLabel3, decryptedLabel4, blankLabel1, blankLabel2;
	private RadioButton radioButton1, radioButton2;
	private int shiftInt = 0;
	private final String BLANK = "                 ";

	private void buildTextFields() {
		plainTextTextField = new TextField();
		inputForEncryptionTextField = new TextField();
		encryptedStringTextField3 = new TextField();
		decryptedTextField4 = new TextField();
	}

	private void buildLabels() {
		plainTextLabel = new Label("Enter plain-text string to encrypt");
		descriptionForInputLabel = new Label("Cyber Key - enter an integer for Caesar Cipher");
		encryptedLabel3 = new Label("Encrypted string");
		decryptedLabel4 = new Label("Decrypted string");
		buildBlankLabels();
	}

	private void buildBlankLabels() {
		blankLabel1 = new Label(BLANK);
		blankLabel2 = new Label(BLANK);
		setRight(blankLabel1);
		setLeft(blankLabel2);
	}

	private void buildRadioButtons() {
		// create three radio button instances
		radioButton1 = new RadioButton("Use Caesar cipher");
		radioButton2 = new RadioButton("Use Bellaso cipher");
		// create a group to make the radio buttons mutually exclusive
		ToggleGroup radioButtonGroup = new ToggleGroup();
		radioButton1.setToggleGroup(radioButtonGroup);
		radioButton2.setToggleGroup(radioButtonGroup);

		radioButton1.setSelected(true);
		radioButton1.setAlignment(Pos.CENTER);
		radioButton2.setAlignment(Pos.CENTER);

		RadioButtonListener radioButtonListener = new RadioButtonListener();
		radioButton1.setOnAction(radioButtonListener);
		radioButton2.setOnAction(radioButtonListener);
	}

	private void buildButtons() {
		// _ in label specifies that the next character is the mnemonic, ie, type Alt-m
		// as a shortcut
		// this activates the mnemonic on exitButton when the Alt key is pressed

		// create the exit Button
		exitButton = new Button("E_xit");
		exitButton.setMnemonicParsing(true);
		exitButton.setTooltip(new Tooltip("Select to close the application"));

		// create the clear Button
		clearButton = new Button("_Clear");
		clearButton.setMnemonicParsing(true);
		clearButton.setTooltip(new Tooltip("Select this to clear the text fields"));

		// create the decryption Button
		decryption = new Button("_Decrypt a string");
		decryption.setMnemonicParsing(true);
		decryption.setTooltip(new Tooltip("Select this to decrypt a string"));

		// create the encryption Button
		encryption = new Button("Encrypt a string");
		encryption.setMnemonicParsing(true);
		encryption.setTooltip(new Tooltip("Encrypt the string in the upper textfield"));
		encryption.setVisible(true);
	}

	private void addActionToExitButton() {
		// use a lambda expression for the EventHandler class for exitButton
		exitButton.setOnAction(event -> {
			Platform.exit();
			System.exit(0);
		});
	}

	private void addActionToClearButton() {
		// create a listener for the other button using a lambda expression
		clearButton.setOnAction(event -> {
			plainTextTextField.setText("");
			inputForEncryptionTextField.setText("");
			encryptedStringTextField3.setText("");
			decryptedTextField4.setText("");
		});
	}

	private void addActionToEncryptButton() {
		// create a listener for the exit button using a lambda expression
		encryption.setOnAction(event -> {
			try {
				String bellasoStr = "";
				String encryptedStr = "";

				if (radioButton1.isSelected()) {
					shiftInt = Integer.parseInt(inputForEncryptionTextField.getText());
					encryptedStr = CryptoManager.caesarEncryption(plainTextTextField.getText().toUpperCase(), shiftInt);
				} else {
					bellasoStr = inputForEncryptionTextField.getText().toUpperCase();
					inputForEncryptionTextField.setText(bellasoStr);
					encryptedStr = CryptoManager.bellasoEncryption(plainTextTextField.getText().toUpperCase(), bellasoStr);
				}

				plainTextTextField.setText(plainTextTextField.getText().toUpperCase());
				if (encryptedStr.equals(""))
					encryptedStringTextField3.setText("encryption failed");
				else
					encryptedStringTextField3.setText(encryptedStr);
			} catch (NumberFormatException e) {
				System.out.println(inputForEncryptionTextField.getText() + " should be an integer");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		});
	}

	private void addActionToDecryptButton() {
		// create a listener for the other button using a lambda expression
		decryption.setOnAction(event -> {
			try {
				String encryptedText = "";
				String bellasoStr = "";
				String decryptedText;
				encryptedText = encryptedStringTextField3.getText().toUpperCase();
				if (radioButton1.isSelected()) {
					shiftInt = Integer.parseInt(inputForEncryptionTextField.getText());
					decryptedText = CryptoManager.caesarDecryption(encryptedText, shiftInt);
				} else {
					bellasoStr = inputForEncryptionTextField.getText().toUpperCase();
					inputForEncryptionTextField.setText(bellasoStr);
					decryptedText = CryptoManager.bellasoDecryption(encryptedText, bellasoStr);
				}
				decryptedTextField4.setText(decryptedText);
			} catch (NumberFormatException e) {
				System.out.println(inputForEncryptionTextField.getText() + " should be an integer");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		});
	}

	private void addButtonActions() {
		addActionToExitButton();
		addActionToClearButton();
		addActionToEncryptButton();
		addActionToDecryptButton();
	}

	FXMainPane() {
		// for setting margins
		Insets inset = new Insets(10);
		buildTextFields();
		buildLabels();
		buildRadioButtons();
		buildButtons();
		addButtonActions();

		HBox topBox = new HBox();
		HBox.setMargin(radioButton1, inset);
		topBox.setAlignment(Pos.CENTER);
		topBox.getChildren().addAll(radioButton1, radioButton2);
		topBox.setStyle("-fx-border-color: gray;");

		// create the leftPanel
		VBox centerBox = new VBox(10);
		centerBox.getChildren().addAll(plainTextLabel, plainTextTextField, encryptedLabel3, encryptedStringTextField3,
				decryptedLabel4, decryptedTextField4, descriptionForInputLabel, inputForEncryptionTextField);
		setCenter(centerBox);

		setTop(topBox);

		HBox bottomBox = new HBox();
		HBox.setMargin(decryption, inset);
		HBox.setMargin(encryption, inset);
		HBox.setMargin(clearButton, inset);

		HBox.setMargin(exitButton, inset);
		bottomBox.getChildren().addAll(encryption, decryption, clearButton, exitButton);
		setBottom(bottomBox);
		bottomBox.setAlignment(Pos.CENTER);

	}

	class RadioButtonListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Object source = event.getTarget();
			if (source == radioButton1) {
				descriptionForInputLabel.setText("Cyber Key - enter an integer for Caesar Cipher");
			} else if (source == radioButton2) {
				descriptionForInputLabel.setText("Cyber Key - enter a string for Bellaso Cipher");
			}
		}
	}

}
