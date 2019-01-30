import java.io.*;

public class Assignment1{
 
 
  public int[][] denseMatrixMult(int[][] A, int[][] B, int size)
  {
    int[][] R = new int[size][size];
    int[][] zero = new int[size][size];
    int[][] M0 = new int[size/2][size/2];
    int[][] M1 = new int[size/2][size/2];
    int[][] M2 = new int[size/2][size/2];
    int[][] M3 = new int[size/2][size/2];
    int[][] M4 = new int[size/2][size/2];
    int[][] M5 = new int[size/2][size/2];
    int[][] M6 = new int[size/2][size/2];

    int[][] C00 = new int[size/2][size/2];
    int[][] C01 = new int[size/2][size/2];
    int[][] C10 = new int[size/2][size/2];
    int[][] C11 = new int[size/2][size/2];

    if(size == 1)
    {
      R[0][0] = A[0][0] * B[0][0];
    }

    else 
    {
      M0 = denseMatrixMult(sum(A,A,0,0,size/2,size/2,size/2),sum(B,B,0,0,size/2,size/2,size/2),size/2);
      M1 = denseMatrixMult(sum(A,A,size/2,0,size/2,size/2,size/2),sum(B,zero,0,0,0,0,size/2),size/2);
      M2 = denseMatrixMult(sum(A,zero,0,0,0,0,size/2),sub(B,B,0,size/2,size/2,size/2,size/2),size/2);
      M3 = denseMatrixMult(sum(A,zero,size/2,size/2,0,0,size/2),sub(B,B,size/2,0,0,0,size/2),size/2);
      M4 = denseMatrixMult(sum(A,A,0,0,0,size/2,size/2),sum(B,zero,size/2,size/2,0,0,size/2),size/2);
      M5 = denseMatrixMult(sub(A,A,size/2,0,0,0,size/2),sum(B,B,0,0,0,size/2,size/2),size/2);
      M6 = denseMatrixMult(sub(A,A,0,size/2,size/2,size/2,size/2),sum(B,B,size/2,0,size/2,size/2,size/2),size/2);
      
      C00 = sum(sub(sum(M0,M3,0,0,0,0,size/2),M4,0,0,0,0,size/2),M6,0,0,0,0,size/2);
      C01 = sum(M2,M4,0,0,0,0,size/2);
      C10 = sum(M1,M3,0,0,0,0,size/2);
      C11 = sum(sum(sub(M0,M1,0,0,0,0,size/2),M2,0,0,0,0,size/2),M5,0,0,0,0,size/2);

      for(int i = 0; i < size/2; i++)
      {
        for(int j = 0; j < size/2; j++)
        {
          R[i][j] = C00[i][j];
        }
      }

      for(int i = 0; i < size/2; i++)
      {
        for(int j = size/2; j < size; j++)
        {
          R[i][j] = C01[i][j - size/2];
        }
      }

      for(int i = size/2; i < size; i++)
      {
        for(int j = 0; j < size/2; j++)
        {
          R[i][j] = C10[i - size/2][j];
        }
      }

      for(int i = size/2; i < size; i++)
      {
        for(int j = size/2; j < size; j++)
        {
          R[i][j] = C11[i - size/2][j - size/2];
        }
      }

      

    }

    return R;

  }

  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    int[][] arr = new int[n][n];

    for (int i = 0; i < n; i++)
    {
      for (int j = 0; j < n; j++)
      {
        arr[i][j] = A[x1 + i][y1 + j] + B[x2 + i][y2 + j];
      }
    }

    return arr;
  }
  
  public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    int[][] arr = new int[n][n];

    for (int i = 0; i < n; i++)
    {
      for (int j = 0; j < n; j++)
      {
        arr[i][j] = A[x1 + i][y1 + j] - B[x2 + i][y2 + j];
      }
    }

    return arr;
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