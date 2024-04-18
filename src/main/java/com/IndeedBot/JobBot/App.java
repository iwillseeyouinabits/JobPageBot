package com.IndeedBot.JobBot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App {
	public void run() {
		// Creating the Frame
		JFrame frame = new JFrame("Indeed Bot");
		try {
			frame.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/logo.png")).getImage());
		} catch (Exception e) {
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);

		// Creating the panel at bottom and adding components
		JPanel panelUrl = new JPanel(); // the panel is not visible in output
		JPanel panelBtn = new JPanel(); // the panel is not visible in output
		JPanel panelRun = new JPanel(); // the panel is not visible in output

		JLabel[] labels = new JLabel[] { new JLabel("Starting Indeed URL: "), new JLabel("Email: "),
				new JLabel("Password: "),

				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),
				new JLabel("Click this radio/checkbox: "), new JLabel("Click this radio/checkbox: "),

				new JLabel("Click this radio/checkbox: "), new JLabel("if:"), new JLabel("or if:"),
				new JLabel("or if:"),
				new JLabel(
						"                                                                                                                                                      Then Value:"),
				new JLabel(" else Value: "), new JLabel("Click this radio/checkbox: "), new JLabel("if:"),
				new JLabel("or if:"), new JLabel("or if:"),
				new JLabel(
						"                                                                                                                                                      Then Value:"),
				new JLabel(" else Value: "), new JLabel("Click this radio/checkbox: "), new JLabel("if:"),
				new JLabel("or if:"), new JLabel("or if:"),
				new JLabel(
						"                                                                                                                                                      Then Value:"),
				new JLabel(" else Value: "), new JLabel("Click this radio/checkbox: "), new JLabel("if:"),
				new JLabel("or if:"), new JLabel("or if:"),
				new JLabel(
						"                                                                                                                                                      Then Value:"),
				new JLabel(" else Value: "), new JLabel("Click this radio/checkbox: "), new JLabel("if:"),
				new JLabel("or if:"), new JLabel("or if:"),
				new JLabel(
						"                                                                                                                                                      Then Value:"),
				new JLabel(" else Value: "), new JLabel("Click this radio/checkbox: "), new JLabel("if:"),
				new JLabel("or if:"), new JLabel("or if:"),
				new JLabel(
						"                                                                                                                                                      Then Value:"),
				new JLabel(" else Value: "), new JLabel("Click this radio/checkbox: "), new JLabel("if:"),
				new JLabel("or if:"), new JLabel("or if:"),
				new JLabel(
						"                                                                                                                                                      Then Value:"),
				new JLabel(" else Value: "), new JLabel("Click this radio/checkbox: "), new JLabel("if:"),
				new JLabel("or if:"), new JLabel("or if:"),
				new JLabel(
						"                                                                                                                                                      Then Value:"),
				new JLabel(" else Value: "),

				new JLabel("      Click this DropDown: "), new JLabel("              or Click this DropDown: "),

				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "), new JLabel("           or Click this DropDown: "),
				new JLabel("           or Click this DropDown: "),

				new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("type"), new JLabel("If Label: "), new JLabel("or If Label: "),
				new JLabel("or If Label: "), new JLabel("or If Label: "), new JLabel("type") };
		final JTextField[] tfs = new JTextField[labels.length];
		for (int i = 0; i < labels.length; i++) {
			tfs[i] = new JTextField(10);
		} // accepts upto 10 characters
		JButton send = new JButton("RUN");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = tfs[0].getText();
				String email = tfs[1].getText();
				String pass = tfs[2].getText();
				ArrayList<String> btn = new ArrayList<String>();
				ArrayList<ArrayList<String>> btnIfElse = new ArrayList<ArrayList<String>>();
				ArrayList<String> select = new ArrayList<String>();
				ArrayList<ArrayList<String>> typeIfLabel = new ArrayList<ArrayList<String>>();

				for (int i = 3; i < 3 + 36; i++) {
					if (tfs[i].getText().replace(" ", "").length() > 0)
						btn.add(tfs[i].getText());
				}

				for (int i = 3 + 36; i < 3 + 36 + 48; i += 6) {
					ArrayList<String> set = new ArrayList<String>();
					for (int j = 0; j < 6; j++)
						if (tfs[i + j].getText().replace(" ", "").length() > 0)
							set.add(tfs[i + j].getText());
					if (!set.isEmpty())
						btnIfElse.add(set);
				}

				for (int i = 3 + 36 + 48; i < 3 + 36 + 48 + 33; i++) {
					if (tfs[i].getText().replace(" ", "").length() > 0)
						select.add(tfs[i].getText());
				}

				for (int i = 3 + 36 + 48 + 33; i < 3 + 36 + 48 + 33 + 175; i += 5) {
					ArrayList<String> set = new ArrayList<String>();
					for (int j = 0; j < 5; j++)
						if (tfs[i + j].getText().replace(" ", "").length() > 0)
							set.add(tfs[i + j].getText());
					if (!set.isEmpty())
						typeIfLabel.add(set);
				}
				System.out.println(3 + 36 + 48 + 33 + 175 + " " + tfs.length);
				if (url.contains("wellfound")) {
					Thread runner = new Thread(new Runner2WellFound(url, email, pass, btn, btnIfElse, select, typeIfLabel));
					runner.start();
				} else {
				Thread runner = new Thread(new Runner2GlassDoor(url, email, pass, btn, btnIfElse, select, typeIfLabel));
				runner.start();
				}

			}
		});

		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (new File("settings.csv").exists()) {
						new File("settings.csv").delete();
					}
					FileWriter fw = new FileWriter("settings.csv");
					for (JTextField tf : tfs) {
						fw.write(tf.getText());
						fw.write("breakcaketake");
					}
					fw.close();
				} catch (IOException e1) {
				}
				JOptionPane.showMessageDialog(null, "Settings Saved!", "Finish", JOptionPane.PLAIN_MESSAGE);
			}
		});

		JButton load = new JButton("Load");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner sc = new Scanner(new File("settings.csv"));
					String data = "";
					while (sc.hasNext()) {
						data += sc.next() + " ";
					}
					sc.close();
					String[] feilds = data.split("breakcaketake");
					for (int i = 0; i < feilds.length - 1; i++) {
						tfs[i].setText(feilds[i]);
					}

					JOptionPane.showMessageDialog(null, "Settings Loaded!", "Finish", JOptionPane.PLAIN_MESSAGE);
				} catch (IOException e1) {
				}
			}
		});

		panelUrl.add(labels[0]);
		panelUrl.add(tfs[0]);
		panelUrl.add(labels[1]);
		panelUrl.add(tfs[1]);
		panelUrl.add(labels[2]); // Components Added using Flow Layout
		panelUrl.add(tfs[2]);

		for (int i = 3; i < labels.length; i++) {
			panelBtn.add(labels[i]); // Components Added using Flow Layout
			panelBtn.add(tfs[i]);
		}
		panelRun.add(send);
		panelRun.add(save);
		panelRun.add(load);

		// Adding Components to the frame.

		panelBtn.setLayout(new FlowLayout());
		panelBtn.setSize(new Dimension(880, 2000));
		panelBtn.setPreferredSize(new Dimension(880, 2000));
		panelBtn.setMaximumSize(new Dimension(880, 2000));

		JScrollPane scrollbar = new JScrollPane(panelBtn);
		scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollbar.setSize(new Dimension(900, 2000));
		scrollbar.setPreferredSize(new Dimension(900, 2000));
		scrollbar.setMaximumSize(new Dimension(900, 2000));
		scrollbar.setVisible(true);

		frame.getContentPane().add(BorderLayout.NORTH, panelUrl);
		frame.getContentPane().add(BorderLayout.CENTER, scrollbar);
		frame.getContentPane().add(BorderLayout.SOUTH, panelRun);
		frame.setVisible(true);
	}
}
