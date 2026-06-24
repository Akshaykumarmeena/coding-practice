A phrase is a palindrome if, after converting all uppercase letters to lowercase and removing all non-alphanumeric characters, it reads the same forward and backward.
      Example:
            Input: s = "A man, a plan, a canal: Panama"
            Output: true  
            Explanation: "amanaplanacanalpanama" is a palindrome
            
            Input: s = "race a car"
            Output: false


  static boolean isAlphanumeric(int a){
        
        if((a>=97 && a <=122) || (a>=48 && a<= 57) || (a>=65 && a<= 90))
            return true;
        
        return false;
    }
    
    static boolean isEqual(char a, char b){
        
        if(a>=65 && a <=90) a = (char)(a+32);
        if(b>=65 && b <=90) b = (char)(b+32);
        if(a!=b) return false;
        return true;
    }
    
    static boolean Palindrome(String input){
        
        if(input.length()==1) return true;
        if(input.length()==0) return false;
        
        
        int i = 0, j = input.length()-1;
        
        while(i<j){
            if(isAlphanumeric(input.charAt(i))&&isAlphanumeric(input.charAt(j))){
                if(!isEqual(input.charAt(i), input.charAt(j))) return false;
                else {
                i++;
                j--;
            }
            }
            else if(!isAlphanumeric(input.charAt(i))) i++;
            else if(!isAlphanumeric(input.charAt(j))) j--;
            
        }
        
        return true;
        
    }
