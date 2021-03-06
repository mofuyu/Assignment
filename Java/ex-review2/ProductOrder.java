     import java.util.*;
     import java.awt.*;
     import java.awt.event.*;
     import javax.swing.*;
     import javax.swing.event.*;

    class SortableDataStore  implements Comparable<SortableDataStore> { 
       protected String productItem, noItem, orderInfo;
       public SortableDataStore() {
       }
       public SortableDataStore(String p, String n, String o) {
	   productItem=p;
	   noItem=n;
	   orderInfo=o;
       }
       public String toString() {
        // fill in the area
         return new String(productItem + ", " + noItem + ", " + orderInfo+"\n") ;
       }

        // fill in the area to implement Comparable interface
	public int compareTo(SortableDataStore o){
	    int res;
	    res=productItem.compareTo(o.productItem);
        return res==0 ? noItem.compareTo(o.noItem):res;
	}
	
    } // end of DataStore

/******
   This provides only the sample code for Java GUI shape.
   You should fill in all the code for envent handling and other operation.
*****/
     public class ProductOrder extends JFrame {
  	protected String list_Contents, noOrder, orderInfo;
	protected SortableDataStore[] v = new SortableDataStore[20];

        protected JPanel topp, leftp, rightp,bottomp,centerup, centerdown;
        protected JButton putb, showb, sortb, clearb;
        protected JLabel title, noorder, orderinfo;
        protected JList plist;
        protected JTextField nofield, infofield;
        protected JTextArea contentsarea;
        protected String[] data = {"IBM NoteBook", "Dell Product", "MS Windows", "Sun Workstation", "Oracle DB", "MySQL"};
        protected int idx = 0;
        protected int lastIndex = -1;

       public ProductOrder() {
        getContentPane().setLayout(new BorderLayout());
        topp = new JPanel();
        leftp = new JPanel();
        rightp = new JPanel();
        bottomp = new JPanel();
        centerup = new JPanel();
        centerdown = new JPanel();

        getContentPane().add(topp, "North");
        getContentPane().add(leftp, "West");
        getContentPane().add(rightp, "Center");
        getContentPane().add(bottomp, "South");

        title = new JLabel("Product Order");
        title.setFont(new Font("Product Order",Font.BOLD, 20));
        topp.add(title);

        plist = new JList(data);
        plist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        plist.addListSelectionListener(new ProductItemListener());
        leftp.add(plist);

   	noorder = new JLabel("No. of Order Items");
   	orderinfo = new JLabel("Order Information");
	nofield = new JTextField();
	infofield = new JTextField();

	putb = new JButton("Put into Cart");
	putb.addActionListener(new PutButtonListener());
	sortb = new JButton("Sort Items");
	sortb.addActionListener(new SortButtonListener());
	clearb = new JButton("Reset Cart");
    clearb.addActionListener(new ResetButtonListener());

	centerup.setLayout(new GridLayout(2,2));
	centerup.add(noorder);
	centerup.add(orderinfo);
	centerup.add(nofield);
	centerup.add(infofield);
	centerdown.setLayout(new GridLayout(1,2));
	centerdown.add(putb);
	centerdown.add(sortb);
	centerdown.add(clearb);

	rightp.setLayout(new GridLayout(2,1));
	rightp.add(centerup);
	rightp.add(centerdown);

	showb = new JButton("Show Cart");
    showb.addActionListener(new ShowButtonListener());


	contentsarea = new JTextArea(10,1);
	bottomp.setLayout(new GridLayout(1,2));
	bottomp.add(showb);
	bottomp.add(contentsarea);

      }  // Product Order constructor



/*****
Fill in code for event listeners.
You can use inner classes for the listeners.
*****/

    class ProductItemListener implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent e){
            list_Contents = (String)plist.getSelectedValue();
        }
    }

	 class PutButtonListener implements ActionListener{
         public void actionPerformed(ActionEvent e){
             if(e.getSource()==putb){
            if(idx<20) {
                v[idx]=new SortableDataStore(list_Contents,nofield.getText(),infofield.getText());
                idx++;
                }
             }
        }
	 }

	  class SortButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==sortb){
			Arrays.sort(v,0,idx);
            }
        }
	 }

	  class ResetButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==clearb){
            v = null;
            v = new SortableDataStore[20];
            }
        }
	 }

	  class ShowButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==showb){
            for(SortableDataStore data:v){
                if(data!=null)contentsarea.append(data.toString());;
                }
            }
        }
    }


   public static void main (String args[]) {
            ProductOrder f = new ProductOrder();
            f.setTitle("Product Order");
            f.setSize(500,400);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
        }
   
    } // end of ProductOrder
   