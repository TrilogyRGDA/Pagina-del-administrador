package admin;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.CompoundBorder;
import javax.swing.JTextField;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import inicioSesion.Inicio_Sesion;
/**
 * La clase "Pagina_administrador" es una página donde podrás realizar las siguientes tareas:
 * 1º Comprobar las cantidades de cada tipo de membresías ("Cristal_Basic", "Zafiro_Standard", "Rubi_Standard" y "Diamante_Premium") y su respectivo beneficio total con la operación cantidad de membresías * el precio.
 * 2º De las opciones anteriores, hay una especial, que es "Total_Ganancias" donde te mostrará el total de las membresías y el total ganado con la aplicación.
 * 3º Comprobar mediante el correo si un usuario existe (Se puede comprobar mediante el correo, porque el correo es único para cada usuario).
 * 4º Cambiar de membresia a un usuario que exista que previamente has comprobado.
 * 5º Eliminar a un usuario que previamente has comprobado.
 * @param contentPane
 * Es el panel principal donde contiene todos los atributos.
 * @param panelLateral
 * Es el panel que interaciona como desplegable cambiando su tamaño, con la funcionalidad de maximizar la pantalla para las personas con dificultad visual y realiza las tareas de salir de la aplicación e ir a la página de início de sesión.
 * @param lbl_Img_Fondo
 * El label lbl_Img_Fondo es una imagen con un degradado para el fondo en la aplicación.
 * @param lbl_FleDesple
 * El label es una imagen pulsable que cambia de imagen con información para el usuario de lo que puede hacer. Lo que puede es minimizar o maximizar el panelLateral.
 * @param btn_Inicio_APP
 * El botón de btn_Inicio_APP te permite ir a la página de inicio de sesión.
 * @param btn_Salir
 * El botón de btn_Salir te permite cerrar la aplicación.
 * @param lblLogoOriginal
 * El label lblLogoOriginal es una imagen con nuestro logo.
 * @param lbl_Texto_1
 * El label lbl_Texto_1 es un atributo informativo para el usuario donde pone "NÚMERO DE INSCRITOS A LA MEMBRESÍA".
 * @param lbl_Texto_2
 * El label lbl_Texto_2 es un atributo informativo para el usuario donde pone "USUARIOS".
 * @param comboBox_Tipo_de_membreisa1
 * El comboBox comboBox_Tipo_de_membreisa1 es un menú en forma de desplegable donde te ofrece unas opciones sobre los diferente tipos de membresías y una opción especial que es "Total_Ganancias".
 * @param comboBox_Tipo_de_membreisa2
 * El comboBox comboBox_Tipo_de_membreisa2 es un menú en forma de desplegable donde te ofrece unas opciones sobre los diferente tipos de membresías.
 * @param lbl_Total_membresias
 * El label lbl_Total_membresias te muestra la cantidad de membresías que hay según  SELECCIONADO en comboBox_Tipo_de_membreisa1.
 * @param lbl_Total_Ganado
 * El label lbl_Total_Ganado te muestra la cantidad total que has ganado con la membresía que has SELECCIONADO en comboBox_Tipo_de_membreisa1.
 * @param txt_Correo
 * El jTextField txt_Correo te permite escribir un correo con el que puedes realizar diferentes tareas.
 * @param lbl_Nombre
 * El label lbl_Nombre te muestra el nombre del usuario que previamente ha escrito en el txt_Correo.
 * @param lbl_Apellidos_User
 * El label lbl_Apellidos_User te muestra los apellidos del usuario que previamente ha escrito en el txt_Correo.
 * @param btnComprobar_users
 * El botón btnComprobar_users te permite comprobar si un usuario existe mediante el atributo txt_Correo.
 * @param btnCambioMembresia
 * El botón btnCambioMembresia te permite cambiar de membresía a un usuario que previamente ha confirmado que existe con el btnComprobar_users.
* @param btnConfirmarEliminacion
 * El botón btnConfirmarEliminacion te permite eliminar un usuario que previamente ha confirmado que existe con el btnComprobar_users. 
 * * @param btnCalcular_Ganancias
 * El botón btnCalcular_Ganancias te permite conocer las ganancias totales según tengas usuarios con un membresía. 
 * @author TRILOGY. Miembros: Adrián Leal Vacas, Gonzalo Amo Cano y Raúl Senso Montojo.
 */
