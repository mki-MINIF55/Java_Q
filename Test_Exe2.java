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

/*mainҿ���
ýĂ̓����Ɖ���̓��e��Test.csv����ǂݍ��ށB
���̌�A���̒����烉���_����1��o�肷��B
�𓚎ҁi���[�U�j�͕W�����͂���񓚂�ł����ށB
�����Ȃ�u�����I�v�ƕ\���A�s�����Ȃ�u�s�����v�ƕ\���B
�S����5��o�肵�A���ⓚ����ꂽ�����Ō�ɕ\������B
test_Exe2������яo��ŏd������
Randam�׽�ɂ͗����������ɕ΂肪���邽�߁A�΂�Ȃ������𐶐�����
java.security.SecureRandom�׽���g�p���邱�Ƃŕ΂�����炷
*/
public class Test_Exe2 {

  public static void main(String[] args) {
    // ���ފJ�n
    System.out.println();
    System.out.println(Test_Constant.START);
    System.out.println(Test_Constant.SATRT_DESCRIPTION);
    System.out.println();
    System.out.println(Test_Constant.ENTER_KEY);

    // ҿ���B ���ނ�����тɏo��Ɠ������킹
    arrayList();
  }

  // ----------------------------------------------------------------
  /**
   * A ���������͂��郁�\�b�h
   *
   * @param �Ȃ�
   * @return inputWord ���͒l
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
   * B enterҿ��
   *
   * @param enter
   */
  public static void enter() {
    // Enter�����Ǝ���\��
    String enter = new java.util.Scanner(System.in).nextLine();
  }

  // ----------------------------------------------------------------
  /**
   * C �z���ǂݍ���ArrauList�Ɋi�[����ҿ���
   *
   * @param �Ȃ�
   * @return arrayList
   */
  public static String arrayList() {
    FileInputStream file = null;
    InputStreamReader isr = null;
    BufferedReader reader = null;
    String arrayList = "";
    try {
      file = new FileInputStream("answer_javaBronz_P6.csv");
      isr = new InputStreamReader(file);
      reader = new BufferedReader(isr);

      // csv����ǂݍ��ޗpArrayList
      List<String[]> arrTest = new ArrayList<String[]>();

      // �ǂݍ��ݍs�p
      String line;
      // �s���Ǘ�
      int row = 0;
      // ���ޏo��Ǘ�
      int q_count = 0;
      // ���𐔊Ǘ�
      int count = 0;
      // �������ʏo��
      int srechCount = 0;
      // ��萔�o�萔
      //final int Q_ROOP_MAX = 3;

      Random random = new SecureRandom();
      enter();

      // ��s���ǂݍ��݂��s�� �󔒍s�ɂȂ�܂œǂݍ���
      while ((line = reader.readLine()) != null) {
        // arrTest�ɃJ���}��؂�̒l��z��Ɋi�[����
        arrTest.add(line.split(","));
      }

      /*
       * ���ފJ�n
       * ���ނ�arrTest.size()ٰ��
       */
      for (int i = 0; i < arrTest.size(); i++) {
        // arrTest ����ѐ���
        for (String[] arrs : arrTest) {
          int q_Arry = (int) Integer.parseInt(arrs[0]);
          int q_Arrys = random.nextInt(arrTest.size()) + 1;

          String q_Arrys_Str = String.valueOf(q_Arrys);
          // �s�ݸ����
          row++;

          // ����тɂ������ނ�1�o��
          if (q_Arrys_Str.equals(arrs[0])) {
            System.out.println(
              q_count + 1 + Test_Constant.ANS_COUNT_MSG + arrs[1]
            );
            q_count++;

            // �W�����͂ŉ񓚂��󂯕t��ҿ���
            String input = inputString();

            // ���͂����l���猟�����ʂ��o�͂��郁�\�b�h
            // for (String[] arr : arrTest) {
            if (input.equals(arrs[2])) {
              System.out.println();

              System.out.println(input + Test_Constant.STRIKE);
              System.out.println(Test_Constant.ANS_MSG + arrs[2]);
              System.out.println(arrs[3]);
              System.out.println(Test_Constant.LINESTRING);

              // �������Ĳݸ����
              srechCount++;
            } else if (srechCount == 0) { // ��������Ƃ��Ɉ�v���Ȃ������Ƃ�
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
        " �� " +
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
