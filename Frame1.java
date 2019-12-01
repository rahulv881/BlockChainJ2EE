import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

import java.util.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Frame1 {

	private JFrame frame;
	JButton btnCreateBlock;
	JButton btnValidateChain;
	private JLabel lblBlockHashValue;
	private JLabel lblBlocksPreviousHash;
	private JLabel lblBlocksTimestamp;
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel label_1;
	private JButton btnUpdateBlock;
	private JTextField txtEnterIndexValue;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	
	private JLabel[] lable;
	private JLabel[] Clable;
	
	private JMenuBar menubar;
	private JMenu	menu;
	private JMenuItem menuItem;
	
	private JTextField txtEnterUpdatedHash;
    private JLabel lblNewLabel_1;
    private JLabel label_12;
    private JTextField txtEnterPreviousHash;

    private static int i;
    private List<Block> blockChainList;
    private List<Block> blockChainList2;
    private Block genesis;
    private static int gval;
    private JButton btnSave;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		i=0;
		blockChainList =  new ArrayList<>();
		blockChainList2 =  new ArrayList<>();
		frame = new JFrame();
		menubar = new JMenuBar();
		menu = new JMenu("File");
		menuItem = new JMenuItem("Open");
		Image bimg = new ImageIcon(this.getClass().getResource("/BlockCubeIcon.png")).getImage();
        frame.setIconImage(bimg);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("/Users/rahul/Downloads/BlockChain"));
				int f = fc.showOpenDialog(null);
				
				if(f==JFileChooser.APPROVE_OPTION)
				{
					System.out.println(fc.getSelectedFile().getAbsolutePath());
					try
				    {
				      ObjectInputStream in=new ObjectInputStream(new FileInputStream(fc.getSelectedFile().getAbsoluteFile()+""));
				      Block block;
				      i=0;
				      blockChainList = null;
				      blockChainList2 = null;
				      for(int i=1;i<10;i++)
				        lable[i].setVisible(false);

				      //blockChainList =  new ArrayList<>();
					  //blockChainList2 =  new ArrayList<>();
					  
				      blockChainList = (List <Block>) in.readObject();
				      
				       for(int j=0;j<blockChainList.size();j++)
				       {
				    	block = blockChainList.get(j);
				        System.out.println(block.getTimestamp());
				        System.out.println(block.getPrevHash());
				        System.out.println(block.getHashValue());
				        //blockChainList.add(block);
						//blockChainList2.add(block);
						lable[j].setVisible(true);
						lable[j].setText(block.getHash()+"\n"+block.getPrevHash()+"\n"+block.getTimestamp()+"\n");
						 i++;
						//System.out.println(i);
				      }
				      in.close();
				      
				    }catch(Exception exp){System.out.println(exp);}
				}
			}
			
		});
		menu.add(menuItem);
		menubar.add(menu);
		frame.setJMenuBar(menubar);
		
		
		
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel.setText("...");
				label.setText("...");
				label_1.setText("...");
				label_12.setText("...");
				txtEnterPreviousHash.setText("Enter Previous Hash Value...");
				txtEnterIndexValue.setText("Enter Index Value...");
				txtEnterUpdatedHash.setText("Enter Hash Value...");
				//txtEnterGenesisValue
				//txtEnterGenesisBlock.setText("Enter Genesis Block Value...");
				//restore();
			}
		});
		lable = new JLabel[10];
		Clable = new JLabel[9];
		
		frame.setBounds(100, 100, 878, 584);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		genesis = new Block("Genesis",0);
		gval = genesis.getHashValue();
		blockChainList.add(genesis);
		blockChainList2.add(genesis);
		i++;
		System.out.println(i);
		btnCreateBlock = new JButton("Create Block");
		btnCreateBlock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(i>=10)
				{
					return ;
				}
				else 
				{
					
					Block block = new Block("Block"+i,blockChainList.get(i-1).getHash());
					blockChainList.add(block);
					blockChainList2.add(block);
					lable[i].setVisible(true);
					lable[i].setText(block.getHash()+"\n"+block.getPrevHash()+"\n"+block.getTimestamp()+"\n");
					i++;
					System.out.println(i);
				}
					
			}
		});
		
		btnCreateBlock.setBounds(25, 460, 157, 29);
		frame.getContentPane().add(btnCreateBlock);
		
		btnValidateChain = new JButton("Validate Chain");
		btnValidateChain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(validate(blockChainList))
					label_12.setText("true");
				else
					label_12.setText("false");
				
			}
		});

		btnValidateChain.setBounds(25, 419, 157, 29);
		frame.getContentPane().add(btnValidateChain);
		
		lblBlockHashValue = new JLabel("Block' Hash Value.                 :");
		lblBlockHashValue.setBounds(508, 424, 195, 16);
		frame.getContentPane().add(lblBlockHashValue);
		
		lblBlocksPreviousHash = new JLabel("Block's Previous Hash Value. :");
		lblBlocksPreviousHash.setBounds(508, 461, 195, 16);
		frame.getContentPane().add(lblBlocksPreviousHash);
		
		lblBlocksTimestamp = new JLabel("Block's Timestamp.               :");
		lblBlocksTimestamp.setBounds(508, 501, 195, 16);
		frame.getContentPane().add(lblBlocksTimestamp);
		
		lblNewLabel = new JLabel("...");
		lblNewLabel.setBounds(715, 424, 157, 16);
		frame.getContentPane().add(lblNewLabel);
		
		label = new JLabel("...");
		label.setBounds(715, 461, 157, 16);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("...");
		label_1.setBounds(715, 501, 157, 16);
		frame.getContentPane().add(label_1);
		
		btnUpdateBlock = new JButton("Update Block");
		btnUpdateBlock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
				int index = Integer.parseInt(txtEnterIndexValue.getText());
				int value = Integer.parseInt(txtEnterUpdatedHash.getText());
				int prevHash = Integer.parseInt(txtEnterPreviousHash.getText());
				
				System.out.println("Before Update");
				blockChainList.forEach(System.out::println);
				System.out.println();
				blockChainList2.forEach(System.out::println);
				System.out.println();
				
				Block b = blockChainList.get(index);
				blockChainList.remove(index);
				Block temp = new Block("temp",prevHash,value);
				blockChainList.add(index,temp);
				lable[index].setText(value+"\n"+prevHash+"\n"+b.getTimestamp()+"\n");
				//validate(blockChainList);
				//blockChainList.remove(index);
				//blockChainList.add(index,b);
				System.out.println("After Update");
				blockChainList.forEach(System.out::println);
				System.out.println();
				blockChainList2.forEach(System.out::println);
				System.out.println();
				}catch(Exception exc) {System.out.println(exc);};
			}
		});
		
		btnUpdateBlock.setBounds(25, 386, 157, 29);
		frame.getContentPane().add(btnUpdateBlock);
		
		txtEnterIndexValue = new JTextField();
		txtEnterIndexValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterUpdatedHash.setText(null);
			}
			
		});
		txtEnterIndexValue .addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtEnterIndexValue.setText(null);
			}
		});
		txtEnterIndexValue.setText("Enter Index Value...");
		txtEnterIndexValue.setBounds(552, 386, 137, 26);
		frame.getContentPane().add(txtEnterIndexValue);
		txtEnterIndexValue.setColumns(10);
		
		Image img = new ImageIcon(this.getClass().getResource("/HHBlock.png")).getImage();
		Image vimg = new ImageIcon(this.getClass().getResource("/VBlock.png")).getImage();
		Image himg = new ImageIcon(this.getClass().getResource("/HBlock.png")).getImage();
	
		label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel.setText("rahul");
				label.setText("rahul");
				label_1.setText("rahul");
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setText("...");
				label.setText("...");
				label_1.setText("...");
			}
		});
		
	
		lable[0] = new JLabel("");
	    lable[0].setBounds(44, 35, 158, 109);
 	    lable[0].setIcon(new ImageIcon(img));
 	    lable[0].setVisible(true);
	    frame.getContentPane().add(lable[0]);
