import static java.lang.System.exit;

public class LargestSquareSubMatrix {

    public int findMaxSquareSize(String[] arr){
        if(arr.length == 0){
            System.out.println("String array is empty");
            exit(1);
        }
        if(arr[0].length() == 0){
            System.out.println("String array is empty");
            exit(1);
        }
        int rows = arr.length;
        int cols = arr[0].length();

        int[][] result = new int[rows][cols];

        //copy 1st row as is into our result matrix while changing the character values to ints for easier calculations later
        for(int j=0; j < cols; j++){
            result[0][j] = Character.getNumericValue(arr[0].charAt(j));
        }

        //copy 1st column as is into our result matrix while changing the character values to ints for easier calculations later
        for(int i=0; i < rows; i++){
            result[i][0] = Character.getNumericValue(arr[i].charAt(0));
        }

        int minValueInSubSquare = 0;
        int maxSquare = 0;
        for(int i=1; i < rows; i++){
            for(int j=1; j < cols; j++){

                //cannot be a square submatrix having all 1's that has a bottom right value of 0
                if(arr[i].charAt(j) == '0'){
                    result[i][j] = 0;
                }
                else if(arr[i].charAt(j) == '1'){
                    minValueInSubSquare = findMinValue(result[i-1][j-1], result[i-1][j], result[i][j-1]);

                    //set result[i][j] to the min value of the other cells in its 2x2 submatrix plus 1
                    result[i][j] = minValueInSubSquare + 1;

                    //while we are updating our result table, keep track of the largest number we've seen
                    //this number represents the bottom right cell of our largest square submatrix found so far
                    if(result[i][j] > maxSquare){
                        maxSquare = result[i][j];
                    }
                }
                else{
                    System.out.println("Error: unrecognized input in string");
                    exit(1);
                }
            }
        }
        int area = maxSquare * maxSquare;
        return area;
    }

    //find the minimum value of the adjacent 2x2 matrix cells to the bottom right one we are looking at
    public int findMinValue(int topLeft, int topRight, int bottomLeft){
        int smallest;
        if(topLeft <= topRight && topLeft <= bottomLeft){
            smallest = topLeft;
        }
        else if(topRight <= topLeft && topRight <= bottomLeft){
            smallest = topRight;
        }
        else{
            smallest = bottomLeft;
        }
        return smallest;
    }

    public static void main(String[] args) {
        String[] arr = {"1000", "0011", "0011"};

        LargestSquareSubMatrix solver = new LargestSquareSubMatrix();
        System.out.println(solver.findMaxSquareSize(arr));
    }
}
