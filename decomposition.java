import java.util.*;

public class decomposition
{
 public String glRE;
 char newagentcount;
 int newoperationcount;
 public int projection_flag;
 public int orig=1;
 int case_num;
 static char topop;
 int REarrayCnt;
 RE[] REarray=new RE[30];
 char[] oparray=new char[30];
 int opcnt;
 int left,right,middle;
 char[] agarray=new char[30];
 int agcnt;
 
 
 public decomposition()
 {
	 glRE="";
	 newoperationcount=1;
	 newagentcount='A';
	 left=1;
	 right=1;
	 middle=1;
	 projection_flag=1;
	 REarrayCnt=0;
	 opcnt=0;
	 agcnt=0;
 }
 
public void initial()
{
	glRE="";
	Scanner in=new Scanner(System.in);
	System.out.println("\nDoes hierarchy of operators exist (0) or all operators are at the same level (1)?\n");
	case_num=in.nextInt();
	if(case_num==1)
	{
		case_num+=2;
		for(int i=0;i<2;i++)
		{
		System.out.println("\nEnter message number: ");
			int m_num=in.nextInt();
			System.out.println("\nEnter sender agent: ");
			char send=in.next().charAt(0);
			System.out.println("\nEnter receiver agent: ");
			char recv=in.next().charAt(0);
			REarray[REarrayCnt]=new RE(m_num,1,send,recv,1);
			glRE+=REarray[REarrayCnt].re;
			REarrayCnt++;
			
			if(i==0)
			{
				System.out.println("\nEnter operator\n");
				oparray[opcnt]=in.next().charAt(0);
				glRE+=oparray[opcnt];
				opcnt++;
			}
			
		}
	}
	
	if(case_num==0)
	{
		case_num+=1;
		

		for(int i=0;i<2;i++)
		{
		System.out.println("\nEnter message number: ");
			int m_num=in.nextInt();
			System.out.println("\nEnter sender agent: ");
			char send=in.next().charAt(0);
			System.out.println("\nEnter receiver agent: ");
			char recv=in.next().charAt(0);
			REarray[REarrayCnt]=new RE(m_num,1,send,recv,1);
			glRE+=REarray[REarrayCnt].re;
			REarrayCnt++;
			
			if(i==0)
			{
				System.out.println("\nEnter operator\n");
				oparray[opcnt]=in.next().charAt(0);
				glRE+=oparray[opcnt];
				opcnt++;
			}
			
		}

		System.out.println("\nEnter top-level operator: ");
		oparray[opcnt]=in.next().charAt(0);
		glRE+=oparray[opcnt];
		opcnt++;
		

		System.out.println("\nNumber of interactions on the right hand side: ");
		int ch=in.nextInt();
		if(ch==1)
		{
			System.out.println("\nEnter message number: ");	
			int m_num=in.nextInt();
			System.out.println("\nEnter sender agent: ");
			char send=in.next().charAt(0);
			System.out.println("\nEnter receiver agent: ");
			char recv=in.next().charAt(0);
			REarray[REarrayCnt]=new RE(m_num,1,send,recv,1);	
			glRE+=REarray[REarrayCnt].re;	
			REarrayCnt++;
		}
		else if (ch==2)
		{
			
			case_num+=1;
			
			for(int i=0;i<2;i++)
		  {
		    System.out.println("\nEnter message number: ");
			int m_num=in.nextInt();
			System.out.println("\nEnter sender agent: ");
			char send=in.next().charAt(0);
			System.out.println("\nEnter receiver agent: ");
			char recv=in.next().charAt(0);
			REarray[REarrayCnt]=new RE(m_num,1,send,recv,1);
			glRE+=REarray[REarrayCnt].re;
			REarrayCnt++;
			
			if(i==0)
			{
				System.out.println("\nEnter operator\n");
				oparray[opcnt]=in.next().charAt(0);
				glRE+=oparray[opcnt];
				opcnt++;
			}
			
		  }
		}
		else
		{
			System.out.println("\nInvalid RE specification!");
		}
		
	}
}

void printTree()
{
	if(case_num==1)
	{
		System.out.println("\t\t\t"+oparray[1]+"\n");
		System.out.println("\t"+oparray[0]+"\t"+REarray[2].re+"\n");
		System.out.println(REarray[0].re+"\t"+REarray[1].re);
	}
	
	if(case_num==2)
	{
		System.out.println("\t\t\t"+oparray[1]+"\n");
		System.out.println("\t\t"+oparray[0]+"\t\t"+oparray[1]+"\n");
		System.out.println(" "+REarray[0].re+" "+REarray[1].re+" "+REarray[2].re+" "+REarray[3].re);
	}
	
	if(case_num==3)
	{
		System.out.println("\t\t\t"+oparray[0]+"\n");
		System.out.println("\t"+REarray[0].re+"\t"+REarray[1].re+"\n");
	}
	
	System.out.println("\n\n\n The given global RE is : "+glRE+"\n\n");
}

void conformance()
{


		if(oparray[0]==';')
		{
			if(REarray[0].receiver==REarray[1].sender)
			{
				System.out.println("Interactions m"+REarray[0].mess_num+" and m"+REarray[1].mess_num+" are connected for sequence\n");
			}
			else
			{
				if(case_num==3){middle=0;}
				else {left=0;}
				projection_flag=0;
				System.out.println("Connectendness for sequence does not hold good between interactions m"+REarray[0].mess_num+" and m"+REarray[1].mess_num+"\n");
			}
		}
		else if(oparray[0]=='+')
		{
			if(REarray[0].sender==REarray[1].sender)
			{
				System.out.println("Interactions m"+REarray[0].mess_num+" and m"+REarray[1].mess_num+" are connected for choice\n");
			}
			else
			{
				if(case_num==3){middle=0;}
				else {left=0;}
				projection_flag=0;
				System.out.println("Connectendness for choice does not hold good between interactions m"+REarray[0].mess_num+" and m"+REarray[1].mess_num+"\n");
			}
		}
			

     if(case_num==1||case_num==2)
     {
		if(oparray[1]==';')
		{

			if(REarray[1].receiver==REarray[2].sender)
			{
				System.out.println("Interactions m"+REarray[1].mess_num+" and m"+REarray[2].mess_num+" are connected for sequence\n");
			}
			else
			{
				middle=0;
				projection_flag=0;
				System.out.println("Connectendness for sequence does not hold good between interactions m"+REarray[1].mess_num+" and m"+REarray[2].mess_num+"\n");
			} 
		   
		}
		
		else if(oparray[1]=='+')
		{

			if(REarray[0].sender==REarray[2].sender)
			{
				System.out.println("Interactions m"+REarray[0].mess_num+" and m"+REarray[2].mess_num+" are connected for choice\n");
			}
			else
			{
				middle=0;
				projection_flag=0;
				System.out.println("Connectendness for choice does not hold good between interactions m"+REarray[0].mess_num+" and m"+REarray[2].mess_num+"\n");
			}		  

		}
	}
	  
	  
	  if(case_num==2)
	  {
		  if(oparray[2]==';')
		  {
			if(REarray[2].receiver==REarray[3].sender)
			{
				System.out.println("Interactions m"+REarray[1].mess_num+" and m"+REarray[2].mess_num+" are connected for sequence\n");
			}
			else
			{
				right=0;
				projection_flag=0;
				System.out.println("Connectendness for sequence does not hold good between interactions m"+REarray[1].mess_num+" and m"+REarray[2].mess_num+"\n");
			}  
		  }
		  
		  else if(oparray[2]=='+')
	      {

			if(REarray[2].sender==REarray[3].sender)
			{
				System.out.println("Interactions m"+REarray[0].mess_num+", m"+REarray[1].mess_num+" and m"+REarray[2].mess_num+" are connected for choice\n");
			}
			else
			{
				right=0;
				projection_flag=0;
				System.out.println("Connectendness for choice does not hold good between interactions m"+REarray[0].mess_num+", m"+REarray[1].mess_num+" and m"+REarray[2].mess_num+"\n");
			}		  

		  }		  
	  }
		
}
 
