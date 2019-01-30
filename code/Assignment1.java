import java.io.*;

public class Assignment1{
 
 
  public int[][] denseMatrixMult(int[][] A, int[][] B, int size)
  {
    return null;
  }
  
  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    return null;
  }
  
  public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    return null;
  }
  
  
  public int[][] initMatrix(int n)
  {

    int[][] arr = new int[n][n];

    for(int i = 0; i < n; i++)
    {
      for(int j = 0; j < n; j++)
      {
        arr[i][j] = 0;
      }
    }

    return arr;

  }
  
  public void printMatrix(int n, int[][] A)
  {
    for(int i = 0; i < n; i++)
    {
      for(int j = 0; j < n; j++)
      {
        System.out.print(A[i][j] + " ");
      }
      System.out.println();
    }
  }
  
  public int[][] readMatrix(String filename, int n) throws Exception
  {

    int[][] arr = new int[n][n];
    int i = 0;

    try
    {
      String row;
      BufferedReader buf = new BufferedReader(new FileReader(filename));
      while((row = buf.readLine()) != null)
      {
        String [] values = row.split(" ");
        for(int j = 0; j < n; j++)
        {
          arr[i][j] = Integer.parseInt(values[j]);
        }
        i++;
      }
      buf.close();
    } catch(Exception e) {
      System.out.println("Exception occured: " + e);
    }
    return arr;
  }



}