public class Pagina_administrador extends JFrame {
	// UID VERSIÓN
	/**
	 * La única versión del programa del administrador.
	 * Incluye las tareas básicas del administrador que son: comprobar las ganacias, comprobar usuarios, cambiar membresías y eliminar usuarios.
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
	// ------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	// ------------------------------------------------------------------------------------------------------
	private JPanel contentPane;
	private JPanel panelLateral;
	private JButton btn_Inicio_APP;
	private JButton btn_Salir;
	private JLabel lbl_FleDesple;
	private JPanel panel_Contenido_Admin;
	private JLabel lbl_Texto_1;
	private JLabel lbl_Texto_2;
	private JComboBox<String> comboBox_Tipo_de_membreisa1;
	private JLabel lbl_Total_membresias;
	private JLabel lbl_Total_Ganado;
	private JTextField txt_Correo;
	private JLabel lbl_Nombre;
	private JButton btnCambioMembresia;
	private JButton btnConfirmarEliminacion;
	private JLabel lbl_Apellidos_User;
	private JLabel lbl_Img_Fondo;
	private JComboBox<String> comboBox_Tipo_de_membreisa2;
	private JLabel lblLogoOriginal;
	private JButton btnComprobar_users;
	private JButton btnCalcular_Ganancias;
	String tipo_de_membreisa1;
	String tipo_de_membreisa2;
	int membresia_id2;
	int membresia_id;
	private boolean comprobar_user=false;
	// ATRIBUTOS Y OBJETOS NECESARIOS PARA AL CONEXIÓN, LA BASE DE DATOS Y LA REALIZACIÓN DE UN CONSULTA BÁSICA
	private static String bd="XE"; // NOMBRE DE LA BASE DE DATOS POR DEFECTO SIEMPRE, DEJAR EL "XE"
	private static String login="TRILOGY"; // USUARIO DE LA BBDD
	private static String password="TRILOGY"; // CONTRASEÑA DE LA BBDD
	// RUTA DE SERVICIO
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	// PONEMOS LOS OBJETOS A NULL Y SIN INSTANCIAR
	static Connection connection=null; // CONEXIÓN A LA BASE DE DATOS
	static Statement st; // PARA REALIZAR SQL ESTÁTICAS (HAY QUE ENLAZARLA SIEMPRE CON EL "Connection" SINO, NO FUNCIONA)
	static ResultSet rs; // PARA REALIZAR LA CONSULTA IGUAL QUE EN SQL DEVELOPER
	// ------------------------------------------------------------------------------------------------------
	// MÉTODO MAIN
	// ------------------------------------------------------------------------------------------------------
	/**
	 * En el método main ejecutamos la ventana principal Pagina_administrador y realizamos la conexión a la base de datos para el correcto funcionamiento de la página.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagina_administrador frame = new Pagina_administrador();
					frame.setVisible(true);
					conectar();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR. No se ha podido acceder a la página del administrador.");
				} 
			}
		});
	}
	// ------------------------------------------------------------------------------------------------------
	// MÉTODOS
	// ------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------
	// MÉTODO PARA CONECTAR A LA BASE DE DATOS
	// ------------------------------------------------------------------------------------------------------
	/**
	 * El método conectar() realiza una conexión a la base de datos.
	 */
	public static void conectar() throws Exception{
		// DRIVER PARA ORACLE
		Class.forName("oracle.jdbc.driver.OracleDriver"); // EL DRIVER DEL JDBC SIEMPRE ES EL MISMO
		connection=DriverManager.getConnection(url,login,password); // NOS CONECTAMOS A LA BASE DE DATOS CON LA URL, LOGIN Y EL PASSWORD QUE PREVIAMENTE PUSIMOS EN LOS ATRIBUTOS
	}
	// ------------------------------------------------------------------------------------------------------
	// MÉTODO PARA CERRAR LA BASE DE DATOS
	// ------------------------------------------------------------------------------------------------------
	/**
	 * El método cerrar() cierra la base de datos.
	 */
	public static void cerrar() throws SQLException{
		// SIEMPRE EN EL MISMO ORDEN, SINO, DA FALLO
		if (rs!=null) rs.close(); // CERRAMOS EL RS SI ES DIFERENTE AL NULL (FUNCIONANDO)
		if (st!=null) st.close(); // CERRAMOS EL ST SI ES DIFERENTE AL NULL (FUNCIONANDO)
		if (connection!=null) connection.close(); // CERRAMOS EL connection SI ES DIFERENTE AL NULL (FUNCIONANDO)
	}
	// ------------------------------------------------------------------------------------------------------
	// PARA OCULTAR LOS BOTONES PARA MEJOR APARENCIA
	// ------------------------------------------------------------------------------------------------------
	/**
	 * El método EsconderBotones() realiza un minimizado de los elementos del panel de la izquierda.
	 */
	public void EsconderBotones() {
		// LABEL DEL LOGO
		lblLogoOriginal.setBounds(6, 11, 60, 58); // HACEMOS EL BOTÓN MÁS PEQUEÑO
		lblLogoOriginal.setText(""); // LIMPIAMOS EL TEXTO
		lblLogoOriginal.setHorizontalAlignment(SwingConstants.CENTER); // CENTRO EL TEXTO
		// AJUSTAR IMAGEN AL CONTENIDO
		ImageIcon logoOriginal2 = new ImageIcon(getClass().getResource("/Trilogy_imagenes/IconoTrilogy.png")); // CREACIÓN NUEVO OBJETO CON LA RUTA DE LA IMAGEN
		ImageIcon imgLogoOriginal2 = new ImageIcon(logoOriginal2.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)); // CREAMOS OTRO OBJETO PARA QUE SE AJUSTE AUTOMÁTICAMENTE LA IMAGEN AL LABEL
		lblLogoOriginal.setIcon(imgLogoOriginal2);
		// BOTÓN DE AJUSTES ADMINISTRADOR
		btn_Inicio_APP.setBounds(6, 295, 60, 58);
		btn_Inicio_APP.setText("");
		btn_Inicio_APP.setHorizontalAlignment(SwingConstants.CENTER);
		// BOTÓN DE SALIR
		btn_Salir.setBounds(6, 592, 60, 58); // HACEMOS EL BOTÓN MÁS PEQUEÑO
		btn_Salir.setText(""); // LIMPIAMOS EL TEXTO
		btn_Salir.setHorizontalAlignment(SwingConstants.CENTER); // CENTRO EL TEXTO
	}
	// ------------------------------------------------------------------------------------------------------
	// MUESTRA LOS BOTONES PARA MAYOR INFORMACIÓN
	// ------------------------------------------------------------------------------------------------------
	/**
	 * El método MostrarBotones() realiza un minimizado de los elementos del panel de la izquierda (por defecto).
	 */
	public void MostrarBotones() {
		// LABEL DEL LOGO
		lblLogoOriginal.setBounds(10, 11, 199, 75); 
		lblLogoOriginal.setText(""); // LIMPIAMOS EL TEXTO
		// AJUSTAR IMAGEN AL CONTENIDO
		ImageIcon logoOriginal = new ImageIcon(getClass().getResource("/Trilogy_imagenes/LogoTrilogyNegro.png")); // CREACIÓN NUEVO OBJETO CON LA RUTA DE LA IMAGEN
		ImageIcon imgLogoOriginal = new ImageIcon(logoOriginal.getImage().getScaledInstance(lblLogoOriginal.getWidth(), lblLogoOriginal.getHeight(), Image.SCALE_SMOOTH)); // CREAMOS OTRO OBJETO PARA QUE SE AJUSTE AUTOMÁTICAMENTE LA IMAGEN AL LABEL
		lblLogoOriginal.setIcon(imgLogoOriginal);
		//-------------------------------------------------------------------------------
		// BOTÓN DE AJUSTES ADMINISTRADOR
		btn_Inicio_APP.setBounds(10, 295, 199, 90);
		btn_Inicio_APP.setText("      INICIO");
		btn_Inicio_APP.setHorizontalAlignment(SwingConstants.LEFT); 
		btn_Inicio_APP.setFont(new Font("Tahoma", Font.BOLD, 14));
		//-------------------------------------------------------------------------------
		// BOTÓN DE SALIR
		btn_Salir.setBounds(10, 562, 199, 90); 
		btn_Salir.setText("      LOGOUT"); 
		btn_Salir.setHorizontalAlignment(SwingConstants.LEFT); 
		btn_Salir.setFont(new Font("Tahoma", Font.BOLD, 14)); 
		ImageIcon iconoSalir = new ImageIcon(getClass().getResource("/Trilogy_imagenes/IconoLogout.png")); // CREACIÓN NUEVO OBJETO CON LA RUTA DE LA IMAGEN
		ImageIcon imgiconoSalir = new ImageIcon(iconoSalir.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)); // CREAMOS OTRO OBJETO PARA QUE SE AJUSTE AUTOMÁTICAMENTE LA IMAGEN AL LABEL
		btn_Salir.setIcon(imgiconoSalir);
	}
	// ------------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ------------------------------------------------------------------------------------------------------
	/**
	 * El constructor llama a los atributos y métodos necesarios para el funcionamiento de la clase Pagina_administrador.
	 */
	public Pagina_administrador() {
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 703); // TAMAÑO DE LA VENTANA
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// ------------------------------------------------------------------------------------------------------
		// PANEL DEL ADMINISTRADOR
		// ------------------------------------------------------------------------------------------------------
		panelLateral = new JPanel();
		panelLateral.setBackground(new Color(153, 255, 153));
		panelLateral.setBounds(0, 0, 219, 664);
		contentPane.add(panelLateral);
		panelLateral.setLayout(null);
		// ------------------------------------------------------------------------------------------------------
		// LABEL DEL LOGO
		// ------------------------------------------------------------------------------------------------------
		lblLogoOriginal = new JLabel();
		lblLogoOriginal.setFocusable(false);
		lblLogoOriginal.setBackground(new Color(153, 255, 153));
		lblLogoOriginal.setBounds(10, 11, 199, 75);
		// AJUSTAR IMAGEN AL CONTENIDO
		ImageIcon logoOriginal = new ImageIcon(getClass().getResource("/Trilogy_imagenes/LogoTrilogyNegro.png")); // CREACIÓN NUEVO OBJETO CON LA RUTA DE LA IMAGEN
		ImageIcon imgLogoOriginal = new ImageIcon(logoOriginal.getImage().getScaledInstance(lblLogoOriginal.getWidth(), lblLogoOriginal.getHeight(), Image.SCALE_SMOOTH)); // CREAMOS OTRO OBJETO PARA QUE SE AJUSTE AUTOMÁTICAMENTE LA IMAGEN
		lblLogoOriginal.setIcon(imgLogoOriginal);
		panelLateral.add(lblLogoOriginal);
		// ------------------------------------------------------------------------------------------------------
		// BOTÓN DE SALIR DE LA APLICACIÓN
		// ------------------------------------------------------------------------------------------------------
		btn_Salir = new JButton("      LOGOUT");
		btn_Salir.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Salir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Salir.setFocusPainted(false);
		btn_Salir.setBorderPainted(false);
		btn_Salir.setBackground(new Color(153, 255, 153));
		btn_Salir.setBounds(10, 562, 199, 90);
		// AJUSTAR IMAGEN AL CONTENIDO
		ImageIcon iconoSalir = new ImageIcon(getClass().getResource("/Trilogy_imagenes/IconoLogout.png")); // CREACIÓN NUEVO OBJETO CON LA RUTA DE LA IMAGEN
		ImageIcon imgiconoSalir = new ImageIcon(iconoSalir.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)); // CREAMOS OTRO OBJETO PARA QUE SE AJUSTE AUTOMÁTICAMENTE LA IMAGEN
		btn_Salir.setIcon(imgiconoSalir);
		// EVENTOS CON EL RATÓN (MOUSE)
		btn_Salir.addMouseListener(new MouseAdapter() {
			// CUANDO ESTÁS PASANDO POR ENCIMA
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_Salir.setBackground(new Color(92,252,92)); // EL COLOR QUE SE VA A PONER CUANDO PASES POR ENCIMA
				btn_Salir.setForeground(new Color(0,0,0)); // COLOR CUANDO YA HAS PASADO POR ENCIMA
			}
			// CUANDO DEJES DE PASAR POR ENCIMA
			@Override
			public void mouseExited(MouseEvent e) {
				btn_Salir.setBackground(new Color(153,255,153)); // EL COLOR QUE SE VA A PONER CUANDO DEJES DE PASES POR ENCIMA
				btn_Salir.setForeground(new Color(0,0,0)); // COLOR CUANDO YA HAS PASADO POR ENCIMA
			}
			// CUANDOS PULSAS
			@Override
			public void mouseClicked(MouseEvent e) {
				// CIERRA LA BASE DE DATOS
				try {
					cerrar();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "ERROR. No se ha podido cerrar la base de datos. Perdone las molestias.");
				}
				System.exit(0); // ESTO ES LO QUE HACE QUE CIERRE LA APP
			}
		});
		panelLateral.add(btn_Salir);
		// ------------------------------------------------------------------------------------------------------
		// BOTÓN PARA LLEVARTE AL INICIO DE LA APP
		// ------------------------------------------------------------------------------------------------------
		btn_Inicio_APP = new JButton("      INÍCIO");
		btn_Inicio_APP.setHorizontalAlignment(SwingConstants.LEFT);
		btn_Inicio_APP.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Inicio_APP.setFocusPainted(false);
		btn_Inicio_APP.setBorderPainted(false);
		btn_Inicio_APP.setBackground(new Color(153, 255, 153));
		btn_Inicio_APP.setBounds(10, 295, 199, 90);
		// AJUSTAR IMAGEN AL CONTENIDO
		ImageIcon iconoEngranaje = new ImageIcon(getClass().getResource("/Trilogy_imagenes/Icono_Inicio.png")); // CREACIÓN NUEVO OBJETO CON LA RUTA DE LA IMAGEN
		ImageIcon imgIconoEngranaje = new ImageIcon(iconoEngranaje.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)); // CREAMOS OTRO OBJETO PARA QUE SE AJUSTE AUTOMÁTICAMENTE LA IMAGEN
		btn_Inicio_APP.setIcon(imgIconoEngranaje);
		// EVENTOS CON EL RATÓN (MOUSE)
		btn_Inicio_APP.addMouseListener(new MouseAdapter() {
			// CUANDO ESTÁS PASANDO POR ENCIMA
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_Inicio_APP.setBackground(new Color(92,252,92)); // EL COLOR QUE SE VA A PONER CUANDO PASES POR ENCIMA
				btn_Inicio_APP.setForeground(new Color(0,0,0)); // COLOR CUANDO YA HAS PASADO POR ENCIMA
			}
			// CUANDO DEJES DE PASAR POR ENCIMA
			@Override
			public void mouseExited(MouseEvent e) {
				btn_Inicio_APP.setBackground(new Color(153,255,153)); // EL COLOR QUE SE VA A PONER CUANDO DEJES DE PASES POR ENCIMA
				btn_Inicio_APP.setForeground(new Color(0,0,0)); // COLOR CUANDO YA HAS PASADO POR ENCIMA
			}
			// CUANDO PULSAS
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					cerrar();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "ERROR. No se ha podido cerrar la base de datos. Perdone las molestias.");
				}
				Inicio_Sesion inicioSesion = new Inicio_Sesion();
				inicioSesion.setVisible(true);
				dispose();
			}
		});
		panelLateral.add(btn_Inicio_APP);
		// ------------------------------------------------------------------------------------------------------
		// PANEL DONDE CONTIENE EL CONTENIDO DE LA PÁGINA DEL ADMINISTRADOR
		// ------------------------------------------------------------------------------------------------------
		panel_Contenido_Admin = new JPanel();
		panel_Contenido_Admin.setBackground(new Color(255, 255, 255));
		panel_Contenido_Admin.setBounds(218, 0, 962, 664);
		contentPane.add(panel_Contenido_Admin);
		panel_Contenido_Admin.setLayout(null);
		// ------------------------------------------------------------------------------------------------------
		// FONDO DEL PANEL
		// ------------------------------------------------------------------------------------------------------
		lbl_Img_Fondo = new JLabel("");
		lbl_Img_Fondo.setBounds(0, 0, 961, 664);
		// AJUSTAR IMAGEN AL CONTENIDO
		ImageIcon fondo = new ImageIcon(getClass().getResource("/Trilogy_imagenes/Fondo_Formulario.png")); // CREACIÓN NUEVO OBJETO CON LA RUTA DE LA IMAGEN
		ImageIcon imgFondo = new ImageIcon(fondo.getImage().getScaledInstance(lbl_Img_Fondo.getWidth(), lbl_Img_Fondo.getHeight(), Image.SCALE_SMOOTH)); // CREAMOS OTRO OBJETO PARA QUE SE AJUSTE AUTOMÁTICAMENTE LA IMAGEN AL LABEL
		lbl_Img_Fondo.setIcon(imgFondo); // DECIMOS QUE EL ICONO ES IGUAL AL OBJETO Y SE AJUSTA AUTOMÁTICAMENTE AL TAMAÑO DEL LABEL
		// ------------------------------------------------------------------------------------------------------
		// LBL CON EL TEXTO "NÚMEROS DE INSCRITOS A LA MEMBRESÍA"
		// ------------------------------------------------------------------------------------------------------		
		lbl_Texto_1 = new JLabel("NÚMERO DE INSCRITOS A LA MEMBRESÍA");
		lbl_Texto_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Texto_1.setForeground(new Color(255, 255, 255));
		lbl_Texto_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		lbl_Texto_1.setBounds(30, 35, 901, 48);
		// ------------------------------------------------------------------------------------------------------
		// LBL CON EL TEXTO "USUARIOS"
		// ------------------------------------------------------------------------------------------------------
		lbl_Texto_2 = new JLabel("USUARIOS");
		lbl_Texto_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Texto_2.setForeground(Color.WHITE);
		lbl_Texto_2.setFont(new Font("Tahoma", Font.BOLD, 28));
		lbl_Texto_2.setBounds(30, 283, 901, 48);
		// ------------------------------------------------------------------------------------------------------
		// DESPLEGABLE DE LAS DIFERENTES MEMBRESÍAS (NÚMEROS DE INSCRITOS A LA MEMBRESÍA)
		// ------------------------------------------------------------------------------------------------------
		comboBox_Tipo_de_membreisa1 = new JComboBox<String>();
		comboBox_Tipo_de_membreisa1.setBorder(new CompoundBorder());
		comboBox_Tipo_de_membreisa1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_Tipo_de_membreisa1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		comboBox_Tipo_de_membreisa1.setOpaque(true);
		comboBox_Tipo_de_membreisa1.setBackground(new Color(255, 255, 255));
		comboBox_Tipo_de_membreisa1.setModel(new DefaultComboBoxModel<String>(new String[] {"Cristal_Basic", "Zafiro_Standard", "Rubi_Standard", "Diamante_Premium","Total_Ganancias"}));
		comboBox_Tipo_de_membreisa1.setBounds(50, 124, 300, 45);
		// ------------------------------------------------------------------------------------------------------
		// LABEL DONDE PONE LA CANTIDAD DE MEMBRESÍAS DEL TIPO SELECCIONADO EN EL DESPLEGABLE (SE SACA CON LA CONSULTA)
		// ------------------------------------------------------------------------------------------------------
		lbl_Total_membresias = new JLabel("TOTAL MEMBRESÍAS");
		lbl_Total_membresias.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_Total_membresias.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Total_membresias.setOpaque(true);
		lbl_Total_membresias.setBackground(new Color(255, 255, 255));
		lbl_Total_membresias.setBounds(375, 124, 240, 45);
		// ------------------------------------------------------------------------------------------------------
		// LABEL DONDE PONE LA CANTIDAD TOTAL GANADA DEL TIPO DE MEMBRESÍA SELECCIONADO EN EL DESPLEGABLE (SE SACA CON LA CONSULTA)
		// ------------------------------------------------------------------------------------------------------
		lbl_Total_Ganado = new JLabel("TOTAL GANADO");
		lbl_Total_Ganado.setOpaque(true);
		lbl_Total_Ganado.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Total_Ganado.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_Total_Ganado.setBackground(Color.WHITE);
		lbl_Total_Ganado.setBounds(640, 124, 270, 45);
		// ------------------------------------------------------------------------------------------------------
		// TEXT FIELD DONDE EL USUARIO (ADMINISTRADOR) TIENE QUE PONER EL ID QUE SE LE ENVIÓ POR CORREO CUANDO SE REGISTRO EL USUARIO
		// ------------------------------------------------------------------------------------------------------
		txt_Correo = new JTextField();
		txt_Correo.setForeground(new Color(150,150,150));
		txt_Correo.setHorizontalAlignment(SwingConstants.CENTER);
		txt_Correo.setText("CORREO");
		txt_Correo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_Correo.setBounds(50, 360, 485, 45);
		txt_Correo.setColumns(10);
		// ------------------------------------------------------------------------------------------------------
		// LABEL DONDE PONE LOS APELLIDOS QUE SE HA SACADO MEDIANTE UNA CONSLTA
		// ------------------------------------------------------------------------------------------------------
		lbl_Nombre = new JLabel("NOMBRE");
		lbl_Nombre.setOpaque(true);
		lbl_Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_Nombre.setBackground(Color.WHITE);
		lbl_Nombre.setBounds(570, 360, 340, 45);
		// ------------------------------------------------------------------------------------------------------
		// LABEL QUE CUANDO PONGAS EL ID, CAMBIE A EL CORREO DEL USUARIO
		// ------------------------------------------------------------------------------------------------------
		lbl_Apellidos_User = new JLabel("APELLIDOS");
		lbl_Apellidos_User.setOpaque(true);
		lbl_Apellidos_User.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Apellidos_User.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_Apellidos_User.setBackground(Color.WHITE);
		lbl_Apellidos_User.setBounds(74, 460, 450, 45);
		// ------------------------------------------------------------------------------------------------------
		// DESPLEGABLE CON LOS NOMBRES DE LAS DIFERENTES MEMBRESÍA (USUARIO)
		// ------------------------------------------------------------------------------------------------------
		comboBox_Tipo_de_membreisa2 = new JComboBox<String>();
		comboBox_Tipo_de_membreisa2.setBorder(new CompoundBorder());
		comboBox_Tipo_de_membreisa2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox_Tipo_de_membreisa2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		comboBox_Tipo_de_membreisa2.setOpaque(true);
		comboBox_Tipo_de_membreisa2.setBackground(new Color(255, 255, 255));
		comboBox_Tipo_de_membreisa2.setModel(new DefaultComboBoxModel<String>(new String[] {"Cristal_Basic", "Zafiro_Standard", "Rubi_Standard", "Diamante_Premium"}));
		comboBox_Tipo_de_membreisa2.setBounds(593, 460, 300, 45);
		// ------------------------------------------------------------------------------------------------------
		// BOTÓN CALCULAR GANANCIAS
		// ------------------------------------------------------------------------------------------------------
		btnCalcular_Ganancias = new JButton("CALCULAR GANANCIAS");
		btnCalcular_Ganancias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// GUARDO EL VALOR DEL comboBox_Tipo_de_membreisa1 PARA LUEGO PODER COMPARARLO CON LA BASE DE DATOS PARA LA CONSULTA DE LA GANANCIA
				tipo_de_membreisa1=comboBox_Tipo_de_membreisa1.getSelectedItem().toString().toUpperCase();
				switch (tipo_de_membreisa1) {
				case "CRISTAL_BASIC":
					membresia_id=1;
					break;
				case "ZAFIRO_STANDARD":
					membresia_id=2;
					break;
				case "RUBI_STANDARD":
					membresia_id=3;
					break;
				case "DIAMANTE_PREMIUM":
					membresia_id=4;
					break;
				case "TOTAL_GANANCIAS":
					membresia_id=5; // CALCULAR EL TOTAL DE TODAS LAS MEMBRESÍAS
					break;
				default:
					membresia_id=1;
					break;
				}
				if (membresia_id==5) {
					try {
						// NOS CONECTAMOS A LA BASE DE DATOS
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "TRILOGY", "TRILOGY");
						// REALIZAMOS EL SELECT CON LOS DATOS QUE QUEREMOS ALMACENAR (? --> ES UN CAMPO QUE LO VA A ADQUIRIR DE LA VARIABLE membresia_id) (SIRVE PARA CREAR LA SENTENCIA SQL)
						String query3 = "SELECT SUM(MEMBRESIAS.PRECIO) FROM CON_MEMBRESIAS JOIN MEMBRESIAS ON CON_MEMBRESIAS.ID_MEMBRESIAS=MEMBRESIAS.ID_MEMBRESIAS";
						PreparedStatement stmt3 = con.prepareStatement(query3);
						rs = stmt3.executeQuery();
						double totalGanancia=0;
						while (rs.next()) {
							// ALMACENA EL DATO DE LA COLUMNA NOMBRE EN LA VARIABLE totalGanancia
							totalGanancia = rs.getDouble(1);
							// CAMBIA EL TEXTO DE lbl_Total_Ganado AL VALOR DE LA VARIABLE totalGanancia
							lbl_Total_Ganado.setText(String.valueOf(totalGanancia));
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "ERROR. No se ha podido realizar la consulta. Perdone las molestias.");
					}
					Connection con;
					try {
						con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "TRILOGY", "TRILOGY");
						String query4 = "SELECT count(ID_USUARIO) FROM CON_MEMBRESIAS";
						PreparedStatement stmt4 = con.prepareStatement(query4);
						rs = stmt4.executeQuery();
						int total_membresias=0;
						while (rs.next()) {
							// ALMACENA EL DATO DE LA COLUMNA NOMBRE EN LA VARIABLE totalGanancia
							total_membresias = rs.getInt(1);
							// CAMBIA EL TEXTO DE lbl_Total_Ganado AL VALOR DE LA VARIABLE totalGanancia
							lbl_Total_membresias.setText(String.valueOf(total_membresias));
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "ERROR. No se ha podido realizar la consulta. Perdone las molestias.");
					}
				} else {
					try {
						// NOS CONECTAMOS A LA BASE DE DATOS
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "TRILOGY", "TRILOGY");
						// REALIZAMOS EL SELECT CON LOS DATOS QUE QUEREMOS ALMACENAR (? --> ES UN CAMPO QUE LO VA A ADQUIRIR DE LA VARIABLE membresia_id) (SIRVE PARA CREAR LA SENTENCIA SQL)
						String query = "SELECT COUNT(ID_USUARIO) FROM CON_MEMBRESIAS WHERE ID_MEMBRESIAS = ?";
						// LE DECIMOS QUE LA ? ANTERIOR USE EL VALOR DE LA VARIABLE membresia_id (1 ES EL NÚMERO DE ? Y membresia_id EL VALOR QUE LE QUEREMOS METER, EN ESTE CASO EL VALOR ALMACENADO EN LA VARIABLE)
						PreparedStatement stmt = con.prepareStatement(query);
						stmt.setInt(1, membresia_id);
						// EJECUTAMOS LA QUERY ANTERIOR
						rs = stmt.executeQuery();
						// VARIABLE CONTADOR
						int num_membresia=0;
						// PARA RECORER LAS LÍNEAS QUE NOS SALGA EN LA SENTENCIA QUERY ANTERIOR
						// MIENTRAS QUE HAYA SIGUIENTE
						while (rs.next()) {
							// ALMACENA EL DATO DE LA COLUMNA NOMBRE EN LA VARIABLE nombre
							num_membresia = rs.getInt(1);
							// CAMBIA EL TEXTO DE lbl_Apellidos_User AL VALOR DE LA VARIABLE apellido
							lbl_Total_membresias.setText(String.valueOf(num_membresia));
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "ERROR. No se ha podido conectar a la base de datos. Perdone las molestias."); // MENSAJE QUE SE LE MUESTRA AL USUARIO
					}
					try {
						// NOS CONECTAMOS A LA BASE DE DATOS
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "TRILOGY", "TRILOGY");
						// REALIZAMOS EL SELECT CON LOS DATOS QUE QUEREMOS ALMACENAR (? --> ES UN CAMPO QUE LO VA A ADQUIRIR DE LA VARIABLE membresia_id) (SIRVE PARA CREAR LA SENTENCIA SQL)
						String query2 = "SELECT SUM(MEMBRESIAS.PRECIO) FROM CON_MEMBRESIAS JOIN MEMBRESIAS ON CON_MEMBRESIAS.ID_MEMBRESIAS=MEMBRESIAS.ID_MEMBRESIAS WHERE CON_MEMBRESIAS.ID_MEMBRESIAS = ?";
						// LE DECIMOS QUE LA ? ANTERIOR USE EL VALOR DE LA VARIABLE membresia_id (1 ES EL NÚMERO DE ? Y membresia_id EL VALOR QUE LE QUEREMOS METER, EN ESTE CASO EL VALOR ALMACENADO EN LA VARIABLE)
						PreparedStatement stmt2 = con.prepareStatement(query2);
						stmt2.setInt(1, membresia_id);
						// EJECUTAMOS LA QUERY ANTERIOR
						rs = stmt2.executeQuery();
						// VARIABLE SUMATORIO
						double totalGanancia=0;
						// PARA RECORER LAS LÍNEAS QUE NOS SALGA EN LA SENTENCIA QUERY ANTERIOR
						// MIENTRAS QUE HAYA SIGUIENTE
						while (rs.next()) {
							// ALMACENA EL DATO DE LA COLUMNA NOMBRE EN LA VARIABLE nombre
							totalGanancia = rs.getDouble(1);
							// CAMBIA EL TEXTO DE lbl_Apellidos_User AL VALOR DE LA VARIABLE apellido
							lbl_Total_Ganado.setText(String.valueOf(totalGanancia));
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "ERROR. No se ha podido conectar a la base de datos. Perdone las molestias."); // MENSAJE QUE SE LE MUESTRA AL USUARIO
					}
				}
			}
		});
		btnCalcular_Ganancias.setOpaque(true);
		btnCalcular_Ganancias.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCalcular_Ganancias.setBackground(new Color(213, 232, 212));
		btnCalcular_Ganancias.setAlignmentX(0.5f);
		btnCalcular_Ganancias.setBounds(338, 202, 285, 55);
		// ------------------------------------------------------------------------------------------------------
		// BOTÓN CONFIRMACIÓN CAMBIO DE MEMBRESÍA (ALTER EN BBDD)
		// ------------------------------------------------------------------------------------------------------
		btnCambioMembresia = new JButton("CAMBIAR MEMBRESÍA");
		btnCambioMembresia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (comprobar_user) {
					comprobar_user=false;
					if (lbl_Nombre.getText().equalsIgnoreCase("NOMBRE")==false && lbl_Apellidos_User.getText().equalsIgnoreCase("APELLIDOS")==false) {
						// GUARDO EL VALOR DEL comboBox_Tipo_de_membreisa2 PARA LUEGO PODER COMPARARLO CON LA BASE DE DATOS PARA LA CONSULTA DE CAMBIAR MEMBRESÍA
						tipo_de_membreisa2=comboBox_Tipo_de_membreisa2.getSelectedItem().toString().toUpperCase();
						switch (tipo_de_membreisa2) {
						case "CRISTAL_BASIC":
							membresia_id2=1;
							break;
						case "ZAFIRO_STANDARD":
							membresia_id2=2;
							break;
						case "RUBI_STANDARD":
							membresia_id2=3;
							break;
						case "DIAMANTE_PREMIUM":
							membresia_id2=4;
							break;
						default:
							membresia_id2=1;
							break;
						}
						try {
							String correos =txt_Correo.getText().toUpperCase();
							Connection conn = DriverManager.getConnection(url, login, password);
							String sql = "UPDATE CON_MEMBRESIAS SET ID_MEMBRESIAS = ? WHERE ID_USUARIO = (SELECT ID_USUARIO FROM USUARIOS WHERE UPPER(CORREO) = ?)";
							PreparedStatement pstmt = conn.prepareStatement(sql);
							pstmt.setInt(1, membresia_id2);
							pstmt.setString(2, correos);
							pstmt.executeUpdate();
							pstmt.close();
							conn.close();
							JOptionPane.showMessageDialog(null, " El usuario se ha actualizado correctamete.");
							txt_Correo.setText("CORREO");
							lbl_Nombre.setText("NOMBRE");
							lbl_Apellidos_User.setText("APELLIDOS");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "ERROR. No se ha encontrado ningún usuario con el correo --> "+txt_Correo.getText());
					}
				} else {
					comprobar_user=false;
					JOptionPane.showMessageDialog(null, "ERROR. No se ha comprobado el usuario, por favor, compruebe el usuario dándole al botón: COMPROBAR USUARIOS");
				}
			}
		});
		btnCambioMembresia.setOpaque(true);
		btnCambioMembresia.setBackground(new Color(213,232,212));
		btnCambioMembresia.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCambioMembresia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCambioMembresia.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCambioMembresia.setBounds(345, 569, 285, 55);
		// ------------------------------------------------------------------------------------------------------
		// BOTÓN CONFIRMACIÓN ELIMINACIÓN (DELETE EN BBDD)
		// ------------------------------------------------------------------------------------------------------
		btnConfirmarEliminacion = new JButton("CONFIRMAR ELIMINACIÓN");
		btnConfirmarEliminacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (comprobar_user) {
					comprobar_user=false;
					if (lbl_Nombre.getText().equalsIgnoreCase("NOMBRE")==false && lbl_Apellidos_User.getText().equalsIgnoreCase("APELLIDOS")==false) {
						try {
							String correo = txt_Correo.getText().toUpperCase();
							PreparedStatement selectStatement = connection.prepareStatement("SELECT ID_USUARIO FROM USUARIOS WHERE UPPER(CORREO) = UPPER(?)");
							selectStatement.setString(1, correo);
							ResultSet resultSet = selectStatement.executeQuery();
							if (resultSet.next()) {
								int idUsuario = resultSet.getInt("ID_USUARIO");
								PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM CON_MEMBRESIAS WHERE ID_USUARIO = ?");
								deleteStatement.setInt(1, idUsuario);
								deleteStatement.executeUpdate();
								deleteStatement.close();
							}
							resultSet.close();
							selectStatement.close();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "ERROR. No se ha realizado el eliminado del usuario. Perdone las molestias.");
						}
						try {
							String correo = txt_Correo.getText().toUpperCase();
							PreparedStatement selectStatement = connection.prepareStatement("SELECT ID_USUARIO FROM USUARIOS WHERE UPPER(CORREO) = UPPER(?)");
							selectStatement.setString(1, correo);
							ResultSet resultSet = selectStatement.executeQuery();
							if (resultSet.next()) {
								int idUsuario = resultSet.getInt("ID_USUARIO");
								PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM ADMINISTRADORES WHERE ID_USUARIO = ?");
								deleteStatement.setInt(1, idUsuario);
								deleteStatement.executeUpdate();
								deleteStatement.close();
							}
							resultSet.close();
							selectStatement.close();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "ERROR. No se ha realizado el eliminado del usuario. Perdone las molestias.");
						}
						try {
							String correo = txt_Correo.getText().toUpperCase();
							PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM USUARIOS WHERE UPPER(CORREO) = UPPER(?)");
							deleteStatement.setString(1, correo);
							deleteStatement.executeUpdate();
							deleteStatement.close();
							JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO CORRECTAMENTE");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "ERROR. No se ha realizado el eliminado del usuario. Perdone las molestias.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "ERROR. No se ha encontrado ningún usuario con el correo --> "+txt_Correo.getText());
					}
				} else {
					comprobar_user=false;
					JOptionPane.showMessageDialog(null, "ERROR. No se ha comprobado el usuario, por favor, compruebe el usuario dandole al botón COMPROBAR USUARIOS");
				}
			}
		});
		btnConfirmarEliminacion.setForeground(new Color(255, 51, 51));
		btnConfirmarEliminacion.setOpaque(true);
		btnConfirmarEliminacion.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnConfirmarEliminacion.setBackground(new Color(255, 255, 255));
		btnConfirmarEliminacion.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnConfirmarEliminacion.setBounds(660, 569, 250, 55);
		// ------------------------------------------------------------------------------------------------------
		// BOTÓN CONFIRMAR SI EXISTE EL USUARIO
		// ------------------------------------------------------------------------------------------------------
		btnComprobar_users = new JButton("COMPROBAR USUARIOS");
		btnComprobar_users.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// GUARDAMOS EN UNA VARIABLE EL VALOR DEL TEXTO DE txt_Correo
				String correos =txt_Correo.getText().toUpperCase();
				// VARIABLE CONTADOR QUE NOS VA A INDICAR SI EXISTE EL CORREO O NO
				int contador_correo=0;
				// COMPROBAMOS CON UN SELECT SI EXISTE EL CORREO EN LA BASE DE DATOS
				try {
					// NOS CONECTAMOS A LA BASE DE DATOS
					st=connection.createStatement();
					// REALIZAMOS EL SELECT CON LOS DATOS QUE QUEREMOS ALMACENAR (? --> ES UN CAMPO QUE LO VA A ADQUIRIR DE LA VARIABLE correos) (SIRVE PARA CREAR LA SENTENCIA SQL)
					PreparedStatement statement = connection.prepareStatement("select count(ID_USUARIO) from USUARIOS WHERE CORREO = ?");
					// LE DECIMOS QUE LA ? ANTERIOR USE EL VALOR DE LA VARIABLE correos (1 ES EL NÚMERO DE ? Y correos EL VALOR QUE LE QUEREMOS METER, EN ESTE CASO EL VALOR ALMACENADO EN LA VARIABLE)
					statement.setString(1, correos);
					// EJECUTAMOS LA QUERY ANTERIOR
					rs = statement.executeQuery();
					// PARA RECORER LAS LÍNEAS QUE NOS SALGA EN LA SENTENCIA QUERY ANTERIOR
					// MIENTRAS QUE HAYA SIGUIENTE
					while (rs.next()) {
						// ALMACENA EL DATO DE LA COLUMNA NOMBRE EN LA VARIABLE nombre
						contador_correo = rs.getInt(1);
					}
					// SI NO SE HA CONECTAR A LA BASE DE DATOS POR CUALQUIER MOTIVO, SALTA UN MENSAJE AL USUARIO
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "ERROR. No se ha podido conectar a la base de datos. Perdone las molestias."); // MENSAJE QUE SE LE MUESTRA AL USUARIO
				}
				if (contador_correo==1) {
					comprobar_user=true;
					// CREAMOS DOS VARIABLES PARA MOSTRAR EL NOMBRE Y APELLIDO DEL USUARIOS QUE CORRESPONDA CON EL CORREO DE LA VARIABLE correos
					String nombre;
					String apellido;
					// EN UN BLOQUE TRY CATCH PARA LAS EXCEPCIONES
					try {
						// NOS CONECTAMOS A LA BASE DE DATOS
						st=connection.createStatement();
						// REALIZAMOS EL SELECT CON LOS DATOS QUE QUEREMOS ALMACENAR (? --> ES UN CAMPO QUE LO VA A ADQUIRIR DE LA VARIABLE correos) (SIRVE PARA CREAR LA SENTENCIA SQL)
						PreparedStatement statement = connection.prepareStatement("select NOMBRE,APELLIDOS from USUARIOS WHERE CORREO = ?");
						// LE DECIMOS QUE LA ? ANTERIOR USE EL VALOR DE LA VARIABLE correos (1 ES EL NÚMERO DE ? Y correos EL VALOR QUE LE QUEREMOS METER, EN ESTE CASO EL VALOR ALMACENADO EN LA VARIABLE)
						statement.setString(1, correos);
						// EJECUTAMOS LA QUERY ANTERIOR
						rs = statement.executeQuery();
						// PARA RECORER LAS LÍNEAS QUE NOS SALGA EN LA SENTENCIA QUERY ANTERIOR
						// MIENTRAS QUE HAYA SIGUIENTE
						while (rs.next()) {
							// ALMACENA EL DATO DE LA COLUMNA NOMBRE EN LA VARIABLE nombre
							nombre = rs.getString("NOMBRE");
							// ALMACENA EL DATO DE LA COLUMNA APELLIDOS EN LA VARIABLE apellido
							apellido = rs.getString("APELLIDOS");
							// CAMBIA EL TEXTO DE lbl_Nombre AL VALOR DE LA VARIABLE nombre
							lbl_Nombre.setText(nombre);
							// CAMBIA EL TEXTO DE lbl_Apellidos_User AL VALOR DE LA VARIABLE apellido
							lbl_Apellidos_User.setText(apellido);
						}
						// SI NO SE HA CONECTADO A LA BASE DE DATOS POR CUALQUIER MOTIVO, SALTA UN MENSAJE AL USUARIO
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "ERROR. No se ha podido conectar a la base de datos. Perdone las molestias."); // MENSAJE QUE SE LE MUESTRA AL USUARIO
					}
				} else {
					comprobar_user=false;
					JOptionPane.showMessageDialog(null, "No se ha encontrado ningún usuario con el correo --> "+correos+".");
					txt_Correo.setText("CORREO");
					lbl_Nombre.setText("NOMBRE");
					lbl_Apellidos_User.setText("APELLIDOS");
				}
			}
		});
		btnComprobar_users.setOpaque(true);
		btnComprobar_users.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnComprobar_users.setBackground(new Color(213, 232, 212));
		btnComprobar_users.setAlignmentX(0.5f);
		btnComprobar_users.setBounds(30, 569, 285, 55);
		// ------------------------------------------------------------------------------------------------------
		//  FLECHA DEL DESPLEGABLE
		// ------------------------------------------------------------------------------------------------------
		lbl_FleDesple = new JLabel("");
		lbl_FleDesple.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lbl_FleDesple.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_FleDesple.setBounds(0, 15, 23, 26);
		// AJUSTAR IMAGEN AL CONTENIDO
		ImageIcon fleDesple = new ImageIcon(getClass().getResource("/Trilogy_imagenes/IconoDesplegableEncoger.png")); // CREACIÓN NUEVO OBJETO CON LA RUTA DE LA IMAGEN
		ImageIcon imgfleDesple = new ImageIcon(fleDesple.getImage().getScaledInstance(lbl_FleDesple.getWidth(), lbl_FleDesple.getHeight(), Image.SCALE_SMOOTH)); // CREAMOS OTRO OBJETO PARA QUE SE AJUSTE AUTOMÁTICAMENTE LA IMAGEN AL LABEL
		lbl_FleDesple.setIcon(imgfleDesple); // DECIMOS QUE EL ICONO ES IGUAL A EL OBJETO QUE SE AJUSTA AUTOMÁTICAMENTE AL TAMAÑO DEL LABEL
		// EVENTOS CON EL RATÓN
		lbl_FleDesple.addMouseListener(new MouseAdapter() {
			@Override
			// CUANDO EL RATÓN ES PULSADO
			public void mouseClicked(MouseEvent e) {
				if (panelLateral.getBounds().width == 219) {
					// CAMBIA EL TAMAÑO Y LA POSICIÓN DE LOS ELEMENTOS DE LA IZQUIERDA (CUANDO LO MINIMIZA)
					panelLateral.setBounds(0,0,72,664);
					ImageIcon fleDespleDesplegar = new ImageIcon(getClass().getResource("/Trilogy_imagenes/IconoDesplegableDesplegar.png")); // CREACIÓN NUEVO OBJETO CON LA RUTA DE LA IMAGEN
					ImageIcon imgfleDespleDesplegar = new ImageIcon(fleDespleDesplegar.getImage().getScaledInstance(lbl_FleDesple.getWidth(), lbl_FleDesple.getHeight(), Image.SCALE_SMOOTH));
					panel_Contenido_Admin.setBounds(70,0,1130,664);
					lbl_Img_Fondo.setBounds(0, 0, 1130, 664);
					ImageIcon fondo = new ImageIcon(getClass().getResource("/Trilogy_imagenes/Fondo_Formulario.png")); // CREACIÓN NUEVO OBJETO CON LA RUTA DE LA IMAGEN
					ImageIcon imgFondo = new ImageIcon(fondo.getImage().getScaledInstance(lbl_Img_Fondo.getWidth(), lbl_Img_Fondo.getHeight(), Image.SCALE_SMOOTH)); // CREAMOS OTRO OBJETO PARA QUE SE AJUSTE AUTOMÁTICAMENTE LA IMAGEN AL LABEL
					lbl_Img_Fondo.setIcon(imgFondo);
					lbl_FleDesple.setIcon(imgfleDespleDesplegar);
					lbl_Texto_1.setBounds(30, 35, 1070, 48);
					lbl_Texto_2.setBounds(30, 283, 1070, 48);
					comboBox_Tipo_de_membreisa1.setBounds(50, 124, 356, 45);
					btnCalcular_Ganancias.setBounds(340, 202, 450, 55); //
					lbl_Total_membresias.setBounds(426, 124, 296, 45);
					lbl_Total_Ganado.setBounds(742, 124, 327, 45);
					txt_Correo.setBounds(50, 360, 597, 45);
					lbl_Nombre.setBounds(672, 360, 397, 45);
					lbl_Apellidos_User.setBounds(74, 460, 550, 45);
					comboBox_Tipo_de_membreisa2.setBounds(662, 460, 369, 45);
					btnComprobar_users.setBounds(30, 569, 334, 55);
					btnCambioMembresia.setBounds(399, 569, 334, 55);
					btnConfirmarEliminacion.setBounds(769, 569, 299, 55);
					EsconderBotones();
				} else {
					// CAMBIA EL TAMAÑO Y LA POSICIÓN DE LOS ELEMENTOS DE LA IZQUIERDA (CUANDO LO ENSANCHA)
					panelLateral.setBounds(0,0,219,664);
					panel_Contenido_Admin.setBounds(219,0,961,664);
					lbl_Img_Fondo.setBounds(0, 0, 961, 664);
					ImageIcon fondo = new ImageIcon(getClass().getResource("/Trilogy_imagenes/Fondo_Formulario.png")); // CREACIÓN NUEVO OBJETO CON LA RUTA DE LA IMAGEN
					ImageIcon imgFondo = new ImageIcon(fondo.getImage().getScaledInstance(lbl_Img_Fondo.getWidth(), lbl_Img_Fondo.getHeight(), Image.SCALE_SMOOTH)); // CREAMOS OTRO OBJETO PARA QUE SE AJUSTE AUTOMÁTICAMENTE LA IMAGEN
					lbl_Img_Fondo.setIcon(imgFondo);
					lbl_FleDesple.setIcon(imgfleDesple);
					lbl_Texto_1.setBounds(30, 35, 901, 48);
					lbl_Texto_2.setBounds(30, 283, 901, 48);
					comboBox_Tipo_de_membreisa1.setBounds(50, 124, 300, 45);
					btnCalcular_Ganancias.setBounds(338, 202, 285, 55);
					lbl_Total_membresias.setBounds(375, 124, 240, 45);
					lbl_Total_Ganado.setBounds(640, 124, 270, 45);
					txt_Correo.setBounds(50, 360, 485, 45);
					lbl_Nombre.setBounds(570, 360, 340, 45);
					lbl_Apellidos_User.setBounds(74, 460, 450, 45);
					comboBox_Tipo_de_membreisa2.setBounds(593, 460, 300, 45);
					btnComprobar_users.setBounds(30, 569, 285, 55);
					btnCambioMembresia.setBounds(345, 569, 285, 55);
					btnConfirmarEliminacion.setBounds(660, 569, 250, 55);
					MostrarBotones();
				}
			}
		});
		// ------------------------------------------------------------------------------------------------------
		// JERARQUÍA DEL PANEL PRINCIPAL
		// ------------------------------------------------------------------------------------------------------
		panel_Contenido_Admin.add(btnCalcular_Ganancias);
		panel_Contenido_Admin.add(btnComprobar_users);
		panel_Contenido_Admin.add(lbl_FleDesple);
		panel_Contenido_Admin.add(lbl_Texto_1);
		panel_Contenido_Admin.add(lbl_Texto_2);
		panel_Contenido_Admin.add(comboBox_Tipo_de_membreisa1);
		panel_Contenido_Admin.add(lbl_Total_membresias);
		panel_Contenido_Admin.add(lbl_Total_Ganado);
		panel_Contenido_Admin.add(txt_Correo);
		panel_Contenido_Admin.add(lbl_Nombre);
		panel_Contenido_Admin.add(lbl_Apellidos_User);
		panel_Contenido_Admin.add(comboBox_Tipo_de_membreisa2);
		panel_Contenido_Admin.add(btnCambioMembresia);
		panel_Contenido_Admin.add(btnConfirmarEliminacion);
		panel_Contenido_Admin.add(lbl_Img_Fondo);
		// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	}
}