/*	    
	    Clable[0] = new JLabel("");
	    Clable[0].setBounds(44+109, 35, 109, 109);
 	    Clable[0].setIcon(new ImageIcon(img));
 	    Clable[0].setVisible(true);
	    frame.getContentPane().add(Clable[0]);
*/	    
	    

		lable[0].setText(genesis.getHash()+"\n"+genesis.getPrevHash()+"\n"+genesis.getTimestamp()+"\n");
		
		for(int i=1;i<4;i++)
		{
		    lable[i] = new JLabel("");
		    lable[i].setBounds(44+158*i, 35, 158, 109);
	 	    lable[i].setIcon(new ImageIcon(img));
		    frame.getContentPane().add(lable[i]);
		}
	    lable[4] = new JLabel("");
	    lable[4].setBounds(44+158*4, 35, 109, 158);
 	    lable[4].setIcon(new ImageIcon(vimg));
	    frame.getContentPane().add(lable[4]);
		
		for(int i=0;i<5;i++)
		{
			lable[i+5] = new JLabel("");
			lable[i+5].setBounds(44+158*3+109-i*158, 190, 158, 109);
			lable[i+5].setIcon(new ImageIcon(himg));
			frame.getContentPane().add(lable[i+5]);
		}
		
		for(int i=1;i<10;i++)
		{
			lable[i].setVisible(false);
		}
		
		
		
		txtEnterUpdatedHash = new JTextField();
		txtEnterUpdatedHash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterUpdatedHash.setText(null);
			}
		});
		txtEnterUpdatedHash.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtEnterUpdatedHash.setText(null);
			}
			
		});
		
		txtEnterUpdatedHash.setText("Enter Hash Value...");
		txtEnterUpdatedHash.setColumns(10);
		txtEnterUpdatedHash.setBounds(392, 386, 157, 26);
		frame.getContentPane().add(txtEnterUpdatedHash);
		
		label_12 = new JLabel("...");
		label_12.setBounds(204, 424, 61, 16);
		frame.getContentPane().add(label_12);
		
		txtEnterPreviousHash = new JTextField();
		txtEnterPreviousHash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterPreviousHash.setText(null);
			}
		});
		txtEnterPreviousHash.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtEnterPreviousHash.setText(null);
			}
		});
		
		txtEnterPreviousHash.setText("Enter Previous Hash Value...");
		txtEnterPreviousHash.setBounds(204, 386, 187, 26);
		frame.getContentPane().add(txtEnterPreviousHash);
		txtEnterPreviousHash.setColumns(10);
		
		JButton btnClear = new JButton("Clear all");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(int i=1;i<10;i++)
				{
					lable[i].setVisible(false);
					lable[i].setText(null);
				}
				
				for(int j=i-1;j>0;j--)
				{
					System.out.println("j = "+j);
					System.out.println("size = "+blockChainList.size());
					blockChainList.remove(j);
					blockChainList2.remove(j);
				}
				i=1;
				System.out.println("i = "+i);
			}
		});
		btnClear.setBounds(25, 496, 157, 29);
		frame.getContentPane().add(btnClear);
		
		JButton btnNewButton = new JButton("Restore");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				restore();
			}
			
		});
		btnNewButton.setBounds(204, 496, 187, 29);
		frame.getContentPane().add(btnNewButton);
		
		btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{

				     //Serialization of Genesis Block;
				       FileOutputStream fout = new FileOutputStream("/Users/rahul/Downloads/SerializedBlocks/BlockChain: "+new Time(new Date().getTime())+".txt");
				       ObjectOutputStream out=new ObjectOutputStream(fout);
				       
				      // for(int i=0;i<blockChainList.size();i++)
				       out.writeObject(blockChainList);
				       out.flush();
				       out.close();
				       System.out.println("success");

				     }catch(Exception exp){System.out.println(exp);};
				     
			}
		});
		btnSave.setBounds(202, 456, 189, 29);
		frame.getContentPane().add(btnSave);
		

