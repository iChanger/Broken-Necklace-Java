
/*
ID: ichangs1
LANG: JAVA
PROG: beads
*/

/*
 * We break the necklace, and then we double the string (because it is in a circle configuration). 
 * We then take all possible strings with the original length (the non-doubled length specified at the top), and we create the strings with the same sublength all the way to the last string with the same sublength. 
 * It essentially forming strings out of all the possible translations using the max index. 
 * This is because it is a given, and observingly it's impossible for the length to be greater than the necklace length itself. 
 * Next, for each string we created, we counted every white as a bead if it's next to it, and did the usual algorithm for checking.
 * We then found the max by temporary storing of integers (much neater than storing in array and finding max), and that's all! 
*/

import java.io.*;
import java.util.*;

class beads {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("beads.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
    // Use StringTokenizer vs. readLine/split -- lots faster
    int str1 = Integer.parseInt(f.readLine());
    String str2 = f.readLine();
    int maxBead = solve(str2, str1);
    out.println(maxBead);                           // output result
    out.close();                                  // close the output file
  }
  
  static int solve(String oldstr, Integer origLength){
      int maxBead = 0;
      oldstr += oldstr;
      for(int b = 0; b < origLength; b++){ //We split the doubled into multi-cases of length n. We check every case
          String str = oldstr.substring(b, b + origLength);
          int maxTempBead = 0;
          for( int i = 1; i < (str.length() - 1); i++){
              int counter = i;
              int tempBead = 1; // We set it to one instead of zero, because selecting one bead of the colored initially will count as one bead. Therefore with zero, it will undercount by one.
              char currentLetter = str.charAt(i);
               if (str.charAt(counter) != 'w') { //Start at a red or blue bead. Not start at white
                if(str.charAt(counter - 1) == currentLetter  || str.charAt(counter - 1) == 'w'){
                  while(str.charAt(counter - 1) == currentLetter || str.charAt(counter - 1) == 'w'){
                      tempBead++;
                      if( counter > 1){
                          counter--;
                      }
                      if( counter == 1){
                          break;
                      }
                  }
                  counter = i;
                  while(str.charAt(counter + 1) != currentLetter){
                      tempBead++;
                      if( counter < str.length() - 1){
                      counter++;
                    }
                      if( counter == str.length() - 1){
                       break;
                    }
                  }
                  counter = i;
                  if (str.charAt(counter + 1) == currentLetter){ //Possibly add this later... I don't think it's necessary because the program goes from the first to last element, so if you start in the perfect place (which will occur earlier), it will only be needed for this case
                      while(str.charAt(counter + 1) == currentLetter || str.charAt(counter + 1) == 'w'){
                      tempBead++;
                      if( counter < str.length() - 1){
                          counter++;
                      }
                      if( counter == str.length() - 1){
                          break;
                      }
                    }
                  }
                  counter = i;
                }
                else if (str.charAt(counter + 1) == currentLetter || str.charAt(counter + 1) == 'w')//To fill in later...
                {
                  while(str.charAt(counter + 1) == currentLetter || str.charAt(counter + 1) == 'w'){
                      tempBead++;
                      if( counter < str.length() - 1){
                          counter++;
                      }
                      if( counter == str.length() - 1){
                          break;
                      }
                  }
                  counter = i;
                  while(str.charAt(counter - 1) != currentLetter){
                      tempBead++;
                      if( counter > 1 ){
                         counter--;
                     }
                     if( counter == 1){
                         break;
                     }
                  }
                  counter = i;
    
                }
              }
              else if (str.indexOf('b') == -1 && str.indexOf('r') == -1){
                  while(str.charAt(counter - 1) == currentLetter){
                      tempBead++;
                      if( counter > 1 ){
                         counter--;
                     }
                     if( counter == 1){
                         break;
                     }
                  }
                  counter = i;
                  while(str.charAt(counter + 1) == currentLetter){
                      tempBead++;
                      if( counter < str.length() - 1){
                          counter++;
                      }
                      if( counter == str.length() - 1){
                          break;
                      }
                  }
                  counter = i;
              }
              if( tempBead > maxTempBead || maxTempBead == 0 ){
                  maxTempBead = tempBead;
              }
          }
          
          if( maxTempBead > maxBead || maxBead == 0 ){
             maxBead = maxTempBead;
          }
      }        
      return maxBead;
  }
}
