import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.*;

public class Execution implements InterListe {
	
	static JFrame frame;
	static JLayeredPane panel;
	/*
	static ArrayList<Quiz> listeQuiz;
	static ArrayList<Player_QUIZ> listePlayer_QUIZ;
	*/
	static ArrayList<ButtonGroup> listeButtonGroup;
	static Player player1;
	static Timer t; 
	static JLabel lapelTime;

	public static void main(String[] args) {
	 
 
		listeButtonGroup = new ArrayList<ButtonGroup>();
		ArrayList<Quiz> listeQuiz = new ArrayList<Quiz>();
		  ArrayList<Player_QUIZ> listePlayer_QUIZ = new ArrayList<Player_QUIZ>();
	 
		
		frame = new JFrame("Quiz");
		frame.setBounds(100, 100, 700, 500);
		panel = new JLayeredPane();
		frame.getContentPane().add(panel);
		login();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lapelTime= new JLabel();
	}
	
	public static void login() {
		
		JLabel label_nom = new JLabel("nom");
		label_nom.setBounds(30, 30, 60, 20);
		panel.add(label_nom);
		
		JLabel label_prenom = new JLabel("prenom");
		label_prenom.setBounds(30, 70, 60, 20);
		panel.add(label_prenom);
		
		JLabel label_age = new JLabel("age");
		label_age.setBounds(30, 110, 60, 20);
		panel.add(label_age);
		
		JTextField txt_nom = new JTextField();
		txt_nom.setBounds(90, 30, 140, 20);
		panel.add(txt_nom);
		
		JTextField txt_prenom = new JTextField();
		txt_prenom.setBounds(90, 70, 140, 20);
		panel.add(txt_prenom);
		
		JTextField txt_age = new JTextField();
		txt_age.setBounds(90, 110, 140, 20);
		panel.add(txt_age);
		
		JButton btn_debut = new JButton("démarrer le quiz");
		btn_debut.setBounds(80, 150, 140, 25);
		panel.add(btn_debut);
		btn_debut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				player1 = new Player(txt_nom.getText(), txt_prenom.getText(), Integer.parseInt(txt_age.getText()));
				viderPanelNiveau();
				t = new Timer(1000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(player1.getDuration() > 0) {
							player1.setDuration(player1.getDuration()-1);
							lapelTime.setText(LocalTime.MIN.plusSeconds(player1.getDuration()).toString());
						}
						else {
							t.stop();
							JOptionPane.showMessageDialog(null, "GAME OVER");
		                    System.exit(0);
						}
					}
				});
				java.util.Timer tt = new java.util.Timer(false);
		        tt.schedule(new TimerTask() {
		            @Override
		            public void run() {
		                t.start();
		            }
		        }, 0);
				niveau1();
				
			}
		});
	}
	
	public static void niveau1() {
		Quiz quiz1 = new Quiz("JAVA est  un langage", "Compilé et interpreté", "Compilé", "Interprété", "Compilé et interpreté");
		Quiz quiz2 = new Quiz("Toutes les classes héritent de la classe", "Object", "Main", "Object", "AWT");
		Quiz quiz3 = new Quiz("Par convention une classe", "commence par une majuscule", "est en minuscule", "commence par une majuscule", "est en majuscules");
		Quiz quiz4 = new Quiz("Est-ce que on peut avoir plusieurs constructeurs pour la même classe", "oui", "oui", "non");
		Quiz quiz5 = new Quiz("Dans la ligne \"public class A implements B\", B est:", "Interfacce", "Interfacce", "Classe parent", "Package");
		
		listeQuiz.add(quiz1);
		listeQuiz.add(quiz2);
		listeQuiz.add(quiz3);
		listeQuiz.add(quiz4);
		listeQuiz.add(quiz5);
		
		remplirePanelNiveau();
		
		JButton btn_valider = new JButton("valider");
		btn_valider.setBounds(500, 400, 100, 30);
		panel.add(btn_valider);
		btn_valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkReponseAllQuestion()) {
					if(calculeScore() >= 40) {
						/*
						viderLesCollection();
						viderPanelNiveau();
						*/
						//affichage du resultat
						System.out.println(nbrReponseCorrect(1,listeQuiz,listePlayer_QUIZ));
						niveau2();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "YOU LOSE !!");
	                    System.exit(0);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Merci de répondre à toutes les questions");
				}
			}
		});
	}
	
	public static void niveau2() {
		
		Quiz quiz1 = new Quiz("Après la compilation, un programme écrit en JAVA, il se transforme en programme en bytecode\r\n"
				+ "Quelle est l’extension du programme en bytecode ?", ".Class", "aucun\r\n"
						+ "des choix", ".JAVA", ".Class");
		Quiz quiz2 = new Quiz("Class Test{\r\n"
				+ "Public Test () {\r\n"
				+ "System.out.println(”Bonjour”);}\r\n"
				+ "public Test (int i) {\r\n"
				+ "this();\r\n"
				+ "System.out.println(”Nous sommes en ”+i+ ” !”);}; }\r\n"
				+ "qu’affichera l’instruction suivante?\r\n"
				+ "Test t1=new Test (2018);", "aucun\r\n"
						+ "des choix", "aucun\r\n"
								+ "des choix", "Bonjour\r\n"
										+ "Nous sommes en 2018 !", "Nous sommes en 2018 !");
		Quiz quiz3 = new Quiz("Voici un constructeur de la classe Employee, y-a-t'il un problème ?\r\n"
				+ "Public void Employe(String n){\r\n"
				+ "Nom=n;}", "faux", "vrai", "faux");
		Quiz quiz4 = new Quiz("Pour spécifier que la variable ne pourra plus être modifiée et doit être initialisée dès sa déclaration, on la déclare comme une constante avec le mot réservé", "final", "aucun\r\n"
				+ "des choix", "final","const");
		Quiz quiz5 = new Quiz("Dans une classe, on accède à ses variables grâce au mot clé", "this", "aucun\r\n"
				+ "des choix", "this", "super");
		
		listeQuiz.add(quiz1);
		listeQuiz.add(quiz2);
		listeQuiz.add(quiz3);
		listeQuiz.add(quiz4);
		listeQuiz.add(quiz5);
		
		remplirePanelNiveau();
		
		JButton btn_valider = new JButton("valider");
		btn_valider.setBounds(500, 400, 100, 30);
		panel.add(btn_valider);
		btn_valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkReponseAllQuestion()) {
					if(calculeScore() >= 60) {
						/*
						viderLesCollection();
						
						*/
						viderPanelNiveau();
						//affichage du resultat
						System.out.println(nbrReponseCorrect(2,listeQuiz,listePlayer_QUIZ));
						niveau3();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "YOU LOSE !!");
	                    System.exit(0);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Merci de répondre à toutes les questions");
				}
			}
		});
		 
		
	}
	
	public static void niveau3() {
		
	}
	
	public static int nbrReponseCorrect(int niveau ,ArrayList<Quiz> q1,ArrayList<Player_QUIZ> pq)
	{
		  int start , end;
		if(niveau == 1)
		{
			start=0;
			end  =5;
 
		}
		else if(niveau == 2)
		{
			start=5;
			end  =10;
		}
		else
		{
			start=10;
			end  =15;
		}
		 int totalReponseCorrectParNiveau =0;
 
		  
		  
		 for(int i = start; i<end; i++) {
 			 
			 if(q1.get(i).getId_quiz() == pq.get(i).getId_quiz())
			 {
				 if(q1.get(i).getReponse().equals(pq.get(i).getReponseChosen()))
				 {
					 totalReponseCorrectParNiveau++;
				 
					 
				 }
				  
			 }
			 
			}
		 
		 
		 return totalReponseCorrectParNiveau;
	}
	
	public static void remplirePanelNiveau() {
		lapelTime.setBounds(600,20,70,20);
		panel.add(lapelTime);
		int y=30;
		for(int i = 0;i<listeQuiz.size();i++) {
			int x=30;
			JLabel lapel_question = new JLabel(listeQuiz.get(i).getQuestion());
			lapel_question.setBounds(x, y, 600, 30);
			panel.add(lapel_question);
			
			y += 30;
			JRadioButton choise1 = new JRadioButton(listeQuiz.get(i).getChoice_one());
			choise1.setActionCommand(listeQuiz.get(i).getChoice_one());
			choise1.setBounds(x, y, 200, 30);
			panel.add(choise1);
			
			x += 200;
			JRadioButton choise2 = new JRadioButton(listeQuiz.get(i).getChoice_two());
			choise2.setActionCommand(listeQuiz.get(i).getChoice_two());
			choise2.setBounds(x, y, 200, 30);
			panel.add(choise2);

			ButtonGroup bg = new ButtonGroup();
			bg.add(choise1);
			bg.add(choise2);
			
			if(listeQuiz.get(i).getChoice_three() != null) {
				x += 200;
				JRadioButton choise3 = new JRadioButton(listeQuiz.get(i).getChoice_three());
				choise3.setActionCommand(listeQuiz.get(i).getChoice_three());
				choise3.setBounds(x, y, 200, 30);
				panel.add(choise3);
				bg.add(choise3);
			}
			y += 40;
			listeButtonGroup.add(bg);
		}
	}
	
	public static void viderPanelNiveau() {
		panel.removeAll();
		panel.repaint();
	}
	
	public static void getreponses()
	{
		for(int i = 0;i<listeQuiz.size();i++) {
			boolean choice; 
			if(listeQuiz.get(i).getReponse() == listeButtonGroup.get(i).getSelection().getActionCommand()) {
				choice=true;
			}
			else {
				choice=false;
			}
			Player_QUIZ player_QUIZ = new Player_QUIZ(player1.getId_Player(), listeQuiz.get(i).getId_quiz(), listeButtonGroup.get(i).getSelection().getActionCommand(), choice);
			listePlayer_QUIZ.add(player_QUIZ);
		}
	}

	public static int calculeScore()
	{
		getreponses();
		int score = 0;
		for(int i = 0;i<listePlayer_QUIZ.size();i++) {
			if(listePlayer_QUIZ.get(i).isGoodchoice()) {
				score += 20;
			}
		}
		return score;
	}
	
	public static void viderLesCollection()
	{
		listeButtonGroup.clear();
		listePlayer_QUIZ.clear();
		listeQuiz.clear();
	}
	
	public static boolean checkReponseAllQuestion(){
		boolean ret=true;
		for(int i = 0;i<listeButtonGroup.size();i++) {
			if(listeButtonGroup.get(i).getSelection() == null) {
				ret = false;
			}
		}
		return ret;
	}
}
