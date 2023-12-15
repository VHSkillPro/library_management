package view;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class PanelBook extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelBook() {

		createContents();
	}
	private void createContents() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đây là panel của sách");
		lblNewLabel.setBounds(153, 138, 128, 13);
		add(lblNewLabel);
	}
}