 void amend()
 {
	 if(case_num==1||case_num==2)
	 {
		 if(left==0)
		 {
			 if(oparray[0]==';')
			 {
				 RE insert1=new RE(newoperationcount++,1,REarray[0].receiver,newagentcount,0);
				 RE insert2=new RE(newoperationcount++,1,newagentcount,REarray[1].sender,0);
				 newagentcount++;
				 int index=glRE.indexOf(oparray[0]);
				 String retain=glRE.substring(index+11);
				 String temp=REarray[0].re+oparray[0]+insert1.re+oparray[0]+insert2.re+oparray[0]+REarray[1].re;
				 
				 glRE=temp+retain;

				 System.out.println("\n After amending for connectedness for sequence : "+glRE);

			 }
			 
			 else if(oparray[0]=='+')
			 {
				 RE insert1=new RE(newoperationcount++,1,newagentcount,REarray[0].sender,0);
				 RE insert2=new RE(newoperationcount++,1,newagentcount,REarray[1].sender,0);
				 
				 newagentcount++;
				 int index=glRE.indexOf(oparray[0]);
				 String retain=glRE.substring(index+11);
				 
				 String temp=insert1.re+";"+REarray[0].re+oparray[0]+insert2.re+";"+REarray[1].re;
				 
				 glRE=temp+retain;
				 
				 System.out.println("\nAfter amending for connectedness for choice: "+glRE);			
				 
			 }
		 }
	 

		 if(middle==0)
		 {
			 if(oparray[1]==';')
			 {
				 int index=glRE.lastIndexOf(oparray[1]);

				 String retain=glRE.substring(0,index-10);

				 
				 RE insert1=new RE(newoperationcount++,1,REarray[1].receiver,newagentcount,0);
				 RE insert2=new RE(newoperationcount++,1,newagentcount,REarray[2].sender,0);
				 
				 newagentcount++;
				 
				 String temp="";
				 
				 if(case_num==1)
				 {temp=REarray[1].re+oparray[1]+insert1.re+oparray[1]+insert2.re+oparray[1]+REarray[2].re;}
				 
				 else if(case_num==2)
				 {temp=REarray[1].re+oparray[1]+insert1.re+oparray[1]+insert2.re+oparray[1]+REarray[2].re+oparray[2]+REarray[3].re;}
				 
				 glRE=retain+temp;
				 
				 System.out.println("\nAfter amending for connectedness for sequence: "+glRE);
			 }
			 
			 else if(oparray[1]=='+')
			 {
				 int index=glRE.lastIndexOf(oparray[1]);

				 String retain=glRE.substring(0,index-10);

				 
				 RE insert1=new RE(newoperationcount++,1,newagentcount,REarray[1].sender,0);
				 RE insert2=new RE(newoperationcount++,1,newagentcount,REarray[2].sender,0);
				 
				 newagentcount++;
				 
				 String temp=insert1.re+";"+REarray[1].re+oparray[1]+insert2.re+";"+REarray[2].re;
				 
				 glRE=retain+temp;
				 
				 System.out.println("\nAfter amending for connectedness for choice: "+glRE);
			 }
		 }
		 
		 
		 	 if(case_num==2)
	 {
		 if(right==0)
		 {
			if(oparray[2]==';')
			 {
				 int index=glRE.lastIndexOf(oparray[2]);

				 String retain=glRE.substring(0,index-10);
				 
				 RE insert1=new RE(newoperationcount++,1,REarray[2].receiver,newagentcount,0);
				 RE insert2=new RE(newoperationcount++,1,newagentcount,REarray[3].sender,0);
				 
				 newagentcount++;
				 
				 String temp=REarray[2].re+oparray[2]+insert1.re+oparray[2]+insert2.re+oparray[2]+REarray[3].re;
				 
				 glRE=retain+temp;
				 
				 System.out.println("\nAfter amending for connectedness for sequence: "+glRE);
			 }
			 
			 else if(oparray[2]=='+')
			 {
				 int index=glRE.lastIndexOf(oparray[2]);

				 String retain=glRE.substring(0,index-10);
				 
				 RE insert1=new RE(newoperationcount++,1,newagentcount,REarray[2].sender,0);
				 RE insert2=new RE(newoperationcount++,1,newagentcount,REarray[3].sender,0);
				 
				 newagentcount++;
				 
				 String temp=insert1.re+";"+REarray[2].re+oparray[2]+insert2.re+";"+REarray[3].re;
				 
				 glRE=retain+temp;
				 
				 System.out.println("\nAfter amending for connectedness for choice: "+glRE);
			 } 
		 }
	 }
		 
		 
	 }
	 
	 

	 
	 else if(case_num==3)
	 {
		if(middle==0)
		{
			if(oparray[0]==';')
			 {
				 RE insert1=new RE(newoperationcount++,1,REarray[0].receiver,newagentcount,0);
				 RE insert2=new RE(newoperationcount++,1,newagentcount,REarray[1].sender,0);
				 newagentcount++;
				 int index=glRE.indexOf(oparray[0]);
				 String retain=glRE.substring(index+11);

				 String temp=REarray[0].re+oparray[0]+insert1.re+oparray[0]+insert2.re+oparray[0]+REarray[1].re;
				 
				 glRE=temp+retain;

				 System.out.println("\n After amending for connectedness for sequence : "+glRE);
		 
			 }
			 
			 else if(oparray[0]=='+')
			 {
				 RE insert1=new RE(newoperationcount++,1,newagentcount,REarray[0].sender,0);
				 RE insert2=new RE(newoperationcount++,1,newagentcount,REarray[1].sender,0);
				 
				 newagentcount++;
				 int index=glRE.indexOf(oparray[0]);
				 String retain=glRE.substring(index+11);
				 
				 String temp=insert1.re+";"+REarray[0].re+oparray[0]+insert2.re+";"+REarray[1].re;
				 
				 glRE=temp+retain;
				 
				 System.out.println("\nAfter amending for connectedness for choice: "+glRE);			
				 
			 }
		} 
	 }
 }
 
