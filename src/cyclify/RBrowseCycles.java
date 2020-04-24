/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyclify;
import cyclify.*;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.sql.*;
import java.util.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
/**
 *
 * @author prana
 */



public class RBrowseCycles extends javax.swing.JFrame {
    java.util.Date date1;
    ResultSet rs;
    static long OID;
    /**
     * Creates new form RBrowseCycles
     */
    public RBrowseCycles(Long a) {
        initComponents();
        OID=a;
    }
    
    
    
  
    
    
    public ArrayList<Cycle> cycleList() throws SQLException
    {
        ArrayList<Cycle> cycleList = new ArrayList<>();
        
        Cycle c1;
        while(rs.next())
        {
            c1= new Cycle(rs.getString("cycleid"),rs.getString("make"),rs.getString("colour"),rs.getInt("monthofpurchase"),rs.getInt("gearednotgeared"),rs.getInt("rate"));
            cycleList.add(c1);
        }
        
        return cycleList;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    class ButtonRenderer extends JButton implements  TableCellRenderer
{

	//CONSTRUCTOR
	public ButtonRenderer() {
		//SET BUTTON PROPERTIES
		setOpaque(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj,
			boolean selected, boolean focused, int row, int col) {
		
		//SET PASSED OBJECT AS BUTTON TEXT
			//setText((obj==null) ? "":obj.toString());
                        setText("Click here");
                        
				
		return this;
	}
	
}
    
    
class ButtonEditor extends DefaultCellEditor
{
	protected JButton btn;
	 private String lbl;
	 private Boolean clicked;
	 
	 public ButtonEditor(JTextField txt) {
		super(txt);
		
		btn=new JButton();
		btn.setOpaque(true);
		
		//WHEN BUTTON IS CLICKED
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				fireEditingStopped();
			}
		});
	}
	 
	 //OVERRIDE A COUPLE OF METHODS
	 @Override
	public Component getTableCellEditorComponent(JTable table, Object obj,
			boolean selected, int row, int col) {

			//SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
		 
                 //table.getModel().getValueAt(row, col);
                 //System.out.println(row +" " +col);
                 //System.out.println(table.getModel().getValueAt(row, 1));
                 String name = null;
                 Long phno = null;
                 String ownid = null;
                 String cid=table.getValueAt(row, 0).toString();
                 System.out.println(cid);
                 
                 
                 
             try{
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost", "system", "prajant");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cycles where cycleid='" + cid +  "'");

            while (rs.next())
            {	
                ownid=(rs.getString("ownerid"));
                
                System.out.println(ownid);
            }
            rs = stmt.executeQuery("SELECT * FROM owners where regno= " + ownid );

            while (rs.next())
            {	

                phno=rs.getLong("phoneno");
                name=(rs.getString("name"));
            }
            
            
            //System.out.println(ownid + " " + name + " " + phno);
            
            con.close();

        }
        catch (Exception ex) 
        {
            System.out.println(ex);			
        }
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 //lbl=(obj==null) ? "":obj.toString();
                 lbl=("Owner's name is " + name + ". Owner's phone number is " + phno + ".");

