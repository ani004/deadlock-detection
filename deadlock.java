import java.util.*;
class Site {
		String site_id;
		String process_id[];
		static int j=1;
		Site(){}
		Site(int no_of_process,int id) {
			site_id="S"+id;
			process_id=new String[no_of_process];
			for(int i=0;i<no_of_process;i++) {
				process_id[i]="p"+(j++);
			}
		}
		void display() {
			System.out.print("No. of processes in Site :"+site_id+" are :");
			for(String s:process_id) {
				System.out.print(s+"  ");
			}
			System.out.println();
		}
}
class PathInfo {
	String sender_site,receiver_site;
	PathInfo(){
		sender_site="xxxx";
		receiver_site="xxxx";
	}
}
class ProbeMsg {
	String i, j, k;
	ProbeMsg() {
		i=j=k="xx";
	}	
}
class TWF  {
	public static void main(String args[]) {
		try {
			int no_of_sites,no_of_process,no_of_edges;
			String s,p,sp1,sp2;
			Scanner obj1=new Scanner(System.in);
			Scanner obj2=new Scanner(System.in);
			System.out.println("Enter total no. of sites");
			no_of_sites=obj1.nextInt();
			Site site[]=new Site[no_of_sites];
			for(int i=0;i<no_of_sites;i++) {
				System.out.println("Enter total no. of process in Site \"s"+(i+1)+"\"");
				no_of_process=obj1.nextInt();
				site[i]=new Site(no_of_process,(i+1));
			}
			for(int i=0;i<no_of_sites;i++) {
					site[i].display();
			}
			System.out.println("Enter the site no. and process id for which deadlock detection shold be initiated:");
			s=obj2.next();
			p=obj2.next();
			System.out.println("For detecting deadlock, Enter the no of requesting edges:");
			no_of_edges=obj1.nextInt();
			PathInfo path[]=new PathInfo[no_of_edges];
			ProbeMsg probe[]=new ProbeMsg[no_of_edges];
			int c=1;
			for(int i=0;i<no_of_edges;i++){
				System.out.println("Enter the processes of two different sites connected with requesting edge:");
				sp1=obj2.next();
				sp2=obj2.next();
				path[i]=new PathInfo();
				probe[i]=new ProbeMsg();
				for(int j=0;j<no_of_sites;j++) {
					for(String pi:site[j].process_id) {
						if(pi.equals(sp1)) {
							path[i].sender_site=site[j].site_id;
						}
						if(pi.equals(sp2)) {
							path[i].receiver_site=site[j].site_id;
						}
					}
				}
				System.out.println("For this edge Probe message is: ("+p+","+sp1+","+sp2+")");
				probe[i].i=p;
				probe[i].j=sp1;
				probe[i].k=sp2;
			}
			System.out.print("Path : ");
			for(int i=0;i<no_of_edges;i++) {
				System.out.print("\t"+path[i].sender_site+"-"+path[i].receiver_site+"\n");
			}
			boolean flag=true;
			for(int l=0;l<no_of_edges;l++) {
				if(probe[l].i.equals(probe[l].k)) {
					String cycle[]=new String[10];
					int x=0;
					cycle[x++]=path[0].sender_site;
					cycle[x]=path[0].receiver_site;
					for(int j=1;j<no_of_edges;j++) {
						if(cycle[x].equals(path[j].sender_site)) {
							//x++;
							cycle[++x]=path[j].receiver_site;
						}
						if(path[j].receiver_site.equals(path[0].sender_site))
							break;
					}
					System.out.print("cycle : "+cycle[0]);
					for(int j=1;j<=x;j++)
						System.out.print("--->"+cycle[j]);
					for(int j=0;j<x-1;j++) {
						if((((int)(cycle[j].charAt(1)))+1)!=((int)(cycle[j+1].charAt(1))))
							flag=false;
					}
					if(flag) {
						System.out.println("\nDeadlock detected ");
						c=0;
					}
					break;
				}
			}
			if(c==1) {
				System.out.println("deadlock Not occured");
			}
		}catch(Exception e) {
			System.out.println("Wrong input:"+e);
		}
	}
}