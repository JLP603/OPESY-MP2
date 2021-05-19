public class Car {
    int i,cap;
    public Car(){

    }
    public void board(){

    }
    public void unboard(){

    }
    public void load(){

    }
    public void unload(){

    }
    public void run(){

    }
/*
loadingArea[i].wait()
load()
boardQueue.signal(C)
allAboard.wait()
loadingArea[next(i)].signal()

run()

unloadingArea[i].wait()
unload()
unboardQueue.signal(C)
allAshore.wait()
unloadingArea[next(i)].signal()
*/
}
