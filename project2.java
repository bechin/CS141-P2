// bechin
// CS141 03
// Project #2 : Bouncy Ball
// Usage:
// First (and only)command line argument will be the time between updates
// in milliseconds.  An input file should be passed in by redirection.
// e.g. java project2 100 < sample.input

import java.util.Scanner;

public class project2
{
  public static void main(String[] args) throws InterruptedException
  {
     Scanner kb = new Scanner(System.in);
     int n = Integer.parseInt(args[0]), dx = 1, dy = 1;
     String str;
     int width = kb.nextInt();
     int height = kb.nextInt();
     char[][] box = new char[width][height];
     int[] position = new int[]{1,1};
     kb.nextLine();
     //fills array
     for (int y = 0; y < height; y++)
     {
       str = kb.nextLine();
       for (int x = 0; x < width; x++)
       {
         box[x][y] = str.charAt(x);
       }
     }
     box[1][1] = 'O';

     //animation using a while loop
     while (true)
     {
       clear();
       resetCursor();
       printArray(box, width, height);
       Thread.sleep(n);
       try
       {
         if (box[position[0] + dx][position[1] + dy]=='|')
           throw new Exception("Ball has hit a side.");
       }
       catch (Exception e)
       {
         if (e.getMessage().equals("Ball has hit a side."))
           dx *= -1;
       }
       try
       {
         if (box[position[0] + dx][position[1] + dy]=='-')
           throw new Exception("Ball has hit top or bottom.");
       }
       catch (Exception e)
       {
         if (e.getMessage().equals("Ball has hit top or bottom."))
           dy *= -1;
       }
       try
       {
         if (box[position[0] + dx][position[1] + dy]=='+')
           throw new Exception("Ball has hit a corner.");
       }
       catch (Exception e)
       {
         if (e.getMessage().equals("Ball has hit a corner.")) {
           dx *= -1;
           dy *= -1;
         }
       }
       box[position[0]][position[1]] = ' ';
       position[0] += dx;
       position[1] += dy;
       box[position[0]][position[1]] = 'O';
     }
     //animation using recursion
     //animate(box, position, n, dx, dy, width, height);
  }
  //BAD IDEA: STACK OVERFLOW
  public static void animate(char[][] box, int[] position,
                             int n, int dx, int dy, int width, int height) throws InterruptedException
  {
    clear();
    resetCursor();
    printArray(box, width, height);
    Thread.sleep(n);
    if (box[position[0] + dx][position[1]]=='|')
      dx = -dx;
    if (box[position[0]][position[1] + dy]=='-')
      dy = -dy;
    box[position[0]][position[1]] = ' ';
    position[0] += dx;
    position[1] += dy;
    box[position[0]][position[1]] = 'O';
    animate(box, position, n, dx, dy, width, height);
  }

  public static void printArray(char[][] box, int width, int height)
  {
    for (int y = 0; y < height; y++)
    {
      for (int x = 0; x < width; x++)
      {
        System.out.print(box[x][y]);
      }
      System.out.println();
    }
  }

  public static void clear()
  {
    System.out.print("\033[2J");
  }

  public static void resetCursor()
  {
    System.out.print("\033[0;0H");
  }

}
