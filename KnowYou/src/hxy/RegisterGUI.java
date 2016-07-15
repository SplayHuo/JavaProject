package hxy;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RegisterGUI extends JFrame 
{
	private static final long serialVersionUID = 3250371445038102261L;
	private JPanel contentPane;
	private JTextField nameText;
	private JTextField idText;
	private JTextField passwordText;

	public void registerGUI() 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterGUI() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nameLabel = new JLabel("Please input user name");
		nameLabel.setBounds(102, 91, 151, 23);
		contentPane.add(nameLabel);

		JLabel idLabel = new JLabel("Please input user id");
		idLabel.setBounds(102, 160, 151, 23);
		contentPane.add(idLabel);

		JLabel passwordLabel = new JLabel("Please input user password");
		passwordLabel.setBounds(102, 224, 163, 23);
		contentPane.add(passwordLabel);

		nameText = new JTextField();
		nameText.setBounds(271, 92, 92, 21);
		nameText.setColumns(10);
		contentPane.add(nameText);
		
		idText = new JTextField();
		idText.setBounds(271, 161, 92, 21);
		idText.setColumns(8);
		contentPane.add(idText);

		passwordText = new JTextField();
		passwordText.setBounds(271, 225, 92, 21);
		passwordText.setColumns(10);
		contentPane.add(passwordText);
		
		JButton register = new JButton("Sign Up");
		register.addMouseListener(new MouseAdapter() 
		{
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e)
			{
				String name = nameText.getText();
				String id = idText.getText();
				String password = passwordText.getText();
				if (Register.checkId(id) == null) 
				{
					if (Register.checkPassword(password) == null) 
					{
						String str = Register.register(name, password, id);
						JOptionPane.showMessageDialog(contentPane, str,
								"information", JOptionPane.PLAIN_MESSAGE);
						setVisible(false);
						IndexGUI.init();
					} 
					else 
					{
						JOptionPane.showMessageDialog(contentPane,
								Register.checkPassword(password), "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} 
				else
				{
					JOptionPane.showMessageDialog(contentPane,
							Register.checkId(id), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		register.setBounds(321, 305, 93, 23);
		contentPane.add(register);

		JButton back = new JButton("BACK");
		/*
		back.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		*/
		back.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				setVisible(false);
				IndexGUI.init();
			}
		});
		back.setBounds(531, 305, 93, 23);
		contentPane.add(back);

		JLabel label = new JLabel("Welcome to use KnowYou");
		label.setFont(new Font("黑体", Font.BOLD | Font.ITALIC, 30));
		label.setBounds(143, 26, 374, 35);
		contentPane.add(label);

		JLabel id_hint = new JLabel("(There are 1 to 8 numbers)");
		id_hint.setBounds(373, 164, 163, 15);
		contentPane.add(id_hint);

		JLabel pwd_hint = new JLabel("(There are 6 to 15 numbers)");
		pwd_hint.setBounds(373, 228, 163, 15);
		contentPane.add(pwd_hint);
	}
}