		 btn.setText(lbl);
		 clicked=true;
		return btn;
	}
	 
	//IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
	 @Override
	public Object getCellEditorValue() {

		 if(clicked)
			{
			//SHOW US SOME MESSAGE
                                
				JOptionPane.showMessageDialog(btn, lbl,"Owner Details",JOptionPane.INFORMATION_MESSAGE);
                                //JOptionPane.showMessageDialog(null, "You won the game in 7 tries!", "my title", JOptionPane.INFORMATION_MESSAGE);

			}
		//SET IT TO FALSE NOW THAT ITS CLICKED
		clicked=false;
	  return new String(lbl);
	}
	
	 @Override
	public boolean stopCellEditing() {

	       //SET CLICKED TO FALSE FIRST
			clicked=false;
		return super.stopCellEditing();
	}
	 
	 @Override
	protected void fireEditingStopped() {
		// TODO Auto-generated method stub
		super.fireEditingStopped();
	}
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void showCycle() throws SQLException
    {
        
        table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JTextField()));
        
        
        ArrayList<Cycle> list = cycleList();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        
        Object[] row = new Object[6];
        for(int i=0;i<list.size();i++)
        {
            row[0]=list.get(i).getcycleid();
            row[1]=list.get(i).getmake();
            row[2]=list.get(i).getcolour();
      
            int mop=list.get(i).getmop();
            
            String m1;
            
            switch(      mop/100              )
            {   
                case 1:
                    m1="January " + (mop%100);
                    row[3]=m1;
                    
                    
                    break;
                    
                case 2:
                    m1="February " + (mop%100);
                    row[3]=m1;
                    
                    break;
                case 3:
                    m1="March " + (mop%100);
                    row[3]=m1;
                    
                    break;
                case 4:
                    m1="April " + (mop%100);
                    row[3]=m1;
                    
                    break;
                case 5:
                    m1="May " + (mop%100);
                    row[3]=m1;
                    
                    break;
                case 6:
                    m1="June " + (mop%100);
                    row[3]=m1;
                    
                    break;
                case 7:
                    m1="July " + (mop%100);
                    row[3]=m1;
                    
                    break;

                    
                case 8:
                    m1="August " + (mop%100);
                    row[3]=m1;
                    
                    
                    break;
                case 9:
                    m1="September " + (mop%100);
                    row[3]=m1;
                    
                    break;
                case 10:
                    m1="October " + (mop%100);
                    row[3]=m1;
                    
                    break;
                case 11:
                    m1="November " + (mop%100);
                    row[3]=m1;
                    
                    
                    break;
                case 12:
                    m1="December " + (mop%100);
                    row[3]=m1;
                    
                    break;

                
            }
            
            
            
            if(list.get(i).getgearsystem()==1)
                row[4]="Geared";
            else
                row[4]="Non Geared";
            
            row[5]=list.get(i).getrate();
            

            model.addRow(row);
        }
    
    
    
    
    }

                                  

         




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pickdate = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        submitbutton = new javax.swing.JButton();
        backbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel1.setText("Choose the date on which you would like to rent a cycle :");

        pickdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickdateActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cycle ID", "Make", "Colour", "Month of Purchase", "Gear system", "Rate per hour", "Owner Details"
            }
        ));
        jScrollPane1.setViewportView(table);

        submitbutton.setText("Submit");
        submitbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitbuttonActionPerformed(evt);
            }
        });

        backbutton.setText("Back");
        backbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pickdate, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(418, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(submitbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(pickdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitbutton)
                    .addComponent(backbutton))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pickdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickdateActionPerformed
        
        
    }//GEN-LAST:event_pickdateActionPerformed

    private void submitbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitbuttonActionPerformed
        // TODO add your handling code here:
        date1= pickdate.getDate();
        //System.out.println(date1);
        
       
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        //month is from 0 to 11 so
        month++;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        
        int DAT;
        DAT=year-2000;
        DAT=DAT*100;
        DAT=DAT+month;
        DAT=DAT*100;
        DAT=DAT+day;
        
        
        //System.out.println(year + " " + month + " " + day);
        //System.out.println(DAT);
        
        
     try{
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost", "system", "prajant");
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM available natural join cycles where starttid like '" + DAT + "%'" );

            showCycle();
            
            
            con.close();

        }
        catch (Exception ex) 
        {
            System.out.println(ex);			
        }
        
        
        
        
        
        
        
    }//GEN-LAST:event_submitbuttonActionPerformed

    private void backbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbuttonActionPerformed
    Renter1 ob = new Renter1(OID);
    ob.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_backbuttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RBrowseCycles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RBrowseCycles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RBrowseCycles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RBrowseCycles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RBrowseCycles(OID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker pickdate;
    private javax.swing.JButton submitbutton;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
