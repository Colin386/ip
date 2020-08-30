package main.java;


public class MyList {

    private Task[] things;
    private int size;


    private Task produceTask(String itemInfo){

        String[] wordsEntered = itemInfo.split(" ");
        String itemType = wordsEntered[0];
        int commandLength;
        int slashIndex;

        switch (itemType) {

        case ("todo"):
            commandLength = 4;
            return new ToDo(itemInfo.substring(commandLength+1));

        case ("deadline"):
            commandLength = 8;
            slashIndex = itemInfo.indexOf("/");
            return new Deadline(itemInfo.substring(commandLength+1, slashIndex-1), itemInfo.substring(slashIndex+1));

        case ("event"):
            commandLength = 5;
            slashIndex = itemInfo.indexOf("/");
            return new Deadline(itemInfo.substring(commandLength+1, slashIndex-1), itemInfo.substring(slashIndex+1));

        default:
            commandLength = -1;
            return new Task(itemInfo.substring(commandLength+1));

        }


    }
    public MyList(){
        this.size = 0;
        this.things = new Task[100];
    }


    public void addItem(String itemInfo) {

        Task item = this.produceTask(itemInfo);
        this.things[this.size] = item;
        this.size++;
        System.out.printf("\nadded: %s\n", item.getName());
    }

    public int getSize(){
        return this.size;
    }

    public void printList(){

        for (int i = 0; i < this.getSize(); i++){
            Task item = this.things[i];
            System.out.printf("\n%d.", i+1);

            System.out.printf("%s", item.toString());


        }
        System.out.println("");
    }

    public void completeTask(int index){

        if (index-1 < 0 || index-1 > this.getSize()){
            System.out.println("Error! No such task exists!");
            return;
        }
        this.things[index-1].setStatus(true);
        System.out.printf("\nNice! I've marked this task as done:");
        System.out.printf("\n[✓] %s\n", this.things[index-1].getName());
    }
}
