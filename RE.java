import java.util.*;

public class RE
{
		public int mess_num;
		public int send_flag;
		public char sender;
		public char receiver;
		public char message_notation;
		public String arrow;
		public String re;
		public int orig;
		
	public RE(int m_num,int sORr,char send,char recv,int orig)
	{
		mess_num=m_num;

		if(sORr==1)
		{
			message_notation='!';
			arrow="->";
			sender=send;
		    receiver=recv;
		}
		else
		{
			message_notation='?';
			arrow="<-";
			sender=recv;
		    receiver=send;
		}
		if(orig==1)
		re="("+message_notation+'m'+Integer.toString(mess_num)+':'+sender+arrow+receiver+')';
		else
		re="("+message_notation+'o'+Integer.toString(mess_num)+':'+sender+arrow+receiver+')';
		
	}
}
