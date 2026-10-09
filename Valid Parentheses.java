import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        // Initialize a stack to store opening brackets
        Stack<Character> stack = new Stack<>();
        
        // Loop through each character in the string
        for (char c : s.toCharArray()) {
            // Push opening brackets onto the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            // Handle closing brackets
            else {
                // If stack is empty, there is no matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();
                // Check if the popped bracket matches the current closing bracket
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        
        // Return true if all opening brackets were successfully matched and popped

        return stack.isEmpty();
    }
}
