
	import java.time.Duration;
	import java.util.List;
	import java.util.Random;
	import put.game2048.*;

	public class BestAgent implements Agent {
   		public Random random = new Random(123);

    		// A nonparametric constructor is required
		public int res(boolean bScore,boolean bGoto, boolean bTakeAction){
			if(!bScore && !bGoto && bTakeAction)
				return 0;
			else if(!bScore && bGoto && !bTakeAction)	
				return 1;
			else if(bScore && !bGoto && !bTakeAction)	
				return 2;
			else if(bScore && !bGoto && bTakeAction)	
				return 3;
			else if(bScore && bGoto && !bTakeAction)	
				return 4;
			else
				return -1;
								
		}
		public Action takeAction(List<Action> possibleActions,int scoreRIGHT,int scoreLEFT,int scoreUP,int scoreDOWN){
			Action maxAct=possibleActions.get(0);
			int maxScore=0;		
			for(int i=0;i<possibleActions.size();++i){
				int score_i=0;
				if(possibleActions.get(i)==Action.RIGHT)
					score_i=scoreRIGHT;
				else if(possibleActions.get(i)==Action.LEFT)
					score_i=scoreLEFT;
				else if(possibleActions.get(i)==Action.UP)
					score_i=scoreUP;
				else
					score_i=scoreDOWN;

				if(score_i>maxScore){
					maxAct=possibleActions.get(i);
					maxScore=score_i;
				}												
			}
			return maxAct;
		}
    		public int HighestValue(Board board){
			int valmax=0;
			for(int i = 0;i<4;++i){
				for(int j = 0;j<4;++j){
					if(board.get()[i][j]>valmax)
						valmax=board.get()[i][j];				
				}		
			}
			return valmax;
		}


		public boolean isEqual(int x1, int y1, int x2, int y2, Board board, int useless_val){
			return (board.get()[x1][y1] == board.get()[x2][y2]);
		}

		public boolean isGreaterThan(int x1, int y1, int x2, int y2, Board board, int useless_val){
			return (board.get()[x1][y1] > board.get()[x2][y2]);
		}

		public boolean isOneLower(int x1, int y1, int x2, int y2, Board board, int useless_val){
			return ((board.get()[x2][y2]) / 2 == (board.get()[x1][y1]));
		}

		public boolean positionEqual(int x1, int y1, int useless_val1, int useless_val2, Board board, int val){
			return (board.get()[x1][y1] == val);	
		}

		public boolean isEmpty(int x1, int y1, int useless_parameter1, int useless_parameter2, Board board, int useless_val){
			return (board.get()[x1][y1] == 0);
		}

		public boolean canSum(int x1, int y1, int x2, int y2, Board board, int useless_val){
			if(board.get()[x1][y1] != board.get()[x2][y2]){
				return false;
			}
			if(x1 == x2){
				if(y1 == y2){
					return false;
				}
				int to_add;
				if(y1 > y2){
					to_add = -1;
				}else{
					to_add = 1;
				}
				y1 += to_add;
				while(y1 != y2){
					if(board.get()[x1][y1] != 0){
						return false;
					}
					y1 += to_add;
				}
				return true;
			}else if(y1 == y2){
				if(x1 == x2){
					return false;
				}
				int to_add;
				if(x1 > x2){
					to_add = -1;
				}else{
					to_add = 1;
				}
				x1 += to_add;
				while(x1 != x2){
					if(board.get()[x1][y1] != 0){
						return false;
					}
					x1 += to_add;
				}
				return true;
			}
			return false;
		}

		public boolean ranking(int x1, int y1, int useless_parameter1, int useless_parameter2, Board board, int val){
			int max = HighestValue(board);
			int b = board.get()[x1][y1];
if(b==0)

return false;
			int rank = 2;
			while(max > b){
				b *= 2;
				rank *= 2;
			}
			return (rank == val);
		}

		/** timeLimit will always be 1 ms */
    		public Action chooseAction(Board board, List<Action> possibleActions, Duration timeLimit) {
        		int goto_switch = 1;
        		int scoreUP = 0;
			int scoreDOWN=0;
			int scoreRIGHT=0;
			int scoreLEFT=0;
			int res_ret=-1;
			int valmax=HighestValue(board);
			for(int instr=1;instr < 10000;++instr){
        			switch(goto_switch){			
	
					case 1:		
						if ( positionEqual(3, 2,0, 2, board ,8 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=3;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=71;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=102;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 2:		
						if (!positionEqual(2, 1,1, 2, board ,16 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=104;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=185;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 3:		
						if ( isEqual(3, 2,2, 2, board ,128) ||  isOneLower(3, 0,2, 1, board ,8192) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=68;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=50;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 4:		
						if (!isEmpty(0, 1,3, 2, board ,16384) || !canSum(2, 3,2, 2, board ,8192) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=2;
								scoreRIGHT+=0;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=11;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=14;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 5:		
						if ( isEqual(0, 3,2, 0, board ,128 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=142;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=117;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 6:		
						if (!isGreaterThan(0, 0,0, 1, board ,32) && !isEqual(2, 1,3, 1, board ,512) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=142;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=8;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 7:		
						if (!canSum(0, 0,0, 2, board ,2048 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=31;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=2;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=143;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 8:		
						if (!isGreaterThan(0, 0,0, 1, board ,256) || !canSum(2, 1,2, 3, board ,65536) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=100;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=21;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 9:		
						if (!isOneLower(2, 3,1, 3, board ,65536 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=13;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=2;
								scoreRIGHT+=1;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=118;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 10:		
						if (!isEmpty(3, 2,1, 1, board ,16 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=85;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=3;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=150;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 11:		
						if (!positionEqual(2, 0,3, 2, board ,8192 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=2;
								scoreRIGHT+=1;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=141;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=132;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 12:		
						if (!canSum(2, 3,0, 1, board ,4) ||  isOneLower(1, 0,0, 0, board ,16) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=4;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=35;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=134;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 13:		
						if (!positionEqual(1, 3,1, 2, board ,65536) && !isOneLower(2, 2,1, 3, board ,32) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=175;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=185;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 14:		
						if ( positionEqual(3, 3,3, 1, board ,8192 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=25;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=2;
								scoreRIGHT+=0;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=194;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 15:		
						if ( ranking(1, 0,3, 2, board ,2048) &&  ranking(0, 2,0, 0, board ,8192) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=115;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=37;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 16:		
						if ( ranking(1, 0,2, 2, board ,16 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=12;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=156;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 17:		
						if ( ranking(3, 3,2, 2, board ,4096 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=0;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=159;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=5;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 18:		
						if ( isOneLower(3, 3,0, 0, board ,2) || !isGreaterThan(2, 1,3, 3, board ,2) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=5;
								scoreRIGHT+=0;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=126;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=0;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=138;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 19:		
						if (!isOneLower(2, 1,1, 1, board ,512 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=87;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=16;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 20:		
						if ( isGreaterThan(2, 3,0, 3, board ,4) ||  isEqual(2, 3,0, 1, board ,8192) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=123;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=13;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 21:		
						if (!ranking(2, 2,0, 2, board ,256) ||  isEmpty(3, 0,1, 0, board ,65536) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=103;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=2;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=131;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 22:		
						if (!positionEqual(1, 2,2, 3, board ,64) &&  isGreaterThan(2, 1,1, 0, board ,64) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=2;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=3;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=10;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 23:		
						if (!canSum(1, 0,1, 0, board ,2048) && !isOneLower(1, 3,0, 1, board ,4096) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=71;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=49;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 24:		
						if (!canSum(2, 0,2, 0, board ,65536) || !canSum(0, 3,0, 0, board ,2) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=178;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=3;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=78;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 25:		
						if (!isGreaterThan(1, 1,1, 3, board ,16) && !canSum(3, 2,3, 3, board ,32) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=62;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=2;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=93;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 26:		
						if ( isEmpty(0, 2,0, 3, board ,256 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=165;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=59;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 27:		
						if (!ranking(0, 0,2, 0, board ,64 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=96;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=19;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 28:		
						if (!isGreaterThan(0, 1,3, 2, board ,64) || !isEmpty(1, 3,0, 2, board ,16384) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=119;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=113;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 29:		
						if (!isEqual(3, 0,2, 2, board ,64 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=34;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=87;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 30:		
						if ( isOneLower(2, 0,2, 0, board ,128) &&  isEmpty(3, 1,2, 3, board ,2) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=42;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=66;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 31:		
						if ( isGreaterThan(3, 3,2, 1, board ,1024 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=2;
								scoreRIGHT+=1;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=105;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=17;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 32:		
						if ( isOneLower(0, 3,2, 1, board ,512 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=2;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=30;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=89;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 33:		
						if ( isEqual(0, 0,3, 3, board ,32 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=0;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=99;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=165;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 34:		
						if ( isGreaterThan(1, 2,0, 2, board ,2048) && !isGreaterThan(1, 2,3, 3, board ,4) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=46;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=0;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=137;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 35:		
						if (!isGreaterThan(1, 1,3, 1, board ,16384 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=33;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=125;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 36:		
						if (!isGreaterThan(0, 2,1, 2, board ,1024 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=166;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=0;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=81;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 37:		
						if (!canSum(3, 3,3, 0, board ,32) || !isEqual(2, 0,3, 0, board ,2048) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=54;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=1;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=17;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 38:		
						if (!isEqual(0, 3,1, 1, board ,512) ||  canSum(2, 0,2, 2, board ,4096) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=25;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=2;
								scoreRIGHT+=1;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=185;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 39:		
						if (!ranking(0, 0,0, 0, board ,32) &&  isGreaterThan(3, 3,2, 0, board ,valmax) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=68;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=38;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 40:		
						if (!canSum(1, 3,0, 3, board ,8) ||  ranking(3, 2,0, 0, board ,8192) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=143;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=154;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 41:		
						if ( ranking(3, 1,2, 0, board ,16 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=4;
								scoreRIGHT+=0;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=70;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=6;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 42:		
						if ( isOneLower(3, 3,3, 1, board ,8) && !isGreaterThan(2, 3,0, 0, board ,8) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=13;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=121;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 43:		
						if ( isGreaterThan(3, 1,0, 1, board ,2 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=1;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=134;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=18;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 44:		
						if ( isEmpty(2, 3,1, 2, board ,65536 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=3;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=42;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=0;
								scoreRIGHT+=4;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=115;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 45:		
						if ( isEqual(3, 1,2, 0, board ,512 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=179;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=45;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 46:		
						if ( canSum(3, 0,0, 1, board ,32768 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=187;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=137;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 47:		
						if ( canSum(0, 1,0, 1, board ,2) || !ranking(2, 3,2, 0, board ,32) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=4;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=134;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=12;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 48:		
						if ( positionEqual(1, 0,0, 0, board ,32) && !positionEqual(3, 3,1, 2, board ,8) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=23;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=0;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=25;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 49:		
						if ( isOneLower(3, 1,0, 0, board ,256 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=1;
								scoreRIGHT+=1;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=104;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=126;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 50:		
						if ( isGreaterThan(1, 3,3, 0, board ,32768) ||  isOneLower(0, 2,1, 3, board ,65536) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=149;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=24;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 51:		
						if ( positionEqual(0, 3,3, 0, board ,32) &&  isGreaterThan(1, 2,3, 3, board ,65536) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=117;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=0;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=184;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 52:		
						if (!ranking(3, 3,2, 0, board ,2048 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=0;
								scoreRIGHT+=4;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=112;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=49;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 53:		
						if (!isEmpty(2, 3,2, 3, board ,256) &&  isEqual(1, 0,3, 0, board ,valmax) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=80;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=95;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 54:		
						if ( ranking(1, 2,0, 0, board ,valmax )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=71;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=168;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 55:		
						if ( isOneLower(2, 0,3, 0, board ,65536) && !isEqual(2, 2,2, 1, board ,32768) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=166;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=191;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 56:		
						if (!canSum(3, 0,2, 2, board ,16384 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=165;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=41;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 57:		
						if ( isEmpty(0, 1,1, 2, board ,8) && !isGreaterThan(0, 2,1, 2, board ,4) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=198;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=4;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=25;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 58:		
						if (!isEmpty(2, 0,3, 3, board ,2048 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=5;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=101;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=3;
								scoreRIGHT+=5;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=98;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 59:		
						if ( canSum(0, 1,1, 2, board ,8 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=0;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=111;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=2;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=42;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 60:		
						if (!positionEqual(0, 2,0, 3, board ,65536) || !isOneLower(0, 3,1, 1, board ,128) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=66;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=65;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 61:		
						if ( isEmpty(1, 3,2, 3, board ,64 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=0;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=156;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=181;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 62:		
						if ( canSum(0, 3,3, 3, board ,16) || !isOneLower(1, 0,0, 3, board ,256) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=3;
								scoreRIGHT+=1;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=77;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=164;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 63:		
						if ( positionEqual(2, 3,0, 1, board ,64) && !isGreaterThan(0, 0,3, 2, board ,128) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=56;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=9;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 64:		
						if (!positionEqual(3, 3,1, 2, board ,8 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=49;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=141;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 65:		
						if ( canSum(0, 2,2, 2, board ,65536) && !isEmpty(1, 1,1, 1, board ,512) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=5;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=63;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=0;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=26;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 66:		
						if ( canSum(2, 0,1, 1, board ,8192 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=2;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=111;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=0;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=136;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 67:		
						if (!positionEqual(3, 0,3, 1, board ,4096 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=3;
								scoreRIGHT+=0;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=127;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=32;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 68:		
						if (!isOneLower(2, 0,3, 2, board ,4) ||  canSum(0, 3,2, 2, board ,32) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=51;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=158;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 69:		
						if ( isEqual(1, 0,3, 1, board ,4) &&  isEqual(3, 0,0, 3, board ,2048) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=63;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=21;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 70:		
						if ( isEqual(2, 2,3, 3, board ,2048) || !isOneLower(1, 2,0, 1, board ,128) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=1;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=170;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=52;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 71:		
						if (!ranking(3, 2,2, 0, board ,4) &&  isGreaterThan(2, 2,3, 2, board ,128) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=87;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=2;
								scoreRIGHT+=1;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=93;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 72:		
						if (!isEqual(0, 2,3, 3, board ,2048 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=67;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=4;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=13;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 73:		
						if (!isEmpty(3, 0,3, 0, board ,65536 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=32;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=84;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 74:		
						if (!positionEqual(0, 1,1, 2, board ,4 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=28;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=74;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 75:		
						if (!positionEqual(3, 0,2, 0, board ,8192) ||  ranking(2, 2,1, 2, board ,32) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=77;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=134;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 76:		
						if ( positionEqual(2, 2,2, 3, board ,256) || !isOneLower(3, 1,1, 3, board ,2048) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=17;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=200;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 77:		
						if (!isOneLower(3, 1,3, 0, board ,256) && !isEqual(2, 3,3, 0, board ,8) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=194;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=47;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 78:		
						if (!canSum(1, 1,3, 0, board ,8 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=86;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=71;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 79:		
						if (!ranking(2, 2,2, 3, board ,valmax )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=158;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=147;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 80:		
						if (!positionEqual(0, 3,2, 3, board ,4096) &&  isEqual(3, 2,1, 2, board ,valmax) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=0;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=124;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=181;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 81:		
						if ( ranking(1, 2,0, 1, board ,2 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=3;
								scoreRIGHT+=0;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=20;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=127;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 82:		
						if (!isGreaterThan(0, 3,1, 0, board ,4096) ||  ranking(0, 0,1, 3, board ,4096) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=148;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=7;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 83:		
						if (!canSum(2, 0,0, 0, board ,8192 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=4;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=136;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=139;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 84:		
						if (!canSum(2, 1,0, 3, board ,8) || !ranking(3, 0,3, 1, board ,128) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=87;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=120;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 85:		
						if ( positionEqual(2, 3,1, 2, board ,1024 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=87;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=3;
								scoreRIGHT+=1;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=154;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 86:		
						if ( ranking(0, 2,2, 2, board ,valmax) || !isEmpty(2, 2,2, 3, board ,valmax) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=4;
								scoreRIGHT+=5;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=59;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=115;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 87:		
						if ( isEqual(0, 3,2, 3, board ,32) ||  isOneLower(3, 2,0, 1, board ,2048) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=78;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=172;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 88:		
						if (!ranking(3, 2,1, 0, board ,256 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=151;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=191;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 89:		
						if ( ranking(2, 1,3, 0, board ,2048) ||  canSum(3, 0,1, 2, board ,32768) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=100;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=2;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=54;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 90:		
						if (!isEmpty(1, 0,2, 1, board ,512 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=4;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=58;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=18;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 91:		
						if ( isGreaterThan(0, 0,3, 0, board ,65536) &&  positionEqual(3, 3,0, 0, board ,16) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=58;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=54;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 92:		
						if (!isOneLower(0, 3,1, 0, board ,8 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=92;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=192;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 93:		
						if (!isEqual(1, 0,1, 2, board ,4 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=1;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=49;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=98;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 94:		
						if ( canSum(0, 0,0, 3, board ,512 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=0;
								scoreRIGHT+=4;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=69;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=2;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=14;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 95:		
						if ( isGreaterThan(0, 2,2, 1, board ,4096) || !isEqual(0, 2,3, 3, board ,valmax) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=143;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=42;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 96:		
						if (!ranking(3, 2,1, 3, board ,16384) ||  positionEqual(2, 1,2, 3, board ,2048) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=131;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=4;
								scoreRIGHT+=4;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=81;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 97:		
						if ( isOneLower(1, 0,1, 1, board ,65536) &&  isGreaterThan(0, 2,1, 0, board ,256) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=2;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=169;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=1;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=69;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 98:		
						if (!canSum(0, 2,1, 1, board ,128 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=2;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=148;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=177;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 99:		
						if ( positionEqual(1, 2,1, 1, board ,16 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=4;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=15;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=163;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 100:		
						if ( isGreaterThan(0, 0,2, 2, board ,2 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=122;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=132;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 101:		
						if (!isEqual(3, 1,0, 2, board ,256 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=45;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=130;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 102:		
						if (!canSum(0, 1,3, 0, board ,32 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=194;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=6;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 103:		
						if ( canSum(2, 1,0, 3, board ,65536) || !isGreaterThan(0, 0,0, 3, board ,4) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=109;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=90;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 104:		
						if ( positionEqual(2, 3,2, 0, board ,16 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=124;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=3;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=167;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 105:		
						if (!isOneLower(2, 0,1, 3, board ,128) ||  ranking(1, 0,2, 1, board ,4096) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=5;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=181;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=0;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=81;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 106:		
						if (!ranking(2, 3,1, 2, board ,65536) &&  positionEqual(2, 3,1, 1, board ,2) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=25;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=4;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=192;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 107:		
						if (!ranking(1, 2,0, 2, board ,4096 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=20;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=2;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=79;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 108:		
						if ( positionEqual(1, 3,2, 2, board ,16) ||  isOneLower(2, 1,1, 0, board ,4096) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=5;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=144;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=110;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 109:		
						if (!canSum(1, 2,3, 2, board ,65536 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=23;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=143;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 110:		
						if ( isEqual(3, 2,0, 0, board ,valmax )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=5;
								scoreRIGHT+=0;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=187;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=23;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 111:		
						if ( isOneLower(3, 1,2, 0, board ,4 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=133;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=29;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 112:		
						if ( ranking(0, 0,3, 3, board ,4096 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=100;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=3;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=112;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 113:		
						if ( positionEqual(0, 3,1, 2, board ,2) &&  canSum(2, 3,3, 3, board ,16384) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=2;
								scoreRIGHT+=0;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=5;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=4;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=50;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 114:		
						if (!positionEqual(2, 3,2, 0, board ,64 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=2;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=117;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=152;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 115:		
						if ( positionEqual(2, 0,0, 2, board ,4096) && !isOneLower(2, 0,1, 3, board ,64) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=3;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=65;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=3;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=123;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 116:		
						if ( ranking(3, 2,0, 1, board ,32768 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=154;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=165;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 117:		
						if ( isEqual(2, 1,2, 1, board ,512 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=133;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=85;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 118:		
						if (!isEmpty(3, 3,0, 2, board ,16384 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=104;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=88;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 119:		
						if (!canSum(0, 1,0, 0, board ,2048 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=4;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=3;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=106;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 120:		
						if (!isEmpty(3, 1,0, 2, board ,64 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=82;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=47;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 121:		
						if ( canSum(3, 1,3, 1, board ,valmax) || !isOneLower(2, 1,3, 2, board ,4) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=0;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=180;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=191;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 122:		
						if (!isOneLower(2, 0,0, 3, board ,4 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=157;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=23;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 123:		
						if ( positionEqual(2, 1,3, 2, board ,4) ||  canSum(0, 1,0, 0, board ,2048) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=177;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=24;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 124:		
						if (!positionEqual(0, 1,1, 1, board ,16 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=180;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=26;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 125:		
						if (!canSum(1, 2,3, 2, board ,128 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=52;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=186;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 126:		
						if ( positionEqual(0, 3,0, 1, board ,16 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=45;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=1;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=90;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 127:		
						if (!ranking(3, 3,3, 1, board ,32) && !canSum(3, 1,3, 0, board ,4096) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=48;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=27;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 128:		
						if ( isGreaterThan(2, 1,0, 3, board ,2048 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=61;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=3;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=16;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 129:		
						if ( ranking(0, 3,2, 3, board ,4) || !isOneLower(0, 0,0, 3, board ,4) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=74;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=0;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=162;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 130:		
						if (!isOneLower(3, 0,3, 1, board ,2048) || !isEqual(1, 1,3, 1, board ,2048) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=3;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=200;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=59;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 131:		
						if ( isEmpty(1, 3,3, 1, board ,64) && !canSum(3, 2,3, 0, board ,256) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=120;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=1;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=36;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 132:		
						if (!isEmpty(3, 3,3, 0, board ,4 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=0;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=144;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=92;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 133:		
						if (!canSum(1, 3,2, 1, board ,128) || !ranking(2, 1,3, 2, board ,16384) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=0;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=178;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=191;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 134:		
						if (!isGreaterThan(1, 2,1, 1, board ,2048) && !isGreaterThan(1, 0,2, 1, board ,8) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=155;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=189;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 135:		
						if (!isEqual(0, 2,2, 0, board ,512 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=4;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=85;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=26;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 136:		
						if ( isEqual(3, 2,0, 3, board ,2 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=6;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=131;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 137:		
						if ( isOneLower(1, 1,3, 0, board ,4) &&  isGreaterThan(1, 3,2, 0, board ,512) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=24;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=194;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 138:		
						if ( positionEqual(2, 0,3, 1, board ,128) || !canSum(1, 3,2, 1, board ,16) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=59;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=178;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 139:		
						if (!isEmpty(0, 1,1, 1, board ,4) && !isGreaterThan(3, 0,2, 3, board ,8192) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=156;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=31;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 140:		
						if (!canSum(3, 0,2, 1, board ,512) ||  isGreaterThan(3, 0,0, 1, board ,64) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=38;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=33;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 141:		
						if (!isEqual(0, 3,0, 1, board ,2 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=92;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=3;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=147;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 142:		
						if ( isEmpty(1, 2,3, 2, board ,valmax) ||  canSum(1, 1,2, 0, board ,4096) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=152;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=137;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 143:		
						if (!ranking(1, 1,2, 0, board ,32768) ||  canSum(2, 1,1, 2, board ,64) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=4;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=142;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=5;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=142;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 144:		
						if (!isEqual(3, 0,3, 1, board ,65536) && !isEmpty(3, 1,2, 1, board ,2048) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=0;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=69;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=148;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 145:		
						if ( isEqual(3, 1,2, 1, board ,16384 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=5;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=148;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=171;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 146:		
						if (!ranking(2, 0,2, 3, board ,32 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=153;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=32;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 147:		
						if (!isOneLower(2, 1,0, 1, board ,16) && !isGreaterThan(2, 0,3, 2, board ,32) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=33;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=40;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 148:		
						if (!positionEqual(2, 0,3, 2, board ,8192 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=88;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=92;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 149:		
						if ( ranking(0, 0,0, 2, board ,128) && !isEmpty(1, 3,1, 0, board ,4) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=0;
								scoreRIGHT+=4;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=185;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=3;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=74;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 150:		
						if ( positionEqual(0, 2,2, 1, board ,64 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=53;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=75;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 151:		
						if (!canSum(2, 0,2, 3, board ,2048) ||  isOneLower(1, 3,3, 3, board ,valmax) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=195;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=122;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 152:		
						if (!canSum(2, 0,3, 0, board ,32 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=2;
								scoreRIGHT+=3;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=137;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=166;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 153:		
						if (!positionEqual(3, 3,2, 0, board ,65536) && !isEqual(3, 0,1, 2, board ,64) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=2;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=2;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=108;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 154:		
						if ( isOneLower(0, 0,0, 1, board ,8192) ||  positionEqual(1, 1,1, 2, board ,32) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=2;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=7;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=80;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 155:		
						if ( isEmpty(3, 1,2, 3, board ,4096 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=2;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=3;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=48;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 156:		
						if (!isEqual(0, 0,2, 3, board ,128 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=144;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=15;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 157:		
						if (!canSum(2, 2,3, 2, board ,32) && !isOneLower(3, 3,2, 1, board ,65536) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=189;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=55;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 158:		
						if (!isGreaterThan(1, 1,2, 0, board ,65536 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=4;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=199;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 159:		
						if (!canSum(0, 2,3, 0, board ,valmax) || !isEqual(3, 3,0, 0, board ,4) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=1;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=126;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=165;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 160:		
						if (!isEmpty(0, 2,3, 3, board ,8 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=0;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=60;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=30;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 161:		
						if ( isEmpty(3, 0,2, 2, board ,128 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=3;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=159;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=196;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 162:		
						if (!isEmpty(3, 0,0, 3, board ,8192 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=93;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=97;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 163:		
						if ( isEmpty(3, 2,3, 3, board ,64 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=4;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=177;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=111;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 164:		
						if ( isOneLower(2, 1,3, 3, board ,2) || !isOneLower(1, 2,2, 3, board ,512) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=78;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=153;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 165:		
						if (!isEmpty(2, 1,2, 2, board ,65536) || !canSum(0, 2,1, 1, board ,1024) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=4;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=6;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=148;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 166:		
						if ( positionEqual(3, 0,0, 0, board ,512 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=5;
								scoreRIGHT+=2;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=191;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=75;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 167:		
						if ( positionEqual(1, 2,3, 1, board ,2048 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=77;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=22;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 168:		
						if (!isOneLower(1, 2,2, 0, board ,65536) ||  isEqual(0, 3,0, 1, board ,64) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=117;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=190;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 169:		
						if (!isEmpty(3, 2,1, 1, board ,4096 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=153;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=1;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=49;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 170:		
						if (!ranking(1, 1,2, 3, board ,8192) &&  positionEqual(2, 3,0, 0, board ,128) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=4;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=37;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=49;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 171:		
						if ( ranking(3, 1,2, 3, board ,65536) ||  isEqual(3, 0,0, 2, board ,2) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=38;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=2;
								scoreRIGHT+=0;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=43;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 172:		
						if (!ranking(1, 3,3, 2, board ,16384) && !ranking(0, 2,2, 2, board ,32768) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=177;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=193;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 173:		
						if ( isGreaterThan(2, 0,0, 1, board ,1024 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=7;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=4;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=145;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 174:		
						if (!canSum(0, 0,2, 0, board ,4 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=187;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=173;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 175:		
						if ( positionEqual(0, 1,2, 0, board ,256) ||  isEmpty(1, 0,1, 3, board ,4096) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=1;
								scoreRIGHT+=1;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=13;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=185;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 176:		
						if ( isGreaterThan(2, 3,2, 0, board ,2048 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=1;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=165;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=23;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 177:		
						if ( positionEqual(1, 1,3, 0, board ,65536) || !isEqual(1, 1,1, 1, board ,32768) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=21;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=4;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=71;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 178:		
						if (!isOneLower(0, 1,1, 1, board ,256) && !isOneLower(2, 2,3, 1, board ,256) ){
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=72;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=70;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 179:		
						if ( canSum(3, 0,0, 3, board ,64 )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=4;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=181;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=2;
								scoreRIGHT+=1;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=185;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 180:		
						if ( canSum(3, 3,0, 2, board ,256 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=5;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=30;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=3;
								scoreRIGHT+=1;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=127;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 181:		
						if (!ranking(1, 3,0, 3, board ,valmax )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=1;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=126;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=2;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=121;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 182:		
						if (!canSum(2, 1,3, 3, board ,8 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=135;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=5;
								scoreRIGHT+=4;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=69;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 183:		
						if ( isEqual(2, 2,1, 2, board ,16384) || !isOneLower(2, 0,3, 1, board ,16384) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=33;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=85;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 184:		
						if (!isEqual(0, 2,1, 3, board ,16384 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=125;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=1;
								scoreRIGHT+=0;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=79;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 185:		
						if (!ranking(1, 2,0, 0, board ,2048 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=5;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=46;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=0;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=7;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 186:		
						if ( ranking(3, 0,3, 1, board ,4) || !canSum(3, 1,2, 1, board ,128) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=104;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=3;
								scoreRIGHT+=1;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=92;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 187:		
						if ( ranking(1, 0,3, 2, board ,4 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=2;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=48;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=48;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 188:		
						if ( positionEqual(3, 2,1, 0, board ,valmax )) {
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=145;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=4;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=157;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 189:		
						if ( isOneLower(1, 3,0, 2, board ,256 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=138;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=1;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=116;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 190:		
						if ( canSum(2, 0,1, 1, board ,16) ||  ranking(1, 0,0, 2, board ,32) ){
            						res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=2;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=124;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=0;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=52;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 191:		
						if (!isOneLower(2, 3,2, 3, board ,256 )) {
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=3;
								scoreRIGHT+=4;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=163;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=3;
								scoreRIGHT+=0;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=58;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 192:		
						if ( canSum(0, 3,1, 3, board ,2048 )) {
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=78;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=4;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=96;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 193:		
						if (!canSum(1, 0,1, 3, board ,4096 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=4;
								scoreDOWN+=1;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=76;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=5;
								scoreRIGHT+=3;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=16;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 194:		
						if (!isEqual(1, 0,2, 1, board ,1024) || !positionEqual(2, 1,2, 1, board ,8) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=1;
								scoreDOWN+=3;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=147;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=3;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=65;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 195:		
						if ( isGreaterThan(0, 3,3, 0, board ,2048) || !ranking(1, 3,1, 1, board ,65536) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=4;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=43;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=167;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 196:		
						if (!ranking(2, 2,1, 2, board ,2048) && !canSum(1, 1,0, 3, board ,32768) ){
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=5;
								scoreRIGHT+=2;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=70;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=5;
								scoreDOWN+=3;
								scoreRIGHT+=5;
								scoreLEFT+=4;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=81;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 197:		
						if ( isGreaterThan(3, 3,0, 3, board ,2048) && !isOneLower(0, 1,1, 1, board ,1024) ){
            						res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=2;
								scoreRIGHT+=3;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=133;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=2;
								scoreRIGHT+=3;
								scoreLEFT+=0;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=151;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 198:		
						if (!isGreaterThan(0, 2,0, 2, board ,128 )) {
            						res_ret=res(true,false,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=2;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=40;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=0;
								scoreDOWN+=1;
								scoreRIGHT+=3;
								scoreLEFT+=5;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=141;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 199:		
						if ( isEqual(2, 3,3, 1, board ,16 )) {
            						res_ret=res(false,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=3;
								scoreRIGHT+=5;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=169;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(false,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=3;
								scoreDOWN+=3;
								scoreRIGHT+=3;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=88;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
					case 200:		
						if (!positionEqual(3, 0,2, 1, board ,2048) && !isEmpty(0, 1,1, 1, board ,64) ){
            						res_ret=res(true,false,true);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=2;
								scoreRIGHT+=5;
								scoreLEFT+=2;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=145;
							}	
							if(res_ret==0 || res_ret==3){								
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}				
						}else{
							res_ret=res(true,true,false);
							if(res_ret==2 ||res_ret==3||res_ret==4){
								scoreUP+=2;
								scoreDOWN+=0;
								scoreRIGHT+=3;
								scoreLEFT+=1;
							}
							if(res_ret==1||res_ret==4){
								goto_switch=98;
							}	
							if(res_ret==0 || res_ret==3){	
								return takeAction(possibleActions,scoreUP,scoreDOWN,scoreRIGHT,scoreLEFT);
							}		
						}
	      
				default:
					return possibleActions.get(random.nextInt(possibleActions.size()));
					
				}
			}
            		return possibleActions.get(random.nextInt(possibleActions.size()));
    		}
	}
	
