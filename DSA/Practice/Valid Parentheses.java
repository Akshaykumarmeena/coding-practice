You are given a string s. Return true if it is a valid parentheses string — every opening bracket (, {, [ has a matching closing bracket of the same type, in the correct order.

Input: s = "()[]{}"  → true
Input: s = "(]"      → false
Input: s = "([)]"    → false
Input: s = "{[]}"    → true



/*
 * Problem: Valid Parentheses
 * Pattern: Stack
 * Time: O(n), Space: O(n)
 * mock-tested: fail→pass (2 rounds — both rounds were TOOLING issues, not logic:
 *   round 1: Deque<char> invalid generic, smart-quotes from text editor, 
 *            stack.peek == comparison missing parens
 *   round 2: Character still misspelled as "Characrter", smart-quotes persisted
 *            — logic itself was correct from round 1 onward)
 * Key insight: push opening brackets; on a closing bracket, check stack top
 *   matches the corresponding opener — pop if yes, false immediately if no
 * Gotcha: always guard !stack.isEmpty() before peek()/pop() — empty stack + 
 *   closing bracket = invalid, not a crash
 * Action item: switch off raw text editor (smart-quotes) to a real IDE —
 *   this class of error won't occur in the actual interview environment
 */


static boolean isValid(String s){
    int n = s.length();
    Deque<Character> stack = new ArrayDeque<>();

    for(int i=0; i<n; i++){
        if(s.charAt(i) == ‘(‘ || s.charAt(i) == ‘[‘ || s.charAt(i) == ‘{‘ ) stack.push(s.charAt(i));

        else if(s.charAt(i) == ‘)’){
        
            if(!stack.isEmpty() && stack.peek()==‘(‘ ) stack.pop();
        
            else return false;
        }

        else if(s.charAt(i) == ‘]’){
        
            if(!stack.isEmpty() && stack.peek()==‘[‘) stack.pop();
            
            else return false;
        }
        
        else if(s.charAt(i) == ‘}’){
        
            if(!stack.isEmpty() && stack.peek()==‘{‘) stack.pop();
             
            else return false;
        }

    }
    return true;
}
