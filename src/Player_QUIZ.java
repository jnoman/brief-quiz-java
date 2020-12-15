public class Player_QUIZ implements InterListe{
	 
	   private int id_Player;  
	   private int id_quiz;
	   private  String reponseChosen;
	   private boolean goodchoice;

	   
	   public Player_QUIZ(int idP, int idQ, String RC, boolean choice)
	   {
		   setId_Player(idP);
		   setId_quiz(idQ);
		   setReponseChosen(RC);
		   setGoodchoice(choice);
	   }
	    
	   
	    
	   /*
	    
	   public static double calculScore()
	   {
		   boolean trouve=false;
		   double totalReponseCorrectParNiveau =0;
		   //a changer
		   if(cpt==1)
		   {
			   int j=0;
			  

				for(Quiz L : listeQuiz  ){
					 totalReponseCorrectParNiveau =2;
					while(!trouve)
					{
						if(L.getId_quiz() == listePlayer_QUIZ.get(j).getId_quiz())
						{
							if(L.getReponse().equals(listePlayer_QUIZ.get(j).getReponseChosen()))
							{
								totalReponseCorrectParNiveau++;
								trouve=true;
								break;
							}
						}
						else
						{
							j++;
						}
					 
					  }
					}
				cpt=0;
				
				return totalReponseCorrectParNiveau;
			   
		   }
		   return -1;
		   
	   }
	   */
	   
	   // good loop
	   /*
	   List<Dept> d = new ArrayList<Dept>();
	   List<Emp> e = new ArrayLlist<Emp>();
	   public void loop()
		   { int min = Math.min(d.size(), e.size());
		   for(int i = 0; i<min; i++) {
			   System.out.println("---"+e.get(i)+"---"+d.get(i));
			}
	   }
	   */

	public int getId_Player() {
		return id_Player;
	}

	public void setId_Player(int id_Player) {
		this.id_Player = id_Player;
	}

	 

	public int getId_quiz() {
		return id_quiz;
	}

	public void setId_quiz(int id_quiz) {
		this.id_quiz = id_quiz;
	}
	public boolean isGoodchoice() {
		return goodchoice;
	}
	public void setGoodchoice(boolean goodchoice) {
		this.goodchoice = goodchoice;
	}

	public String getReponseChosen() {
		return reponseChosen;
	}

	public void setReponseChosen(String reponseChosen) {
		this.reponseChosen = reponseChosen;
	}

 

	
	   
	  


}
