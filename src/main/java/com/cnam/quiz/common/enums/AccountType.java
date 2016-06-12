package com.cnam.quiz.common.enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum AccountType {
	ADMINISTRATOR("administrator"),PROFESSOR("professor"),AUDITOR("auditor"), ;
	
	String name;
	AccountType(String name){
		this.name = name;		
	}
	
	
	public static String getName(AccountType accountType){
		for (AccountType at: new ArrayList<AccountType>(Arrays.asList(AccountType.values())))
			if(accountType == at)
				return at.name;	
		return null;
		
	}
	

}
