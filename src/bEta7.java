


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import chemaxon.formats.MolConverter;
import chemaxon.formats.MolExporter;
import chemaxon.formats.MolFormatException;
import chemaxon.formats.MolImporter;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;




@SuppressWarnings("serial")
public class bEta7 extends JFrame{
	/**
	 * 
	 */
	String Name1, Name2, Name3;

	private JFrame frmNew;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bEta7 window = new bEta7();
					window.frmNew.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}

	/**
	 * Create the application.
	 */
	public bEta7() {
		
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmNew = new JFrame();
		frmNew.setTitle("SDF/SMI to MOL/PNG Converter Beta v7.0");
		frmNew.setBounds(100, 100, 756, 372);
		frmNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNew.getContentPane().setLayout(null);
		
		final JLabel lblNa = new JLabel("NA");
		lblNa.setBounds(587, 151, 107, 14);
		frmNew.getContentPane().add(lblNa);
		
		final JLabel lblNa_1 = new JLabel("NA");
		lblNa_1.setBounds(587, 71, 46, 14);
		frmNew.getContentPane().add(lblNa_1);
		
		final JLabel lblNa_2 = new JLabel("NA");
		lblNa_2.setBounds(587, 111, 46, 14);
		frmNew.getContentPane().add(lblNa_2);
		
		final JLabel lblNewLabel_3 = new JLabel("NA");
		lblNewLabel_3.setBounds(587, 191, 107, 14);
		frmNew.getContentPane().add(lblNewLabel_3);
		
		final JLabel lblNewLabel_4 = new JLabel("NA");
		lblNewLabel_4.setBounds(587, 31, 107, 14);
		frmNew.getContentPane().add(lblNewLabel_4);
		
		final JLabel lblNewLabel_5 = new JLabel("NA");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(587, 231, 46, 14);
		frmNew.getContentPane().add(lblNewLabel_5);
		
		final JLabel statusBar = new JLabel("No Status");
		statusBar.setBounds(423, 270, 307, 32);
		frmNew.getContentPane().add(statusBar);
		
		JButton btnNewButton = new JButton("Source");
		btnNewButton.setBounds(19, 49, 107, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				
				//OPEN
				  JFileChooser chooser = new JFileChooser();
				   FileNameExtensionFilter filter = new FileNameExtensionFilter("SDFILES/SMILES", "sdf","smi","smiles");
					  chooser.setFileFilter(filter);
					   chooser.setMultiSelectionEnabled(true);
				   int option = chooser.showOpenDialog(bEta7.this);
				   if (option == JFileChooser.APPROVE_OPTION) {
				   
				     Name1 = chooser.getSelectedFile().toString();
				     Name1 = Name1.replace("\\", "/");
				     textField.setText(Name1);
				     
				   }
				   else {
				     textField.setText("You canceled.");
				   }
			
			}
		});
		frmNew.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Destination");
		btnNewButton_1.setBounds(19, 146, 107, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//SAVE
				 JFileChooser chooser = new JFileChooser();
				   chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				   int option = chooser.showOpenDialog(bEta7.this);
				   if (option == JFileChooser.APPROVE_OPTION) {
					   Name2 = chooser.getSelectedFile().toString();
					   Name2 = Name2.replace("\\", "/");
					   Name2=Name2+("/");
					   textField_1.setText(Name2);
				   }
				   else {
					   textField_1.setText("You canceled.");
				   }
			}
		});
		frmNew.getContentPane().add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(150, 50, 222, 20);
		frmNew.getContentPane().add(textField);
		textField.setColumns(10);
		
		final JComboBox<Object> comboBox_1 = new JComboBox<Object>();
		comboBox_1.setModel(new DefaultComboBoxModel<Object>(new String[] {"mol", "png","smiles"}));
		comboBox_1.setBounds(150, 98, 131, 20);
		frmNew.getContentPane().add(comboBox_1);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 147, 222, 20);
		textField_1.setColumns(10);
		frmNew.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(150, 201, 222, 20);
		frmNew.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.setBounds(143, 279, 99, 23);
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//give a validation check for file existence
				 final String extOut = (String) (comboBox_1.getSelectedItem());
			     //System.out.println(extOut);
				Name3=textField_2.getText();
				Name3=Name2+Name3;
				
				 SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
					 
				
					 
					 
					    @Override
					   protected Boolean doInBackground() throws Exception {
					    // Simulate doing something useful.
					    	int avProcess = Runtime.getRuntime().availableProcessors();
							long freeMem = Runtime.getRuntime().freeMemory();
							Runtime.getRuntime().maxMemory();
							Runtime.getRuntime().totalMemory();
							lblNa.setText(avProcess+"\n");
							lblNewLabel_3.setText(freeMem+"\n");
							
							
												
					    return true;
					   }

					   // Can safely update the GUI from this method.
					   protected void done() {
					    
					    boolean status;
					    try {
					     // Retrieve the return value of doInBackground.
					     status = get();
					     
					     statusBar.setText("Converting :" +status);
					     
					        } catch (InterruptedException e) {
					     // This is thrown if the thread's interrupted.
					    } catch (ExecutionException e) {
					     // This is thrown if we throw an exception
					     // from doInBackground.
					    }
					   }

					  };
					  
					  SwingWorker<Void, Integer> worker1 = new SwingWorker<Void, Integer>() {

						@Override
						protected Void doInBackground() throws Exception {
							
							lblNa_2.setText(extOut);	
							String filename = Name1;
							int dotPosition = filename.lastIndexOf(".");
							String extension = "";
							if (dotPosition != -1) {
							    extension = filename.substring(dotPosition);
							}
							System.out.println("The file is of type: " + extension);
							extension = extension.substring(1);
							lblNa_1.setText(extension);
						
								Thread.sleep(100);
								if (extOut.equals("mol")|extOut.equals("png"))
								{
													
							MolConverter.Builder mcbld = new MolConverter.Builder();
							mcbld.addInput(Name1, "");
							mcbld.setOutput(Name3+"."+extOut, extOut);
							mcbld.setOutputFlags(MolExporter.TEXT|MolExporter.MULTIPLE);
							int strIN = mcbld.getInputCount();
							MolConverter mc = mcbld.build();
							int counter = 0;
							while(mc.convert())
									{
								counter = counter+1;
								System.out.println("Converting molecule no: "+counter);
								lblNewLabel_5.setText(counter+"\n");
								
									}
							mc.close();
							System.out.println("Total Converted molecules: "+counter); 
							System.out.println("Total number of input file: "+strIN);
							statusBar.setText("Total number of molecules in output: " + counter );
							
														
						}
						
						else if (extOut.equals("smiles"))
						{
							MolConverter.Builder mcbld = new MolConverter.Builder();
							mcbld.addInput(Name1, "");
							mcbld.setOutput(Name3+"."+extOut, extOut);
							mcbld.setOutputFlags(MolExporter.TEXT);
							int strIN = mcbld.getInputCount();
							MolConverter mc = mcbld.build();
							int counter = 1;
							while(mc.convert());
							mc.close();
							System.out.println("Total Converted molecules: "+counter); 
							System.out.println("Total number of input file: "+strIN);
							statusBar.setText("Total number of molecules in output: " + counter );
														
						}
								return null;	
						
						}
					
					  };
					  
					  SwingWorker<Void, Integer> worker2 = new SwingWorker<Void, Integer>() {

							@Override
							protected Void doInBackground() throws Exception {
							
								/** Defines a MolImporter object to the structure file. */
								
							
								

								/** counts molecules from a structure file. */
								
									// loads molecules
									MolImporter mi = createMolImporter(Name1); 

									//count all our little molecules - seek back later or close/open again
									long globalmolcounter = 0;
									while (( mi.read()) != null) {
										globalmolcounter++;
									}
									mi.close();
									System.out.println("Total number of molecules detected are: "+globalmolcounter);
									lblNewLabel_4.setText(globalmolcounter+"\n");
									Thread.sleep(100);
									
									
								
								return null;
							}

							private MolImporter createMolImporter(String name1) {
								
								MolImporter mi = null;
								try{
									File f = new File(name1);
									FileInputStream fis = new FileInputStream(f);
									mi = new MolImporter(fis);

								} catch(FileNotFoundException ex) {
									System.err.println(name1+": not found");
									System.exit(1);
								} catch(MolFormatException ex) {
									System.err.println(name1+": "+ex.getMessage());
									System.exit(1);
								} catch(Exception ex) {
									System.err.println("Error: "+name1+" is not a structure file.");
									System.exit(1);
								}
								return mi;
						}
						
						  };
					  worker.execute();
					  worker1.execute();
					  worker2.execute();
					 
				
				
				/*
				textArea.append(sdf2mol.giveIntro()+"\n");
								
				try {
					textArea.append("Number of molecules detected are: "+sdf2mol.countMolecules(Name1)+"\n");
				} catch (MolFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (PluginException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				try {
					textArea.append(sdf2mol.molCon2(Name1, Name3, extOut)+"\n");
				} catch (MolFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //catch (PluginException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IllegalStateException e){
				e.printStackTrace();
				}
			
			    statusBar.setText("Completed");
				
				*/
			}
		});
		frmNew.getContentPane().add(btnConvert);
		
		JLabel lblOutputName = new JLabel("Output Name");
		lblOutputName.setBounds(19, 200, 107, 23);
		frmNew.getContentPane().add(lblOutputName);
		
		JLabel lblshouldEndWith = new JLabel("(Without format suffix)");
		lblshouldEndWith.setBounds(19, 232, 140, 14);
		frmNew.getContentPane().add(lblshouldEndWith);
		
		JLabel lblOutputFileFormat = new JLabel("Output File Format");
		lblOutputFileFormat.setBounds(19, 101, 121, 14);
		frmNew.getContentPane().add(lblOutputFileFormat);
		
		JButton btnNewButton_2 = new JButton("About");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String about = ("SDF/SMI to MOL/PNG Converter\n"+"Beta v7.0\n"+"Author: Jitesh");
				JOptionPane.showMessageDialog(null, about);
				
			}
		});
		btnNewButton_2.setBounds(19, 279, 89, 23);
		frmNew.getContentPane().add(btnNewButton_2);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNa.setText(null);
				lblNa_1.setText(null);
				lblNa_2.setText(null);
				lblNewLabel_3.setText(null);
				lblNewLabel_4.setText(null);
				lblNewLabel_5.setText(null);
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				comboBox_1.setToolTipText(null);
				statusBar.setText("No Status");
				
			}
		});
		btnReset.setBounds(283, 279, 89, 23);
		frmNew.getContentPane().add(btnReset);
		
		JLabel lblNewLabel = new JLabel("No. of molecules in input :");
		lblNewLabel.setBounds(423, 31, 154, 14);
		frmNew.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Input molecule format :");
		lblNewLabel_1.setBounds(423, 71, 154, 14);
		frmNew.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Output molecule format :");
		lblNewLabel_2.setBounds(423, 111, 160, 14);
		frmNew.getContentPane().add(lblNewLabel_2);
		
		JLabel lblProcessorsUtilized = new JLabel("Processors utilized :");
		lblProcessorsUtilized.setBounds(423, 151, 140, 14);
		frmNew.getContentPane().add(lblProcessorsUtilized);
		
		JLabel lblFreeMemoryAvailable = new JLabel("Free memory available :");
		lblFreeMemoryAvailable.setBounds(423, 191, 154, 14);
		frmNew.getContentPane().add(lblFreeMemoryAvailable);
		
		JLabel lblConvertingMolecules = new JLabel("Converting molecules :");
		lblConvertingMolecules.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConvertingMolecules.setBounds(423, 231, 154, 14);
		frmNew.getContentPane().add(lblConvertingMolecules);
		
				
	}
}
