package captainsly.anvil.ui.nodes;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * Increments and Decrements an integer
 * @author Azraein
 *
 */
public class IncrementalIntegerField extends Region {

	private SimpleIntegerProperty fieldCountProperty;
	private SimpleBooleanProperty negativesAllowedProperty;

	private Label fieldLbl;

	public IncrementalIntegerField(boolean isAllowed) {
		fieldCountProperty = new SimpleIntegerProperty(0);
		negativesAllowedProperty = new SimpleBooleanProperty(isAllowed);

		BorderPane rootPane = new BorderPane();
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(0, 10, 0, 10));

		fieldLbl = new Label();
		fieldLbl.setText(fieldCountProperty.get() + "");

		Button incrementBtn = new Button();
		incrementBtn.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.ARROW_UP));
		incrementBtn.setOnAction(e -> {
			fieldCountProperty.set(fieldCountProperty.get() + 1);
			fieldLbl.setText(getFieldCount() + "");
		});

		Button decrementBtn = new Button();
		decrementBtn.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.ARROW_DOWN));
		decrementBtn.setOnAction(e -> {
			fieldCountProperty.set(fieldCountProperty.get() - 1);

			if (!isNegativesAllowed()) {
				if (getFieldCount() < 0)
					fieldCountProperty.set(0);
			}

			fieldLbl.setText(getFieldCount() + "");

		});

		gridPane.add(incrementBtn, 0, 0);
		gridPane.add(decrementBtn, 0, 1);

		rootPane.setCenter(fieldLbl);
		rootPane.setRight(gridPane);

		this.getChildren().add(rootPane);
	}

	public void setFieldCount(int fieldCount) {
		fieldCountProperty.set(fieldCount);
		fieldLbl.setText(getFieldCount() + "");
	}

	public SimpleIntegerProperty getFieldCountProperty() {
		return fieldCountProperty;
	}

	public SimpleBooleanProperty getNegativesAllowedProperty() {
		return negativesAllowedProperty;
	}

	public boolean isNegativesAllowed() {
		return negativesAllowedProperty.get();
	}

	public int getFieldCount() {
		return fieldCountProperty.get();
	}

}
