package materiel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import materiel.People.PMode;

public class Floor
{
	//numero de l'étage
	private int number;
	//list des personne dans ce étage
	private ArrayList<People> Peoples;
	private ArrayList<People> Departing;
	//we need an y for the drawing 
	private int Ay;
	
	public Floor(int Ay,int number)
	{
		this.Ay=Ay;
		this.number=number;
		Peoples = new ArrayList<People>();
		Departing = new ArrayList<People>();
	}
	public Floor()
	{
		Peoples = new ArrayList<People>();
		Departing = new ArrayList<People>();
	}
	
	// to draw all floors
	public static ArrayList<Floor> genFloors()
	{
		ArrayList<Floor> Floors = new ArrayList<Floor>();
		for(int i=0,j=5;i<500 && j>0 ; i+=100,j--)
		{
			Floor newF= new Floor();
			newF.setAy(i);
			newF.setNumber(j);
			Floors.add(newF);
		}
		return Floors;
	}
	public void drawFloors(Graphics g) 
	{
		//draw floor lines
		Graphics2D g2 = (Graphics2D) g ;
		g2.setColor(new Color(136,116,163)); // white smock 245,245,245 
		g2.setStroke(new BasicStroke(3));
		//drawlines for each floor
		for(int i=0,j=5;i<500 && j>0 ; i+=100,j--)
		{
			Floor newF= new Floor();
			newF.setAy(i);
			newF.setNumber(j);
			if(newF.getAy()<300)
			{
				g.drawLine(50,newF.getAy(),693,newF.getAy());
				g.drawString(" Etage : "+newF.getNumber(), 50, i+30);
			}
			else
			{
				g.drawLine(50,newF.getAy(),693,newF.getAy());
				g.drawString(" Etage : "+newF.getNumber() , 50, i+30);
			}	
		}
        
		Iterator<People> it = Departing.iterator();
        
        while (it.hasNext())
        {
        	People temp = it.next();
            
            temp.setDestX(775);
            temp.setState(PMode.RIGHT);
            temp.drawPerson(g);
            
            if (temp.getAx() == temp.getDestX())
            {
                it.remove();
            }
        }
	}
	public void ClearDeparting()
	{
		Departing.clear();
	}
	public int getNumber() 
	{
		return this.number;
	}
	
	public void getNumberFromY(int Ay) 
	{
		switch (Ay) 
		{
		case 0:
			this.number =5;
			break;
		case 100:
			this.number =4;
			break;
		case 200:
			this.number =3;
			break;
		case 300:
			this.number =2;
			break;
		case 400:
			this.number=1;
			break;
		default:
			break;
		}
	}
	public void addPerson(People p)
	{
		Peoples.add(p);
	}
	public ArrayList<People> getPeoples()
	{
		return Peoples;
	}
	public void setPeoples(ArrayList<People> peoples)
	{
		Peoples = peoples;
	}
	public ArrayList<People> getDeparting()
	{
		return Departing;
	}
	public void setDeparting(ArrayList<People> Departing)
	{
		this.Departing = Departing;
	}
	public int getAy()
	{
		return Ay;
	}
	public void setAy(int ay)
	{
		Ay = ay;
	}
	public void setNumber(int number)
	{
		this.number = number;
	}
	public void goTo(int number) 
	{
		// a switch  to go the floor  
		
	}
	@Override
	public String toString()
	{
		return "Floor [number=" + number + ", Ay=" + Ay + "]";
	}
	
	
	//constractor
	//add people to the floor
	//draw the lines
}
