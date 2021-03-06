# ZxingBarcodeWriter

下記の形式のCSVからバーコードを生成するソフトです。

```
"出力ファイル名","テキスト",BarcodeFormat
```

# Exsample CSV

サンプルのCSV内容は下記のとおりです。

```
./output/NW7_12345.png,12345,NW7
./output/CODE_39_abcd.png,abcd,CODE_39
./output/CODE_93_abcd.png,abcd,CODE_93
./output/CODE_128_abcd.png,abcd,CODE_128
./output/DATA_MATRIX_abcd.png,abcd,DATA_MATRIX
./output/EAN_8_1234567.png,1234567,EAN_8
./output/EAN_13_123456789012.png,123456789012,EAN_13
./output/ITF_1234567890.png,1234567890,ITF
./output/PDF_417_1234567890.png,1234567891,PDF_417
./output/UPC_A_12345678901.png,12345678901,UPC_A
./output/UPC_E_1234567.png,1234567,UPC_E
```

# Exsample Execute

実行方法の例は下記のとおりです。


```
java -jar ZxingBarcodeWriter0.1.0.jar inputCSV.csv
```

# 環境

ZxingBarcodeWriterのビルド環境

```
jdk-15.0.1
pleiades-2020-12-java-win-64bit-jre_20201222
```

zxingのビルド環境

```
apache-maven-3.6.3
core-3.4.2-SNAPSHOT.jar（生成されたファイル）
javase-3.4.2-SNAPSHOT.jar（生成されたファイル）
```