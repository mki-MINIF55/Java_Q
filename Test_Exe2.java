import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*mainﾒｿｯﾄﾞ
ﾃｽﾄの答えと解説の内容をTest.csvから読み込む。
その後、その中からランダムに1問出題する。
解答者（ユーザ）は標準入力から回答を打ち込む。
正解なら「正解！」と表示、不正解なら「不正解」と表示。
全部で5問出題し、何問答えられたかを最後に表示する。
test_Exe2はﾗﾝﾀﾞﾑ出題で重複無し
Randamｸﾗｽには乱数生成時に偏りがあるため、偏りなく乱数を生成する
java.security.SecureRandomｸﾗｽを使用することで偏りを減らす
*/
public class Test_Exe2 {

  public static void main(String[] args) {
    // ｸｲｽﾞ開始
    System.out.println();
    System.out.println(Test_Constant.START);
    System.out.println(Test_Constant.SATRT_DESCRIPTION);
    System.out.println();
    System.out.println(Test_Constant.ENTER_KEY);

    // ﾒｿｯﾄﾞB ｸｲｽﾞをﾗﾝﾀﾞﾑに出題と答え合わせ
    arrayList();
  }

  // ----------------------------------------------------------------
  /**
   * A 文字列を入力するメソッド
   *
   * @param なし
   * @return inputWord 入力値
   */
  public static String inputString() {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(isr);
    String inputWord = "";
    try {
      inputWord = reader.readLine();
    } catch (IOException e) {
      System.out.println(e);
    }
    return inputWord;
  }

  // ----------------------------------------------------------------
  /**
   * B enterﾒｿｯﾄ
   *
   * @param enter
   */
  public static void enter() {
    // Enter押すと次を表示
    String enter = new java.util.Scanner(System.in).nextLine();
  }

  // ----------------------------------------------------------------
  /**
   * C 配列を読み込みArrauListに格納するﾒｿｯﾄﾞ
   *
   * @param なし
   * @return arrayList
   */
  public static String arrayList() {
    FileInputStream file = null;
    InputStreamReader isr = null;
    BufferedReader reader = null;
    String arrayList = "";
    try {
      file = new FileInputStream("answer.csv");
      isr = new InputStreamReader(file);
      reader = new BufferedReader(isr);

      // csvから読み込む用ArrayList
      List<String[]> arrTest = new ArrayList<String[]>();

      // 読み込み行用
      String line;
      // 行数管理
      int row = 0;
      // ｸｲｽﾞ出題管理
      int q_count = 0;
      // 正解数管理
      int count = 0;
      // 検索結果出力
      int srechCount = 0;
      
      Random random = new SecureRandom();
      enter();

      // 一行ずつ読み込みを行う 空白行になるまで読み込み
      while ((line = reader.readLine()) != null) {
        // arrTestにカンマ区切りの値を配列に格納する
        arrTest.add(line.split(","));
      }

      /*
       * ｸｲｽﾞ開始
       * ｸｲｽﾞをarrTest.size()ﾙｰﾌﾟ
       */
      for (int i = 0; i < arrTest.size(); i++) {
        // arrTest ﾗﾝﾀﾞﾑ生成
        for (String[] arrs : arrTest) {
          int q_Arry = (int) Integer.parseInt(arrs[0]);
          int q_Arrys = random.nextInt(arrTest.size()) + 1;

          String q_Arrys_Str = String.valueOf(q_Arrys);
          // 行ｲﾝｸﾘﾒﾝﾄ
          row++;

          // ﾗﾝﾀﾞﾑにしたｸｲｽﾞを1つ出題
          if (q_Arrys_Str.equals(arrs[0])) {
            System.out.println(
              q_count + 1 + Test_Constant.ANS_COUNT_MSG + arrs[1]
            );
            q_count++;

            // 標準入力で回答を受け付けﾒｿｯﾄﾞ
            String input = inputString();

            // 入力した値から検索結果を出力するメソッド
            // for (String[] arr : arrTest) {
            if (input.equals(arrs[2])) {
              System.out.println();

              System.out.println(input + Test_Constant.STRIKE);
              System.out.println(Test_Constant.ANS_MSG + arrs[2]);
              System.out.println(arrs[3]);
              System.out.println(Test_Constant.LINESTRING);

              // 検索ｶｳﾝﾄｲﾝｸﾘﾒﾝﾄ
              srechCount++;
            }  
            if (srechCount == 0) { // 検索するときに一致しなかったとき
              System.out.println();

              System.out.println(input + Test_Constant.NOT_STRIKE);
              System.out.println(Test_Constant.ANS_MSG + arrs[2]);
              System.out.println(arrs[3]);
              System.out.println(Test_Constant.LINESTRING);
            }
          }
          if (arrTest.size() == q_count) {
            break;
          }
        }
      }
      count += srechCount;
      System.out.println(
        q_count +
        Test_Constant.ANS_COUNT_TOTAL_QMSG +
        count +
        " ☆ " +
        Test_Constant.ANS_COUNT_TOTAL_MSG
      );

      int totalPlitical = (count * 100) / q_count;
      System.out.println(
        Test_Constant.TOTAL_ANS_PROBABILITY_MSG +
        totalPlitical +
        Test_Constant.TOTAL_ANS_MSG
      );
      reader.close();
    } catch (IOException e) {
      System.out.println(e);
    }
    return arrayList;
  }
}