 public void process_agents()
 {
	 int len=glRE.length();
	 for(int i=0;i<len;i++)
	 {
		 if(Character.isLetter(glRE.charAt(i))&&glRE.charAt(i)!='m'&&glRE.charAt(i)!='o')
		 {
			 agarray[agcnt++]=glRE.charAt(i);
		 }
	 }
	 
   for(int i=0; i < agcnt; i++) 
   {
      for(int j=i+1; j < agcnt; )
      {
         if(agarray[j] == agarray[i])
         {
            for(int k=j; k < agcnt;k++) 
            {
               agarray[k] = agarray[k+1];
            }
            agcnt--;
         }
         else {
            j++;
         }
      }
   }
    
    for(int i=0;i<agcnt;i++)
    {
		project(agarray[i]);
	}
    
 }
 
 public void project(char ag)
 {
	 int len=glRE.length();
	 int msgcnt=1;
	 String localRE="";
	 
	 for(int i=0;i<len;i++)
	 {
		 if(glRE.charAt(i)==ag)
		 {
			 if(glRE.charAt(i+1)=='-')
			 {
				 RE temp=new RE(msgcnt++,1,glRE.charAt(i),glRE.charAt(i+3),1);
				 localRE+=temp.re;
				 localRE+=glRE.charAt(i+5);
			 }

			 else if(glRE.charAt(i-1)=='>')
			 {
				 RE temp=new RE(msgcnt++,0,glRE.charAt(i-3),glRE.charAt(i),1);
				 localRE+=temp.re;
				 localRE+=glRE.charAt(i+2);
			 }
			 
		 }
	 }
	 

	 len=localRE.length();
	 if(localRE.charAt(len-1)=='+'||localRE.charAt(len-1)==';'||localRE.charAt(len-1)=='|')
	 {
		 StringBuffer buf=new StringBuffer(localRE);
		 buf.deleteCharAt(len-1);
		 localRE=buf.toString();
	 }
	 
	 System.out.println("\nThe local RE for agent "+ag+" is: "+localRE);	 
 }
 
 
 public static void main(String args[])
{
   decomposition D=new decomposition();
   D.initial();
   System.out.println("\n***************** The tree structure is **************\n");
   D.printTree();
   System.out.println("\n\n CONFORMANCE CHECKING PHASE: \n\n");
   D.conformance();
   if(D.projection_flag==0)
   {
   System.out.println("\n\n AMENDING PHASE: \n\n");
   D.amend();
   }
  
   System.out.println("\n\n DECOMPOSITION PHASE: \n\n");
   StringBuffer buf=new StringBuffer(D.glRE);
   buf.append(' ');
   D.glRE=buf.toString();
   D.process_agents();
}

}
