package com.tukutano;

import com.google.zxing.BarcodeFormat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * メインクラス.
 *
 * @author tktomaru
 *
 */
public class ZxingBarcodeWriter {

  /**
   * メインメソッド.
   *
   * @param args ファイル名
   */
  public static final void main(String[] args) {
    List<FileFormatCsv> inputData = new ArrayList<>();

    String validFormat = "AZTEC" + " / " + "CODABAR" + " / " + "NW7" + " / " + "NW-7" + " / "
        + "CODE_39" + " / " + "CODE39" + " / " + "CODE_93" + " / " + "CODE93" + " / " + "CODE_128"
        + " / " + "CODE128" + " / " + "DATA_MATRIX" + " / " + "EAN_8" + " / " + "EAN8" + " / "
        + "EAN_13" + " / " + "EAN13" + " / " + "ITF" + " / " + "PDF_417" + " / " + "UPC_A" + " / "
        + "UPC_E";

    System.out.println("java -jar ZxingBarcodeWriter.jar input.csv");
    System.out.println("input.csv='outFileName','content','format'");
    System.out.println("format=" + validFormat);
    if (args.length == 0) {
      System.out.println("コマンドライン引数がありません。");
      System.exit(1);
    }

    System.out.println("File name:" + args[0]);
    String inputFileName = args[0];

    System.out.println("input file = " + inputFileName);

    // ファイルからlistLineに文字列を読み込み
    try {
      File f = new File(inputFileName);
      BufferedReader br = new BufferedReader(new FileReader(f));

      String line = br.readLine();
      String[] csvData; // 分割後のデータを保持する配列
      while (line != null) {
        System.out.println(line);
        // lineをカンマで分割し、配列dataに設定
        csvData = line.split(",");
        if (csvData == null) {
          System.out.println("期待：out.png etc,1234566 etc,NW7 etc");
          System.out.println("CSVが不正です：" + line);
          System.exit(1);
        }
        if (3 > csvData.length) {
          System.out.println("期待：out.png etc,1234566 etc,NW7 etc");
          System.out.println("CSVが不正です（3つ未満の行を発見）:" + line);
          System.exit(1);
        }
        FileFormatCsv one = new FileFormatCsv();
        one.setFileName(csvData[0]);
        makedir(csvData[0]);
        one.setContentString(csvData[1]);
        one.setFormatString(csvData[2]);
        inputData.add(one);

        line = br.readLine();
      }
      br.close();
    } catch (IOException e) {
      System.out.println(e);
      System.exit(1);
    }

    for (FileFormatCsv one : inputData) {
      GenerateBarcode barcodeGenerater = new GenerateBarcode();
      BarcodeFormat format = barcodeGenerater.getBarcodeFormat(one.getFormatString());
      barcodeGenerater.genBarcode(one.getContentString(), one.getFileName(), format);
    }
  }

  private static void makedir(String outputfilename) {
    Path path = Paths.get(outputfilename);
    Path absolutePath = path.toAbsolutePath(); // 絶対パスに変換
    Path dirName = absolutePath.getParent();
    if (dirName != null) {
      File fileDir = new File(dirName.toString());
      fileDir.mkdirs();
    }
  }
}