//Starting of lable  Mouse Evenets;
		
		   lable[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			
				lblNewLabel.setText(blockChainList.get(0).getHash()+"");
				label.setText(blockChainList.get(0).getPreviousHash()+"");
				label_1.setText(blockChainList.get(0).getTimestamp()+"");
		   }
		   });
		
			lable[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			
				lblNewLabel.setText(blockChainList.get(1).getHash()+"");
				label.setText(blockChainList.get(1).getPreviousHash()+"");
				label_1.setText(blockChainList.get(1).getTimestamp()+"");
		   }
		   });	
			lable[2].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
				
					lblNewLabel.setText(blockChainList.get(2).getHash()+"");
					label.setText(blockChainList.get(2).getPreviousHash()+"");
					label_1.setText(blockChainList.get(2).getTimestamp()+"");
			   }
			   });
			lable[3].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
				
					lblNewLabel.setText(blockChainList.get(3).getHash()+"");
					label.setText(blockChainList.get(3).getPreviousHash()+"");
					label_1.setText(blockChainList.get(3).getTimestamp()+"");
			   }
			   });
			lable[4].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
				
					lblNewLabel.setText(blockChainList.get(4).getHash()+"");
					label.setText(blockChainList.get(4).getPreviousHash()+"");
					label_1.setText(blockChainList.get(4).getTimestamp()+"");
			   }
			   });
			lable[5].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
				
					lblNewLabel.setText(blockChainList.get(5).getHash()+"");
					label.setText(blockChainList.get(5).getPreviousHash()+"");
					label_1.setText(blockChainList.get(5).getTimestamp()+"");
			   }
			   });
			lable[6].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
				
					lblNewLabel.setText(blockChainList.get(6).getHash()+"");
					label.setText(blockChainList.get(6).getPreviousHash()+"");
					label_1.setText(blockChainList.get(6).getTimestamp()+"");
			   }
			   });
			lable[7].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
				
					lblNewLabel.setText(blockChainList.get(7).getHash()+"");
					label.setText(blockChainList.get(7).getPreviousHash()+"");
					label_1.setText(blockChainList.get(7).getTimestamp()+"");
			   }
			   });
			lable[8].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
				
					lblNewLabel.setText(blockChainList.get(8).getHash()+"");
					label.setText(blockChainList.get(8).getPreviousHash()+"");
					label_1.setText(blockChainList.get(8).getTimestamp()+"");
			   }
			   });
			lable[9].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
				
					lblNewLabel.setText(blockChainList.get(9).getHash()+"");
					label.setText(blockChainList.get(9).getPreviousHash()+"");
					label_1.setText(blockChainList.get(9).getTimestamp()+"");
			   }
			   });
	/*		
		for(int i=0;i<10;i++)
		{
			
		//	lable[i].setIcon(new ImageIcon(img));
			lable[i].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			
				lblNewLabel.setText(blockChainList.get(i).getHash()+"");
				label.setText(blockChainList.get(i).getPreviousHash()+"");
				label_1.setText(blockChainList.get(i).getTimestamp()+"");
		   
		    	JLabel l = (JLabel)e.getComponent();
				String[] s = l.getText().split("\n");
				lblNewLabel.setText(s[0]);
				label.setText(s[1]);
				label_1.setText(s[2]);
			
				
			}
		   });
		}	
	*/
	}
	
	
	private static boolean validate(List<Block> blockChain) {
        boolean result = true;
        Block lastBlock = null;
        if(blockChain.get(0).getPreviousHash()!=0)
        	return false;
        for(int i = blockChain.size() -1; i >= 0; i--) {
            if(lastBlock == null) {
                lastBlock = blockChain.get(i);
            }
            else {
                Block current = blockChain.get(i);
                if(lastBlock.getPreviousHash() != current.getHash()) {
                    result = false;
                    break;
                }
                lastBlock = current;
            }
        }
        return result;
    }

	private void restore()
	{
		
		for(int i=0;i<blockChainList2.size();i++)
		{
			blockChainList.get(i).setHash(blockChainList2.get(i).getHash());
			blockChainList.get(i).setPreviousHash(blockChainList2.get(i).getPreviousHash());
			blockChainList.get(i).setTimestamp(blockChainList2.get(i).getTimestamp());
		}
	}
	
	private class LabelAdapter extends MouseAdapter {
	    @Override
	    public void mouseClicked(MouseEvent e) {
	    
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {
	    	//lblBlocksTimestamp.setText("hello");
	    	

	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
	    }
	}
}

