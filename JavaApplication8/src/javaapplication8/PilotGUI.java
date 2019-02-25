import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;
import javax.swing.border.LineBorder;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
//import javax.swing.text.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.Calendar;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class PilotGUI extends JFrame implements ActionListener, FocusListener, DocumentListener
{
    private Container contentPane;
    private CardLayout card;
    private int r = 62;
    private int g = 91;
    private int b = 121;
    final String html1 = "<html><center><body style='width:";
    final String html2 = "px'>";
    private String myDir = System.getProperty("user.dir");
    private String[] names = {"Close", "Pilots", "Maintenance", "Customers", "Inventory"};
    private JButton[] listButtons;
    private String strDate;

    private JPanel[] centerP;
    private JPanel[] titlePanel;
    private JPanel[] upCenter;
    private JPanel[] midCenter;
    private JPanel[] lowCenter;
    private JLabel[] title;
    private JLabel[] cert;
    private JLabel[] extdate;
    private JLabel[] sal;
    private JLabel[] hrs;
    private JLabel[] planeUsed;
    private JTextField[] cert1;
    private JTextField[] extdate1;
    private JTextField[] sal1;
    private JTextField[] hrs1;
    private JCheckBox[] checkbox;
    private JLabel[] space;
    private int docLength5;

    private JPanel custPanel;
    private JPanel[] custP;
    private JPanel[] centerCust;
    private JLabel[] titleCust;
    private JLabel[] flightHours;
    private JTextField[] flightHours1;
    private JLabel[] custBill;
    private JLabel[] custBill1;
    private JPanel[] midCenterCust;
    private JCheckBox[] checkbox2;
    private JButton updateCusts;
    private JLabel[] space2;

    private JPanel maintanencePanel;
    private JPanel mainPanel;
    private JPanel[] centerMain;
    private JLabel[] titleMain;
    private JLabel[] flightHours2;
    private JLabel[] mainBill;
    private JLabel[] mainBuild;
    private JLabel[] flightHours21;
    private JLabel[] mainBill1;
    private JLabel[] mainBuild1;

    private JPanel[] mainP;
    private JPanel[] midCenterMain;
    private JCheckBox[] checkbox3;
    private JButton updateMains;
    private JLabel[] space3;

    private JPanel invPanel;
    private JLabel invLabel;
    private JLabel quanLabel;
    private JLabel priceLabel;
    private JLabel costLabel;

    private Document doc;
    private DocumentBuilderFactory dbFactory1;
    private DocumentBuilder dBuilder1;
    private Document doc1;
    private NodeList nList;
    private NodeList nList1;
    private Element eElement;
    private Document doc2;
    private DocumentBuilderFactory dbFactory2;
    private DocumentBuilder dBuilder2;
    private NodeList nList2;
    private Element eElement2;
    private int docLength2;

    private Document doc3;
    private DocumentBuilderFactory dbFactory3;
    private DocumentBuilder dBuilder3;
    private NodeList nList3;
    private Element eElement3;

    private Document doc4;
    private DocumentBuilderFactory dbFactory4;
    private DocumentBuilder dBuilder4;
    private NodeList nList4;
    private Element eElement4;
    private File xmlFile5;
    private DocumentBuilderFactory dbFactory5;
    private DocumentBuilder dBuilder5;
    private Document doc5;
    private NodeList nList5;
    private Node nNode5;

    private String day;
    private JPanel pilotPanel;
    private JPanel center;
    private JButton updatePilots;
    private int docLength;
    private int num;
    private String myHours;
    private String mySalary;
    private int dayOfWeek;
    private JLabel myDate;
    private String custname;
    private JComboBox[] pilotPlane;
    private JComboBox[] custPlane;
    private JComboBox[] custPilot;
    private String[] planesArray = {"Cesna1", "Cesna2", "Cesna3"};
    private JLabel[] custplaneUsed;
    private JLabel[] pilotFlown;
    private String[] thePilots;
    private Object owner;

    private String pilotName;
    private String pilotSalary;
    private String pilotHours;
    private String thisThread;


    public PilotGUI (String title)
    {
        buildGUI();
        setUndecorated(true);
        setAlwaysOnTop(true);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
    }
    private void buildGUI()
    {
        contentPane = getContentPane();
        buildNorthPanel();
        buildWestPanel();
        buildCenterPanel();
    }
    private void buildNorthPanel()
    {
        JPanel datePanel;

        datePanel = new JPanel();
        datePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        datePanel.setBackground(new Color(r, g, b));
        Date today = new Date();
        //Date format
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        strDate = df.format(today);
        // Day of week
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1)
        { day = "Sunday"; }
        if (dayOfWeek == 2)
        { day = "Monday"; }
        if (dayOfWeek == 3)
        { day = "Tuesday"; }
        if (dayOfWeek == 4)
        { day = "Wednesday"; }
        if (dayOfWeek == 5)
        { day = "Thursday"; }
        if (dayOfWeek == 6)
        { day = "Friday"; }
        if (dayOfWeek == 7)
        { day = "Saturday"; }


        //myDate = new JLabel();
        //myDate.setFont(new Font("Serif", Font.BOLD, 20));
        //myDate.setAlignmentX(Component.RIGHT_ALIGNMENT);
        //myDate.setForeground(Color.WHITE);
        //myDate.setText(day + "     " + strDate);
        String [] chooseDate = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        JComboBox myDate = new JComboBox<>(chooseDate);
        myDate.setSelectedItem(day);
        myDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                day =  (String) myDate.getSelectedItem();
            }
        });
        datePanel.add(myDate);

        contentPane.add("North", datePanel);
    }

    private void buildWestPanel()
    {
        JPanel leftPanel;
        JPanel listPanel;

        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(r, g, b));

        listPanel = new JPanel();
        listPanel.setBackground(new Color(r, g, b));
        GridLayout sideButtonLayout = new GridLayout(6,0);
        listPanel.setLayout(sideButtonLayout);
        listButtons = new JButton[names.length];
        for(int i=0; i<names.length; i++)
        {
            final int curRow = i;
            listButtons[i] = new JButton(names[i]);
            listButtons[i].setBackground(new Color(r, g, b));
            listButtons[i].setForeground(Color.WHITE);
            listButtons[i].setFont(new Font("Serif", Font.BOLD, 25));
            listButtons[i].setBorder(new LineBorder(new Color(r, g, b)));

            listButtons[i].setFocusPainted(false);
            listButtons[i].setMargin(new Insets(0, 0, 0, 0));

            listButtons[i].addActionListener(this);
            listButtons[i].addFocusListener(this);

            listPanel.add(listButtons[i]);
        }
        leftPanel.add(listPanel);
        contentPane.add("West", leftPanel);
    }
    private void buildCenterPanel()
    {
        card = new CardLayout(40,30);
        center = new JPanel();
        center.setBackground(Color.BLACK);
        center.setLayout(card);
        // close card
        JPanel closePanel = new JPanel();
        ImageIcon image = new ImageIcon(myDir + "/logo.png");
        Image scaled = scaleImage(image.getImage(), 985, 650);
        ImageIcon reimage = new ImageIcon(scaled);
        JLabel closeLabel = new JLabel(reimage);
        closePanel.add(closeLabel);
        // end close card

        // Start pilot card

        try {
            File xmlFile = new File(myDir + "/Pilots.xml");
            dbFactory1 = DocumentBuilderFactory.newInstance();
            dBuilder1 = dbFactory1.newDocumentBuilder();
            doc1 = dBuilder1.parse(xmlFile);
            nList = doc1.getElementsByTagName("name");
            docLength = nList.getLength();
        }
        catch (Exception ex)
        {
            System.out.println("cant open");
        }

        pilotPanel = new JPanel();
        pilotPanel.setBackground(Color.BLACK);
        pilotPanel.setLayout(new BoxLayout(pilotPanel, BoxLayout.Y_AXIS));

        centerP = new JPanel[docLength];
        upCenter = new JPanel[docLength];
        midCenter = new JPanel[docLength];
        lowCenter = new JPanel[docLength];
        title = new JLabel[docLength];
        cert = new JLabel[docLength];
        cert1 = new JTextField[docLength];
        extdate = new JLabel[docLength];
        extdate1 = new JTextField[docLength];
        sal = new JLabel[docLength];
        sal1 = new JTextField[docLength];
        hrs = new JLabel[docLength];
        hrs1 = new JTextField[docLength];
        checkbox = new JCheckBox[docLength];
        space = new JLabel[docLength];
        planeUsed = new JLabel[docLength];
        pilotPlane = new JComboBox[docLength];
        thePilots = new String[docLength];


        // button to write changes to Pilots.xml
        updatePilots = new JButton("Update Reports");
        updatePilots.setBackground(Color.WHITE);
        updatePilots.setFont(new Font("Serif", Font.BOLD, 20));
        updatePilots.setAlignmentX(Component.CENTER_ALIGNMENT);
        updatePilots.addActionListener(this);

        for (int i = 0;i<docLength;i++)
        {
            Node nNode = nList.item(i);
            eElement = (Element) nNode;
            String myname = eElement.getAttribute("id");
            String myCert = eElement.getElementsByTagName("certificate").item(0).getTextContent();
            String exp = eElement.getElementsByTagName("expiration-date").item(0).getTextContent();
            mySalary = eElement.getElementsByTagName("salary").item(0).getTextContent();
            myHours = eElement.getElementsByTagName("hours").item(0).getTextContent();

            centerP[i] = new JPanel();
            centerP[i].setBackground(Color.BLACK);

            title[i] = new JLabel();
            title[i].setForeground(Color.WHITE);
            title[i].setFont(new Font("Serif", Font.BOLD, 25));
            title[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            title[i].setText(myname);
            thePilots[i] = myname;

            upCenter[i] = new JPanel();
            upCenter[i].setBackground(Color.DARK_GRAY);
            upCenter[i].setAlignmentX(Component.CENTER_ALIGNMENT);

            midCenter[i] = new JPanel();
            midCenter[i].setBackground(Color.DARK_GRAY);
            midCenter[i].setAlignmentX(Component.CENTER_ALIGNMENT);

            cert[i] = new JLabel();
            cert[i].setForeground(Color.WHITE);
            cert[i].setFont(new Font("serif", Font.BOLD + Font.ITALIC, 20));
            cert[i].setText("Certificates: ");

            cert1[i] = new JTextField("", 5);
            cert1[i].setFont(new Font("Serif", Font.PLAIN, 20));
            cert1[i].setText(myCert);

            extdate[i] = new JLabel();
            extdate[i].setForeground(Color.WHITE);
            extdate[i].setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
            extdate[i].setText("     Expiration Date: ");

            extdate1[i] = new JTextField("", 5);
            extdate1[i].setFont(new Font("Serif", Font.PLAIN, 20));
            extdate1[i].setText(exp);

            sal[i] = new JLabel();
            sal[i].setForeground(Color.WHITE);
            sal[i].setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
            sal[i].setText("     Wage Rate: $");

            sal1[i] = new JTextField("", 5);
            sal1[i].setFont(new Font("Serif", Font.PLAIN, 20));
            sal1[i].setText(mySalary);

            hrs[i] = new JLabel();
            hrs[i].setForeground(Color.WHITE);
            hrs[i].setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
            hrs[i].setText("     Flight Hours: ");

            hrs1[i] = new JTextField("", 5);
            hrs1[i].setFont(new Font("Serif", Font.PLAIN, 20));
            //hrs1[i].setText(myHours);
            hrs1[i].setText("");

            pilotPlane[i] = new JComboBox<>(planesArray);
            pilotPlane[i].setSelectedIndex(0);
            pilotPlane[i].addActionListener(this);

            planeUsed[i] = new JLabel();
            planeUsed[i].setForeground(Color.WHITE);
            planeUsed[i].setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
            planeUsed[i].setText("     Plane Used");

            checkbox[i] = new JCheckBox();
            checkbox[i].setBackground(Color.DARK_GRAY);
            checkbox[i].addActionListener(this);

            // Spacer for on top of button
            space[i] = new JLabel(" ");
            space[i].setBackground(Color.DARK_GRAY);
            space[i].setFont(new Font("Serif", Font.BOLD, 20));

            upCenter[i].add(cert[i]);
            upCenter[i].add(cert1[i]);

            upCenter[i].add(extdate[i]);
            upCenter[i].add(extdate1[i]);

            upCenter[i].add(sal[i]);
            upCenter[i].add(sal1[i]);

            upCenter[i].add(hrs[i]);
            upCenter[i].add(hrs1[i]);
            upCenter[i].add(planeUsed[i]);
            upCenter[i].add(pilotPlane[i]);
            upCenter[i].add(checkbox[i]);
            midCenter[i].add(upCenter[i]);

            centerP[i].add(title[i]);
            centerP[i].add(midCenter[i]);
            centerP[i].add(space[i]);
            //centerP[i].add(pilotPlane[i]);

            pilotPanel.add(centerP[i]);
        }
        pilotPanel.add(updatePilots);

        /// End pilot card
        /// Start Maintanence card

        try {
            File xmlFile3 = new File(myDir + "/Planes.xml"); //maintainence
            dbFactory3 = DocumentBuilderFactory.newInstance();
            dBuilder3 = dbFactory3.newDocumentBuilder();
            doc3 = dBuilder3.parse(xmlFile3);
            nList3 = doc3.getElementsByTagName("name"); // plane
            //docLength3 = nList3.getLength();
        }
        catch (Exception ex)
        {
            System.out.println("cant open");
        }

        maintanencePanel = new JPanel();
        maintanencePanel.setBackground(Color.BLACK);
        maintanencePanel.setLayout(new BoxLayout(maintanencePanel, BoxLayout.Y_AXIS));

        centerMain = new JPanel[docLength];
        mainP = new JPanel[docLength];
        midCenterMain = new JPanel[docLength];
        titleMain = new JLabel[docLength];
        flightHours2 = new JLabel[docLength];
        mainBill = new JLabel[docLength];
        mainBuild = new JLabel[docLength];
        flightHours21 = new JLabel[docLength];
        mainBill1 = new JLabel[docLength];
        mainBuild1 = new JLabel[docLength];
        checkbox3 = new JCheckBox[docLength];
        space3 = new JLabel[docLength];

        // button to write changes to Pilot.xml
        updateMains = new JButton("Update Reports");
        updateMains.setBackground(Color.WHITE);
        updateMains.setFont(new Font("Serif", Font.BOLD, 20));
        updateMains.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateMains.addActionListener(this);

        for (int i = 0;i<docLength;i++)
        {
            Node nNode3 = nList3.item(i);
            eElement3 = (Element) nNode3;
            String mainname = eElement3.getAttribute("id");
            String flh = eElement3.getElementsByTagName("flight-hours").item(0).getTextContent();
            String fhm = eElement3.getElementsByTagName("fhm").item(0).getTextContent();
            String rbld = eElement3.getElementsByTagName("rebuild").item(0).getTextContent();
            String nm = eElement3.getElementsByTagName("next-maint").item(0).getTextContent();
           
            if (Integer.parseInt(flh) > Integer.parseInt(nm))
            {
               System.out.println("Time for maintenance  flh: " + flh + "  nm: " + nm);
            }
            else
            {
               System.out.println("Not time for maint  flh: " + flh + "  nm: " + nm);
            }

            centerMain[i] = new JPanel();
            centerMain[i].setBackground(Color.BLACK);
            centerMain[i].setLayout(new BoxLayout(centerMain[i], BoxLayout.Y_AXIS));

            mainP[i] = new JPanel();
            mainP[i].setBackground(Color.BLACK);

            titleMain[i] = new JLabel();
            titleMain[i].setForeground(Color.WHITE);
            titleMain[i].setFont(new Font("Serif", Font.BOLD, 25));
            titleMain[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            titleMain[i].setText(mainname);

            midCenterMain[i] = new JPanel();
            midCenterMain[i].setBackground(Color.DARK_GRAY);
            //midCenterMain[i].setLayout(new BoxLayout(midCenterMain[i], BoxLayout.X_AXIS));
            //midCenterMain[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            //midCenterMain[i].add(Box.createRigidArea(new Dimension(5, 0)));

            flightHours2[i] = new JLabel();
            flightHours2[i].setForeground(Color.WHITE);
            flightHours2[i].setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
            flightHours2[i].setText("     Flight Hours: ");

            flightHours21[i] = new JLabel();
            flightHours21[i].setForeground(Color.WHITE);
            flightHours21[i].setFont(new Font("Serif", Font.PLAIN, 20));
            flightHours21[i].setText(flh);

            mainBill[i] = new JLabel();
            mainBill[i].setForeground(Color.WHITE);
            mainBill[i].setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
            mainBill[i].setText("     Hours till maintainence: ");

            mainBill1[i] = new JLabel();
            mainBill1[i].setForeground(Color.WHITE);
            mainBill1[i].setFont(new Font("Serif", Font.PLAIN, 20));
            mainBill1[i].setText(fhm);

            mainBuild[i] = new JLabel();
            mainBuild[i].setForeground(Color.WHITE);
            mainBuild[i].setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
            mainBuild[i].setText("     Hours till engine rebuild: ");

            mainBuild1[i] = new JLabel();
            mainBuild1[i].setForeground(Color.WHITE);
            mainBuild1[i].setFont(new Font("Serif", Font.PLAIN, 20));
            mainBuild1[i].setText(rbld);

            checkbox3[i] = new JCheckBox();
            checkbox3[i].setBackground(Color.DARK_GRAY);
            checkbox3[i].addActionListener(this);

            // Spacer for on top of button
            space3[i] = new JLabel(" ");
            space3[i].setBackground(Color.DARK_GRAY);
            space3[i].setFont(new Font("Serif", Font.BOLD, 20));

            midCenterMain[i].add(flightHours2[i]);
            midCenterMain[i].add(flightHours21[i]);
            midCenterMain[i].add(mainBill[i]);
            midCenterMain[i].add(mainBill1[i]);
            midCenterMain[i].add(mainBuild[i]);
            midCenterMain[i].add(mainBuild1[i]);
            midCenterMain[i].add(checkbox3[i]);
            centerMain[i].add(titleMain[i]);
            centerMain[i].add(midCenterMain[i]);
            //centerMain[i].add(space3[i]);
            mainP[i].add(centerMain[i]);

            maintanencePanel.add(mainP[i]);
        }
        maintanencePanel.add(updateMains);

        // end maintanence card
        // start customer card
        try {
            File xmlFile2 = new File(myDir + "/customers.xml");
            dbFactory2 = DocumentBuilderFactory.newInstance();
            dBuilder2 = dbFactory2.newDocumentBuilder();
            doc2 = dBuilder2.parse(xmlFile2);
            nList2 = doc2.getElementsByTagName("name");
            docLength2 = nList2.getLength();
        }
        catch (Exception ex)
        {
            System.out.println("cant open");
        }

        custPanel = new JPanel();
        custPanel.setBackground(Color.BLACK);
        custPanel.setLayout(new BoxLayout(custPanel, BoxLayout.Y_AXIS));

        centerCust = new JPanel[docLength];
        custP = new JPanel[docLength];
        midCenterCust = new JPanel[docLength];
        titleCust = new JLabel[docLength];
        flightHours = new JLabel[docLength];
        flightHours1 = new JTextField[docLength];
        custBill = new JLabel[docLength];
        custBill1 = new JLabel[docLength];
        checkbox2 = new JCheckBox[docLength];
        space2 = new JLabel[docLength];
        custplaneUsed = new JLabel[docLength];
        custPlane = new JComboBox[docLength];
        pilotFlown = new JLabel[docLength];
        custPilot = new JComboBox[thePilots.length];

        // button to write changes to customers.xml
        updateCusts = new JButton("Update Reports");
        updateCusts.setBackground(Color.WHITE);
        updateCusts.setFont(new Font("Serif", Font.BOLD, 20));
        updateCusts.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateCusts.addActionListener(this);

        for (int i = 0;i<docLength;i++)
        {
            Node nNode2 = nList2.item(i);
            eElement2 = (Element) nNode2;
            custname = eElement2.getAttribute("id");
            String fh = eElement2.getElementsByTagName("flight-hours").item(0).getTextContent();
            String bill = eElement2.getElementsByTagName("bill").item(0).getTextContent();

            centerCust[i] = new JPanel();
            centerCust[i].setBackground(Color.BLACK);
            centerCust[i].setLayout(new BoxLayout(centerCust[i], BoxLayout.Y_AXIS));

            custP[i] = new JPanel();
            custP[i].setBackground(Color.BLACK);
            //custP[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            //custP[i].setAlignmentY(Component.CENTER_ALIGNMENT);

            titleCust[i] = new JLabel();
            titleCust[i].setForeground(Color.WHITE);
            titleCust[i].setFont(new Font("Serif", Font.BOLD, 25));
            titleCust[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            titleCust[i].setAlignmentY(Component.CENTER_ALIGNMENT);
            titleCust[i].setText(custname);

            midCenterCust[i] = new JPanel();
            midCenterCust[i].setBackground(Color.DARK_GRAY);
            //midCenterCust[i].setLayout(new BoxLayout(midCenterCust[i], BoxLayout.X_AXIS));
            //midCenterCust[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            //midCenterCust[i].add(Box.createRigidArea(new Dimension(5, 0)));

            flightHours[i] = new JLabel();
            flightHours[i].setForeground(Color.WHITE);
            flightHours[i].setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
            flightHours[i].setText("     Flight Hours: ");


            flightHours1[i] = new JTextField("", 5);
            flightHours1[i].setFont(new Font("Serif", Font.PLAIN, 20));
            flightHours1[i].setText(fh);
            int fltHours = Integer.parseInt(fh);

            custBill1[i] = new JLabel();
            custBill1[i].setForeground(Color.WHITE);
            custBill1[i].setFont(new Font("Serif", Font.PLAIN, 20));
            custBill1[i].setText(bill);

            flightHours1[i].getDocument().putProperty("owner", flightHours1[i]);
            flightHours1[i].getDocument().addDocumentListener(this);

            custBill[i] = new JLabel();
            custBill[i].setForeground(Color.WHITE);
            custBill[i].setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
            custBill[i].setText("     Bill: ");

            custPlane[i] = new JComboBox<>(planesArray);
            custPlane[i].setSelectedIndex(0);
            custPlane[i].addActionListener(this);

            custplaneUsed[i] = new JLabel();
            custplaneUsed[i].setForeground(Color.WHITE);
            custplaneUsed[i].setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
            custplaneUsed[i].setText("     Plane Used");

            custPilot[i] = new JComboBox<>(thePilots);
            custPilot[i].setSelectedIndex(0);
            custPilot[i].addActionListener(this);

            pilotFlown[i] = new JLabel();
            pilotFlown[i].setForeground(Color.WHITE);
            pilotFlown[i].setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
            pilotFlown[i].setText("     Pilot Flown With");


            checkbox2[i] = new JCheckBox();
            checkbox2[i].setBackground(Color.DARK_GRAY);
            checkbox2[i].addActionListener(this);

            // Spacer for on top of button
            space2[i] = new JLabel(" ");
            space2[i].setBackground(Color.DARK_GRAY);
            space2[i].setFont(new Font("Serif", Font.BOLD, 20));


            midCenterCust[i].add(flightHours[i]);
            midCenterCust[i].add(flightHours1[i]);
            midCenterCust[i].add(custBill[i]);
            midCenterCust[i].add(custBill1[i]);
            midCenterCust[i].add(custplaneUsed[i]);
            midCenterCust[i].add(custPlane[i]);
            midCenterCust[i].add(pilotFlown[i]);
            midCenterCust[i].add(custPilot[i]);

            midCenterCust[i].add(checkbox2[i]);
            centerCust[i].add(titleCust[i]);
            custP[i].add(titleCust[i]);
            centerCust[i].add(midCenterCust[i]);
            centerCust[i].add(space2[i]);
            custP[i].add(centerCust[i]);
            custPanel.add(custP[i]);

        }
        custPanel.add(updateCusts);
        // end customer card

        // start inventory card
        try {
            File xmlFile4 = new File(myDir + "/inventory.xml");
            dbFactory4 = DocumentBuilderFactory.newInstance();
            dBuilder4 = dbFactory1.newDocumentBuilder();
            doc4 = dBuilder1.parse(xmlFile4);
            nList4 = doc4.getElementsByTagName("part");
            //docLength4 = nList.getLength();
        }
        catch (Exception ex)
        {
            System.out.println("cant open");
        }
        Node nNode4 = nList4.item(0);
        eElement4 = (Element) nNode4;
        String partname = eElement4.getElementsByTagName("name").item(0).getTextContent();
        String quan = eElement4.getElementsByTagName("quantity").item(0).getTextContent();
        String price = eElement4.getElementsByTagName("price").item(0).getTextContent();
        String cost = eElement4.getElementsByTagName("cost").item(0).getTextContent();

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setBackground(Color.BLACK);
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));

        JLabel invLabel = new JLabel();
        invLabel.setForeground(Color.WHITE);
        invLabel.setFont(new Font("Serif", Font.BOLD, 25));
        invLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        invLabel.setText(partname);

        invPanel = new JPanel();
        invPanel.setBackground(Color.DARK_GRAY);
        invPanel.setLayout(new BoxLayout(invPanel, BoxLayout.X_AXIS));
        invPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        invPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        JPanel invP = new JPanel();
        invP.setBackground(Color.BLACK);

        JLabel quanLabel = new JLabel();
        quanLabel.setForeground(Color.WHITE);
        quanLabel.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
        quanLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quanLabel.setText("     Quantitly: ");

        JLabel quanLabel1 = new JLabel();
        quanLabel1.setForeground(Color.WHITE);
        quanLabel1.setFont(new Font("Serif", Font.BOLD, 20));
        quanLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        quanLabel1.setText(quan);

        JLabel priceLabel = new JLabel();
        priceLabel.setForeground(Color.WHITE);
        priceLabel.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel.setText("     Price per Piece: ");

        JLabel priceLabel1 = new JLabel();
        priceLabel1.setForeground(Color.WHITE);
        priceLabel1.setFont(new Font("Serif", Font.BOLD, 20));
        priceLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel1.setText(price);

        JLabel costLabel = new JLabel();
        costLabel.setForeground(Color.WHITE);
        costLabel.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
        costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        costLabel.setText("     Totlal Cost: ");

        JLabel costLabel1 = new JLabel();
        costLabel1.setForeground(Color.WHITE);
        costLabel1.setFont(new Font("Serif", Font.BOLD, 20));
        costLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        costLabel1.setText(cost);

        //invPanel.add(invLabel);
        invP.add(invLabel);
        invPanel.add(quanLabel);
        invPanel.add(quanLabel1);
        invPanel.add(priceLabel);
        invPanel.add(priceLabel1);
        invPanel.add(costLabel);
        invPanel.add(costLabel1);
        invP.add(invPanel);
        inventoryPanel.add(invP);
        // end inventory card

        center.add(closePanel, "0"); // Close display
        center.add(pilotPanel, "1"); // Pilots display
        center.add(maintanencePanel, "2"); // maintanence display
        center.add(custPanel, "3"); // Customer display
        center.add(inventoryPanel, "4"); // Inventory display

        contentPane.add("Center", center);

    }
    public void insertUpdate(DocumentEvent e) {
        owner = e.getDocument().getProperty("owner");
        //int number = (int) owner;
        updateLog(e, owner);

    }
    public void removeUpdate(DocumentEvent e) {
        //updateLog(e);
    }
    public void changedUpdate(DocumentEvent e) {
        //updateLog(e);
    }

    public void updateLog(DocumentEvent e, Object owner) {
        for (int i = 0;i<3;i++)
        {
            if(owner == flightHours1[i])
            {
                int a = Integer.parseInt(flightHours1[i].getText());
                double b = a*20.00;
                String c = String.format("%.2f", b);
                custBill1[i].setText(c);
                updatePilotHours(a, i);
                updatePlaneHours(a, i);
            }

        }
    }
    public void updatePilotHours(int a, int i)
    {
        int x = custPilot[i].getSelectedIndex();
        int d;
        if (!hrs1[x].getText().equals("") || hrs1[x].getText().equals(null))
        {
            d = Integer.parseInt(hrs1[x].getText());
        }
        else
        {
            d = 0;
        }
        int f = d + a;
        hrs1[x].setText(String.valueOf(f));
    }
    public void updatePlaneHours(int a, int i)
    {
        int x = custPlane[i].getSelectedIndex();
        int d;
        if (!flightHours21[x].getText().equals("") || flightHours21[x].getText().equals(null))
        {
            d = Integer.parseInt(flightHours21[x].getText());
        }
        else
        {
            d = 0;
        }
        int f = d + a;
        flightHours21[x].setText(String.valueOf(f));

        int flighthrs = Integer.parseInt(flightHours21[x].getText());
        int maintenance = Integer.parseInt(mainBill1[x].getText());
        System.out.println(maintenance);
        int engineRebuild = Integer.parseInt(mainBuild1[x].getText());
        // till next maintenance
        int maint_next = 100;
        int maincomb = maint_next - maintenance;
        String maintSubtract = String.valueOf(maincomb);
        System.out.println(maintSubtract);
        mainBill1[x].setText(maintSubtract);
        // till next rebuild
        int eng_rebuild = 1000;
        int engcomb = eng_rebuild - engineRebuild;
        String engSubtract = String.valueOf(engcomb);
        System.out.println(engSubtract);
        mainBuild1[x].setText(engSubtract);
    }
    public void focusGained(FocusEvent e)
    {
        if (e.getSource() == listButtons[0])
        {
            listButtons[0].setBackground(Color.BLACK);
            card.show(center, String.valueOf(0));
        }
        if (e.getSource() == listButtons[1])
        {
            listButtons[1].setBackground(Color.BLACK);
            card.show(center, String.valueOf(1));
        }
        if (e.getSource() == listButtons[2])
        {
            listButtons[2].setBackground(Color.BLACK);
            card.show(center, String.valueOf(2));
        }

        if (e.getSource() == listButtons[3])
        {
            listButtons[3].setBackground(Color.BLACK);
            card.show(center, String.valueOf(3));
        }
        if (e.getSource() == listButtons[4])
        {
            listButtons[4].setBackground(Color.BLACK);
            card.show(center, String.valueOf(4));
        }

    }
    public void focusLost(FocusEvent e)
    {
        if (e.getSource() == listButtons[0])
        {
            listButtons[0].setBackground(new Color(r, g, b));
        }
        if (e.getSource() == listButtons[1])
        {
            listButtons[1].setBackground(new Color(r, g, b));
        }
        if (e.getSource() == listButtons[2])
        {
            listButtons[2].setBackground(new Color(r, g, b));
        }
        if (e.getSource() == listButtons[3])
        {
            listButtons[3].setBackground(new Color(r, g, b));
        }
        if (e.getSource() == listButtons[4])
        {
            listButtons[4].setBackground(new Color(r, g, b));
        }

    }
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == listButtons[0])
        {
            dispose();
        }

        if (e.getSource() == updatePilots)
        {
            int[] hours = new int[3];
            double[] wage = new double[3];
            double[] totalPay = new double[3];
            String[] pName = new String[3];
            String[] pCert = new String[3];
            String[] pEDate = new String[3];
            for (int i=0;i<3;i++)
            {
                hours[i] = Integer.parseInt(hrs1[i].getText());
                wage[i] = Double.parseDouble(sal1[i].getText());
                totalPay[i] = hours[i] * wage[i];
                pName[i] = title[i].getText();
                pCert[i] = cert1[i].getText();
                pEDate[i] = extdate1[i].getText();
            }
            PilotXML xml = new PilotXML();
            if (day.equals("Friday"))
            {
                xml.writeXML(strDate, pName, pCert, pEDate, wage, hours, totalPay);
                printPaychecks();
            }
            else
            {
                xml.writeXML(strDate, pName, pCert, pEDate, wage, hours, totalPay);
            }
        }
        if (e.getSource() == updateCusts)
        {
            int[] cfl = new int[3];
            double[] bill = new double[3];
            double[] bill1 = new double[3];
            String[] cName = new String[3];
            String[] cDate = new String[3];

            for (int i=0;i<3;i++)
            {
                cfl[i] = Integer.parseInt(flightHours1[i].getText());
                bill[i] = Double.parseDouble(custBill1[i].getText());
                cName[i] = title[i].getText();
            }
            CustomerXML cxml = new CustomerXML();
            cxml.writeXML(strDate, cName, cfl, bill);
        }
        if (e.getSource() == updateMains)
        {
            int[] mflthrs = new int[3];
            int[] mant = new int[3];
            int[] erebuild = new int[3];
            String[] mName = new String[3];
            //String[] mDate = new String[3];

            for (int i=0;i<3;i++)
            {
                mflthrs[i] = Integer.parseInt(flightHours21[i].getText());
                mant[i] = Integer.parseInt(mainBuild1[i].getText());
                erebuild[i] = Integer.parseInt(mainBill1[i].getText());
                mName[i] = titleMain[i].getText();
            }
            PlanesXML pxml = new PlanesXML();
            pxml.writeXML(strDate, mName, mflthrs, mant, erebuild);
        }
        for (int i = 0;i<docLength;i++)
        {
            if (e.getSource() == checkbox[i])
            {
                System.out.println(title[i].getText());
                setNum(i);
            }
        }
    }
    public void printPaychecks()
    {
        try {
            xmlFile5 = new File(myDir + "/WeekPilot.xml");
            dbFactory5 = DocumentBuilderFactory.newInstance();
            dBuilder5 = dbFactory5.newDocumentBuilder();
            doc5 = dBuilder5.parse(xmlFile5);
            nList5 = doc5.getElementsByTagName("name");
            docLength5 = nList5.getLength();
        }
        catch (Exception ex)
        {
            System.out.println("cant open");
        }
        String[] pilotName = new String[docLength5];
        String[] pilotSalary = new String[docLength5];
        String[] pilotHours = new String[docLength5];
        String[] paychecks = new String[docLength5];

        for (int i = 0;i<docLength5;i++)
        {
            nNode5 = nList5.item(i);
            Element eElement5 = (Element) nNode5;
            pilotName[i] = eElement5.getAttribute("id");
            pilotSalary[i] = eElement5.getElementsByTagName("pay").item(0).getTextContent();
            pilotHours[i] = eElement5.getElementsByTagName("hours").item(0).getTextContent();
            paychecks[i] = "gogo" + i;
            System.out.println(pilotName[i]);
        }
        for (int i = 0;i<docLength5;i++)
        {
            thisThread = paychecks[i];
            Thread thisThread = new Thread (new PayChecks(pilotName[i], pilotHours[i], pilotSalary[i])); thisThread.start();
            System.out.println(pilotHours[i]);
        }

    }
    public void setNum(int n)
    {
        num = n;
    }
    public int getNum()
    {
        return num;
    }
    public Image scaleImage(Image image, int w, int h) {

        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);

        return scaled;
    }


}











