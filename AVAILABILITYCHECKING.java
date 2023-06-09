import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.io.FileWriter;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class AVAILABILITYCHECKING extends JFrame  {
    private static final long serialVersionUID = 1L;
    private List<String> reservations;
    private Date selectedDate;
    private String selectedStartTime;
    private String selectedEndTime;
    private JTextArea textArea;

    public AVAILABILITYCHECKING() {
        initialize();
        methods m = new methods();
        reservations = m.loadReservations();
    }

    private void initialize () {
        setTitle("Calendar");
        setBounds(100, 100, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        // Create and configure components
        JDateChooser daychecker = new JDateChooser();
        daychecker.setBounds(186, 151, 243, 20);
        daychecker.setForeground(new Color(0, 0, 0));
        daychecker.setDateFormatString("MMMM d, y");

        List<String> startOptions = generateTimeOptions();
        DefaultComboBoxModel<String> comboBoxModelStart = new DefaultComboBoxModel<>(startOptions.toArray(new String[0]));

        List<String> endOptions = generateTimeOptions();
        DefaultComboBoxModel<String> comboBoxModelEnd = new DefaultComboBoxModel<>(endOptions.toArray(new String[0]));

        JComboBox<String> startBox = new JComboBox<>(comboBoxModelStart);
        startBox.setBounds(186, 305, 195, 22);

        JComboBox<String> endBox = new JComboBox<>(comboBoxModelEnd);
        endBox.setBounds(186, 355, 195, 22);

        // Add components to the content pane
        getContentPane().add(daychecker);
        getContentPane().add(startBox);
        getContentPane().add(endBox);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(500, 150, 410, 250);
        getContentPane().add(scrollPane_1);
        
        
        textArea = new JTextArea();
        scrollPane_1.setViewportView(textArea);
        textArea.setEditable(false);

        // Register event listeners
        daychecker.getCalendarButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedDate = daychecker.getDate();
                String selectedDateString = String.format("Selected Date: %tF", selectedDate);
                System.out.println(selectedDateString);

                try {
                    FileWriter writer = new FileWriter("date_time.txt", true);
                    writer.write(selectedDateString + "\n");
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        endBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedEndTime = (String) endBox.getSelectedItem();
                System.out.println("Selected end time: " + selectedEndTime);
            }
        });

        startBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedStartTime = (String) startBox.getSelectedItem();
                System.out.println("Selected start time: " + selectedStartTime);
            }
        });
                
        JPanel checkPanel = new JPanel();
		checkPanel.setOpaque(false);
		checkPanel.setBounds(157, 414, 140, 40);
		getContentPane().add(checkPanel);
		checkPanel.setLayout(null);
		
		JLabel checkBtn = new JLabel("");
		checkBtn.setBounds(5, 5, 125, 30);
		checkPanel.add(checkBtn);
		checkBtn.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/availbtn.png")));
		
		JLabel checkBig = new JLabel("");
		checkPanel.addMouseListener(new ButtonMouseAdapter(checkBtn, checkBig) {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		checkBig.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/availBig.png")));
		checkBig.setBounds(0, 0, 140, 40);
		checkBig.setVisible(false);
		checkPanel.add(checkBig);
		checkPanel.addMouseListener(new ButtonMouseAdapter(checkBtn, checkBig) {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		JPanel seePanel = new JPanel();
		seePanel.setOpaque(false);
		seePanel.setBounds(500, 100, 140, 45);
		getContentPane().add(seePanel);
		seePanel.setLayout(null);
		
		JLabel seeBtn = new JLabel("");
		seeBtn.setBounds(5, 5, 128, 36);
		seePanel.add(seeBtn);
		seeBtn.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/reservationsbtn.png")));
		
		JLabel seeBig = new JLabel("");
		seePanel.addMouseListener(new ButtonMouseAdapter(seeBtn, seeBig) {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		seeBig.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/reservationsBig.png")));
		seeBig.setHorizontalAlignment(SwingConstants.CENTER);
		seeBig.setBounds(0, 0, 140, 45);
		seeBig.setVisible(false);
		seePanel.add(seeBig);
		
		JPanel reservePanel = new JPanel();
		reservePanel.setOpaque(false);
		reservePanel.setBounds(460, 480, 100, 57);
		getContentPane().add(reservePanel);
		reservePanel.setLayout(null);
		
		JLabel reserveBtn = new JLabel("");
		reserveBtn.setHorizontalAlignment(SwingConstants.CENTER);
		reserveBtn.setBounds(0, 11, 100, 36);
		reservePanel.add(reserveBtn);
		reserveBtn.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/reservebtn.png")));
		
		JLabel reserveBig = new JLabel("");
		reservePanel.addMouseListener(new ButtonMouseAdapter(reserveBtn, reserveBig) {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		reserveBig.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/reserveBig.png")));
		reserveBig.setBounds(0, 11, 100, 35);
		reserveBig.setVisible(false);
		reservePanel.add(reserveBig);
		
		JLabel availImg = new JLabel("");
		availImg.setIcon(new ImageIcon(AVAILABILITYCHECKING.class.getResource("/IMAGES/AVAILABILITYCHECKING.png")));
		availImg.setBounds(0, 0, 984, 561);
		getContentPane().add(availImg);
    }

    private List<String> generateTimeOptions() {
        methods m = new methods();
        return m.generateTimeOptions();
    }

    private String getFormattedDateTime(Date date, String time) {
        methods m = new methods();
        return m.getFormattedDateTime(date, time);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AVAILABILITYCHECKING window = new AVAILABILITYCHECKING();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<String> loadReservations() {
        List<String> reservations = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                reservations.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    private List<String> removeDuplicateReservations(List<String> reservations) {
        Set<String> uniqueReservations = new TreeSet<>();
        for (String reservation : reservations) {
            uniqueReservations.add(reservation);
        }
        return new ArrayList<>(uniqueReservations);
    }

    private void sortReservations(List<String> reservations) {
        SimpleDateFormat format = new SimpleDateFormat("y-MM-dd HH:mm");
        reservations.sort(new Comparator<String>() {
            public int compare(String r1, String r2) {
                try {
                    Date d1 = format.parse(r1.split(" - ")[0]);
                    Date d2 = format.parse(r2.split(" - ")[0]);
                    return d1.compareTo(d2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    private void displayReservations(List<String> reservations) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat inputFormat = new SimpleDateFormat("y-MM-dd HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d, y - HH:mm");
        for (String reservation : reservations) {
            String[] parts = reservation.split(" - ");
            try {
                Date startDate = inputFormat.parse(parts[0]);
                Date endDate = inputFormat.parse(parts[1]);
                String formattedReservation = outputFormat.format(startDate) + " - " + outputFormat.format(endDate);
                sb.append(formattedReservation).append("\n\n");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        textArea.setText(sb.toString());
    }
    private class ButtonMouseAdapter extends MouseAdapter{
		JLabel label;
		JLabel bigLabel;
		public ButtonMouseAdapter(JLabel label, JLabel bigLabel) {
			this.label = label;
			this.bigLabel = bigLabel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			label.setVisible(false);
			bigLabel.setVisible(true);
		}
		public void mouseExited(MouseEvent e) {
			label.setVisible(true);
			bigLabel.setVisible(false);
		}
		public void mousePressed(MouseEvent e) {
			label.setVisible(true);
			bigLabel.setVisible(false);
		}
		public void mouseReleased(MouseEvent e) {
			label.setVisible(false);
			bigLabel.setVisible(true);
		}	
    }
}