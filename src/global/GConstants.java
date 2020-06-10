package global;

import menu.GColorMenu;
import menu.GEditMenu;
import menu.GFileMenu;
import menu.GMenu;
import shape.*;

import java.awt.*;

public class GConstants {


    public static final Color COLOR_LINE_DEFAULT = Color.BLACK ;
    public final static Color COLOR_FILL_DEFAULT = Color.WHITE ;
    public static final Color FOREGROUND_COLOR = Color.BLACK ;
    public static final Color BACKGROUND_COLOR = Color.WHITE ;
    public static final String FILLCOLOR_TITLE = "Selected Fill Color";
    public static final String LINECOLOR_TITLE = "Selected Line Color";
    public static final String SAVE_TITLE = "종료 전 저장하시겠습니까?";
    public static final String EXIT_TITLE = "정말 프로그램을 종료하시겠습니까?";

    public static long serialVersionUID = 1L;


    public GConstants() {
    }

    public enum EMainFrame{
        eWidth(600),
        eHeight(800);

        private int value;
        private EMainFrame(int value){
            this.value = value;
        }
        public int getValue() {
            return this.value;
        }
    }

    public enum EMenubar{
        eFile( new GFileMenu("파일")),
        eEdit( new GEditMenu("편집")),
        eColor(new GColorMenu("컬러"));

        private GMenu menu;
        private EMenubar(GMenu menu){
            this.menu = menu;
        }
        public GMenu getMenu(){
            return this.menu;
        }
    }

    public enum EToolbar {
        eRectangle("rectangle", new GRectangle()),
        eOval("oval", new GOval()),
        eLine("line", new GLine()),
        ePolygon("polygon", new GPolygon()),
        eSelect("select",new GGroup())
        ;

        private String title;
        private GShape actionCommand;

        private EToolbar(String title, GShape actionCommand) {
            this.title = title;
            this.actionCommand = actionCommand;
        }
        public String getTitle(){
            return this.title;
        }
        public GShape getActionCommand() {
            return this.actionCommand;
        }
    }


    public enum EFileMenu {
        enew("New", "nnew"),
        eopen("열기", "open"),
        esave("저장", "save"),
        esaveAs("다른이름으로", "saveAs"),
        eprint("프린트", "print"),
        eexit("닫기", "exit"),
        ;

        private String title;
        private String actionCommand;

        private	EFileMenu(String title, String actionCommand){
            this.title = title;
            this.actionCommand = actionCommand;
        }
        public String getTitle() {
            return this.title;
        }
        public String getActionCommand() {
            return this.actionCommand;
        }
    }

    public enum EEditMenu {
        eundo("되돌리기", "undo"),
        eredo("다시 실행", "redo"),
        ecut("잘라내기", "cut"),
        ecopy("복사하기", "copy"),
        epaste("붙여넣기", "paste"),
        egroup("그룹화", "group"),
        eungroup("그룹해제", "ungroup")
        ;

        private String title;
        private String tool;

        private	EEditMenu(String title, String tool){
            this.title = title;
            this.tool = tool;
        }
        public String getTitle() {
            return this.title;
        }
        public String getTool() {
            return this.tool;
        }
    }

    public enum EColorMenu {
        elinecolor("선 색", "linecolor"),
        efillcolor("채우기 색", "fillcolor"),
        ;

        private String title;
        private String actionCommand;

        private	EColorMenu(String title, String actionCommand){
            this.title = title;
            this.actionCommand = actionCommand;
        }
        public String getTitle() {
            return this.title;
        }
        public String getActionCommand() {
            return this.actionCommand;
        }
    }

    public final static int MAXPOINTS = 100;

    public enum ECursor {
        eDefault(new Cursor(Cursor.DEFAULT_CURSOR)),
        eMove(new Cursor(Cursor.MOVE_CURSOR)),
        eRotate(new Cursor(Cursor.HAND_CURSOR)), //추가
        eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
        eWW(new Cursor(Cursor.W_RESIZE_CURSOR)),
        eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
        eSS(new Cursor(Cursor.S_RESIZE_CURSOR)),
        eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
        eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)),
        eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
        eSW(new Cursor(Cursor.SW_RESIZE_CURSOR)),

        ;

        private Cursor cursor;

        ECursor(Cursor cursor) {
            this.cursor = cursor;
        }

        public Cursor getCursor() {
            return this.cursor;
        }
    }
    public static final int ANCHOR_W = 6;
    public static final int ANCHOR_H = 6;
    public static final int RR_OFFSET = 40;
    public static final Color ANCHOR_LINECOLOR = Color.BLACK ;
    public static final Color ANCHOR_FILLCOLOR = Color.WHITE ;


}