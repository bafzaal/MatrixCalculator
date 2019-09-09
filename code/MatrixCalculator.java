import java.io.*;

public class MatrixCalculator{
 
 
  public int[][] denseMatrixMult(int[][] A, int[][] B, int size)
  {
    
    int[][] R = new int[size][size]; // Decleration of result matrix that will be returned following the multiplication.
    int[][] zero = new int[size/2][size/2]; // Decleration of zero matrix that will aid in inputting matrices into the sub and sum methods.

    if(size == 1) // Base case in recurssive method, when size is equal to one.
    {
      R[0][0] = A[0][0] * B[0][0]; // When size is one, the zeroth index of both matrices will be multiplied and held in the result matrix.
    }

    else // If the base case is not valid
    {
      int[][] M0 = denseMatrixMult(sum(A,A,0,0,size/2,size/2,size/2),sum(B,B,0,0,size/2,size/2,size/2),size/2); // M0 = (A0,0 + A1,1)(B0,0 + B1,1)
      int[][] M1 = denseMatrixMult(sum(A,A,size/2,0,size/2,size/2,size/2),sum(B,zero,0,0,0,0,size/2),size/2); // M1 = (A1,0 + A1,1)B0,0
      int[][] M2 = denseMatrixMult(sum(A,zero,0,0,0,0,size/2),sub(B,B,0,size/2,size/2,size/2,size/2),size/2); // M2 = A0,0(B0,1 − B1,1)
      int[][] M3 = denseMatrixMult(sum(A,zero,size/2,size/2,0,0,size/2),sub(B,B,size/2,0,0,0,size/2),size/2); // M3 = A1,1(B1,0 − B0,0)
      int[][] M4 = denseMatrixMult(sum(A,A,0,0,0,size/2,size/2),sum(B,zero,size/2,size/2,0,0,size/2),size/2); // M4 = (A0,0 + A0,1)B1,1
      int[][] M5 = denseMatrixMult(sub(A,A,size/2,0,0,0,size/2),sum(B,B,0,0,0,size/2,size/2),size/2); // M5 = (A1,0 − A0,0)(B0,0 + B0,1)
      int[][] M6 = denseMatrixMult(sub(A,A,0,size/2,size/2,size/2,size/2),sum(B,B,size/2,0,size/2,size/2,size/2),size/2); // M6 = (A0,1 − A1,1)(B1,0 + B1,1)

      
      int[][] C00 = sum(sub(sum(M0,M3,0,0,0,0,size/2),M4,0,0,0,0,size/2),M6,0,0,0,0,size/2); // C0,0 = M0 + M3 − M4 + M6
      int[][] C01 = sum(M2,M4,0,0,0,0,size/2); // C0,1 = M2 + M4
      int[][] C10 = sum(M1,M3,0,0,0,0,size/2); // C1,0 = M1 + M3
      int[][] C11 = sum(sum(sub(M0,M1,0,0,0,0,size/2),M2,0,0,0,0,size/2),M5,0,0,0,0,size/2); // C1,1 = M0 − M1 + M2 + M5

      // This loop transfers all the values from the C00 matrix to the upper left of the result matrix.
      for(int i = 0; i < size/2; i++)
      {
        for(int j = 0; j < size/2; j++)
        {
          R[i][j] = C00[i][j];
        }
      }

      // This loop transfers all the values from the C01 matrix to the upper right of the result matrix.
      for(int i = 0; i < size/2; i++)
      {
        for(int j = size/2; j < size; j++)
        {
          R[i][j] = C01[i][j - size/2];
        }
      }

      // This loop transfers all the values from the C10 matrix to the lower left of the result matrix.
      for(int i = size/2; i < size; i++)
      {
        for(int j = 0; j < size/2; j++)
        {
          R[i][j] = C10[i - size/2][j];
        }
      }

      // This loop transfers all the values from the C11 matrix to the lower right of the result matrix.
      for(int i = size/2; i < size; i++)
      {
        for(int j = size/2; j < size; j++)
        {
          R[i][j] = C11[i - size/2][j - size/2];
        }
      }

    }

    return R; // Returns the result matrrix

  }

  public int[][] sum(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    int[][] arr = new int[n][n]; // Declares a new 2D array of size n by n.

    for (int i = 0; i < n; i++) // Traverses through each row.
    {
      for (int j = 0; j < n; j++) // Traverses through each column.
      {
        arr[i][j] = A[x1 + i][y1 + j] + B[x2 + i][y2 + j]; // Computes value and the sum is held in the previously declared array.
      }
    }

    return arr; // Returns sum array.
  }
  
  public int[][] sub(int[][] A, int[][] B, int x1, int y1, int x2, int y2, int n)
  {
    int[][] arr = new int[n][n]; // Declares a new 2D array of size n by n.

    for (int i = 0; i < n; i++) // Traverses through each row.
    {
      for (int j = 0; j < n; j++) // Traverses through each column.
      {
        arr[i][j] = A[x1 + i][y1 + j] - B[x2 + i][y2 + j]; // Computes value and the difference is held in the previously declared array.
      }
    }

    return arr; // Returns the difference array.
  }
  
  
  public int[][] initMatrix(int n)
  {
    int[][] arr = new int[n][n]; // Declares a new 2D array of size n by n.

    for(int i = 0; i < n; i++) // Traverses through each row.
    {
      for(int j = 0; j < n; j++) // Traverses through each column.
      {
        arr[i][j] = 0; // Sets value to 0.
      }
    }

    return arr; // Returns initialized array.
  }
  
  public void printMatrix(int n, int[][] A)
  {
    for(int i = 0; i < n; i++) // Traverses through each row.
    {
      for(int j = 0; j < n; j++) // Traverses through each column.
      {
        System.out.print(A[i][j] + " "); // Prints value and also a space.
      }
      System.out.println(); // Skips to the next line after each row has been printed.
    }
  }
  
  public int[][] readMatrix(String filename, int n) throws Exception
  {
    int[][] arr = new int[n][n]; // Declares a new 2D array of size n by n.
    int i = 0; // Local integer i is declared and initalized to zero. 

    try
    {
      String workingDir = System.getProperty("user.dir"); // String called workingDir declared and initalized.
      File file = new File(workingDir+"/"+filename); // File object is created.
      String row; // Declared new String called row.
      BufferedReader buf = new BufferedReader(new FileReader(file)); // BufferedReader object is created.
      while((row = buf.readLine()) != null) // Sets row to the BufferedReader line and continues the loop while the line is not empty.
      {
        String [] values = row.split(" "); // Any array of Strings hold every value in row. Each value that is seperated by a space has its own index.
        for(int j = 0; j < n; j++)
        {
          arr[i][j] = Integer.parseInt(values[j]); // Each value in the String array is set to an integer and held in the previously declared array.
        }
        i++; // increments i by 1.
      }
      buf.close();
    } catch(Exception e) {                            // If Exception is caught
      System.out.println("Exception occured: " + e);  // then print exception message.
    }

    return arr;
  }



}