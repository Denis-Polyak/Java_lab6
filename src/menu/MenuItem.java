package menu;
public class MenuItem {
    private String name;
    MenuCall menuCall;
    public MenuItem(String name, MenuCall menuCall) {
        this.name = name;
        this.menuCall = menuCall;
    }
    public String getName() {
        return this.name;
    }
    public void runMethod(){
        this.menuCall.execute();
    }
}
