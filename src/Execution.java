import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;

public class Execution implements InterListe {
	
	static JFrame frame;
	static JLayeredPane panel;
	static ArrayList<ButtonGroup> listeButtonGroup;
	static Players player1;
	static Timer t; 
	static JLabel lapelTime;
	String chemin;
	static Player player;
	static int cpt=0;
	static JButton btn_valider;
	static JLabel lapel_score;

	public static void main(String[] args) {
	 
 
		listeButtonGroup = new ArrayList<ButtonGroup>();
		ArrayList<Quiz> listeQuiz = new ArrayList<Quiz>();
		  ArrayList<Player_QUIZ> listePlayer_QUIZ = new ArrayList<Player_QUIZ>();
	 
		
		frame = new JFrame("Quiz");
		frame.setBounds(100, 100, 800, 600);
		panel = new JLayeredPane();
		frame.getContentPane().add(panel);
		login();
	 
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lapelTime= new JLabel();
		
		playerMusic("quiz-show.mp3",30000);
		
		
	}
	public static void StopMusic()  throws JavaLayerException
	{
		if (player!=null)
		  {
		    player.close();
		    player = null;
		    
		  }
	}
	
	public static void playerMusic(String chemin,int frames)
	{
		
		try 
		{
			FileInputStream fileInputStrem = new FileInputStream(chemin);
			player = new Player(fileInputStrem);
			player.play(frames);
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(JavaLayerException e)
		{
			e.printStackTrace();
		}
		 
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
				 
				player1 = new Players(txt_nom.getText(), txt_prenom.getText(), Integer.parseInt(txt_age.getText()));
				//viderPanelNiveau();
				t = new Timer(1000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(player1.getDuration() > 0) {
							player1.setDuration(player1.getDuration()-1);
							lapelTime.setText(LocalTime.MIN.plusSeconds(player1.getDuration()).toString());
							frame.setTitle(NiveauActuel(listeQuiz));
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
		
		remplirePanelNiveau(1);
		

		btn_valider = new JButton("valider");
		btn_valider.setBounds(650, 500, 100, 30);
		panel.add(btn_valider);
		btn_valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkReponseAllQuestion(1)) {
					afficheCorrection(1);
				}
				else {
					JOptionPane.showMessageDialog(null, "Merci de répondre à toutes les questions");
				}
			}
		});
	}
	
	public static void niveau2() {
		
		Quiz quiz1 = new Quiz("Après la compilation, un programme écrit en JAVA, il se transforme en programme en bytecode. Quelle est l’extension du programme en bytecode ?", ".Class", "aucun des choix", ".JAVA", ".Class");
		Quiz quiz2 = new Quiz("Class Test{Public Test () {System.out.println(”Bonjour”);}public Test (int i) {this(); System.out.println(”Nous sommes en ”+i+”!”);}; }qu’affichera l’instruction suivante? Test t1=new Test (2020);", "Bonjour Nous sommes en 2020 !", "aucun des choix", "Bonjour Nous sommes en 2020 !", "Nous sommes en 2020 !");
		Quiz quiz3 = new Quiz("Voici un constructeur de la classe Employee, y-a-t'il un problème Public void Employe(String n){Nom=n;}", "vrai", "vrai", "faux");
		Quiz quiz4 = new Quiz("Pour spécifier que la variable ne pourra plus être modifiée et doit être initialisée dès sa déclaration, on la déclare comme une constante avec le mot réservé", "final", "aucun des choix", "final","const");
		Quiz quiz5 = new Quiz("Dans une classe, on accède à ses variables grâce au mot clé", "this", "aucun des choix", "this", "super");
		
		listeQuiz.add(quiz1);
		listeQuiz.add(quiz2);
		listeQuiz.add(quiz3);
		listeQuiz.add(quiz4);
		listeQuiz.add(quiz5);
		
		remplirePanelNiveau(2);
		
		JButton btn_valider = new JButton("valider");
		btn_valider.setBounds(650, 500, 100, 30);
		panel.add(btn_valider);
		btn_valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkReponseAllQuestion(2)) {
					afficheCorrection(2);
				}
				else {
					JOptionPane.showMessageDialog(null, "Merci de répondre à toutes les questions");
				}
			}
		});
	}
	
	public static void niveau3() {
		Quiz quiz1 = new Quiz("calculerSalaire(int) calculerSalaire(int, double)La méthode calculerSalaire est:", "surchargée", "aucun des choix", "surchargée", "redéfinie");
		Quiz quiz2 = new Quiz("Une classe qui contient au moins une méthode abstraite doit être déclarée abstraite.", "vrai", "vrai", "faux");
		Quiz quiz3 = new Quiz("Est-ce qu’une classe peut implémenter plusieurs interfaces?", "vrai", "vrai", "faux");
		Quiz quiz4 = new Quiz("La déclaration d'une méthode suivante :public void traitement() throws IOExceptionprécise que la méthode propage une exception contrôlée", "vrai", "vrai", "faux");
		Quiz quiz5 = new Quiz("class Test{public static void main (String[] args) {try {int a =10;System.out.println (\"a = \" + a );int b = 0 / a;System.out.println (\"b = \" + b);}catch(ArithmeticException e){System.out.println (\"diviser par 0!\"); }finally{System.out.println(\"je suis à l’intérieur de finally\");}}}", "a=10 b=0 Je suis à l’intérieur de finally", "aucun des choix", "a=10 b=0 Je suis à l’intérieur de finally", "a=10 b=0 diviser par 0! je suis à l’intérieur de finally");
		
		listeQuiz.add(quiz1);
		listeQuiz.add(quiz2);
		listeQuiz.add(quiz3);
		listeQuiz.add(quiz4);
		listeQuiz.add(quiz5);
		
		remplirePanelNiveau(3);
		
		JButton btn_valider = new JButton("valider");
		btn_valider.setBounds(650, 500, 100, 30);
		panel.add(btn_valider);
		btn_valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkReponseAllQuestion(3)) {
					afficheCorrection(3);
				}
				else {
					JOptionPane.showMessageDialog(null, "Merci de répondre à toutes les questions");
				}
			}
		});
		
	}
	
	public static String NiveauActuel(ArrayList<Quiz> q)
	{
		String ret ="QUIZ";
		if(q.size()==5)
		{
			ret= "Niveau 1";
		}
		else if (q.size()==10)
		{
			ret= "Niveau 2";
		}
		else if(q.size()==15)
		{
			ret= "Niveau 3";
		}
		return ret;
		
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
	
	public static void remplirePanelNiveau(int niveau) {
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
		panel.removeAll();
		panel.repaint();
		lapel_score =new JLabel();
		lapel_score.setBounds(700,30,70,20);
		panel.add(lapel_score);
		lapelTime.setBounds(700,10,70,20);
		panel.add(lapelTime);
		int y=40;
		for(int i = start;i<end;i++) {
			int x=30;
			JLabel lapel_question = new JLabel("<html><p>"+listeQuiz.get(i).getQuestion()+"</p></html>");
			lapel_question.setBounds(x, y, 700, 60);
			panel.add(lapel_question);
			
			y += 50;
			JRadioButton choise1 = new JRadioButton("<html><p>"+listeQuiz.get(i).getChoice_one()+"</p></html>");
			choise1.setActionCommand(listeQuiz.get(i).getChoice_one());
			choise1.setBounds(x, y, 200, 30);
			panel.add(choise1);
			
			x += 200;
			JRadioButton choise2 = new JRadioButton("<html><p>"+listeQuiz.get(i).getChoice_two()+"</p></html>");
			choise2.setActionCommand(listeQuiz.get(i).getChoice_two());
			choise2.setBounds(x, y, 200, 30);
			panel.add(choise2);

			ButtonGroup bg = new ButtonGroup();
			bg.add(choise1);
			bg.add(choise2);
			
			if(listeQuiz.get(i).getChoice_three() != null) {
				x += 200;
				JRadioButton choise3 = new JRadioButton("<html><p>"+listeQuiz.get(i).getChoice_three()+"</p></html>");
				choise3.setActionCommand(listeQuiz.get(i).getChoice_three());
				choise3.setBounds(x, y, 200, 30);
				panel.add(choise3);
				bg.add(choise3);
			}
			y += 40;
			listeButtonGroup.add(bg);
		}
	}
	
	public static void getreponses(int niveau)
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
		for(int i = start;i<end;i++) {
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

	public static int calculeScore(int niveau)
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
		int score = 0;
		for(int i = start;i<end;i++) {
			if(listePlayer_QUIZ.get(i).isGoodchoice()) {
				score += 20;
			}
		}
		return score;
	}
	
	public static boolean checkReponseAllQuestion(int niveau){
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
		boolean ret=true;
		for(int i = start;i<end;i++) {
			if(listeButtonGroup.get(i).getSelection() == null) {
				ret = false;
			}
		}
		return ret;
	}
	
	public static void correction(int niveau){
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
		for(int i = start;i<end;i++) {
			if(listePlayer_QUIZ.get(i).isGoodchoice()) {
				Enumeration<AbstractButton> radsV = listeButtonGroup.get(i).getElements();
		        while (radsV.hasMoreElements()) {
		            JRadioButton temp = (JRadioButton) radsV.nextElement();
		            if (temp.isSelected()) {
		                temp.setForeground(Color.green);
		                break;
		            }
		        }
			}
			else{
				Enumeration<AbstractButton> radsV = listeButtonGroup.get(i).getElements();
		        while (radsV.hasMoreElements()) {
		            JRadioButton temp = (JRadioButton) radsV.nextElement();
		            if (temp.isSelected()) {
		                temp.setForeground(Color.red);
		            }
		            else if(temp.getActionCommand() == listeQuiz.get(i).getReponse()) {
		            	temp.setForeground(Color.green);
		            }
		        }
			}
		}
	}
	public static void afficheCorrection(int niveau) {
			if(cpt==0) {
				getreponses(niveau);
				cpt=1;
				btn_valider.setText("Suivant");
				correction(niveau);
				lapel_score.setText("score : " + calculeScore(niveau));
			}
			else {
				cpt=0;
				if(niveau==1) {
					if(calculeScore(1) >= 40) {
						
						niveau2();
						
					}
					else {
						 try {
								StopMusic();
							} catch (JavaLayerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						playerMusic("lose.mp3",100);
						 String invisibleChar= "\u200e";
							final ImageIcon icon = new ImageIcon("lose.gif");
	                        JOptionPane.showMessageDialog(null,invisibleChar, "YOU LOST HAHAHAH", JOptionPane.INFORMATION_MESSAGE, icon);
						
	                    System.exit(0);
					}
				}
				else if(niveau==2) {
					if(calculeScore(2) >= 60) {
						niveau3();
						
					}
					else {
						 try {
								StopMusic();
							} catch (JavaLayerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						playerMusic("lose.mp3",100);
						 String invisibleChar= "\u200e";
							final ImageIcon icon = new ImageIcon("lose.gif");
	                        JOptionPane.showMessageDialog(null,invisibleChar, "YOU LOST HAHAHAH ", JOptionPane.INFORMATION_MESSAGE, icon);
						
	                    System.exit(0);
					}
				}
				else {
					if(calculeScore(3) >= 80) {

						//affichage du resultat
						t.stop();
						try {
							StopMusic();
						} catch (JavaLayerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						playerMusic("win.mp3",100);
	                  
	                   String invisibleChar= "\u200e";
						final ImageIcon icon = new ImageIcon("source.gif");
                        JOptionPane.showMessageDialog(null,invisibleChar, "YOU WON ! ", JOptionPane.INFORMATION_MESSAGE, icon);
						 try {
								StopMusic();
							} catch (JavaLayerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						  System.exit(0);
						
					}
					else {
						 try {
								StopMusic();
							} catch (JavaLayerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						playerMusic("lose.mp3",100);
						 String invisibleChar= "\u200e";
							final ImageIcon icon = new ImageIcon("lose.gif");
	                        JOptionPane.showMessageDialog(null,invisibleChar, "YOU LOST HAHAHAH ", JOptionPane.INFORMATION_MESSAGE, icon);
						
	                    System.exit(0);
					}
					
				}
		}
	}
}
