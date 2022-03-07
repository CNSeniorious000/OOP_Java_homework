//笔记本

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.*;

//import static java.awt.SystemColor.window;

public class NotePad extends Application {
    File path = null;
    int textHash = 0;
    Stage primaryStage = new Stage();
    MenuBar bar = new MenuBar();
    Menu file = new Menu("文件");
    Menu edit = new Menu("编辑");
    Menu format = new Menu("格式");

    MenuItem newFile = new MenuItem("新建");
    MenuItem newWindow = new MenuItem("新窗口");
    MenuItem open = new MenuItem("打开");
    MenuItem save = new MenuItem("保存");
    MenuItem saveAs = new MenuItem("另存为");

    MenuItem find = new MenuItem("查找");

    CheckMenuItem autoLine = new CheckMenuItem("自动换行");
    MenuItem font = new MenuItem("字体");
    MenuItem fontColor = new MenuItem("字体颜色");

    BorderPane root = new BorderPane();
    TextArea area = new TextArea("Code Here!"); // 创建一个多行输入框

    Scene primaryScene = new Scene(root);
    // font 框架
    BorderPane fontRoot = new BorderPane();
    Scene scene = new Scene(fontRoot);
    Stage fontStage = new Stage();

    String[] nameStr = Font.getFontNames().toArray(new String[0]);
    String[] postureStr = {"普通", "加粗", "斜体", "粗斜体"};
    double[] sizeDouble = new double[36];

    String[] nameList = nameStr;//引用
    FontPosture[] postureList = {FontPosture.REGULAR, FontPosture.REGULAR, FontPosture.ITALIC, FontPosture.ITALIC};
    FontWeight[] weightList = {FontWeight.NORMAL, FontWeight.BOLD, FontWeight.NORMAL, FontWeight.BOLD};
    //			sizeDouble;

    ObservableList olPosture = FXCollections.observableArrayList();
    ObservableList olSize = FXCollections.observableArrayList();

    ListView nameView = new ListView<String>(FXCollections.observableList(Font.getFontNames()));
    ListView postureView = new ListView();
    ListView sizeView = new ListView();
    //构造底部hBox
    HBox bottom = new HBox();
    TextField sample = new TextField("Sample");
    Button ok = new Button("确定");
    Button cancel = new Button("取消");

    public static void main(String[] args) {
        Application.launch();
    }

    //初始化窗口
    public void start() throws NullPointerException {

        bar.getMenus().addAll(file, edit, format);

        /*
             format String
        */

        file.getItems().addAll(newFile, newWindow, open, save, saveAs);


        edit.getItems().add(find);


        format.getItems().addAll(autoLine, font, fontColor);

		/*
		快捷键设置
		 */
        newFile.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        newWindow.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+N"));
        open.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        save.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveAs.setAccelerator(KeyCombination.keyCombination("Ctrl+Shift+S"));
        ///

        root.setTop(bar);
        root.setCenter(area);


        this.primaryStage.setScene(primaryScene);
        this.primaryStage.setTitle("记事本");
        this.primaryStage.show();

        fontStage.setScene(scene);

        for (int i = 0; i <= 35; i++) {
            sizeDouble[i] = (double) 2 + 2 * i;
            olSize.add(2 + 2 * i);
        }

        olPosture.addAll(postureStr);//

//		olSize.addAll(sizeDouble);

        //TOP：选定值
        //CENTER:3个下拉列表
        //BOTTOM:样例+ok键+cancel键
        //字体名称，字形，字号listview

        postureView.setItems(olPosture);

        sizeView.setItems(olSize);

        //放置下拉列表
        fontRoot.setLeft(nameView);
        fontRoot.setCenter(postureView);
        fontRoot.setRight(sizeView);
        //构造底部hBox
        fontRoot.setBottom(bottom);
        bottom.setAlignment(Pos.CENTER);

        bottom.getChildren().addAll(sample, ok, cancel);
        //底部button 设置处理器
        ok.setOnAction(actionEvent -> {
            area.setFont(createFont());
            fontStage.close();
        });
        cancel.setOnAction(actionEvent -> fontStage.close());
        //选择列表处理器
        nameView.setOnMouseClicked(e -> setFont());
        postureView.setOnMouseClicked(e -> setFont());
        sizeView.setOnMouseClicked(e -> setFont());
        //功能实现
//新建：保存并刷新窗口
		/*
				空白：无反应
				修改过？
					是：保存
					刷新
		 */
        newFile.setOnAction(event -> {
            int nullPoint = 0;
            {

//				if(area.getText().contentEquals("")||area.getText().contentEquals("Code Here!")){
//
//				}else
                if (this.textHash != this.area.getText().hashCode()) {
                    nullPoint = saveAs();
                }
                if (nullPoint == 0)
                    flush();
            }
        });
//新建窗口
        newWindow.setOnAction(event -> {
            NotePad note = new NotePad();
            note.primaryStage.setTitle("记事本");
            note.start();
        });
//自动换行;
        autoLine.setOnAction(e -> area.setWrapText(autoLine.isSelected()));// 设置多行输入框是否支持自动换行。true表示支持，false表示不支持
//字体设置。。。
        font.setOnAction(event -> {

            //展示
            fontStage.show();

        });

//Find 定位
        find.setOnAction(event -> {
            HBox hBox = new HBox();
            Scene findScene = new Scene(hBox);
            Stage findStage = new Stage();
            findStage.setScene(findScene);

            Label label = new Label("查找内容(N):");
            TextField textField = new TextField("请输入查找对象");
            Button findButton = new Button("查找下一个(F)");

            hBox.getChildren().addAll(label, textField, findButton);
            findStage.show();

            findButton.setOnMouseClicked(mouseEvent -> {
                String text = area.getText();
                String key = textField.getText();
                int length = key.length();
                int index = text.indexOf(key, area.getCaretPosition());
                if (index != -1) {
                    area.selectRange(index, index + length);
                } else if (text.contains(key)) {
                    index = text.indexOf(key);
                    area.selectRange(index, index + length);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("大概是个警告");
                    alert.setContentText("好像找不到相关字段噢~");
                    alert.show();
                }
            });

        });

//Open 打开文件
        open.setOnAction(event -> {
            //新窗口
            NotePad note = new NotePad();
            //选择文件路径
            FileChooser fileChooser = new FileChooser();
            File in = fileChooser.showOpenDialog(note.primaryStage);
            //读写文件
            StringBuilder newText = new StringBuilder();
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(in)))) {
                while (true) {
                    newText.append(objectInputStream.readChar());
                }
            } catch (EOFException e) {
                note.start();
                note.area.setText(newText.toString());
                note.primaryStage.setTitle("记事本: " + in.getName());

            } catch (IOException | NullPointerException E) {
                System.out.print(E);
            }
        });
