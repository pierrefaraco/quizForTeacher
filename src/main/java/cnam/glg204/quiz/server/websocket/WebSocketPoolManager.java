package cnam.glg204.quiz.server.websocket;


import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import cnam.glg204.quiz.common.exceptions.NoWebSocketMethodToSubscribeException;

@Component("webSocketPoolManager")
public class WebSocketPoolManager {
	public static int POOLSIZE = 10;
	WebSocketMethode[] pool = new WebSocketMethode[POOLSIZE];

	@PostConstruct
	public void init() {
		for (int i = 0; i < POOLSIZE; i++)
			pool[i] = new WebSocketMethode();
	}

	
	public void remove(long userId) {
		for (int i = 0; i < POOLSIZE; i++)
			pool[i].remove(userId);
	}

	public int getWebSocketMethodeNumber(long coursId, long userId) throws NoWebSocketMethodToSubscribeException {
		
		// j'enleve l'utilisateur de toutes les méthodes avant de le placer ailleur.
		remove(userId);
		
		
		//la valeur du cours à  =-1   est considéré comme un  unsuscribe
		
		if ( coursId == -1 )
			return -1;
		
		// test si une web socket methode est deja reservé pour ce cours
		// rajout de l'utilisateur le cas echeant

		for (int i = 0; i < POOLSIZE; i++)
			if (pool[i].getCoursId() == coursId) {
				pool[i].add(userId);
				return i;
			}

		// Si il n'y a pas de methode reservé pour le cours ,
		// On en reserve une et on rajoute l'auditor

		for (int i = 0; i < POOLSIZE; i++)
			if (pool[i].getCoursId() == -1) {
				pool[i].setCoursId(coursId);
				pool[i].add(userId);
				return i;
			}
		
		throw new NoWebSocketMethodToSubscribeException("no methode  to suscribe for receive or push questions");
		

	}
	
	public void print(){
		for (int i = 0; i < POOLSIZE; i++)
			pool[i].print();
	}
	

		
	class WebSocketMethode {

		long coursId = -1;
		Set<Long> usersConnected = new HashSet();


		public long getCoursId() {
			return coursId;
		}

		public void setCoursId(long coursId) {
			this.coursId = coursId;
		}
		
		public int getUsersConnectedNumber() {
			return usersConnected.size();
		}

		public void add(long userId) {
			usersConnected.add(userId);
		}

		public void remove(long userId) {
			usersConnected.remove(userId);
			
			// Si il n'y a plus d'utilisateurs associé à la méthode 
			// on la libere en passant la valeur de coursId à -1
			if (usersConnected.isEmpty())
				coursId = -1;
		}
	
		public void print(){
			System.out.println("- " + coursId);
			for ( Long userId: usersConnected )
				System.out.println("	" + userId);
		}
	}

}
