package connect;

import javax.management.StandardEmitterMBean;

public class ConnectMain {

  private String addToBoard(String position) {
    if(position.length() == 1)
      return "";

    return position;
  }git 

  private boolean checkForWinnerVertical(int[][] arr, int playCard) {
    int sum = 0;
    int i = arr.length-1;
    int index = 0;

    while (i >= 0) {
      int[] temp = arr[i];

      if((temp[index] == playCard) && sum != (playCard * 4)) {
        sum += temp[index];
        --i;
      } else if (sum == (playCard * 4)){
        return true;
      } else {
        index++;
        sum = 0;
        i = i == arr.length-1 ? arr.length-1: --i; // text this code is true
        // --i; replace if above is false
      }
    }

    return false;
  }

}