//保存
/*
 *	判断路径
 *		无：选择路径-保存-设置title
 *		有：保存
 */
        save.setOnAction(event -> {
            try {
                save(this.path);
//				System.out.print("003");
            } catch (NullPointerException nullPointerException) {
//				System.out.print("004");
                saveAs();
//				System.out.print("005");
            }
        });
//另存为
/*
	选择路径
	保存
	设置title
 */
        saveAs.setOnAction(event -> saveAs());
    }

    //保存副本
    public void setTextHash(@NotNull String text) {
        this.textHash = text.hashCode();
    }

    //保存
	/*
	输入文件
	提取对象文本
	写入
	hash
	 */
    private void save(File path) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)))) {
            String text = this.area.getText();
            objectOutputStream.writeChars(text);
            setTextHash(text);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ignored) {
        }
    }

    //另存为=选择路径+保存+设置title
    public int saveAs() {
        try {
            FileChooser fileChooser = new FileChooser();
            File savePath = fileChooser.showSaveDialog(null);
            save(savePath);
            this.primaryStage.setTitle(savePath.getName());
            this.path = savePath;
        } catch (NullPointerException n) {
            return 1;//未保存返回1
        }
        return 0;
    }

    //刷新
    public void flush() {
        this.area.setText("");
        this.primaryStage.setTitle("记事本");
        this.textHash = 0;
        this.path = null;
    }

    //设置字体
    public void setFont() {
        sample.setFont(createFont());
    }

    //构造选定的字体font
    public Font createFont() {
        int nameIndex = nameView.getSelectionModel().getSelectedIndex();
        int postureIndex = postureView.getSelectionModel().getSelectedIndex();
        int sizeIndex = sizeView.getSelectionModel().getSelectedIndex();
        System.out.print(nameIndex + "" + postureIndex + "" + sizeIndex + "\n");
        if (nameIndex == -1) {
            nameIndex = 0;
        }
        if (postureIndex == -1) {
            postureIndex = 0;
        }
        if (sizeIndex == -1) {
            sizeIndex = 0;
        }
        System.out.print(nameIndex + "" + postureIndex + "" + sizeIndex + "\n");
        return Font.font(nameList[nameIndex], weightList[postureIndex], postureList[postureIndex], sizeDouble[sizeIndex]);

    }

    @Override
    public void start(Stage primaryStage) {
        NotePad note = new NotePad();
        note.start();
    }
}
//字体格式未保存,路径未保存：应用objectOutPutStream 储存
//ok，cancel键大小不确定：maxweight解决不了
//部分字体不支持粗体斜体
