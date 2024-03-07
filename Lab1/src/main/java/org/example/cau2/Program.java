package org.example.cau2;

import org.example.cau2.vn.edu.tdtu.*;

public class Program {
    public static void main(String[] args) {
        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6};
        
        ArrayOutput.print(a);
        ArrayOutput.print(b);
        
        int[] c = ArrayHandler.merge(a, b);
        
        ArrayOutput.print(c);
        
        ArrayHandler.sort(c);
        ArrayOutput.print(c);
        
        ArrayOutput.write(c, "output.txt");
    }
}
