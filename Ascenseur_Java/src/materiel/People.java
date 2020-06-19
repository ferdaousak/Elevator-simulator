package materiel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import materiel.Ascenseur.Mode;

import javax.imageio.ImageIO;

public class People
{
	//image graphic
	private static Image icon;
	//position du personne
	private int Ax,Ay;
	//Target destinationX for people
	private int DestX;
	//étage originale 
	private Floor CurrentF;
	//destinatio
	private Floor DestinationF;
	//vitesse de mouvement
	private int PSpeed = 1;
	//person state
	private PMode state;
	private PMode direction;

	public People(int Ax,int Ay)
	{
		this.Ax=Ax;
		this.Ay=Ay;
		this.state = PMode.WAIT;
		this.direction = PMode.RIGHT;
		this.DestX = Ax;
	}
	public People(int Ax,int Ay,Floor CurrentF)
	{
		this.Ax=Ax;
		this.Ay=Ay;
		this.CurrentF = CurrentF;
		this.state = PMode.WAIT;
		this.direction = PMode.RIGHT;
		this.DestX = Ax;
	}
	public People(int Ax,int Ay,Floor CurrentF, int DestX)
	{
		this.Ax=Ax;
		this.Ay=Ay;
		this.CurrentF = CurrentF;
		this.state = PMode.WAIT;
		this.direction = PMode.RIGHT;
		this.DestX = DestX;
	}
	public void drawPerson(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		File pathToFile = new File("man.png");
        try
        {
        	g2D.setColor(new Color(237,245,247));  
    		g2D.setStroke(new BasicStroke(3));
    		
			Image img = ImageIO.read(pathToFile);
			g.drawImage(img, Ax+50, Ay,60,50, null);	
			g.drawString(" Dest : " + DestinationF.getNumber(),Ax+40, Ay-5);
			if(Ax == DestX)
			{
				state = PMode.WAIT;
			}
			
			else
			{
				state = direction;
			}
			
			MovePerson();
		} catch (IOException e)
        {
			e.printStackTrace();
		}	
	}
	//genérer un nombre spécifique de personnes
	public static ArrayList<People> genPeoples(ArrayList<Floor> Floors,int n)
	{
		ArrayList<People> peoples = new ArrayList<People>();
		for(int i=0; i<n;i++)
		{
			//random number generation from 0 to (n-1)
			int min=0, max=n-1;
			//Destination floor random number
			int DFn;
			do
			{
				 DFn = (int)(Math.random() * (max - min + 1) + min);
			}while(DFn == i);
			//la nouvelle personne
			People p;
			//selectionner l'étage
			int Fn=i;
			if(i<3)
			{
				//creer la personne et affecter l'étage sélectionner
				p = new People(40,Floors.get(Fn).getAy()+48,Floors.get(Fn));
				//affecter une destination au hasard
				p.setDestinationF(Floors.get(DFn));
			}else
			{
				//same thing but for the bottom floors
				p = new People(40,Floors.get(Fn).getAy()+48,Floors.get(Fn));
				p.setDestinationF(Floors.get(DFn));
			}
			Floors.get(Fn).addPerson(p);
			peoples.add(p);
		}
		
		return peoples;
	}
	
	public static void genPerson(ArrayList<People> People, ArrayList<Floor> Floors)
	{
		//random number generation from 0 to (n-1)
		int min=0, max=Floors.size()-1;
		//Destination floor random number
		int DFn = (int)(Math.random() * (max - min + 1) + min);
		int Fn;
		do
		{
			Fn = (int)(Math.random() * (max - min + 1) + min);

		}while(Fn==DFn);
		//la nouvelle personne
		People p;
		//selectionner l'étage
		
		if(Fn<3)
		{
			//creer la personne et affecter l'étage sélectionner
			p = new People(90,Floors.get(Fn).getAy()+48,Floors.get(Fn));
			//affecter une destination au hasard
			p.setDestinationF(Floors.get(DFn));
		}else
		{
			//same thing but for the bottom floors
			p = new People(40,Floors.get(Fn).getAy()+48,Floors.get(Fn));
			p.setDestinationF(Floors.get(DFn));
		}
		Floors.get(Fn).addPerson(p);
		People.add(p);
	}
	public void MovePerson()
	{
		switch(state)
		{
			case WAIT : break;
						
			case LEFT : this.setAx(this.getAx() - PSpeed);
						break;
			
			case RIGHT : this.setAx(this.getAx() + PSpeed);
						break;
						
			default : 	System.err.println("WRONG STATE!");
						break;
		}
	}
	
	public static void drawPeople(ArrayList<People> ps,Graphics g)
	{
		for(People p: ps)
		{
			p.drawPerson(g);
		}
	}
	public static Image getIcon()
	{
		return icon;
	}
	public static void setIcon(Image icon)
	{
		People.icon = icon;
	}
	public Floor getCurrentF()
	{
		return CurrentF;
	}
	public void setCurrentF(Floor currentF)
	{
		CurrentF = currentF;
	}
	public Floor getDestinationF()
	{
		return DestinationF;
	}
	public void setDestinationF(Floor destinationF)
	{
		DestinationF = destinationF;
	}
	public PMode getState()
	{
		return state;
	}

	public void setState(PMode state)
	{
		this.state = state;
	}
	public int getDestX()
	{
		return DestX;
	}
	public void setDestX(int DestX)
	{
		this.DestX = DestX;
	}
	public int getAx()
	{
		return Ax;
	}
	public void setAx(int ax)
	{
		Ax = ax;
	}
	public int getAy()
	{
		return Ay;
	}
	public void setAy(int ay)
	{
		Ay = ay;
	}
	public int getPSpeed()
	{
		return PSpeed;
	}
	
	public void setPSpeed(int PSpeed)
	{
		this.PSpeed = PSpeed;
	}
	
	@Override
	public String toString()
	{
		return "People [Ax=" + Ax + ", Ay=" + Ay + ", CurrentF=" + CurrentF + ", DestinationF=" + DestinationF + "]";
	}
	
	public enum PMode {LEFT, RIGHT,WAIT};
	
	
	

}
